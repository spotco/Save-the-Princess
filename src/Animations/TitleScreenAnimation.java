package Animations;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import save_the_princess.AnimationManager;

public class TitleScreenAnimation extends BasicAnimation {
	
	public static final int INTRO_LENGTH = 1825;
	
	public int timer;
	
	public TitleScreenAnimation(AnimationManager manager) {
		this.manager = manager;
		timer = INTRO_LENGTH;
		manager.inAnimation = true;
	}
	
	public void render(Graphics g) {
		//DONT MESS WITH THIS LOL
		if (timer > (INTRO_LENGTH-200)) {
			manager.display.menu.spotcologo.draw(235,270);
		} else if (timer > (INTRO_LENGTH - 350)) { //150
			manager.display.menu.introleft.draw(85+(INTRO_LENGTH-200-timer),240);
			manager.display.menu.introright.draw(485-(INTRO_LENGTH-200-timer),200);
		} else if (timer > (INTRO_LENGTH-350*2)) { //150
			manager.display.menu.introleft.draw(235,240);
			manager.display.menu.introright.draw(335,200);
			manager.display.menu.introheart.draw(285,180);
		} else if (timer > (INTRO_LENGTH-350*2-200)) { //INTRO IS AT 500 (200)
			manager.display.menu.introleft.draw(235,240);
			manager.display.menu.introright.draw(335,200);
			manager.display.menu.introheart.draw(285,180);
			manager.display.menu.guardleft.draw(600-((INTRO_LENGTH-350*2)-timer),30);
			manager.display.menu.guardleft.draw(600-((INTRO_LENGTH-350*2)-timer),400);
		} else if (timer > (INTRO_LENGTH-350*2-500)) { //300
			manager.display.menu.introleft.draw(235,240,66,150);
			manager.display.menu.introright.draw(335+(INTRO_LENGTH-350*2-200)-timer,200);
			manager.display.menu.guardright.draw(400 +(INTRO_LENGTH-350*2-200)-timer,30);
			manager.display.menu.guardright.draw(400 +(INTRO_LENGTH-350*2-200)-timer,400);
		} else if (timer > (INTRO_LENGTH-350*2-700)) {
			manager.display.menu.introleft.draw(235+((INTRO_LENGTH-350*2-500)-timer)*2,240);
		} else {
			manager.display.menu.menuimg.draw(0,(0-625)+((INTRO_LENGTH-150*2-900)-timer));
		}

	}
	
	public void update(GameContainer frame) {
		timer--;
		if (frame.getInput().isKeyPressed(Input.KEY_SPACE)) {
			timer = 1;
		}
		if (timer < 0) {
			manager.inAnimation = false;
		}
	}

}
