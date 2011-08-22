package other;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Torch extends Other {
	
	public Torch(int x,int y) {
		try {
			this.x = x; this.y = y;
			Image[] sto = {new Image("art//tiles//torch//1.png"),new Image("art//tiles//torch//2.png"),new Image("art//tiles//torch//3.png"),new Image("art//tiles//torch//4.png"),new Image("art//tiles//torch//5.png"),new Image("art//tiles//torch//6.png")};
			int timer = 200;
			int[] timersto = {timer,timer,timer,timer,timer,timer};
			img = new Animation(sto,timersto,true);
		} catch (SlickException e) {
			System.out.println("error loading window.png in folder art//torch//something.png");
		}
		hitbox = new Rectangle(-1,-1,0,0);
	}
	
	public void render() {
		img.draw(x,y);
	}

}
