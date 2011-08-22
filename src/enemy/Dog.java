package enemy;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import save_the_princess.STPGame;

public class Dog extends Enemy {
	
	private int counter;
	private boolean notice,noticereturn;
	
	public Animation standdown,standright,standleft,standup;
	public Animation walkdown,walkright,walkleft,walkup;
	public int orientation; //0 - DOWN, 1 - RIGHT, 2 - LEFT, 3 - UP
	
	public int getType() {
		return 1;
	}
	
	public Dog(int orientation,int x,int y) {
		imageinit();
		this.orientation = orientation;
		centerme(x,y);
		setImgStanding();
		setHitbox();
	}
	
	private void centerme(int x,int y) {
		if (orientation == 0 || orientation == 3) {
			this.x = x+8;
			this.y = y+4;
		} else if (orientation == 1 || orientation == 2) {
			this.x = x+5;
			this.y = y+5;
		}
	}
	
	public void update(STPGame game) {
		counter++;
		if (counter == 5) {
			counter = 0;
			if (!notice && !noticereturn && los(orientation,game)) {
				game.display.sound.dogbark.play();
				notice = true;
				setImgRun();
			}
		}
		if (notice) {
			noticeRun();
		}
		if (notice && !viewboxstatichit(hitbox,game.staticslist)) {
			notice = false;
			noticereturn = true;
			noticeReturnRun(); noticeReturnRun(); noticeReturnRun();
			setImgReturnRun();
		}
		if (noticereturn) {
			noticeReturnRun();
			if (!viewboxstatichit(hitbox,game.staticslist)) {
				noticereturn = false;
				noticeRun();
				setImgStanding();
			}
		}
	}
	
	public void render() {
		img.draw(x,y);
		if (notice) {
			emote.draw(x,y-10);
		}
	}
	
	private void noticeReturnRun() {
		if (orientation == 0) {
			y = y - 1;
		} else if (orientation == 1) {
			x = x - 1;
		} else if (orientation == 2) {
			x = x + 1;
		} else if (orientation == 3) {
			y = y + 1;
		}
		setHitbox();
	}
	
	private void noticeRun() {
		if (orientation == 0) {
			y = y + 2;
		} else if (orientation == 1) {
			x = x + 2;
		} else if (orientation == 2) {
			x = x - 2;
		} else if (orientation == 3) {
			y = y - 2;
		}
		setHitbox();
	}
	
	private void setImgReturnRun() {
		if (orientation == 3) {
			img = walkdown;
		} else if (orientation == 2) {
			img = walkright;
		} else if (orientation == 1) {
			img = walkleft;
		} else if (orientation == 0) {
			img = walkup;
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
	
	private void setImgStanding() {
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
	
	private void setHitbox() {
		hitbox = new Rectangle(x,y, img.getWidth(), img.getHeight());
	}
	
	public void imageinit() {
		try {
			emote = new Image("art//misc//notice.png");
			int[] nullduration = {10000};
			Image[] standDownSto = {new Image("art//dog//dogdown.png")};
			standdown = new Animation(standDownSto,nullduration,true);
			Image[] standRightSto = {new Image("art//dog//dogright.png")};
			standright = new Animation(standRightSto,nullduration,true);
			Image[] standLeftSto = {new Image("art//dog//dogleft.png")};
			standleft = new Animation(standLeftSto,nullduration,true);
			Image[] standUpSto = {new Image("art//dog//dogup.png")};
			standup = new Animation(standUpSto,nullduration,true);
			
			Image[] stoWLeft = {new Image("art//dog//dogleft2.png"),new Image("art//dog//dogleft3.png")};
			int[] duration = {150,150};
			walkleft = new Animation(stoWLeft,duration,true);
			Image[] stoWRight = {new Image("art//dog//dogright2.png"),new Image("art//dog//dogright3.png")};
			walkright = new Animation(stoWRight,duration,true);
			Image[] stoWUp = {new Image("art//dog//dogup.png"),new Image("art//dog//dogup2.png"),new Image("art//dog//dogup.png"),new Image("art//dog//dogup3.png")};
			int[] duration2 = {200,200,200,200};
			int[] durationup = {100,100,100,100};
			walkup = new Animation(stoWUp,durationup,true);
			Image[] stoWDown = {new Image("art//dog//dogdown.png"),new Image("art//dog//dogdown1.png"),new Image("art//dog//dogdown.png"),new Image("art//dog//dogdown2.png")};
			walkdown = new Animation(stoWDown,duration2,true);
			killerimg = new Image("art//menu//dogkiller.png");
		} catch (SlickException e) {
			System.out.println("dog image(s) missing!");
		}
	}
}
