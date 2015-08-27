import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class NewTableMain {


	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ArrayList<Integer> errorRuleNrsList = new ArrayList<Integer>();
		ArrayList<Integer> penalties = new ArrayList<Integer>();
		int predicatesSize = 1;
		String str;
		
		BufferedReader inPenalty = new BufferedReader(new FileReader(
				"/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/newErrorList.txt"));
		str = inPenalty.readLine(); 
		if(str != null){
			if(str.contains(";")){
				String str2 = str.substring(2, str.length() - 2);
				String[] input = str2.split("; ");
				for(int i = 0; i < input.length; i++){
					errorRuleNrsList.add(Integer.parseInt(input[i]));
				}
			}else
				System.out.println("No errors!");
			String positions = "";
			while ((str = inPenalty.readLine()) != null) {
				if(str.contains("position")){
					positions = str;
					break;
				}
			}
			System.out.println("This is the new position: " + positions);
			Rules r = new Rules();
				predicatesSize = r.countPredicatesInRule(positions);

			
			inPenalty.close();
		
			BufferedReader penaltyFile = new BufferedReader(new FileReader(
					"/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/ruleNrPenalty.txt"));
			int sum = 0;
			for(int i = 0; i < errorRuleNrsList.size(); i++){
				while ((str = penaltyFile.readLine()) != null) {
					if(str.contains("(" + errorRuleNrsList.get(i) + ")")){
						String[] input = str.split(",");
						int penalty = Integer.parseInt(input[1]);
						penalties.add(penalty);
						sum += penalty;
						break;
					}
				}
			}

			
			double denominator = 0;
			
			if(predicatesSize == 1)
				denominator = 1.75;
			else if(predicatesSize == 2)
				denominator = 2.5;
			else if(predicatesSize == 3)
				denominator = 3.25;
			else
				denominator = predicatesSize;
			
			sum = (int) (sum / denominator);
			penaltyFile.close();
			
			if(sum > 61){
				System.out.println("But it is still a bad table setting because the threshold is: " + sum);
				System.out.println("I will try to improve it by rearranging al the objects with previous choice");	
				
				PrintWriter structureExpansion = new PrintWriter("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/expansion/structureExpansion.idp");
				BufferedReader structureExpansionUncertain = new BufferedReader(new FileReader("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/expansion/structureExpansionUncertain.idp"));
				
				while ((str = structureExpansionUncertain.readLine()) != null) {
					structureExpansion.println(str);
				}
				
				structureExpansion.close();
				structureExpansionUncertain.close();
			}else{
				System.out.println("Good table setting because the threshold is: " + sum);
			}
			
			}else{
				System.out.println("Impossible tablesetting... Please restart model checker...");
				PrintWriter structureExpansion = new PrintWriter("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/expansion/structureExpansion.idp");
				BufferedReader structureExpansionUncertain = new BufferedReader(new FileReader("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/expansion/structureExpansionUncertain.idp"));
				
				while ((str = structureExpansionUncertain.readLine()) != null) {
					structureExpansion.println(str);
				}
				
				structureExpansion.close();
				structureExpansionUncertain.close();
			}
		
	}

}
