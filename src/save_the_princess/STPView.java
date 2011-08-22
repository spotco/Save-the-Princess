package save_the_princess;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;

public class STPView extends BasicGame {
	public static void main(String[] args) {
		STPView mgame = new STPView("Save The Princess");
		try {
			AppGameContainer container = new AppGameContainer(mgame);
			container.setDisplayMode(625,625, false);
			container.setShowFPS(false);
			container.setTargetFrameRate(60);
			container.setVSync(true);
			container.start();
		} catch (SlickException e) {
			System.out.println("ERROR HAS OCCURED");
		}
	}
	
	public Menu menu;
	public STPGame game;
	public boolean inMenu;
	public boolean inGame;
	public SoundManager sound;
	public SaveReader save;
	public AnimationManager animations;
	
	public STPView(String name) {
		super(name);
	}
	
	private DeferredResource nextResource;
	private boolean loading;
	
	public void init(GameContainer frame) throws SlickException {
		LoadingList.setDeferredLoading(true); 
		loading = true;
		sound = new SoundManager();
		save = new SaveReader("data//save.dat","data//levels.dat");
		menu = new Menu(this);
		game = new STPGame(this);
		animations = new AnimationManager(this);
	}

	public void render(GameContainer frame, Graphics g) throws SlickException {
		if (frame.hasFocus()) {
			if (loading) {
				loadbarrender(g);
			} else if (animations.inAnimation) {
				animations.render(frame,g);
			} else if (inMenu) {
				menu.render(frame,g);
			} else if (inGame) {
				game.render(frame,g);
			}
		}
	}

	public void update(GameContainer frame, int arg1) {
		if (frame.hasFocus()) {
			if (loading) { //deferred loading bar
		        loadbarupdate();
			} else if (animations.inAnimation) {
				animations.update(frame);
			} else if (inMenu) {
				menu.update(frame);
			} else if (inGame) {
				game.update(frame);
			}
		}
	}

	
	private void loadbarrender(Graphics g) {
        int total = LoadingList.get().getTotalResources(); 
        int loaded = LoadingList.get().getTotalResources() - LoadingList.get().getRemainingResources(); 
        g.setColor(Color.yellow);
        if (nextResource != null) {
        	g.drawString("NOW LOADING: "+nextResource.getDescription(), 150, 300); 
        }
        g.fillRect(50,350,loaded*14,20); 
        g.drawRect(50,350,total*14 ,20); 
        g.setColor(Color.white);
	}
	
	private void loadbarupdate() {
		if (nextResource != null) { 
            try { 
                nextResource.load(); 
            } catch (IOException e) { 
                System.out.println("ERROR LOADING FILES");
            }     
            nextResource = null; 
        } 
        if (LoadingList.get().getRemainingResources() > 0) { 
            nextResource = LoadingList.get().getNext(); 
        } else { 
            loading = false;
            inMenu = true;
            LoadingList.setDeferredLoading(false);
            menu.startintro();
            sound.play("menu1");
        }
	}
	
}
