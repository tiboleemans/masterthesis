import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class WriteExpansionRules {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// ArrayList<Tag> tags = new ArrayList<Tag>();
		
		// Construction of background elements

		Background bg = new Background();
		
		try {
			PrintWriter writer = new PrintWriter("/home/thibault/Documents/claudienOut.txt");
			PrintWriter penaltyCost = new PrintWriter("/home/thibault/Documents/penaltyCost.txt");
			BufferedReader in = new BufferedReader(new FileReader(
					"/home/thibault/Documents/claudien.txt"));
			String str;
			penaltyCost.write("penaltyCost = {");
			int ruleNr = 0;
			while ((str = in.readLine()) != null) {
				
				double penalty = 1.0;
				String[] input = str.split("]|->|/\\\\.|\\\\/");
				for(int i = 1; i < input.length; i++){
					penalty *= bg.getPenaltyElement(input[i]);
				}
				int inputLength  = (int) Character.getNumericValue(input[0].charAt(1));
				penalty *= (double) 1/inputLength;
				
				String[] auxString = str.split("]");
				String auxString2 = auxString[1].replace("->", ") => (");
				auxString2 = auxString2.replace("/\\", "&");
				auxString2 = auxString2.replace("\\/", "|");
				if(auxString2.contains("4")){
					auxString2 = "~( !V, W, X, Y, Z : (".concat(auxString2);
					auxString2 = auxString2.replace("0", "V");
					auxString2 = auxString2.replace("1", "W");
					auxString2 = auxString2.replace("2", "X");
					auxString2 = auxString2.replace("3", "Y");
					auxString2 = auxString2.replace("4", "Z");

				}
				else if(auxString2.contains("3")){
					auxString2 = "~( !W, X, Y, Z : (".concat(auxString2);
					auxString2 = auxString2.replace("0", "W");
					auxString2 = auxString2.replace("1", "X");
					auxString2 = auxString2.replace("2", "Y");
					auxString2 = auxString2.replace("3", "Z");
				}
				else if(auxString2.contains("2")){
					auxString2 = "~( !X, Y, Z : (".concat(auxString2);
					auxString2 = auxString2.replace("0", "X");
					auxString2 = auxString2.replace("1", "Y");
					auxString2 = auxString2.replace("2", "Z");
				}
				else if(auxString2.contains("1")){
					auxString2 = "~( !X, Y : (".concat(auxString2);
					auxString2 = auxString2.replace("0", "X");
					auxString2 = auxString2.replace("1", "Y");
				}
				else if(auxString2.contains("0")){
					auxString2 = "~( !X : (".concat(auxString2);
					auxString2 = auxString2.replace("0", "X");
				}
				else
					auxString2 = "~((".concat(auxString2);
				
				auxString2 = auxString2.concat(")).");
				int penaltyInt = (int) (penalty * 100);
				String penaltyString = "penalty(" + ruleNr + ") <- ";
				String result = penaltyString.concat(auxString2);
								
				writer.println(result);
				penaltyCost.println(ruleNr + "->" + penaltyInt + ";");
				ruleNr++;

			}
			penaltyCost.println("}");
			in.close();
			writer.close();
			penaltyCost.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}

	}

}
