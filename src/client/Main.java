package client;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	static char key;
	KeyboarbInteraction obj;
	
	//static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static Scanner reader = new Scanner(System.in);
	
	public static void main(String [] args) throws IOException 
	{
		do 
		{
			System.out.println("Digite uma letra:");
			key = reader.next().charAt(0);
			
			switch(key)
			{
				case 'q':
					break;
				case 'l':
					System.out.println("slap_left");
					break;
				case 'r':
					System.out.println("slap_right");
					break;
				case 't':
					System.out.println("slap_top");
					break;
				case 'b':
					System.out.println("slap_bottom");
					break;
				default:
					System.out.println("Hand gesture not recognized!");
					break;	
			} 
		} while (key != 'q');
	}

}
