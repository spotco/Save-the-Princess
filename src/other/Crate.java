package other;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import enemy.Enemy;

import save_the_princess.STPGame;

public class Crate extends Other {
	
	private Image crateimg;
	private Rectangle pushleft,pushright,pushup,pushdown;
	
	public Crate(int x, int y) {
		this.x = x+3; this.y = y+3;
		try {
			crateimg = new Image("art//misc//crate.png");
		} catch (SlickException e) {
			System.out.println("error in finding art//misc//crate.png");
		}
		createpushbox();
	}
	
	public void createpushbox() {
		hitbox = new Rectangle(x,y,20,20);
		pushleft = new Rectangle(x-1,y+9,2,3);
		pushright = new Rectangle(x+19,y+9,2,3);
		pushup = new Rectangle(x+9,y-1,3,2);
		pushdown = new Rectangle(x+9,y+19,3,2);
		
		/*
		pushleft = new Rectangle(x-1,y,2,20);
		pushright = new Rectangle(x+19,y,2,20);
		pushup = new Rectangle(x,y-1,20,2);
		pushdown = new Rectangle(x,y+19,20,2);
		*/
	}
	
	public void render() {
		crateimg.draw(x,y);
	}
	
	public void update(STPGame game) {
		removecurrentstatic(game);
		game.staticslist.add(hitbox);
		if(pushleft.intersects(game.player.hitbox) && candir(2,game)) {
			removecurrentstatic(game);
			x = x + 1;
			createpushbox();
		} else if (pushright.intersects(game.player.hitbox) && candir(3,game)) {
			removecurrentstatic(game);
			x = x - 1;
			createpushbox();
		} else if (pushdown.intersects(game.player.hitbox) && candir(1,game) ) {
			removecurrentstatic(game);
			y = y - 1;
			createpushbox();
		} else if (pushup.intersects(game.player.hitbox)&& candir(0,game)) {
			removecurrentstatic(game);
			y = y + 1;
			createpushbox();
		}
	}
	
	public boolean candir(int dir, STPGame game) { //0 pushup, 1 pushdown, 2 pushleft, 3 pushright
		Rectangle test = null;
		if (dir == 0) {
			test = new Rectangle(x,y+21,20,1);
		} else if (dir == 1) {
			test = new Rectangle(x,y-2,20,1);
		} else if (dir == 2) {
			test = new Rectangle(x+21,y,1,20);
		} else if (dir == 3) {
			test = new Rectangle(x-2,y,1,20);
		}
		
		for (Rectangle r : game.staticslist) {
			if (test.intersects(r)) {
				return false;
			}
		}
		for (Enemy e : game.enemylist) {
			//System.out.println("testin");
			if (test.intersects(e.hitbox)) {
				return false;
			}
		}
		
		return true;
	}
	
	public void removecurrentstatic(STPGame game) {
		for(int i = 0; i < game.staticslist.size();i++) {
			if (game.staticslist.get(i).getX() == x && game.staticslist.get(i).getY() == y) {
				game.staticslist.remove(i);
				break;
			}
		}
	}

}
