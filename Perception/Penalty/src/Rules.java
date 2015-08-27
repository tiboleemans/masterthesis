import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Rules {
	
	private ArrayList<Rule> ruleList = new ArrayList<>();
	private int predicatesSize;
	private String[] objectList = new String[12];
	private final int threshold = 61;
	
	public Rules(){
		initiateObjList();
	}
	
	public Rules(ArrayList<Rule> list, int predicatesSize){
		this.ruleList = list;
		this.predicatesSize = predicatesSize;
		initiateObjList();
	}

	private void initiateObjList() {
		objectList[0] = "fork";
		objectList[1] = "knife";
		objectList[2] = "spoon";
		objectList[3] = "smallKnife";
		
		objectList[4] = "plate";
		objectList[5] = "bowl";
		objectList[6] = "saucer";
		objectList[7] = "pastaPlate";
		objectList[8] = "soupPlate";
		
		objectList[9] = "waterGlass";
		objectList[10] = "wineGlass";
		objectList[11] = "teaCup";
	}
	
	public int countPredicatesInRule(String rule){
		int count = 0;
		for(int i = 0; i < this.objectList.length; i++){
			if(rule.contains(objectList[i]))
				count++;
		}
		return count;
	}
	
	public int getApproach(){
		int approach = 0;
		boolean userInput = false;
		int totalPenalty = (int) getTotalPenalty();
		if(totalPenalty > this.threshold){
			System.out.println("I can improve the table setting! Penalty = " + totalPenalty );
				if(rule100()){
					approach = 0;
				}else{
					if(!userInput){
						System.out.println("I'm not sure I can improve... Do you want me to try to add an new object or just rearange everything? (1 = Rearange, 2 = Add, 3 = Remove)");
						Scanner keyboard = new Scanner(System.in);
						approach = keyboard.nextInt();
						userInput = true;
					}
				}
		}else
			System.out.println("This is already a good tablesetting! Penalty = " + totalPenalty);
		return approach;
	}
	
	public ArrayList<String> getNewPredicates(ArrayList<String> oldPredicates){
		
		ArrayList<Rule> newRuleList = chooseRules();
		ArrayList<String> uncertainPredicates = new ArrayList<String>();
		System.out.println("I will rearange: ");
		for(String oldPredicate : oldPredicates){
			System.out.println(oldPredicate);
		}
		
		for(Rule r : newRuleList){
			for(String object : this.objectList){
				if(!uncertainPredicates.contains(object) & (r.getRule().contains(object) | r.getRule().contains(("_".concat(object))))){
					uncertainPredicates.add(object);
				}
			}
		}
		return uncertainPredicates;
	}
	
	public ArrayList<String> getUncertainPredicates(ArrayList<String> oldPredicates){
		ArrayList<String> uncertainPredicates = new ArrayList<String>();
		
		String predicateToAdd = chooseBestPredicateToAdd(oldPredicates);
		uncertainPredicates.add(predicateToAdd);
		String predicateToRemove = chooseBestPredicateToRemove(oldPredicates);
		uncertainPredicates.add(predicateToRemove);
		System.out.println("I will add or remove: " + predicateToAdd + predicateToRemove);
		
		return uncertainPredicates;
	}
	
	private ArrayList<Rule> chooseRules() {
		ArrayList<Rule> newRuleList = new ArrayList<Rule>();
		for(Rule r : this.ruleList){
			if(r.getPenalty() == 100){
				newRuleList.add(r);
			}
		}
		return newRuleList;
	}
	
	private boolean rule100(){
		boolean result = false;
		for(Rule r : this.ruleList){
			if(r.getPenalty() == 100){
				result = true;
				break;
			}
		}
		return result;
	}

	private Rule chooseBestRule() {
		int penalty = this.ruleList.get(0).getPenalty();
		Rule bestRule = this.ruleList.get(0);
		for(Rule r: this.ruleList){
			if(r.getPenalty() > penalty){
				penalty = r.getPenalty();
				bestRule = r;
			}			
		}
		return bestRule;
	}
	
	public String chooseBestPredicateToAdd(ArrayList<String> oldPredicates){
		String result = "";
		String[] newObjectList = transformObjectList(this.objectList);
		Map<String, Integer> predicates = new HashMap<String, Integer>();
		for(String object : newObjectList){
			predicates.put(object, 0);
		}
		for(Rule r : this.ruleList){
			for(String object : newObjectList){
				if(r.getRule().contains(object)){
					predicates.put(object, predicates.get(object) + r.getPenalty());
				}
			}
				
		}
		boolean isIn = false;
		int max = 0;
		for(String object : predicates.keySet()){
			for(String oldPredicate : oldPredicates){
				if(object.contains(oldPredicate)){
					isIn = true;
					break;
				}
				isIn = false;
			}
			if(!isIn & predicates.get(object) > max){
				result = object;
				max = predicates.get(object);
			}
		}
		result = result.substring(1);
		System.out.println(result);
		
		return result;
	}
	
	public String chooseBestPredicateToRemove(ArrayList<String> oldPredicates){
		String result = "";
		String[] newObjectList = transformObjectList(this.objectList);
		Map<String, Integer> predicates = new HashMap<String, Integer>();
		for(String object : newObjectList){
			predicates.put(object, 0);
		}
		for(Rule r : this.ruleList){
			for(String object : newObjectList){
				if(r.getRule().contains(object)){
					predicates.put(object, predicates.get(object) + r.getPenalty());
				}
			}
				
		}
		for(String object : predicates.keySet()){
			System.out.println(object + predicates.get(object));
		}
		
		int max = 0;
		boolean isIn = false;
		for(String object : predicates.keySet()){
			for(String oldPredicate : oldPredicates){
				if(object.contains(oldPredicate)){
					isIn = true;
					break;
				}
				isIn = false;
			}
			if(isIn & predicates.get(object) > max){
				result = object;
				max = predicates.get(object);
			}
		}
		result = result.substring(1);
		System.out.println(result);
		
		return result;
	}

	public String getNameObject(int id){
		return objectList[id];
	}
	
	private double getTotalPenalty(){
		double sum = 0;
		for(Rule r: this.ruleList){
			sum += r.getPenalty();
		}
		
		double denominator = 0;
		
		if(this.predicatesSize == 1)
			denominator = 1.75;
		else if(this.predicatesSize == 2)
			denominator = 2.5;
		else if(this.predicatesSize == 3)
			denominator = 3.25;
		else
			denominator = this.predicatesSize;
		
		sum = sum /denominator;
		return sum;
	}
	
	private String[] transformObjectList(String[] oldObjectList){
		String[] newObjectList = new String[12];
		for(int i = 0; i < oldObjectList.length; i++){
			newObjectList[i] = "_".concat(oldObjectList[i]);
		}
		return newObjectList;
	}
	

	
	
	
	

}
