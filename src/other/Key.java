package other;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import save_the_princess.STPGame;

public class Key extends Other {
	
	public Key(int x, int y) {
		this.x = x+8; this.y = y+3;
		Image[] sto = new Image[6];
		try {
		sto[0] = new Image("art//misc//key//key.png"); 
		sto[1] = new Image("art//misc//key//key1.png"); 
		sto[2] = new Image("art//misc//key//key2.png"); 
		sto[3] = new Image("art//misc//key//key3.png"); 
		sto[4] = new Image("art//misc//key//key4.png"); 
		sto[5] = new Image("art//misc//key//key5.png"); 
		} catch (SlickException e) {
			System.out.println("art//misc//key error not found");
		}
		int[] stotim = {200,200,200,200,200,200};
		img = new Animation(sto,stotim,true);
		hitbox = new Rectangle(this.x,this.y,9,18);
	}
	
	public void update(STPGame game) {
		if (game.player.hitbox.intersects(this.hitbox) && !game.player.haskey) {
			game.player.haskey = true;
			for (int i = 0; i < game.objectlist.size(); i++) {
				if (game.objectlist.get(i).hitbox.getCenterX() == this.hitbox.getCenterX() &&
					game.objectlist.get(i).hitbox.getCenterY() == this.hitbox.getCenterY()) {
					game.display.sound.getkey.play();
					game.objectlist.remove(i);
				}
			}
		}
	}
	
	public void render() {
		img.draw(x,y);
	}

}
