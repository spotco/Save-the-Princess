package levels;

import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import Player.Player;

import other.*;
import save_the_princess.ListContainer;
import enemy.*;



public class Level {
	
	public String name() {
		return null;
	}
	
	public TiledMap[][] storedmap;
	public TiledMap currentmap;
	public int locationx,locationy;
	public String mapsong;
	
	public void init() {}
	
	public ListContainer[][] masterlist; //stores statuses of all screens, default is 1x1 screen
	
	public void createmasterlist() {
		createmasterlist(1,1);
	}
	
	public void createmasterlist(int sx, int sy) {
		masterlist = new ListContainer[sx][sy];
		int stolocationx = locationx;
		int stolocationy = locationy;
		for(int i = 0; i < sx; i++) {
			for(int j = 0; j < sy; j++) {
				locationx = i; locationy = j;
				ListContainer sto = new ListContainer();
				sto.staticslist = createstatics();
				sto.enemylist = createenemys();
				sto.objectlist = createobjects();
				masterlist[i][j] = sto;
			}
		}
		locationx = stolocationx;
		locationy = stolocationy;
	}
	

	public ArrayList<Rectangle> createstatics() {
		TiledMap temp = storedmap[locationx][locationy];
		ArrayList<Rectangle> stolist = new ArrayList<Rectangle>(); 
		for(int y = 0;y < temp.getHeight();y++) {
			for(int x = 0;x < temp.getWidth();x++) {
				if (temp.getTileProperty(temp.getTileId(x,y,0),"wall","false").equals("true")) {
					stolist.add(new Rectangle((x*25),(y*25),25,25));
				}
			}
		}
		return stolist;
	}
	
	public ArrayList<Enemy> createenemys() {
		TiledMap temp = storedmap[locationx][locationy];
		ArrayList<Enemy> stolist = new ArrayList<Enemy>(); 
		for(int y = 0;y < temp.getHeight();y++) { 
			for(int x = 0;x < temp.getWidth();x++) {
				//dog creator
				//0 - DOWN, 1 - RIGHT, 2 - LEFT, 3 - UP
				if (temp.getTileProperty(temp.getTileId(x,y,0),"dog","false").equals("down")) {
					stolist.add(new Dog(0,x*25,y*25));
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"dog","false").equals("left")) {
					stolist.add(new Dog(2,x*25,y*25));
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"dog","false").equals("right")) {
					stolist.add(new Dog(1,x*25,y*25));
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"dog","false").equals("up")) {
					stolist.add(new Dog(3,x*25,y*25));
				}
				
