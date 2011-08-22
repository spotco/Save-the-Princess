package levels;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level5 extends Level {
	public static final String thislevelsong = "main1";
	public String name() {
		return "are u a wizard";
	}
	
	public void init() {
		mapsong = thislevelsong;
		storedmap = new TiledMap[1][3];
		try {
			storedmap[0][0] = new TiledMap("data//level5(0-0).tmx","art");
			storedmap[0][1] = new TiledMap("data//level5(0-1).tmx","art");
			storedmap[0][2] = new TiledMap("data//level5(0-2).tmx","art");
		} catch (SlickException e) {
			System.out.println("level5 could not load data");
			e.printStackTrace();
		}
		locationx = 0; locationy = 0;
	}
	
	public void createmasterlist() {
		createmasterlist(1,3);
	}

}
