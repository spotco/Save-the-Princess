package save_the_princess;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import levels.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import Player.Player;

import other.Other;
import other.Tracker;

import enemy.Enemy;
import enemy.KnightBoss;

public class STPGame {
	
	public final boolean DISPLAYHITBOXES = false;
	
	public STPView display;
	public Level loadedlevel;
	
	public ArrayList<Rectangle> staticslist;
	public ArrayList<Enemy> enemylist;
	public ArrayList<Other> objectlist;
	
	public boolean seeme;
	private int seemecounter;
	
	public Player player;
	public STPGame(STPView display) {
		this.display = display;
	}
	
	public Timer timer;
	public TimerCounter timercounter;
	
	public void loadlevel() {		
		loadedlevel.locationx = 0; loadedlevel.locationy = 0;
		loadedlevel.init();
		player = loadedlevel.createplayer();
		loadedlevel.createmasterlist();
		
		staticslist = loadedlevel.masterlist[0][0].staticslist;
		enemylist = loadedlevel.masterlist[0][0].enemylist;
		objectlist = loadedlevel.masterlist[0][0].objectlist;
		
		display.sound.play(loadedlevel.mapsong);
		System.out.println("LOADED:"+display.save.getCurrentLevel()+" \""+loadedlevel.name()+"\"");
		seeme = true;
		seemecounter = 26;
		
		timer = new Timer();
		timercounter = new TimerCounter(display.save.getCurrentLevel());
		timer.schedule(timercounter,10,10);
	}
	
	public void changeloc() {
		staticslist = loadedlevel.masterlist[loadedlevel.locationx][loadedlevel.locationy].staticslist;
		enemylist = loadedlevel.masterlist[loadedlevel.locationx][loadedlevel.locationy].enemylist;
		objectlist = loadedlevel.masterlist[loadedlevel.locationx][loadedlevel.locationy].objectlist;
	}
	
	public void update(GameContainer frame) {
		player.update(this,frame,staticslist,objectlist);
		//updates
		for (int i = 0; i < enemylist.size(); i++) {
			enemylist.get(i).update(this);
		}

		for (int i = 0; i < objectlist.size(); i++) {
			objectlist.get(i).update(this);
		}
		//check collisions
		for (Enemy e : enemylist) {
			if (e.hitbox.intersects(player.hitbox)) {
				timer.cancel();
				display.animations.startAnimation("deathAnimation",e.killerimg);
			}
		}
		//other hit effects
		for (Other o : objectlist) {
			if (o.hitbox.intersects(player.hitbox)) {
				o.hit(display);
			}
		}
		
		if(seeme) seemecounter++;
		if(seemecounter > 50) seemecounter = 0;
		
		
	}
	
	public void render(GameContainer frame,Graphics g) {
		loadedlevel.storedmap[loadedlevel.locationx][loadedlevel.locationy].render(0, 0);
		if (DISPLAYHITBOXES) { //you heard the man
			g.setColor(Color.white);
			for (Rectangle element:staticslist) g.draw(element);
			g.setColor(Color.red);
			for (Enemy e : enemylist) g.draw(e.hitbox);
			g.setColor(Color.yellow);
			for (Other o : objectlist) g.draw(o.hitbox);
			g.setColor(Color.blue);
			g.draw(player.hitbox);
		}
		for (Other o : objectlist) {
			o.render();
		}
		player.render();
		for (Enemy e : enemylist) {
			e.render();
		}
		if (this.seeme && seemecounter < 25) display.menu.seeme.draw(player.x-56,player.y-65);
		
		if(seeme) {
			for (Other o : objectlist) {
				if (o.type() == 23) {
					display.menu.goal.draw(o.x-52,o.y-78);
					break;
				}
			}
		}
		
		g.setColor(Color.yellow);
		if (timercounter != null) {
			g.drawString(timercounter.getCurTime(), 0, 0);
			g.drawString(timercounter.gettime(display.save.getCurrentLevel()), 0, 13);
		}
		
		/*KnightBoss sto = null;
		for(Enemy e:enemylist) {
			if (e.getType() == 67) {
				sto = (KnightBoss)e;
			}
		}
		
		for(Other o:objectlist) {
			if (o.type() == 67) {
				((Tracker)o).specialRender(g,sto,player);
			}
		}*/

	}

}
