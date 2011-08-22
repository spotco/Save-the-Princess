package other;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;

import save_the_princess.STPGame;
import save_the_princess.STPView;

public class Other {
	public Rectangle hitbox;
	public Animation img;
	public int x,y;
	
	public void update(STPGame game) {}
	
	public void render() {}
	
	public void hit(STPView display) {}
	
	public int type() {
		return 0;
	}
	//99 is guardpath
	//51 is door
	//23 is princess
	//67 is tracker
}
