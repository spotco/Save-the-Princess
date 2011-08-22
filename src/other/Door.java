package other;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import save_the_princess.STPGame;

public class Door extends Other {
	
	private boolean isClosed;
	private Image bars;
	
	public Door(boolean isClosed, int x, int y) {
		this.x = x;
		this.y = y;
		this.isClosed = isClosed;
		try {
			bars = new Image("art//tiles/bars.png");
		} catch (SlickException e) {
			System.out.println("could not find art//tiles//bars.png");
		}
		hitbox = new Rectangle(0,0,0,0);
	}
	
	
	public void render() {
		if(isClosed) {
			bars.draw(x,y);
		}
	}
	
	public int type() {
		return 51;
	}
	
	private boolean hasaddedrect;
	public void update(STPGame game) {
		if(!hasaddedrect && isClosed) {
			game.staticslist.add(new Rectangle(x,y,25,25));
			hasaddedrect = true;
		} 
	}
	
	public void openorclose(STPGame game) {
		if(isClosed) { //going from closed to open, remove the rect
			for(int i = 0; i < game.staticslist.size();i++) {
				if (game.staticslist.get(i).getX() == x && game.staticslist.get(i).getY() == y) {
					game.staticslist.remove(i);
					hasaddedrect = false;
					break;
				}
			}
		}
		isClosed = !isClosed;
	}



}
