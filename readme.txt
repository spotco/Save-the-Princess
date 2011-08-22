SAVE THE PRINCESS V1.0 (the first release :D)
legal stuff-feel free to do whatever you want with any of the code/art/music you find here

Anyway, thanks for playing Save the Princess!
It was entirely created in my spare time and is the product of many hours of work.
I hope you enjoy it!  - spotco

Contact me at: mootothemax@gmail.com

Troubleshooting-
If you're running windows, run the game with run_CMD.bat and report to me any errors you find.
This game should also work on Mac OSX/Linux if you go to http://lwjgl.org/ , download the appropriate files and 
place them in the same folder. I haven't been able to
test it extensively. Again, contact me with any problems you have.
If your times aren't loading, try reloading the game.

Save editing:
Open up data\save.dat with your text editor of choice, and change it from
CUR: Level(1-6) to your level of choice. It'll probably crash if you try to do anything funky.

Extras folder:
In the extras folder is the old menu screen (by me as opposed to reeve). Maybe you like it better?
Also two screenshots and concept art by reeve.

Level editing:
1. Download Tiled editor QT here: http://sourceforge.net/projects/tiled/files/tiled-qt/0.5.1/
2. Replace art\guard1set.png, tileset1.png and wizard1set.png with the files in the art\pointers folder.
(Undo this by copying the files from the art\nopointers folder after you're done)
3. Open up the level(1-6).tmx files with tiled, now you're playing with power.
4. A quick rundown of the tiles:

s - player spawn, you need to have one in your map or the game will crash
p - princess, ends current level
c - crate spawn
d - dog, with arrow pointing initial direction
any tile with wall - true as its property will function as a wall.
g - guard spawn with initial direction as arrow
Gp - guard point, a guard entering this tile will go in the direction the door is facing
Dc and Do - metal door/grate, Dc is initially closed and Do is initially open, change state by stepping on:
dB - door button
Gs - guard static, guard that walks into this tile will stop and turn the arrow direction
K - key
KD - keydoor
arrow - exit to another part of the level (standard cartesian coordinates, a map looks like this:
	0,1	1,1
	0,0	1,0 )
W - wizard spawn, initial direction with the arrow
KB - spawns the knight boss that is inactive until BA(no arrow) tile is stepped on.
KB (ds) - spawns an active knight boss when BA(with arrow) tile is stepped on
T - tracker, you need on on the map if you're using knight boss
F - starts the final end game cutscene

Please contact me if you have any questions/comments or have a badass level you want to show.

Website coming soon!:
http://staff.washington.edu/shinyy/