package other;

import org.newdawn.slick.geom.Rectangle;

public class GuardPath extends Other {
	public int orientation; //0 - DOWN, 1 - RIGHT, 2 - LEFT, 3 - UP
	public boolean isStop;
	
	public GuardPath(int orientation, int x, int y, boolean isStop) {
		this.orientation = orientation;
		hitbox = new Rectangle(x,y,25,25);
		this.isStop = isStop;
	}
	
	public int getOrientation() {
		return orientation;
	}
	
	public int type() {
		return 99;
	}

}
