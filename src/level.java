import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class level {  //Chakhon
	Random r = new Random();
	int width = 40;
	int height = 25;
	
	level()throws java.io.IOException{
		createLevel();
		levelPolish("Lv1.txt", "Lv2.txt", 2);
		levelPolish("Lv2.txt", "Lv1.txt", 3);
		levelPolish("Lv1.txt", "Lv2.txt", 4);
		loadLevel("Lv2.txt");
	}
	
	void createLevel()throws java.io.IOException {
		FileWriter fw = new FileWriter("Lv1.txt");
		int charCheck = 0;
		char lineCheck[] = new char[width];
		char randomTile[] = new char[]{'g', 'b', 'w'};
		int tileChance[] = new int[20];
		int tileChance1[] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2};
		int tileChance2[] = new int[] {2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
		int tileChance3[] = new int[] {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
		for (int x = 0; x < height; x++) {
			char lastChar = ' ';
			char tile = ' ';
			for (int y = 0; y < width; y++) {
				switch(x) {
					case 0: 
						tile = 'e';
						break;
					case 24:
						tile = 'e';
						break;
					default:
						switch(y) {
						case 0: 
							tile = 'e';
							break;
						case 39:
							tile = 'e';
							break;
						default:
							if (lineCheck[y] == 'w' && lastChar == 'w') {
								tileChance = tileChance3;
							}
							else if (lineCheck[y] == 'w') {
								tileChance = tileChance2;
							}
						    else if (lastChar == 'w') {
								tileChance = tileChance2;
							}
							else {
								tileChance = tileChance1;
							}
							tile = randomTile[tileChance[r.nextInt(20)+0]];
							break;
						}
					break;
				}
				lastChar = tile;
				fw.write(tile);
				lineCheck[charCheck] = tile;
				charCheck++;
				if (y == 39) {
					fw.write('\n');
					charCheck = 0;
				}
			}
		}
		fw.close();
	}
	void levelPolish(String fileFrom, String fileTo, int req) throws java.io.IOException {
		FileReader fr = new FileReader(fileFrom);
		Scanner fileScanner = new Scanner(fr);
		FileWriter fw = new FileWriter(fileTo);
		String lineLoad[] = new String[3];
		lineLoad[0] = fileScanner.next();
		lineLoad[1] = fileScanner.next();
		lineLoad[2] = fileScanner.next();
		for (int i = 0; i < width; i++) {
			fw.write('e');
		}
		fw.write('\n');
		for (int line = 0; line < height-2; line++) {
			for (int letter = 0; letter < width; letter++) {
				int wCount = 0;
				if (lineLoad[1].charAt(letter) == 'w') {
					if (lineLoad[1].charAt(letter-1) != 'w') {wCount++;}
					if (lineLoad[1].charAt(letter+1) != 'w') {wCount++;}
					if (lineLoad[0].charAt(letter) != 'w') {wCount++;}
					if (lineLoad[2].charAt(letter) != 'w') {wCount++;}
					if (wCount >= req) {
						fw.write('g');
					}
					else {
						fw.write('w');
					}
				}
				else if (lineLoad[1].charAt(letter) != 'w') {
					fw.write(lineLoad[1].charAt(letter));
				}
				if (letter == 39) {
					fw.write('\n');
					if (line < 22) {
						lineLoad[0] = lineLoad[1];
						lineLoad[1] = lineLoad[2];
						lineLoad[2] = fileScanner.next();
					}
				}
			}
		}
		for (int i = 0; i < width; i++) {
			fw.write('e');
		}
		fw.write('\n');
		fileScanner.close();
		fw.close();
	}
	void loadLevel(String fileName) throws java.io.IOException {
		FileReader fr = new FileReader(fileName);
		Scanner fileScanner = new Scanner(fr);
		String inputText;
		
		EZ.setFrameRate(1000);
		EZ.setBackgroundColor(new Color(0, 0,0));
		EZImage mypics[] = new EZImage[10000];
		int i = 0;
		for(int row = 0; row < height; row++){
			inputText = fileScanner.next();
			System.out.println(inputText);// Read a whole line of text
			for (int column = 0; column < width; column++){
				char ch = inputText.charAt(column);   // Retrieve the character at position "column" of the inputText.
				if (ch == 'g'){
					mypics[i] = EZ.addImage("resources/g.png",column*32,row*32);
				} 
				else if (ch == 'b'){
					mypics[i] = EZ.addImage("resources/t.png",column*32,row*32);
				} 
				else if (ch == 'w'){
						mypics[i] = EZ.addImage("resources/w.png",column*32,row*32);
				}
				i++;
			}
		}
	fileScanner.close();
	EZ.setFrameRate(60);
	}
}
