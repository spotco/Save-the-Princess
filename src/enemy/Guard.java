package enemy;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import other.GuardPath;
import other.Other;

import save_the_princess.STPGame;

public class Guard extends Enemy {
	public Animation standdown,standright,standleft,standup;
	public Animation walkdown,walkright,walkleft,walkup;
	public int orientation; //0 - DOWN, 1 - RIGHT, 2 - LEFT, 3 - UP
	public Image help,question;
	public Animation search;
	
	public Guard(int orientation,int x,int y) {
		this.orientation = orientation;
		imageinit();
		centerme(x,y);
		setImgRun();
		setHitbox();
	}
	
	private int counter;
	private boolean chase;
	
	public void render() {
		
		if (stuckcounter > 3) {
			help.draw(x+5,y-10);
			img.draw(x,y);
		} else if (chase) {
			emote.draw(x+5,y-10);
			img.draw(x,y);
		} else if (questioncounter > 0) {
			questioncounter--;
			question.draw(x+5,y-10);
			search.draw(x,y);
		} else {
			img.draw(x,y);
		}
	}
	public int questioncounter;
	public int stuckcounter;
	private boolean justhit;
	public int stuncounter;
	
	public void update(STPGame game) {
		counter++;
		if (counter > 10) {
			counter = 0;
			if (los(orientation,game) && !chase) {
				game.display.sound.hey.play();
				setImgRun();
				chase = true;
			}
		}
		if(stuckcounter > 3 && !viewboxstatichit(hitbox,game.staticslist)) {
		} else if (chase) {
			if(counter%3 == 0) {
				normalupdate(game,1);
				stuckcounter = 0;
			} else {
				normalupdate(game,2);
				stuckcounter = 0;
			}
			if (!viewboxstatichit(hitbox,game.staticslist)) {
				questioncounter = 100;
				normalupdate(game,-4);
				chase = false;
				stuckcounter++;
			} else {
			}
		} else if (questioncounter > 0) {
			for(int i = 0; i < 4;i++) {
				if(los(i,game)) {
					orientation = i;
					setImgRun();
					break;
				}
			}
			if (questioncounter < 2) {
				orientationreverse();
				setImgRun();
			}
		} else if(!viewboxstatichit(hitbox,game.staticslist)) {
			normalupdate(game,-1);
			orientationChange();
			setImgRun();
			stuckcounter++;
		} else {
			if (insidepathchangesq(game.objectlist)) {
				setImgStand();
			} else {
				setImgRun();
				normalupdate(game,1);
			}
			stuckcounter = 0;
		}
		setHitbox();
	}
	
	
	private boolean insidepathchangesq(ArrayList<Other> objectlist) {
		for(Other e : objectlist) {
			if(e.type() == 99) {
				if(e.hitbox.contains(hitbox.getX(),hitbox.getY()) &&
				   e.hitbox.contains(hitbox.getX()+hitbox.getWidth(),hitbox.getY()+hitbox.getHeight())){
					this.orientation = ((GuardPath)e).getOrientation();
					setImgRun();
					if (((GuardPath)e).isStop) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private void orientationreverse() {
		if (orientation == 0) {
			orientation = 3;
		} else if (orientation == 1) {
			orientation = 2;
		} else if (orientation == 2) {
			orientation = 1;
		} else if (orientation == 3) {
			orientation = 0;
		}
	}
	
	private void orientationChange() {
		if (orientation == 0) {
			orientation = 2;
		} else if (orientation == 1) {
			orientation = 0;
		} else if (orientation == 2) {
			orientation = 3;
		} else if (orientation == 3) {
			orientation = 1;
		}
	}
	
	private void normalupdate(STPGame game,int dist) {
		if (orientation == 0) {
			y = y+dist;
		} else if (orientation == 1) {
			x = x+dist;
		} else if (orientation == 2) {
			x = x-dist;
		} else if (orientation == 3) {
			y = y-dist;
		}
	}
	
	private void setImgRun() {
		if (orientation == 0) {
			img = walkdown;
		} else if (orientation == 1) {
			img = walkright;
		} else if (orientation == 2) {
			img = walkleft;
		} else if (orientation == 3) {
			img = walkup;
		}
	}
	
	private void setImgStand() {
		if (orientation == 0) {
			img = standdown;
		} else if (orientation == 1) {
			img = standright;
		} else if (orientation == 2) {
			img = standleft;
		} else if (orientation == 3) {
			img = standup;
		}
	}
	
	private void centerme(int x, int y) {
		this.x = x + 5;
		this.y = y;
	}
	
	private void setHitbox() {
		hitbox = new Rectangle(x+2,y+5, 18-2, 25-8);
	}
	
	private void imageinit() {
		try {
			emote = new Image("art//misc//notice.png");
			question = new Image("art//misc//question.png");
			help = new Image("art//misc//help.png");
			int[] nullduration = {10000};
			Image[] standDownSto = {new Image("art//guard//standdown.png")};
			standdown = new Animation(standDownSto,nullduration,true);
			Image[] standRightSto = {new Image("art//guard//standright.png")};
			standright = new Animation(standRightSto,nullduration,true);
			Image[] standLeftSto = {new Image("art//guard//standleft.png")};
			standleft = new Animation(standLeftSto,nullduration,true);
			Image[] standUpSto = {new Image("art//guard//standup.png")};
			standup = new Animation(standUpSto,nullduration,true);
			
			Image[] stoWLeft = {new Image("art//guard//walkleft.png"),new Image("art//guard//standleft.png")};
			int[] duration = {200,200};
			walkleft = new Animation(stoWLeft,duration,true);
			Image[] stoWRight = {new Image("art//guard//walkright.png"),new Image("art//guard//standright.png")};
			walkright = new Animation(stoWRight,duration,true);
			Image[] stoWUp = {new Image("art//guard//walkup1.png"),new Image("art//guard//standup.png"),new Image("art//guard//walkup2.png"),new Image("art//guard//standup.png")};
			int[] duration2 = {200,200,200,200};
			walkup = new Animation(stoWUp,duration2,true);
			Image[] stoWDown = {new Image("art//guard//walkdown1.png"),new Image("art//guard//standdown.png"),new Image("art//guard//walkdown2.png"),new Image("art//guard//standdown.png")};
			walkdown = new Animation(stoWDown,duration2,true);
			killerimg = new Image("art//menu//guardleft1.png");
			Image[] stoSearch = {new Image("art//guard//standdown.png"),new Image("art//guard//standright.png"),new Image("art//guard//standup.png"),new Image("art//guard//standleft.png")};
			int[] duration3 = {500,500,500,500};
			search = new Animation(stoSearch,duration3,true);
		} catch (SlickException e) {
			System.out.println("guard image(s) missing!");
		}
	}


}
