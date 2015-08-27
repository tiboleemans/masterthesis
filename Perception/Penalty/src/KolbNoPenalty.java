import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class KolbNoPenalty {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			PrintWriter writer = new PrintWriter("/home/thibault/Documents/kolbOut.txt");
			BufferedReader in = new BufferedReader(new FileReader(
					"/home/thibault/Documents/kolb.txt"));
			String str;
			while ((str = in.readLine()) != null) {
								
				String[] auxString = str.split("]");
				String auxString2 = auxString[1].replace("=>", ") => (");

				if(auxString2.contains("2")){
					auxString2 = "!X, Y, Z : (".concat(auxString2);
					auxString2 = auxString2.replace("0", "X");
					auxString2 = auxString2.replace("1", "Y");
					auxString2 = auxString2.replace("2", "Z");
				}
				else if(auxString2.contains("1")){
					auxString2 = "!X, Y : (".concat(auxString2);
					auxString2 = auxString2.replace("0", "X");
					auxString2 = auxString2.replace("1", "Y");
				}
				else if(auxString2.contains("0")){
					auxString2 = "!X : (".concat(auxString2);
					auxString2 = auxString2.replace("0", "X");
				}
				else
					auxString2 = "(".concat(auxString2);
				
				String result = auxString2.concat(").");
				
				writer.println(result);
			}
			in.close();
			writer.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}

	}

}
