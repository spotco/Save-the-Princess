package other;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import save_the_princess.STPView;

public class Princess extends Other {
	public Animation stand;
	
	public Princess(int x,int y) {
		this.x = x+6;
		this.y = y-8;
		imageinit();
		img = stand;
		hitboxinit();
	}
	
	public void render() {
		img.draw(x,y);
	}
	
	public void hitboxinit() {
		hitbox = new Rectangle(x,y,stand.getWidth(),stand.getHeight());
	}
	
	public void imageinit() {
		try {
			Image[] bar = {new Image("art//princess//princess1.png"),new Image("art//princess//princess2.png")};
			int[] duration = {800,800};
			stand = new Animation(bar,duration,true);
		} catch (SlickException e) {
			System.out.println("princess image not found!");
		}	
	}
	
	public void hit(STPView display) {
		try {
			display.animations.startAnimation("winAnimation",new Image("art//princess//princess1.png"));
		} catch (SlickException e) {
		}
	}
	
	public int type() {
		return 23;
	}
}
