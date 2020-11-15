package model;

import java.util.Map;

public class Grammar {
	
	private Map<String, String> gram;
	private String initial_variable;

	/**
	 * <b>Description:</b>
	 * This function allows to create a new instance of a grammar
	 *
	 * @param gram, a map representing for each output of the grammar, the variable which can produce it
	 * @param initial_variable, a String representing the initial variable of the grammar
	 * <b>post:</b>  a new instance of Grammar has been created
	 */
	public Grammar(Map<String, String> gram, String initial_variable) {
		this.gram = gram;
		this. initial_variable =  initial_variable;
	}

	/**
	 * <b>Description:</b>
	 * This function allows to obtain the map that represents the grammar
	 * @return the map that represents the grammar
	 */
	public Map<String, String> getGram() {
		return gram;
	}

	/**
	 * <b>Description:</b>
	 * This function allows to modify the map that represents the grammar
	 *
	 * @param gram, a map representing the new map that is the replacement of the old one
	 * <b>post:</b>  the old map has been replaced
	 */
	public void setGram(Map<String, String> gram) {
		this.gram = gram;
	}

	/**
	 * <b>Description:</b>
	 * This function allows to the initial variable of the grammar
	 * @return A string representing the initial variable of the grammar
	 */
	public String getInitial_variable() {
		return initial_variable;
	}

	/**
	 * <b>Description:</b>
	 * This function allows to modify the String that represents the initial variable of the grammar
	 *
	 * @param initial_variable, a String representing the new initial variable that is the replacement of the old one
	 * <b>post:</b>  the old initial variable has been replaced
	 */
	public void setInitial_variable(String initial_variable) {
		this.initial_variable = initial_variable;
	}
	
	
	
	
}
