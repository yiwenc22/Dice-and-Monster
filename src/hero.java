import java.awt.Color;
import java.awt.color.*;
import java.util.Timer;
import java.util.TimerTask;
public class hero { //YiWen 
int basehealth = 500;
int healthlable = 100;
int damge;
EZText hpnum;
EZRectangle hp;
EZImage hit; 
EZRectangle hpbar;
EZText lable;

void addhealth() {
	hpbar = EZ.addRectangle(915, 650, 500+10, 40, Color.BLACK, true);
	hp = EZ.addRectangle(915-(damge/2), 650, (basehealth-damge), 30, Color.GREEN, true);
	lable = EZ.addText(900, 600, "YOU", Color.WHITE,45);
	hpbar.hide();
	hp.hide();
	lable.hide();
}

	public void health(){
		hpbar.show();
		hp.show();
		lable.show();
		hpnum.show();
	
		

}
	
	void ok() {
		hpnum.setMsg(healthlable+"/ 100");
	}
			
		
	}


