package save_the_princess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveReader {
	private String savepath;
	private ArrayList<String> levellist;
	
	private String clvlload;
	
	public SaveReader(String savepath,String levelseqpath) {
		this.savepath = savepath;
		Scanner savereader = null;
		Scanner levelseqreader = null;
		try {
			savereader = new Scanner(new File(savepath));
			levelseqreader = new Scanner(new File(levelseqpath));
		} catch (FileNotFoundException e) {
			System.out.println("save/levelpath file not found FUCK");
		}
		savereader.next();
		clvlload = savereader.next();
		loadlevelseq(levelseqreader);
	}
	
	private int clvl;
	public void newGame() {
		clvl = 0;
	}
	
	public void loadGame() {
		clvl = -1;
		for(int i = 0; i < levellist.size(); i++) {
			if (levellist.get(i).equals(clvlload)) {
				clvl = i;
			}
		}
	}
	
	public String getCurrentLevel() {
		return levellist.get(clvl);
	}
	
	public void nextLevel() {
		clvl++;
	}
	
	public void loadlevelseq(Scanner levelseqreader) {
		levellist = new ArrayList<String>();
		while(levelseqreader.hasNext()) {
			String foo = levelseqreader.next();
			if (foo.equals("LEVEL:")) {
				levellist.add(levelseqreader.next());
			} else if (foo.equals("End")) {
				levellist.add("End");
				break;
			} else {
				System.out.println("level reading error!:" +foo);
				break;
			}
		}
	}
	
	public String getSaveCurrent() {
		return clvlload;
	}
	
	public void writeSaveCurrent(String clvl) {
		PrintStream savewriter = null;
		try {
			savewriter = new PrintStream(new File(savepath));
		} catch (FileNotFoundException e) {
			System.out.println("couldnt write save :(");
		}
		savewriter.println("CUR: "+clvl);
	}
	
}
