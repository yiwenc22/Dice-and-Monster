import java.awt.Color;
import java.io.IOException;

public class main { //Yiwen
void game1() throws IOException {
	level L = new level();
	mapPlayer M = new mapPlayer();
	Monster m = new Monster();
	String x = "knights.png";
	String g = "knight.png";
	hero h = new hero();
	M.spawnPlayer(x);
	M.spawncombatitem();
	boolean gg = true;
	while(gg) {
		M.roll();
		M.attack(0);
		M.defend(2);
		M.movePlayer(g);
		if (M.itstime<=0) {
			EZ.removeAllEZElements();
			EZ.addText(628, 400, ""+M.kills, Color.WHITE,45);
			M.delay2(5000);
		
		}
		
		EZ.refreshScreen();
}
}
void game2() throws IOException {
	level L = new level();
	mapPlayer M = new mapPlayer();
	Monster m = new Monster();
	String x = "resources/player.png";
	String g = "monk_1.png";
	hero h = new hero();
	M.spawnPlayer(x);
	M.spawncombatitem();
	boolean gg = true;
	while(gg) {
		M.roll();
		M.attack(0);
		M.defend(0);
		M.movePlayer(g);
		if (M.itstime<=0) {
			EZ.removeAllEZElements();
			EZ.addText(628, 400, ""+M.kills, Color.WHITE,45);
			M.delay2(5000);
			gg=false;
		}
		
		EZ.refreshScreen();
}
}
void game3() throws IOException {
	level L = new level();
	mapPlayer M = new mapPlayer();
	Monster m = new Monster();
	String x = "vikings.png";
	String g = "viking.png";
	hero h = new hero();
	M.spawnPlayer(x);
	M.spawncombatitem();
	boolean gg = true;
	while(gg) {
		M.roll();
		M.attack(0);
		M.defend(0);
		M.movePlayer(g);
		if (M.itstime<=0) {
			EZ.removeAllEZElements();
			EZ.addText(628, 400, ""+M.kills, Color.WHITE,45);
			M.delay2(5000);
			gg=false;
		}
		
		EZ.refreshScreen();
}
}
	public static void main(String[] args) throws IOException {
		EZ.initialize(1248,768);
		EZImage start =EZ.addImage("startpage.png", 624, 384);
		main g = new main();
		EZImage champselect= EZ.addImage("heroselect.png", 624, 384);
		int x =0;
		champselect.hide();
		
		while(true) {
			
			if(EZInteraction.wasKeyReleased(' ')) {
				start.hide();
				 champselect.show();
				 x++;
			}
			 
		if (EZInteraction.wasKeyPressed('1')&&x>=1) {
			g.game1();
			champselect.hide();
		}
		else if (EZInteraction.wasKeyPressed('2')&&x>=1) {
			g.game2();
			champselect.hide();
		}
		else if (EZInteraction.wasKeyPressed('3')&&x>=1) {
			g.game3();
			champselect.hide();
		}
			EZ.refreshScreen();
		}
	

}
	}

