import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ExpansionMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {	
		// Construction of background elements
		ArrayList<Integer> errorRuleNrsList = new ArrayList<Integer>();
		ArrayList<Integer> penalties = new ArrayList<Integer>();
		ArrayList<String> clauses = new ArrayList<String>();
		ArrayList<Rule> wrongRules = new ArrayList<Rule>();
		String position = "";


		try {
			BufferedReader inPenalty = new BufferedReader(new FileReader(
					"/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/errorList.txt"));
			String str;
			while ((str = inPenalty.readLine()) != null) {
				String str2 = str.substring(2, str.length() - 2);
				String[] input = str2.split("; ");
				for(int i = 0; i < input.length; i++){
					errorRuleNrsList.add(Integer.parseInt(input[i]));
				}
			}
			inPenalty.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader theoryRules = new BufferedReader(new FileReader(
					"/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/theoryRules.idp"));
			String str;
			String rule;
			for(int i = 0; i < errorRuleNrsList.size(); i++){
				while ((str = theoryRules.readLine()) != null) {
					if(str.contains("(".concat(errorRuleNrsList.get(i).toString().concat(")")))){
						if(str.contains(":")){
							String[] rules = str.split(":");
							rule = rules[1].substring(0, rules[1].length() - 1);
							clauses.add(rule);
							break;
						}
						else{
							String[] rules = str.split("~");
							rule = rules[1].substring(1, rules[1].length() - 2);
							clauses.add(rule);
							break;
						}
					}
				}
			}
			theoryRules.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader penaltyFile = new BufferedReader(new FileReader(
					"/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/ruleNrPenalty.txt"));
			String str;
			for(int i = 0; i < errorRuleNrsList.size(); i++){
				while ((str = penaltyFile.readLine()) != null) {
					if(str.contains("(" + errorRuleNrsList.get(i) + ")")){
						String[] input = str.split(",");
						int penalty = Integer.parseInt(input[1]);
						penalties.add(penalty);
						break;
					}
				}
			}
			penaltyFile.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}
		
		ArrayList<String> predicates = new ArrayList<String>();
			
		BufferedReader exampleNr = new BufferedReader(new FileReader("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/exampleNr.txt"));
		String exampleNbr = exampleNr.readLine();
		exampleNr.close();
		BufferedReader example = new BufferedReader(new FileReader(
				"/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/examples/".concat(exampleNbr)));
		String str;
		while ((str = example.readLine()) != null) {
			if(str.contains("=") & str.contains("{") & !str.contains("position") & !str.contains("/")){
				String[] input = str.split("=");
				String predicate = input[0].substring(0, input[0].length());
				predicate = predicate.replaceAll("\\s","");
				predicates.add(predicate);
			}
			if(str.contains("position")){
				position = str.replace("position", "position<ct>");
			}
		}
		example.close();
		

		
		for(int i = 0; i < errorRuleNrsList.size(); i++){
			Rule r = new Rule(errorRuleNrsList.get(i), penalties.get(i), clauses.get(i));
			wrongRules.add(r);
		}
		
		ArrayList<String> newPredicates = new ArrayList<String>();
		
//		for(String predicate : predicates){
//			System.out.println(predicate);
//		}
		
		for(Rule r2 : wrongRules){
			System.out.println(r2.toString());
		}
		
		Rules r = new Rules(wrongRules, predicates.size());
		int approach = r.getApproach();
		ArrayList<String> uncertainPredicates = new ArrayList<String>();
		
		if(approach == 0){
			uncertainPredicates = r.getNewPredicates(predicates);
			for(String predicate : predicates){
				boolean isIn = false;
				for(String uncertainPredicate : uncertainPredicates){
					if(predicate.equals(uncertainPredicate))
						isIn = true;
				}
				if(!isIn)
					newPredicates.add(predicate);
				isIn = false;
			}		}else if(approach == 1){ // rearange
			newPredicates = predicates;
		}else if(approach == 2){ // Add
			uncertainPredicates.add(r.chooseBestPredicateToAdd(predicates));
			for(String predicate : predicates){
				boolean isIn = false;
				for(String uncertainPredicate : uncertainPredicates){
					if(predicate.equals(uncertainPredicate))
						isIn = true;
				}
				if(!isIn)
					newPredicates.add(predicate);
				isIn = false;
			}
		}
		else if(approach == 3){ // remove
			uncertainPredicates.add(r.chooseBestPredicateToRemove(predicates));
			for(String predicate : predicates){
				boolean isIn = false;
				for(String uncertainPredicate : uncertainPredicates){
					if(predicate.equals(uncertainPredicate))
						isIn = true;
				}
				if(!isIn)
					newPredicates.add(predicate);
				isIn = false;
			}
		}
		
		for(String predicate : newPredicates){
			System.out.println("Certain of this predicate: " + predicate);
		}
		
		for(String uncertain : uncertainPredicates){
			System.out.println("Uncertain of this predicate: " + uncertain);
		}

		
		PrintWriter structureExpansion = new PrintWriter("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/expansion/structureExpansion.idp");
		BufferedReader penaltyCost = new BufferedReader(new FileReader("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/penaltyCost.idp"));
		structureExpansion.println("structure S : V {");
		while ((str = penaltyCost.readLine()) != null) {
			structureExpansion.println(str);
		}
		
		for(int i = 0; i < newPredicates.size(); i++){
			structureExpansion.println("\t" + newPredicates.get(i) + " = {" + newPredicates.get(i) + "}");
		}
		
		for(String uncertain : uncertainPredicates){
			structureExpansion.println("\t" + uncertain + "<ct> = {}");
			structureExpansion.println("\t" + uncertain + "<u> = {" + uncertain + "}");
		}
		structureExpansion.println("}");
		structureExpansion.close();
		penaltyCost.close();

		
		PrintWriter structureExpansionForModelCheck = new PrintWriter("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/examples/expansion.idp");
		for(String predicate : newPredicates){
			structureExpansionForModelCheck.println("\t" + predicate + " = {" + predicate + "}");
		}
		structureExpansionForModelCheck.println(position);
		structureExpansionForModelCheck.close();

		
		PrintWriter structureExpansionUncertain = new PrintWriter("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/expansion/structureExpansionUncertain.idp");
		BufferedReader penaltyCost2 = new BufferedReader(new FileReader("/home/thibault/workspace2/ClausalDiscovery/bin/executable/mac/idp/bin/problems/penaltyCost.idp"));
		structureExpansionUncertain.println("structure S : V {");
		while ((str = penaltyCost2.readLine()) != null) {
			structureExpansionUncertain.println(str);
		}
		
		for(String predicate : newPredicates){
			structureExpansionUncertain.println("\t" + predicate + "<ct>" + " = {}");
			structureExpansionUncertain.println("\t" + predicate + "<u>" + " = {" + predicate + "}");
		}
		for(String uncertain : uncertainPredicates){
			structureExpansionUncertain.println("\t" + uncertain + "<ct> = {}");
			structureExpansionUncertain.println("\t" + uncertain + "<u> = {" + uncertain + "}");
		}
		structureExpansionUncertain.println("}");
		structureExpansionUncertain.close();
		penaltyCost2.close();




		
	}

}
