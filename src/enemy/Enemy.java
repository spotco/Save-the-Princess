package enemy;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import save_the_princess.STPGame;

public class Enemy {
	public Rectangle hitbox;
	public Animation img;
	public Image emote;
	
	public Image killerimg;
	public int x,y;
	
	public void update(STPGame game) {}
	
	public void render() {
		img.draw(x,y);
	}
	
	public int getType() {
		return 0;
	}
	//1 is dog
	//27 is fireball
	//67 is knightboss
	
	public boolean los(int direction, STPGame game) {
		Rectangle viewbox = new Rectangle(hitbox.getX(),hitbox.getY(),hitbox.getWidth(),hitbox.getHeight());
		while (viewboxstatichit(viewbox,game.staticslist)) {
			if (direction == 0) viewbox.setHeight(viewbox.getHeight()+hitbox.getHeight()); //down
			if (direction == 1) viewbox.setWidth(viewbox.getWidth()+hitbox.getWidth());//right
			if (direction == 2) { //left
				viewbox.setWidth(viewbox.getWidth()+hitbox.getWidth());
				viewbox.setX(viewbox.getX()-hitbox.getWidth());
			}
			if (direction == 3) { //up
				viewbox.setHeight(viewbox.getHeight()+hitbox.getHeight());
				viewbox.setY(viewbox.getY()-hitbox.getHeight());
			}
			if (viewbox.intersects(game.player.hitbox) && viewboxstatichit(viewbox,game.staticslist)) {
				return true;
			}
		}
		return false;
	}
	
	//false if a hit,true if no hits(its weird like that lol)
	public boolean viewboxstatichit(Rectangle viewbox,ArrayList<Rectangle> staticslist) {
		for (Rectangle check : staticslist) {
			if (check.intersects(viewbox)) {
				return false;
			}
		}
		return true;
	}
	
}
