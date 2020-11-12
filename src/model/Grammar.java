package model;

import java.util.Map;

public class Grammar {
	
	private Map<String, String> gram;
	private String initial_variable;
	
	
	public Grammar(Map<String, String> gram, String initial_variable) {
		this.gram = gram;
		this. initial_variable =  initial_variable;
	}

	public Map<String, String> getGram() {
		return gram;
	}

	public void setGram(Map<String, String> gram) {
		this.gram = gram;
	}

	public String getInitial_variable() {
		return initial_variable;
	}

	public void setInitial_variable(String initial_variable) {
		this.initial_variable = initial_variable;
	}
	
	
	
	
}
