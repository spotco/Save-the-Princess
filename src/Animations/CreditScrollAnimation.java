package Animations;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import save_the_princess.AnimationManager;

public class CreditScrollAnimation extends BasicAnimation {
	public Image fgimage,scrollimage,bgstars;
	public Image postscriptbg,postscriptknight,notice,wemustfight,tbc;
	public int cloc;
	
	public CreditScrollAnimation(AnimationManager manager) {
		this.manager = manager;
		manager.display.sound.current.pause();
		manager.display.sound.play("credits"); //55 seconds
		try {
			fgimage = new Image("art//final//creditscreen.png");
			scrollimage = new Image("art//final//creditslist.png");
			bgstars = new Image("art//final//firstend2.png");
			
			postscriptbg = new Image("art//final//postscriptbg.png");
			postscriptknight = new Image("art//final//postscriptknight.png");
			notice = new Image("art//final//notice.png");
			wemustfight = new Image("art//final//wemustfight.png");
			
			tbc = new Image("art//final//tbc.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		cloc = 500;
		waittimer = -1;
	}
	
	public void render(Graphics g) {
		if (!postscript) {
			bgstars.draw(0,0);
			scrollimage.draw(0,cloc-100);
			fgimage.draw(0,0);
		} else {
			if (postscripttimer < 240) {
				//System.out.println(postscripttimer);
				postscriptbg.draw(0,0);
				if (postscripttimer > 40) {
					notice.draw(0,0);
				}
				if (postscripttimer > 50 && postscripttimer < 190) {
					postscriptknight.draw(0,0);
				}
				if (postscripttimer > 90 && postscripttimer < 190) {
					wemustfight.draw(0,0);
				}
				if (postscripttimer >= 190) {
					postscriptknight.draw(-(postscripttimer-190),0);
				}
			} else {
				tbc.draw(165,222);
			}
		}
	}
	private int waittimer,postscripttimer;
	private boolean postscript;
	public void update(GameContainer frame) {
		if (!postscript) {
			//System.out.println(cloc);
			/*if (frame.getInput().isKeyPressed(Input.KEY_SPACE)) {
				cloc = -2300;
				if (waittimer > 0) {
					waittimer = 0;
				} else {
					waittimer = 300;
				}
			}*/
			if (cloc > -2300) {
				cloc--;
				if (cloc <= -2300) {
					waittimer = 400;
				}
			} else if (waittimer > 0) {
				waittimer--;
			} else {
				postscript = true;
			}
		} else {
			postscripttimer++;
			if (postscripttimer == 41) {
				manager.display.sound.standandfight.play();
			}
			if (postscripttimer >= 240) {
				if (postscripttimer >= 500/* || frame.getInput().isKeyPressed(Input.KEY_SPACE)*/) {
					manager.display.sound.current.pause();
					manager.inAnimation = false;
					manager.display.save.nextLevel();
					if(!manager.display.save.getCurrentLevel().equals("End")) {
						manager.display.save.writeSaveCurrent(manager.display.save.getCurrentLevel());
					}
					manager.display.menu.loadfiles(manager.display.save.getCurrentLevel());
					manager.startAnimation("titleScreenAnimation", null);
				}
			}
		}
	}

}
