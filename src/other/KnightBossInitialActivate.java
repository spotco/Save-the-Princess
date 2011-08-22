package other;

import org.newdawn.slick.geom.Rectangle;

import enemy.Enemy;
import enemy.KnightBoss;

import save_the_princess.STPGame;

public class KnightBossInitialActivate extends Other {
	
	public KnightBossInitialActivate(int x, int y) {
		this.x = x; this.y = y;
		this.hitbox = new Rectangle(x,y,25,25);
	}
	
	public void update(STPGame game) {
		if (game.player.hitbox.intersects(this.hitbox)) {
			game.display.animations.startAnimation("knightBossInitAnimation",null);
			
			for (Other o : game.objectlist) { //activate
				if (o.type() == 67) {
					if (!((Tracker)o).knightinitialpathset) {
						for(Enemy e:game.enemylist) {
							if (e.getType() == 67) {
								((Tracker)o).initknightpath((KnightBoss)e,"down");
							}
						}
						((Tracker)o).knightinitialpathset = true;
					}
				}
			}
			
			for (int i = 0; i < game.objectlist.size(); i++) { //erase
				if (game.objectlist.get(i).hashCode() == this.hashCode()) {
					game.objectlist.remove(i);
				}
			}
		}
	}
	
	
	//TEMP
	/*for (Other o : objectlist) { //special shit for tracker
		if (o.type() == 67) {
			g.setColor(Color.green);
			((Tracker)o).specialRender(g);
		}
	}*/
	//TEMP

}
