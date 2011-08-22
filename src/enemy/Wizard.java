package enemy;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import save_the_princess.STPGame;

public class Wizard extends Enemy {
	
	public int orientation; //0DOWN 1RIGHT 2LEFT 3UP
	public Animation upshoot,downshoot,leftshoot,rightshoot;
	public Animation upstand,downstand,leftstand,rightstand;
	public Animation fup,fdown,fleft,fright;
	public boolean isfiring;
	public Image fbkill;
	
	public Wizard(int x,int y,int orientation) {
		this.x = x; this.y = y; this.orientation = orientation;
		hitbox = new Rectangle(x+8,y+6,12,15);
		imageinit();
		imgupdate();
		fireballtimer = 35;
	}
	
	private int fireballtimer;
	
	public void update(STPGame game) {
		if(los(orientation,game)) {
			isfiring = true;
			fireballtimer++;
			if (fireballtimer > 35) {
				game.display.sound.fireball.play();
				game.enemylist.add(new Fireball(x+7,y+7,orientation,this));
				fireballtimer = 0;
			}
		} else {
			isfiring = false;
		}
		imgupdate();
	}
	
	public void render() {
		img.draw(x,y);
		if (isfiring) {
			emote.draw(x+8,y-10);
		}
	}
	
	
	public void imgupdate() {
		if (isfiring) {
			if (orientation == 3) {
				img = upshoot;
			} else if (orientation == 0) {
				img = downshoot;
			} else if (orientation == 2) {
				img = leftshoot;
			} else if (orientation == 1) {
				img = rightshoot;
			}
		} else {
			if (orientation == 3) {
				img = upstand;
			} else if (orientation == 0) {
				img = downstand;
			} else if (orientation == 2) {
				img = leftstand;
			} else if (orientation == 1) {
				img = rightstand;
			}
		}
	}
	
	public void imageinit() {
		try {
			Image[] sto1 = {new Image("art//wizard//wizardup1.png"),new Image("art//wizard//wizardup2.png")};
			Image[] sto2 = {new Image("art//wizard//wizarddown1.png"),new Image("art//wizard//wizarddown2.png")};
			Image[] sto3 = {new Image("art//wizard//wizardleft1.png"), new Image("art//wizard//wizardleft2.png")};
			Image[] sto4 = {new Image("art//wizard//wizardright1.png"),new Image("art//wizard//wizardright2.png")};
			int[] sto = {400,400};
			upshoot = new Animation(sto1,sto,true);
			downshoot = new Animation(sto2,sto,true);
			leftshoot = new Animation(sto3,sto,true);
			rightshoot = new Animation(sto4,sto,true);
			Image[] ssto1 = {new Image("art//wizard//wizardup1.png")};
			Image[] ssto2 = {new Image("art//wizard//wizarddown1.png")};
			Image[] ssto3 = {new Image("art//wizard//wizardleft1.png")};
			Image[] ssto4 = {new Image("art//wizard//wizardright1.png")};
			int[] ssto = {10000};
			upstand = new Animation(ssto1,ssto,true);
			downstand = new Animation(ssto2,ssto,true);
			leftstand = new Animation(ssto3,ssto,true);
			rightstand = new Animation(ssto4,ssto,true);
			killerimg = new Image("art//menu//wizardkiller.png");
			Image[] fsto1 = {new Image("art//wizard//fb//fbu1.png"),new Image("art//wizard//fb//fbu2.png"),new Image("art//wizard//fb//fbu3.png")};
			Image[] fsto2 = {new Image("art//wizard//fb//fbd1.png"),new Image("art//wizard//fb//fbd2.png"),new Image("art//wizard//fb//fbd3.png")};
			Image[] fsto3 = {new Image("art//wizard//fb//fbl1.png"),new Image("art//wizard//fb//fbl2.png"),new Image("art//wizard//fb//fbl3.png")};
			Image[] fsto4 = {new Image("art//wizard//fb//fbr1.png"),new Image("art//wizard//fb//fbr2.png"),new Image("art//wizard//fb//fbr3.png")};
			int[] fsto = {200,200,200};
			fup = new Animation(fsto1,fsto,true);
			fdown = new Animation(fsto2,fsto,true);
			fleft = new Animation(fsto3,fsto,true);
			fright = new Animation(fsto4,fsto,true);
			fbkill = new Image("art//menu//fireballkiller.png");
			emote = new Image("art//misc//notice.png");
			
		} catch (SlickException e) {
			System.out.println("error loading wizard images");
		}
	}

}
