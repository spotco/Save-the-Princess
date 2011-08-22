package other;

import org.newdawn.slick.geom.Rectangle;

import save_the_princess.STPGame;

public class FinalCutscene extends Other {
	
	public FinalCutscene(int x,int y) {
		this.x = x; this.y = y;
		hitbox = new Rectangle(x,y,25,25);
	}
	
	public void update(STPGame game) {
		if (game.player.hitbox.intersects(this.hitbox)) {
			game.display.animations.startAnimation("finalTowerLedge",null);
		}
	}

}
