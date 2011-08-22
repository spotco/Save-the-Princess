package Animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import save_the_princess.AnimationManager;

public class KnightBossInitAnimation extends BasicAnimation {
	public static int A_LENGTH = 250;
	
	
	public int timer;
	
	public Image right;
	public Animation left;
	public Image wemustfight;
	
	
	
	public KnightBossInitAnimation(AnimationManager manager) {
		manager.display.sound.stop();
		manager.display.sound.play("boss");
		testcounter = 0;
		this.manager = manager;
		timer = A_LENGTH;
		manager.inAnimation = true;
		try {
			right = new Image("art//menu//knightkiller.png");
			left = manager.display.menu.introleft;
			wemustfight = new Image("art//menu//wemustfight.png");
		} catch (SlickException e) {
			System.out.println("cutscene couldnt load ");
		}
	}
	private int testcounter;
	private int standsto;
	public void render(Graphics g) {
		if (timer > 150) {
			left.draw(testcounter,240);
			right.draw(625-testcounter,150);
			standsto = testcounter;
		} else if (timer > 50) {
			left.draw(standsto,240);
			right.draw(625-standsto,150);
			wemustfight.draw(450,30);
		} else {
			left.draw(standsto,240);
			right.draw(625-standsto-(50-timer),150);
		}
		
	}
	
	public void update(GameContainer frame) {
		//System.out.println(timer);
		testcounter++;
		timer--;
		if (frame.getInput().isKeyDown(Input.KEY_SPACE)) {
			//System.out.println("this is activating");
			timer = 1;
		}
		if (timer < 0) {
			manager.display.sound.standandfight.play();
			manager.inAnimation = false;
		}
	}

}
