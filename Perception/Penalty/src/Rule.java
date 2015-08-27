import java.util.ArrayList;


public class Rule {
	
	private int ruleNr;
	private int penalty;
	private String rule;
	
	public Rule(int ruleNr, int penalty, String rule){
		this.ruleNr = ruleNr;
		this.penalty = penalty;
		this.rule = rule;
	}
	
	public int getPenalty(){
		return this.penalty;
	}
	
	public String getRule(){
		return this.rule;
	}
	
	public String toString(){
		return "RuleNr: " + ruleNr + " Penalty: " + penalty + " Clause: " + rule;
	}

}