				//guard creator
				//0 - DOWN, 1 - RIGHT, 2 - LEFT, 3 - UP
				if (temp.getTileProperty(temp.getTileId(x,y,0),"guardspawn","false").equals("down")) {
					stolist.add(new Guard(0,x*25,y*25));
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"guardspawn","false").equals("left")) {
					stolist.add(new Guard(2,x*25,y*25));
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"guardspawn","false").equals("right")) {
					stolist.add(new Guard(1,x*25,y*25));
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"guardspawn","false").equals("up")) {
					stolist.add(new Guard(3,x*25,y*25));
				}
				
				//wizard creator
				//0DOWN 1RIGHT 2LEFT 3UP
				if (temp.getTileProperty(temp.getTileId(x,y,0),"wizard","false").equals("up")) {
					stolist.add(new Wizard(x*25,y*25,3));
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"wizard","false").equals("down")) {
					stolist.add(new Wizard(x*25,y*25,0));
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"wizard","false").equals("left")) {
					stolist.add(new Wizard(x*25,y*25,2));
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"wizard","false").equals("right")) {
					stolist.add(new Wizard(x*25,y*25,1));
				}
				
				//knightboss creator
				if (temp.getTileProperty(temp.getTileId(x,y,0),"knightboss","false").equals("true")) {
					stolist.add(new KnightBoss(x*25,y*25,x,y,false));
				}
			}
		}
		return stolist;
	}
	
	public Player createplayer() {
		Player retme = null;
		TiledMap temp = storedmap[locationx][locationy];
		for(int y = 0;y < temp.getHeight();y++) {
			for(int x = 0;x < temp.getWidth();x++) {
				if (temp.getTileProperty(temp.getTileId(x,y,0),"player","false").equals("true")) {
					retme = new Player(x*25,y*25);
					break;
				}
			}
		}
		return retme;
	}
	
	public ArrayList<Other> createobjects() {
		TiledMap temp = storedmap[locationx][locationy];
		ArrayList<Other> stolist = new ArrayList<Other>(); 
		
		for(int y = 0;y < temp.getHeight();y++) { 
			for(int x = 0;x < temp.getWidth();x++) {
				if (temp.getTileProperty(temp.getTileId(x,y,0),"guardpoint","false").equals("down")) { //guardpath creator
					if (temp.getTileProperty(temp.getTileId(x,y,0),"stop","false").equals("true")) {
						stolist.add(new GuardPath(0,x*25,y*25,true));
					} else {
						stolist.add(new GuardPath(0,x*25,y*25,false));
					}
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"guardpoint","false").equals("left")) { //guardpath creator
					if (temp.getTileProperty(temp.getTileId(x,y,0),"stop","false").equals("true")) {
						stolist.add(new GuardPath(2,x*25,y*25,true));
					} else {
						stolist.add(new GuardPath(2,x*25,y*25,false));
					}
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"guardpoint","false").equals("right")) { //guardpath creator
					if (temp.getTileProperty(temp.getTileId(x,y,0),"stop","false").equals("true")) {
						stolist.add(new GuardPath(1,x*25,y*25,true));
					} else {
						stolist.add(new GuardPath(1,x*25,y*25,false));
					}
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"guardpoint","false").equals("up")) { //guardpath creator
					if (temp.getTileProperty(temp.getTileId(x,y,0),"stop","false").equals("true")) {
						stolist.add(new GuardPath(3,x*25,y*25,true));
					} else {
						stolist.add(new GuardPath(3,x*25,y*25,false));
					}
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"princess","false").equals("true")) {
					stolist.add(new Princess(x*25,y*25)); //princess creator
				}
				
				if (temp.getTileProperty(temp.getTileId(x,y,0),"cratespawn","false").equals("true")) {
					stolist.add(new Crate(x*25,y*25));//crate creator
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"door","false").equals("closed")) {
					stolist.add(new Door(true,x*25,y*25));  //door creator
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"door","false").equals("open")) {
					stolist.add(new Door(false,x*25,y*25));  //door creator
				}
				if (temp.getTileProperty(temp.getTileId(x,y,0),"doorbutton","false").equals("true")) { //doorbuttoncreator
					stolist.add(new DoorButton(x*25,y*25));
				}
				
				if (temp.getTileProperty(temp.getTileId(x,y,0),"key","false").equals("true")) { //key creator
					stolist.add(new Key(x*25,y*25));
				}
				
				if (temp.getTileProperty(temp.getTileId(x,y,0),"keydoor","false").equals("true")) { //keydoor creator
					stolist.add(new KeyDoor(x*25,y*25));
				}
				
				if (temp.getTileProperty(temp.getTileId(x,y,0),"exit","false").equals("true")) { //exit creator
					stolist.add(new Exit(x*25,y*25,temp.getTileProperty(temp.getTileId(x,y,0),"direction","null") ));
				}
				
				if (temp.getTileProperty(temp.getTileId(x,y,0),"window","false").equals("true")) { //window creator
					stolist.add(new Window(x*25,y*25));
				}
				
				if (temp.getTileProperty(temp.getTileId(x,y,0),"torch","false").equals("true")) { //window creator
					stolist.add(new Torch(x*25,y*25));
				}
				
				if (temp.getTileProperty(temp.getTileId(x,y,0),"tracker","false").equals("true")) { //tracker creator
					stolist.add(new Tracker());
				}
				
				if (temp.getTileProperty(temp.getTileId(x,y,0),"bossactivate","false").equals("true")) { //bossactivate creator
					stolist.add(new KnightBossInitialActivate(x*25,y*25));
				}
				
				if (temp.getTileProperty(temp.getTileId(x,y,0),"bossactivatespawn","false").equals("true")) { //bossactivate spawn creator
					stolist.add(new KnightBossSpawn(x*25,y*25));
				}
				
				if (temp.getTileProperty(temp.getTileId(x,y,0),"final","false").equals("true")) { //finalcutscene stepon
					stolist.add(new FinalCutscene(x*25,y*25));
				}
			}
		}
		return stolist;
	}
	
}
