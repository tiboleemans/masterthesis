import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class CheckerMain {

	/**
	 * @param args
	 */
		
	public static void main(String[] args) {
		int choice = 0;
		String example;
		if(args.length != 0){
			choice = Integer.parseInt(args[0]);
			example = "expansion.idp";
			System.out.println("The model of the expansion will be checked...");
		}else{
			Scanner keyboard = new Scanner(System.in);
			System.out.println("What example do you want to check?");
			System.out.println("Press 1: Good example 1");
			System.out.println("Press 2: Good example 4");
			System.out.println("Press 3: Good example 8");
			System.out.println("Press 4: Good example 11");
			System.out.println("Press 5: Good example 14");
			System.out.println("Press 6: Good example 17");
			System.out.println("Press 7: Good example 20");
			System.out.println("Press 8: Good example 21");
			System.out.println("Press 9: Good example 29");
			System.out.println("Press 10: Good example 31");
			
			System.out.println("For subtile bad examples: press 11-20");
			System.out.println("For very bad examples: press 21-25");
			
			System.out.println("Self made: press 30");
	
			if(choice == 0){
				choice = keyboard.nextInt();
			}
			switch (choice){
			case 1:
				example = "GoodExample1.idp";
				break;
			case 2:
				example = "GoodExample4.idp";
				break;
			case 3:
				example = "GoodExample8.idp";
				break;
			case 4:
				example = "GoodExample11.idp";
				break;
			case 5:
				example = "GoodExample14.idp";
				break;
			case 6:
				example = "GoodExample17.idp";
				break;
			case 7:
				example = "GoodExample20.idp";
				break;
			case 8:
				example = "GoodExample21.idp";
				break;
			case 9: 
				example = "GoodExample29.idp";
				break;
			case 10:
				example = "GoodExample31.idp";
				break;
			case 11:
				example = "SubtileBad1.idp";
				break;
			case 12:
				example = "SubtileBad2.idp";
				break;
			case 13:
				example = "SubtileBad3.idp";
				break;
			case 14:
				example = "SubtileBad4.idp";
				break;
			case 15:
				example = "SubtileBad5.idp";
				break;
			case 16:
				example = "SubtileBad6.idp";
				break;
			case 17:
				example = "SubtileBad7.idp";
				break;
			case 18:
				example = "SubtileBad8.idp";
				break;
			case 19:
				example = "SubtileBad9.idp";
				break;
			case 20:
				example = "SubtileBad10.idp";
				break;
			case 21:
				example = "VeryBad1.idp";
				break;
			case 22:
				example = "VeryBad2.idp";
				break;
			case 23:
				example = "VeryBad3.idp";
				break;
			case 24:
				example = "VeryBad4.idp";
				break;
			case 25:
				example = "VeryBad5.idp";
				break;
			case 31:
				example = "expansion.idp";
				break;
			default:
				example = "check";
		}
			
			
		}
		
		Background bg = new Background();
		int ruleNr = 0;
		int importantRuleNr = 0;
		try {
			System.out.println("Example:" + example);
			PrintWriter theoryRules = new PrintWriter("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/theoryRules.idp");
			PrintWriter structureChecking = new PrintWriter("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/checking/structureChecking.idp");
			PrintWriter penaltyCost = new PrintWriter("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/penaltyCost.idp");
			BufferedReader in = new BufferedReader(new FileReader(
					"/home/thibault/Documents/OutputClaudien/claudien.txt"));
			PrintWriter ruleNrPenalty = new PrintWriter("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/ruleNrPenalty.txt");
			
			String str;
			
			theoryRules.println("theory T2 : V {");
			theoryRules.println("{");
			
			structureChecking.println("structure S : V {");
			structureChecking.println("penaltyCost = {");
			penaltyCost.println("penaltyCost = {");
			
			while ((str = in.readLine()) != null) {
				
				double penalty = 1.0;
				String[] input = str.split("]|->|/\\\\.|\\\\/");
				int inputLength  = (int) Character.getNumericValue(input[0].charAt(1));
				
				if(inputLength > 2){
					for(int i = 1; i < input.length; i++){
						penalty *= bg.getPenaltyElement(input[i]);
					}
					if(inputLength == 3)
						penalty *= (double) 1/2;
					else if(inputLength == 4)
						penalty *= (double) 1/3.5;
					else
						penalty *= (double) 1/inputLength;
				}
				
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
				
				String findStr = "X";
				int lastIndex = 0;
				int count = 0;

				while(lastIndex != -1){

				    lastIndex = result.indexOf(findStr,lastIndex);

				    if(lastIndex != -1){
				        count ++;
				        lastIndex += findStr.length();
				    }
				}

				
				if(count > 2 | result.contains("Y") | !result.contains("X")){
					if(penaltyInt == 100){
						importantRuleNr++;
					}
					theoryRules.println(result);
					structureChecking.println("\t" + ruleNr + "->" + penaltyInt + ";");
					penaltyCost.println("\t" + ruleNr + "->" + penaltyInt + ";");
					ruleNrPenalty.println("RuleNr(" + ruleNr + ")," + penaltyInt);
					ruleNr++;
				}

			}
			
			ruleNrPenalty.close();
			in.close();
			penaltyCost.println("}");
			theoryRules.println("}");
			theoryRules.println("}");
			theoryRules.close();
			
			ruleNr-=1;
			structureChecking.println("}");
			structureChecking.println("\t RuleNr = {0.."+ruleNr+"}");
			structureChecking.println("\t Penalty = {0..100}");
			structureChecking.println("\t PossCoX = {0..5}");
			structureChecking.println("\t PossCoY = {0..5}");
			structureChecking.println("\t PossCoZ = {0..5}");
			
			penaltyCost.println("\t RuleNr = {0.."+ruleNr+"}");
			penaltyCost.println("\t Penalty = {0..100}");
			penaltyCost.println("\t PossCoX = {0..5}");
			penaltyCost.println("\t PossCoY = {0..5}");
			penaltyCost.println("\t PossCoZ = {0..5}");
			penaltyCost.println();
			penaltyCost.println("\t penalty<ct> = {}");
			importantRuleNr-=1;
			penaltyCost.println("\t penalty<cf> = {1.." + importantRuleNr + "}");

			
			penaltyCost.close();
			BufferedReader exampleFile = new BufferedReader(new FileReader("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/examples/".concat(example)));
			while ((str = exampleFile.readLine()) != null) {
				if(!str.contains("/")){
					structureChecking.println("\t" + str);
				}
					
			}
			exampleFile.close();
			
			structureChecking.println("}");
			
			structureChecking.close();
			
			PrintWriter exampleNr = new PrintWriter("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/exampleNr.txt");
			exampleNr.write(example);
			exampleNr.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}

	}

}
