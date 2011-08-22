package other;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import Player.Player;

import enemy.Enemy;
import enemy.KnightBoss;

import save_the_princess.STPGame;

public class Tracker extends Other {
	public boolean active;
	public TrackNode[][] nodemap;
	//public long counter;
	
	public boolean knightinitialpathset;
	
	public Tracker() {
		hitbox = new Rectangle(0,0,0,0);
		nodemap = new TrackNode[25][25];
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				nodemap[i][j] = new TrackNode(i*25,j*25);
			}
		}
	}
	
	public void update(STPGame game) {
		//counter++;
		//int activatedthisround = 0;
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				if(game.player.hitbox.intersects(nodemap[i][j].hitbox)) {
					nodemap[i][j].activated = true;
					nodemap[i][j].steptime = System.nanoTime();
					//activatedthisround++;
				}
			}
		}
		//System.out.println("activated: "+activatedthisround);
	}
	int testcount = 100;
	public void specialRender(Graphics g,KnightBoss e,Player p) {
		//System.out.println(testcount);
		g.setColor(Color.blue);
		g.draw(nodemap[e.basicx][e.basicy].hitbox);
		
		g.setColor(Color.magenta);
		g.draw(e.hitbox);
		
		g.setColor(Color.yellow);
		boolean test = false;
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				if (p.hitbox.intersects(nodemap[i][j].hitbox)) {
					g.draw(nodemap[i][j].hitbox);
					test = true;
				}
			}
		}
		if (test == false) {
			testcount--;
		}
		if (testcount < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	public void initknightpath(KnightBoss e,String direction) {
		int cx = e.basicx;
		int cy = e.basicy;
		e.activated = true;
		long cval = 0;
		while(true) {
			if (nodemap[cx][cy].activated) {
				//System.out.println("initialpath break");
				break;
			}
			
			nodemap[cx][cy].activated = true;
			nodemap[cx][cy].steptime = cval;
			if (direction.equals("down")) {
				cy++;
				cval++;
			}
			
			//System.out.println("test:"+cx+","+cy);
			if (cy > 24) {
				break;
			}
		}
	}
	
	public int type() {
		return 67;
	}
	
	
	public class TrackNode {
		public int x;
		public int y;
		public Rectangle pathbox;
		public Rectangle hitbox;
		public boolean activated;
		public long steptime;
		
		public TrackNode(int x,int y) {
			this.x = x;
			this.y = y;
			//hitbox = new Rectangle(x+8,y+10,10,10);
			//hitbox = new Rectangle(x+8,y+10,9,9);
			
			hitbox = new Rectangle(x,y,25,25);
			pathbox = new Rectangle(x,y,25,25);
		}
		
	}
	
	

}
