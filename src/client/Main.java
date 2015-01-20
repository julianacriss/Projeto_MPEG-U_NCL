package client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

import scpto.ScpTo;

public class Main {
	
	static char key;
	static Timestamp timestamp;
	static Scanner reader;
	static KeyboarbInteraction obj;
	
	static BufferedWriter writer;
	
	public static void main(String [] args) throws IOException 
	{
		reader = new Scanner(System.in);
		//writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("mpeg-u.xml")));
		
		
		File file2 = new File("C:\\Users\\juliana.andrade\\workspace\\MPEG-U-e-NCL-v2\\mpeg-u.xml");
		
		do 
		{
			FileWriter file = new FileWriter("mpeg-u.xml");
			PrintWriter writer = new PrintWriter(file); 
			
			System.out.println("Digite uma letra:");
			key = reader.next().charAt(0);
			timestamp = new Timestamp(System.currentTimeMillis());
			ScpTo objSend = new ScpTo("mpeg-u.xml", "root", "10.7.7.7", "/misc/ncl30/mpeg-u.xml");
			
			switch(key)
			{
				case 'q':
					writer.close();
					System.out.println(file2.getAbsolutePath());
					file2.delete();
					break;
				case 'l':
					System.out.println("slap_left");
					obj = new KeyboarbInteraction("slap_left", timestamp);
					writer.printf(obj.getDataFormat());
					file.close();
					objSend.send();
					break;
				case 'r':
					System.out.println("slap_right");
					obj = new KeyboarbInteraction("slap_right", timestamp);
					writer.printf(obj.getDataFormat());
					file.close();
					objSend.send();
					break;
				case 'b':
					System.out.println("slap_bottom");
					obj = new KeyboarbInteraction("slap_bottom", timestamp);
					writer.printf(obj.getDataFormat());
					file.close();
					objSend.send();
					break;
				case 't':
					System.out.println("slap_top");
					obj = new KeyboarbInteraction("slap_top", timestamp);
					writer.printf(obj.getDataFormat());
					file.close();
					objSend.send();
					break;
				default:
					System.out.println("Hand gesture not recognized!");
					break;	
			} 
		} while (key != 'q');	
	}

}
