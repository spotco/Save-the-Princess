package other;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import save_the_princess.STPGame;

public class KeyDoor extends Other {
	
	private Image door;
	private boolean isActive;
	
	public KeyDoor(int x, int y) {
		this.x = x;
		this.y = y;
		try {
			door = new Image("art//tiles//keydoor.png");
		} catch (SlickException e) {
			System.out.println("could not find art//tiles//keydoor.png");
		}
		hitbox = new Rectangle(x-1,y-1,27,27);
		isActive = true;
	}
	
	private boolean hasAddedStatic;
	
	private void addstatic(STPGame game) {
		game.staticslist.add(new Rectangle(x,y,25,25));
	}
	
	private void removeme(STPGame game) {
		isActive = false;
		for (int i = 0; i < game.staticslist.size(); i++) {
			if (game.staticslist.get(i).getX() == this.x &&
				game.staticslist.get(i).getY() == this.y) {
				game.staticslist.remove(i);
				game.display.sound.unlockdoor.play();
				break;
			}
		}
		for (int i = 0; i < game.objectlist.size(); i++) {
			if (game.objectlist.get(i).x == this.x &&
				game.objectlist.get(i).y == this.y) {
				game.objectlist.remove(i);
				break;
			}
		}
	}
	
	public void render() {
		if (isActive) {
			door.draw(x,y);
		}
	}
	
	public void update(STPGame game) {
		if (!hasAddedStatic) {
			addstatic(game);
			hasAddedStatic = true;
		}
		if (game.player.hitbox.intersects(this.hitbox) && game.player.haskey) {
			removeme(game);
			game.player.haskey = false;
		}
	}

}
