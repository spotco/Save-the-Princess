package save_the_princess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TimerTask;

public class TimerCounter extends TimerTask {
	public static int Level1 = 4595;
	public static int Level2 = 5439;
	public static int Level3 = 5857;
	public static int Level4 = 24093;
	public static int Level5 = 19102;
	public static int Level6 = 12583;
	//abs is in centiseconds btw lol
	public long abs;
	public long tenms;
	public long sec;
	public long min;
	
	public Map<String,Long> stats;
	
	public TimerCounter(String levelname) {
		Scanner temp = null;
		stats = new HashMap<String,Long>();
		try {
			temp = new Scanner(new File("data\\times.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(temp.hasNext()) {
			stats.put(temp.next(), Long.parseLong(temp.next()));
		}
		
		/*for (Map.Entry<String, Long> me : stats.entrySet()) {
			System.out.println(me.getKey()+","+me.getValue());
		}*/
	}

	public void run() {
		tenms++;
		abs++;
		if (tenms == 100) {
			tenms = 0;
			sec++;
		}
		if (sec == 60) {
			sec = 0;
			min++;
		}
		//System.out.println(abs);
		//System.out.println("TIME:"+min+":"+sec+":"+tenms);
	}
	
	public void writetime(String name,long time) {
		if(stats.containsKey(name)) {
			if (time < stats.get(name)) {
				stats.put(name, time);
				PrintStream test = null;
				try {
					test = new PrintStream(new File("data\\times.dat"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				for (Map.Entry<String, Long> me : stats.entrySet()) {
					test.println(me.getKey()+" "+me.getValue());
				}
				
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public String gettime(String name) {
		long sto = stats.get(name);
		int tensec = (int)sto%100;
		int sec = (int)(sto%6000)/100;
		int min = (int)(sto/(100*60));
		String secc = ""+sec;
		String tensecc = ""+tensec;
		if (sec < 10) {
			secc = "0"+secc;
		}
		if (tensec < 10) {
			tensecc = "0"+tensecc;
		}
		return min+":"+secc+":"+tensecc;
	}
	
	public long gettimeraw(String name) {
		return stats.get(name);
	}
	
	public String getCurTime() {
		String minc = ""+this.min;
		String secc = ""+this.sec;
		String tensecc = ""+this.tenms;
		if (sec < 10) {
			secc = "0"+secc;
		}
		if (tenms < 10) {
			tensecc = "0"+tensecc;
		}
		return minc+":"+secc+":"+tensecc;
	}
	
	public int getRecTime(String q) {
		if (q.equals("Level1")) {
			return Level1;
		} else if (q.equals("Level2")) {
			return Level2;
		} else if (q.equals("Level3")) {
			return Level3;
		} else if (q.equals("Level4")) { 
			return Level4;
		} else if (q.equals("Level5")) {
			return Level5;
		} else if (q.equals("Level6")) {
			return Level6;
		} else {
			return 999999;
		}
	}
}
