package enemy;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import other.Other;
import other.Tracker;

import save_the_princess.STPGame;

public class KnightBoss extends Enemy {
	public int orientation; //0DOWN 1RIGHT 2LEFT 3UP
	public Animation standup,standleft,standdown,standright;
	public Animation walkup,walkleft,walkright,walkdown;
	public int basicx,basicy;
	public Image emote1,emote2;
	
	public Rectangle walkbox;
	
	public KnightBoss(int x,int y,int basicx, int basicy,boolean initactivate) {
		this.basicx = basicx; this.basicy = basicy;
		this.x = x-2; this.y = y-28;
		makehitboxes();
		imageinit();
		oldtarget = -1;
		activated = initactivate;
	}
	
	private void makehitboxes() {
		//hitbox = new Rectangle(x+6,y+9,19,44);
		hitbox = new Rectangle(x+5,y+28,20,21);
		walkbox = new Rectangle(x+5,y+30,20,21);
	}
	
	public boolean hastarget,activated;
	private int oldtarget,stobx,stoby;
	
	public Tracker tracker;
	public boolean hasGetTracker;
	public void update(STPGame game) {
		if (!hasGetTracker) {
			getTracker(game);
			hasGetTracker = true;
			//System.out.println("tracker found");
		}
		getbasicloc();
		int direction = -1;
		if (hastarget == false && activated) {
			hastarget = true;
			oldtarget = getcurrenttar();
			stobx = basicx; stoby = basicy;
		}
		if (basicx != stobx || basicy != stoby) {
			hastarget = false;
		}
		//System.out.println(hastarget);
		direction = oldtarget;
		
		if (direction == 0) {//D
			y++;
			if(!viewboxstatichit(walkbox,game.staticslist)) y--;
		} else if (direction == 1) {//R
			x++;
			if(!viewboxstatichit(walkbox,game.staticslist)) x--;
		} else if (direction == 2) {//L
			x--;
			if(!viewboxstatichit(walkbox,game.staticslist)) x++;
		} else if (direction == 3) {//U
			y--;
			if(!viewboxstatichit(walkbox,game.staticslist)) y++;
		} else {
			//System.out.println("knightboss direction error!");
		}
		//System.out.println("direction:"+direction+", current:"+tracker.nodemap[basicx][basicy].steptime);
		orientation = direction;
		makehitboxes();
		
	}
	
	private int getcurrenttar() { //0DOWN 1RIGHT 2LEFT 3UP
		int dirsto = -1;
		long maxcounter = -1;
		if(basicy < 24 && tracker.nodemap[basicx][basicy+1].activated && tracker.nodemap[basicx][basicy+1].steptime > maxcounter) { //down
			dirsto = 0; maxcounter = tracker.nodemap[basicx][basicy+1].steptime;
		}
		if(basicx < 24 && tracker.nodemap[basicx+1][basicy].activated && tracker.nodemap[basicx+1][basicy].steptime > maxcounter) { //right
			dirsto = 1; maxcounter = tracker.nodemap[basicx+1][basicy].steptime;
		}
		if(basicx > 0 && tracker.nodemap[basicx-1][basicy].activated && tracker.nodemap[basicx-1][basicy].steptime > maxcounter) { //left
			dirsto = 2; maxcounter = tracker.nodemap[basicx-1][basicy].steptime;
		}
		if(basicy > 0 && tracker.nodemap[basicx][basicy-1].activated && tracker.nodemap[basicx][basicy-1].steptime > maxcounter) { //up
			dirsto = 3; maxcounter = tracker.nodemap[basicx][basicy-1].steptime;
		}
		return dirsto;
		
	}
	
	private void getbasicloc() {
		for(int y = 0; y < 25; y++) {
			for(int x = 0;x < 25;x++) {
				if(tracker.nodemap[x][y].pathbox.contains(walkbox.getX(), walkbox.getY()) &&
					tracker.nodemap[x][y].pathbox.contains(walkbox.getX() + walkbox.getWidth(), walkbox.getY()) &&
					tracker.nodemap[x][y].pathbox.contains(walkbox.getX(), walkbox.getY() + walkbox.getHeight()) &&
					tracker.nodemap[x][y].pathbox.contains(walkbox.getX() + walkbox.getWidth(), walkbox.getY() + walkbox.getHeight()) 
				) {
					basicx = x;
					basicy = y;
				}
			}
		}
	}
	
	public int emotecounter;
	
	public void render() {
		emotecounter++;
		if (emotecounter > 400 && emotecounter < 500 && orientation != -1) {
			emote1.draw(x-1,y-18);
		} else if (emotecounter > 1000 && emotecounter < 1100 && orientation != -1) {
			emote2.draw(x-1,y-18);
		} else if (emotecounter > 1700) {
			emotecounter = 0;
		}
		
		if (orientation == 0) {//D
			walkdown.draw(x,y);
		} else if (orientation == 1) {//R
			walkright.draw(x,y);
		} else if (orientation == 2) {//L
			walkleft.draw(x,y);
		} else if (orientation == 3) {//U
			walkup.draw(x,y);
		} else if (orientation == -1) {
			standdown.draw(x,y);
		}
	}
	
	public int getType() {
		return 67;
	}
	
	private void getTracker(STPGame game) {
		for(Other o:game.objectlist) {
			if (o.type() == 67) {
				tracker = (Tracker)o;
				break;
			}
		}
	}
	
	public void imageinit() {
		try {
			emote1 = new Image("art//misc//fight.png");
			emote2 = new Image("art//misc//coward.png");
			killerimg = new Image("art//menu//knightkiller.png");
			int[] temp = {100000};
			Image[] sto = {new Image("art//knight//knightdown1.png")};
			standdown = new Animation(sto,temp,true);
			Image[] sto1 = {new Image("art//knight//knightleft1.png")};
			standleft = new Animation(sto1,temp,true);
			Image[] sto2 = {new Image("art//knight//knightup1.png")};
			standup = new Animation(sto2,temp,true);
			Image[] sto3 = {new Image("art//knight//knightright1.png")};
			standright = new Animation(sto3,temp,true);
			Image[] asto = {new Image("art//knight//knightdown1.png"),new Image("art//knight//knightdown2.png"),new Image("art//knight//knightdown1.png"),new Image("art//knight//knightdown3.png")};
			int[] temp2 = {200,200,200,200};
			walkdown = new Animation(asto,temp2,true);
			Image[] asto2 = {new Image("art//knight//knightup1.png"),new Image("art//knight//knightup2.png"),new Image("art//knight//knightup1.png"),new Image("art//knight//knightup3.png")};
			walkup = new Animation(asto2,temp2,true);
			int[] temp3 = {200,200};
			Image[] asto3 = {new Image("art//knight//knightleft1.png"),new Image("art//knight//knightleft2.png")};
			walkleft = new Animation(asto3,temp3,true);
			Image[] asto4 = {new Image("art//knight//knightright1.png"),new Image("art//knight//knightright2.png")};
			walkright = new Animation(asto4,temp3,true);
		} catch (SlickException e) {
			System.out.println("error loading knight image");
			e.printStackTrace();
		}
	}

}
