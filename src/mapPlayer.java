import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class mapPlayer { //CHAKHON , Yiwen 
	Random r = new Random();
	char map[][] = new char[30][50];
	int playerX = 0;
	int playerY = 0;
	int movement = 1;
	EZImage player;
	hero x = new hero();
	Monster m = new Monster();
	int kills = 0;
	int sum;
	int heal = r.nextInt(30)+1;
	int itstime = 1;
	int extradamge=0;
	int extradefend=0;
	EZText damge;
	EZImage combat;
	EZImage attackordefend;
	Dice dice1;
	Dice dice2;
	Dice dice3;
	EZImage sheild;
	EZImage hit;
	EZImage boss;
	mapPlayer() throws java.io.IOException {
		mapLocation();
		
		
		
	}
	void mapLocation() throws java.io.IOException {
		FileReader fr = new FileReader("Lv2.txt");
		Scanner fileScanner = new Scanner(fr);
		String lineCheck = "";
		for (int y = 0; y < 25; y++) {
			lineCheck=fileScanner.next();
			for (int x = 0; x < 40; x++) {
				map[y][x] = lineCheck.charAt(x);
			}
		}
	}
	void randLocaton() {
		playerY = r.nextInt(25)+5;
		playerX = r.nextInt(45)+5;
	}
	void spawnPlayer(String x) {
		char charAtP = map[playerY][playerX];
		while(charAtP != 'g') {
			randLocaton();
			charAtP = map[playerY][playerX];
		}
		player = EZ.addImage(x, playerX*32, playerY*32);
		
			
	}
	void spawncombatitem() {  //YiWen //To add everything used in for combat in the main
		EZ.setFrameRate(100000);
		combat = EZ.addImage("bg copy.png", 625, 385);
		combat.hide();
		damge =EZ.addText(625, 420, "MANA : "+sum, Color.YELLOW,25);
		damge.hide();
		attackordefend = EZ.addImage("boxlog.png", 630, 500);
		attackordefend.hide();
		dice1 = new Dice(625,385); 
		dice2 = new Dice(585,385); 
		dice3 = new Dice(665,385);
		x.hpnum = EZ.addText(800, 680, x.healthlable+"/ 100", Color.WHITE,25);
		x.hpnum.hide();
		m.hpnum = EZ.addText(180, 130, m.healthlable+"/ 100", Color.RED,25);
		m.hpnum.hide();
		x.addhealth();
		
		hideall();
		EZ.setFrameRate(60);
	}
	void movePlayer(String g) {
		getsum();
		hpnum();
		char charAtPUp = map[playerY-1][playerX];
		char charAtPDown = map[playerY+1][playerX];
		char charAtPLeft = map[playerY][playerX-1];
		char charAtPRight = map[playerY][playerX+1];
		if (EZInteraction.wasKeyReleased('w') && charAtPUp != 'w' && charAtPUp != 'e'&&movement !=0) {
			playerY-=movement;
			checkBush(g);
		}
		else if (EZInteraction.wasKeyReleased('s') && charAtPDown != 'w' && charAtPDown != 'e'&&movement !=0) {
			playerY+=movement;
			checkBush(g);
		}
		else if (EZInteraction.wasKeyReleased('a') && charAtPLeft != 'w' && charAtPLeft != 'e'&&movement !=0) {
			playerX-=movement;
			checkBush(g);
		}
		else if (EZInteraction.wasKeyReleased('d') && charAtPRight != 'w' && charAtPRight != 'e'&&movement !=0) {
			playerX+=movement;
			checkBush(g);
		}
		player.translateTo(playerX*32, playerY*32);
	}
	void heropostion(String g) {   //Yiwen // creating the hero for combat 
		boss = EZ.addImage(g, 200, 620);
		boss.scaleTo(4);
	}
	void hideall() {    // Yiwen // To hide all dice picture
		dice1.diceFaces.hide();
		dice2.diceFaces.hide();
		dice3.diceFaces.hide();
	}
	void roll() { //Yiwen // Call roll function and stop for all three dice
		
		dice1.roll();dice2.roll();dice3.roll();
		dice1.stop();
		dice2.stop();
		dice3.stop();
	}
	void dicegx(int g, int k) {  // Yiwen //To asigh all three dice g and k to something.
		dice1.g=g;
		dice2.g=g;
		dice3.g=g;
		dice1.k=k;
		dice2.k=k;
		dice3.k=k;
		
	}
	void attack(int bonus){    // Yiwen  // if A pressed calculate all the damge and result with bonus damge
		if(EZInteraction.wasKeyReleased('a')&&dice1.g==2) {
			bonus = bonus+extradamge;
			EZText extra = EZ.addText(705, 420, "+ "+bonus, Color.YELLOW,25);
			if(r.nextInt(2)+1==1) {
				hit.show();
				delay();
				hit.hide();
			x.healthlable=((x.hp.getWidth()/5)-(m.damge));
			m.healthlable=((m.hp.getWidth()/5)-(damgefinder(0)+bonus));
			x.hp.setWidth((x.hp.getWidth()-(m.damge*5)));
			m.hp.setWidth((m.hp.getWidth()-((damgefinder(0)+bonus)*5)));
			x.hp.translateTo((x.hp.getXCenter()-((m.damge*5)/2)),x.hp.getYCenter());
			m.hp.translateTo((m.hp.getXCenter()-(((damgefinder(0)+bonus)*5)/2)),m.hp.getYCenter());
			System.out.println(damgefinder(0));
			System.out.println(x.healthlable);
			extra.hide();
			dicegx(0,0);
			delay();
			reset();
			endgame();
			}
			else if((damgefinder(0)+bonus)-m.damge>=0){
			sheild.show();
			hit.show();
			delay();
			sheild.hide();
			hit.hide();
			x.healthlable=((x.hp.getWidth()/5));
			x.hp.setWidth((x.hp.getWidth()));
			x.hp.translateTo((x.hp.getXCenter()),x.hp.getYCenter());
			m.healthlable=((m.hp.getWidth()/5)-((damgefinder(0)+bonus)-m.damge));
			m.hp.setWidth((m.hp.getWidth()-(((damgefinder(0)+bonus)-m.damge)*5)));
			m.hp.translateTo((m.hp.getXCenter()-((((damgefinder(0)+bonus)-m.damge)*5)/2)),m.hp.getYCenter());
			System.out.println(damgefinder(0));
			System.out.println(x.healthlable);
			extra.hide();
			dicegx(0,0);
			delay();
			reset();
			endgame();
			}
			else {
				sheild.show();
				hit.show();
				delay();
				hit.hide();
				sheild.hide();
				x.healthlable=((x.hp.getWidth()/5));
				x.hp.setWidth((x.hp.getWidth()));
				x.hp.translateTo((x.hp.getXCenter()),x.hp.getYCenter());
				m.healthlable=((m.hp.getWidth()/5)-(0));
				m.hp.setWidth((m.hp.getWidth()-(0)));
				m.hp.translateTo((m.hp.getXCenter()-(0)),m.hp.getYCenter());
				System.out.println(damgefinder(0));
				System.out.println(x.healthlable);
				extra.hide();
				dicegx(0,0);
				delay();
				reset();
				endgame();
			}
	}
	}
	void defend(int bounus) {  //Yiwen //if D pressed caculate defend and damge with bouns defend
		if(EZInteraction.wasKeyReleased('d')&&dice1.g==2) {
			bounus=bounus+extradefend;
			EZText extra2 =EZ.addText(705, 420, "+ "+bounus, Color.YELLOW,25);
			if(r.nextInt(2)+1==2) {
				sheild.show();
				delay();
				sheild.hide();
			x.healthlable=((x.hp.getWidth()/5));
			m.healthlable=((m.hp.getWidth()/5));
			extra2.hide();
			dicegx(0,0);
			delay();
			reset();
			endgame();
			}
			
			else if(m.damge-(damgefinder(0)+bounus)>=0){
				
			x.healthlable=((x.hp.getWidth()/5)-((m.damge)-(damgefinder(0)+bounus)));
			x.hp.setWidth((x.hp.getWidth())-((m.damge-damgefinder(0)+bounus)*5));
			x.hp.translateTo((x.hp.getXCenter()-((((m.damge-(damgefinder(0)+bounus))*5)/2))),x.hp.getYCenter());
			m.healthlable=((m.hp.getWidth()/5));
			m.hp.setWidth((m.hp.getWidth()));
			m.hp.translateTo((m.hp.getXCenter()),m.hp.getYCenter());
			System.out.println(damgefinder(0));
			System.out.println(x.healthlable);
			extra2.hide();
			dicegx(0,0);
			delay();
			reset();
			endgame();
			}
			else {
				sheild.show();
				delay();
				sheild.hide();
				x.healthlable=((x.hp.getWidth()/5));
				m.healthlable=((m.hp.getWidth()/5));
				extra2.hide();
				dicegx(0,0);
				delay();
				reset();
				endgame();
	}
		}
	}
	

	void getsum() {  //Yiwen  // calculate damge and show in screen
		if (EZInteraction.wasKeyReleased(' ')&&movement==0) {
			int sum = dice1.num+dice2.num+dice3.num;
			damge.show();
			damge.setMsg("MANA : "+sum);
			attackordefend.show();
		
			
		}
		}
	void delay() { //Yiwen // delay for 3 ms
		try
		{
		    Thread.sleep(300);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		
	}
	void delay2(int x) {  // Yiwen //delay for given time
		try
		{
		    Thread.sleep(x);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
	void hpnum() {  //Yiwen // add health indcator
		
		x.hpnum.setMsg(x.healthlable+"/ 100");
	
		m.hpnum.setMsg(m.healthlable+"/ "+m.maxhealth);
	}
	int damgefinder(int damge) { //Yiwen // find sum of dice and assign it to damge and return
		int sum = dice1.num+dice2.num+dice3.num;
		damge= sum + damge;
		return damge;
	}
	
	
	public void showdice() { //Yiwen //All three dice picture shows
		
	                	 dice1.diceFaces.show();
	            		dice2.diceFaces.show();
	            		dice3.diceFaces.show();
	            	}
	                
	void reset() {  // Yiwen // remove and hide all combat relative item
		if(m.healthlable<=0) {
			m.hpnum.hide();
			x.healthlable=((x.hp.getWidth()/5)+(heal));
			x.hp.setWidth((x.hp.getWidth()+((heal)*5)));
			x.hp.translateTo((x.hp.getXCenter()+((heal*5)/2)),x.hp.getYCenter());
		dicegx(1,2);
		delay2(1000);
		kills+=1;
		x.hpnum.hide();
		m.hpnum.hide();
		movement=1;
		combat.hide();
		boss.hide();
		x.hpbar.hide();
		x.hp.hide();
		m.hpbar.hide();
		m.hp.hide();
		x.lable.hide();
		x.basehealth=(x.hp.getWidth());
		damge.hide();
		attackordefend.hide();
		m.name.hide();
		m.damges.hide();
		m.picture.hide();
		hideall();
		System.out.println("kills"+kills);
		}
		
	}
	void endgame() {  // Yiwen // check if hero health == 0 if it change its time to 0
		if(x.healthlable<=0) {
			itstime=0;
			
		}
	}
	              
	
	
	void checkBush(String g) { // Yiwen  //If hero is incoming on bush 50 % spawn a monster and set up combat
		if (map[playerY][playerX] == 'b') {
			if (r.nextInt(2)+1 == 1&&movement==1) {
				movement =0;
				combat.show();
				combat.scaleTo(1.5);
				heropostion(g);
				x.health();
				m.spawn(r.nextInt(6)+1);
				delay2(1000);
				x.hpnum.show();
				m.hpnum.show();
				m.healthbar();
				showdice();
				dicegx(0,0);
				EZ.setFrameRate(3000);
				sheild = EZ.addImage("sheild.png", 1008, 220);
				sheild.scaleTo(1.5);
				sheild.hide();
				hit = EZ.addImage("hit.png", 1008, 220);
				hit.hide();
				EZ.setFrameRate(60);
				
				
				
			
				System.out.println("Encounter");
			}
		}
	}
	}




