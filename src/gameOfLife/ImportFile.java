package gameOfLife;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ImportFile {
	Path filePath;
	boolean[][] parsedArray;
	Scanner in;
	//method constructor, takes String and uses it to import the file
	//file is then scanned and boolean[][] is created from its contents
	//if there is not file or the file is incorrectly formated then an error
	//dialogue box is generated and the program terminates.
	public ImportFile(String path) { 
		filePath= Paths.get(path);
		try {
			in= new Scanner(filePath);
			String input="";
			int lineCount=0;
			while(in.hasNextLine()) {
				input= input.concat(in.nextLine()+" ");
				lineCount++;
			}
			boolean whiteSpace=true;
			int columCount=0;
			while(whiteSpace) {
				if(input.charAt(columCount)==' ') {
					whiteSpace=false;
				}
				else {
					columCount++;
				}
			}
			input=input.replaceAll(" ", "");
			parsedArray=new boolean[lineCount][columCount];
			try {
				if(lineCount*columCount!=input.length()) {
					throw new StringIndexOutOfBoundsException();
				}
				for(int i=0; i<parsedArray.length; i++) {
					for(int j=0; j<parsedArray[0].length; j++) {
						if(input.charAt(i*parsedArray[0].length+j)=='1') {
							parsedArray[i][j]=true;
						}
						else if(input.charAt(i*parsedArray[0].length+j)=='0') {
							parsedArray[i][j]=false;
						}
						else {
							JOptionPane.showMessageDialog(new JFrame(), "Invalid Character In File", "ERROR", JOptionPane.ERROR_MESSAGE);
							System.exit(0);
						}
					}
				}
			}catch(StringIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(new JFrame(), "File Rows Are Not Equal", "ERROR", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "Invalid File", "ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public boolean[][] getArray() {
		return parsedArray;
	}
}
