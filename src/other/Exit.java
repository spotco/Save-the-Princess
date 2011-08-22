package other;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import save_the_princess.STPGame;

public class Exit extends Other {
	
	public Image pointer;
	public String direction; //0UP, 1DOWN, 2LEFT, 3RIGHT
	
	public Exit(int x, int y, String direction) {
		this.x = x; this.y = y;
		this.direction = direction;
		try {
			if (direction.equals("up")) {
				hitbox = new Rectangle(x,y,25,8);
				pointer = new Image("art//menu//exitpointerU.png");
			} else if (direction.equals("down")) {
				hitbox = new Rectangle(x,y+17,25,8);
				pointer = new Image("art//menu//exitpointerD.png");
			} else if (direction.equals("left")) {
				hitbox = new Rectangle(x,y,8,25);
				pointer = new Image("art//menu//exitpointerL.png");
			} else if (direction.equals("right")) {
				hitbox = new Rectangle(x+17,y,8,25);
				pointer = new Image("art//menu//exitpointerR.png");
			} else {
				System.out.println("exit direction error at "+x+","+y);
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 0,1	1,1

	 0,0	1,0
	 */
	public void update(STPGame game) {
		if(game.player.hitbox.intersects(this.hitbox)) {
			if (direction.equals("up")) {
				game.player.y = 591;
				game.loadedlevel.locationy++;
			} else if (direction.equals("down")) {
				game.player.y = 9;
				game.loadedlevel.locationy--;
			} else if (direction.equals("left")) {
				game.player.x = 591;
				game.loadedlevel.locationx--;
			} else if (direction.equals("right")) {
				game.player.x = 9;
				game.loadedlevel.locationx++;
			}
			game.changeloc();
			game.player.inithitbox();
		}
		//System.out.println(game.player.x+","+game.player.y);
	}
	
	private int arrowdisplay;
	public void render() {
		arrowdisplay++;
		if (arrowdisplay < 25) {
			if (direction.equals("up")) {
				pointer.draw(x+8,y);
			} else if (direction.equals("down")) {
				pointer.draw(x+8,y+15);
			} else if (direction.equals("left")) {
				pointer.draw(x,y+7);
			} else if (direction.equals("right")) {
				pointer.draw(x+14,y+7);
			}
		}
		if (arrowdisplay > 50) {
			arrowdisplay = 0;
		}
	}

}
