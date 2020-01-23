import java.awt.Color;
import java.awt.color.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
public class Monster { //Yiwen
int basehealth = 500;
int maxhealth =100;
int healthlable = 100;
int damge;
int spawn;
EZRectangle hp;
EZText hpnum;
EZRectangle hpbar;
Random r = new Random();
EZImage picture;
EZText damges;
EZText name;


public void spawn(int gg) { //Yiwen  //TO spawn monster randomly
	spawn = gg;
	
	switch (spawn) {
	case 1:
		healthamount(100,100);
		picture("monster (1).png", 1000, 208,1);
		Myname("Mr. N",125);
		
		damge=damge(r.nextInt(6));
		break;
	case 2:
		healthamount(50,50);picture("dog (1).png",  1008, 225,1);
		Myname("DOG",125);
		
		damge=damge(r.nextInt(15)+1);
		break;
	case 3:
		healthamount(30,30);picture("wasp.png",  1010, 215,1.8);
		Myname("BBEE",125);
		
		damge=damge(r.nextInt(3));
		break;
	case 4:
		healthamount(100,100);picture("tree.png", 1008, 216,2);
		Myname("Tree Sprit",150);
		
		damge=damge(5);
		break;
	case 5:
		healthamount(70,70);picture("pixil-frame-0 (14).png", 985, 195,0.55);
		Myname("Ugly Worm",160);
		
		damge=damge(5);
		break;
	case 6:
		healthamount(40,40);picture("boss1.png", 1008, 225, 0.8 );
		Myname("Green Slime",145);
		
		damge=	damge(0);
		break;
	case 7:
		healthamount(200,200);picture("1.png", 1008, 215,2.5);
		Myname("Big Boy",140);
		
		damge=damge(r.nextInt(19)+1);
		break;
	default:
		
		break;
	}
	
}
void healthamount(int g, int e) { //Yiwen  //Set health amount for monster
	basehealth =(e*5);
	healthlable = g;
	maxhealth = e;
	
}
int damge(int exta) {  //Yiwen   //Show damge and return damge
	int damge = 6+exta;
	damges = EZ.addText(1008, 325, ""+damge, Color.RED,45);
	return damge;
}


void healthbar(){ //Yiwen  //ADD monster hp and healh bar
	hpbar = EZ.addRectangle((320-((500-basehealth)/2)), 100, basehealth+10, 40, Color.BLACK, true);
	hp = EZ.addRectangle((320-((500-basehealth)/2)), 100, basehealth, 30, Color.RED, true);
	
}


void Myname(String x, int y) { //Yiwen //add name to the monster
	name = EZ.addText(y, 60, x, Color.BLACK,35);
}
void picture(String name, int x ,int y, double s) { //Yiwen //add picture to combat place and set size
	picture = EZ.addImage(name, x, y);
	picture.scaleTo(s);
	
	
}
}