package other;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Window extends Other {
	public Window(int x,int y) {
		try {
			this.x = x; this.y = y;
			Image[] sto = {new Image("art//tiles//window1.png"), new Image("art//tiles//window2.png"), new Image("art//tiles//window3.png"), new Image("art//tiles//window4.png"), new Image("art//tiles//window5.png")};
			int timer = 400;
			int[] timersto = {timer,timer,timer,timer,timer};
			img = new Animation(sto,timersto,true);
		} catch (SlickException e) {
			System.out.println("error loading window.png in folder art//tiles");
		}
		hitbox = new Rectangle(-1,-1,0,0);
	}
	
	public void render() {
		img.draw(x,y);
	}
	
	

}
