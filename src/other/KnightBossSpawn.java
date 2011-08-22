package other;

import org.newdawn.slick.tiled.TiledMap;

import save_the_princess.STPGame;
import enemy.Enemy;
import enemy.KnightBoss;

//spawning of knightboss in other screens
public class KnightBossSpawn extends KnightBossInitialActivate {

	public KnightBossSpawn(int x, int y) {
		super(x, y);
	}
	
	public void update(STPGame game) {
		if (game.player.hitbox.intersects(this.hitbox)) {
			game.display.sound.standandfight.play();
			
			TiledMap temp = game.loadedlevel.storedmap[game.loadedlevel.locationx][game.loadedlevel.locationy];
			for(int y = 0;y < temp.getHeight();y++) {
				for(int x = 0;x < temp.getWidth();x++) {
					if (temp.getTileProperty(temp.getTileId(x,y,0),"knightbossdelayspawn","false").equals("true")) {
						game.enemylist.add(new KnightBoss(x*25,y*25,x,y,true));
					}
				}
			}
			
			for (Other o : game.objectlist) { //activate
				if (o.type() == 67) {
					((Tracker)o).knightinitialpathset = true;
				}
			}
			
			for (int i = 0; i < game.objectlist.size(); i++) { //erase
				if (game.objectlist.get(i).hashCode() == this.hashCode()) {
					game.objectlist.remove(i);
				}
			}
		}
	}

}
