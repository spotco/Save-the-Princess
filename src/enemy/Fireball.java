package enemy;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import save_the_princess.STPGame;

public class Fireball extends Enemy {
	
	public int orientation; //0DOWN 1RIGHT 2LEFT 3UP
	public Animation img;
	
	public Fireball(int x,int y,int direction,Wizard wizard) {
		this.x = x; this.y = y; this.orientation = direction;
		imageinit(wizard);
		hitboxupdate();
	}
	
	public int getType() {
		return 27;
	}
	
	public void hitboxupdate() {
		hitbox = new Rectangle(x+2,y+2,9,9);
	}
	
	public void render() {
		img.draw(x,y);
	}
	
	private int extraspdcounter;
	public void update(STPGame game) {
		extraspdcounter++;
		if (orientation == 3) {
			y--;
			if (extraspdcounter > 2) {
				y--;
				extraspdcounter = 0;
			}
		} else if (orientation == 0) {
			y++;
			if (extraspdcounter > 2) {
				y++;
				extraspdcounter = 0;
			}
		} else if (orientation == 2) {
			x--;
			if (extraspdcounter > 2) {
				x--;
				extraspdcounter = 0;
			}
		} else if (orientation == 1) {
			x++;
			if (extraspdcounter > 2) {
				x++;
				extraspdcounter = 0;
			}
		}
		hitboxupdate();
		if (!viewboxstatichit(hitbox,game.staticslist)) {
			for(int i = 0;i < game.enemylist.size();i++) {
				if (game.enemylist.get(i).hashCode() == this.hashCode()) {
					game.enemylist.remove(i);
					break;
				}
			}
		}
	}
	
	public void imageinit(Wizard wizard) {
		if (orientation == 3) {
			img = wizard.fup;
		} else if (orientation == 0) {
			img = wizard.fdown;
		} else if (orientation == 2) {
			img = wizard.fleft;
		} else if (orientation == 1) {
			img = wizard.fright;
		}
		killerimg = wizard.fbkill;
	}

}
