package other;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import enemy.Enemy;

import save_the_princess.STPGame;

public class DoorButton extends Other {
	
	private Image button,buttonstep;
	
	public DoorButton(int x, int y) {
		this.x = x;
		this.y = y;
		this.hitbox = new Rectangle(x+6,y+6,12,10);
		try {
			button = new Image("art//tiles//presspush.png");
			buttonstep = new Image("art//tiles//presspushdown.png");
		} catch (SlickException e) {
			System.out.println("cannot file art//tiles//presspush.png or presspushdown.png");
		}
	}
	
	private boolean isStep;
	
	public void update(STPGame game) {
		if ( (game.player.hitbox.intersects(this.hitbox) || enemystep(game.enemylist)) && !isStep) {
			game.display.sound.gate.play();
			for(Other e : game.objectlist) {
				if (e.type() == 51) {
					((Door)e).openorclose(game);
				}
			}
			isStep = true;
		} else if (!game.player.hitbox.intersects(this.hitbox) && !enemystep(game.enemylist)) {
			isStep = false;
		}
	}
	
	public boolean enemystep(ArrayList<Enemy> list) {
		for(Enemy e:list) {
			if (e.hitbox.intersects(this.hitbox) && e.getType() != 27) {
				return true;
			}
		}
		return false;
	}
	
	public void render() {
		if (isStep) {
			buttonstep.draw(x,y);
		} else {
			button.draw(x,y);
		}
	}
}
