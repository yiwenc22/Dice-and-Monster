import java.util.Random;

public class Dice { //Created by Chakhon , edited by Yiwen
	Random r = new Random();
	EZImage diceFaces;
	int num = 0;
	int k = 2;
	int g = 1;
	hero x= new hero();
	Monster m = new Monster();
	
 Dice(int x, int y){
	diceFaces = EZ.addImage("dicepics/dice.png",x,y);
 	diceFaces.setFocus(8*46,4*46,9*46,5*46);
	}
 
 void stop() {
	 if (EZInteraction.wasKeyReleased(' ')&& g==0&& k==0){
		 k+=1;
		 g=2;
		 System.out.println(k);
	 }
 }
 void roll() {
	if (EZInteraction.isKeyDown(' ')&&k==0) {
		
		int picX = r.nextInt(15)+1;
		int picY = r.nextInt(6)+2;
		diceFaces.setFocus((picX-1)*46,(picY-1)*46,picX*46,picY*46);
	}
	if (EZInteraction.wasKeyReleased(' ') && k==0){
		num = r.nextInt(6)+1;
		
		switch(num) {
		case 1:
			diceFaces.setFocus(0,4*46,46,5*46);
			break;
		case 2:
			diceFaces.setFocus(4*46,4*46,5*46,5*46);
			break;
		case 3:
			diceFaces.setFocus(0,8*46,46,9*46);
			break;
		case 4:
			diceFaces.setFocus(0,0,46,46);
			break;
		case 5:
			diceFaces.setFocus(12*46,4*46,13*46,5*46);
			break;
		case 6:
			diceFaces.setFocus(8*46,4*46,9*46,5*46);
			break;
		}
	}
 }
}
