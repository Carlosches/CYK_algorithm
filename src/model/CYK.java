package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CYK {

	/**
	 * <b>Description:</b>
	 * A matrix of Strings representing the states of the dynamic programming algorithm.
	 */
	private String[][] dp;

	/**
	 * <b>Description:</b>
	 * This function allows to create a new instance of a CYK algorithm solver
	 *
	 * @param text, The text that is to be verified whether or not it can be generated by the specified grammar
	 * @param gram, The grammar to which the algorithm is going to be applied on.
	 * <b>post:</b>  a new instance of a CYK algorithm solver has been created
	 */
	public CYK(String text, Grammar gram) {
		
		dp = new String[text.length()][text.length()];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				dp[i][j]="";
			}
		}
		
		initialize(text,gram);
		repeat(text,gram);
	}

	/**
	 * <b>Description:</b>
	 * This function initializes the dynamic programming table, that is it fills the base states of the dynamic programming.
	 *
	 * @param text, The text that is to be verified whether or not it can be generated by the specified grammar
	 * @param gram, The grammar to which the algorithm is going to be applied on.
	 * <b>post:</b>  The first column of the dynamic programming table is filled completely
	 */
	public void initialize(String text, Grammar gram) {
		int n = text.length();
		for(int i=0; i<n; i++) {
			String getVar = gram.getGram().get(""+text.charAt(i));
			dp[i][0] = getVar;
		}
		
	}

	/**
	 * <b>Description:</b>
	 * This function runs the dynamic programming recurrence in order to fill the whole table and find the answer.
	 *
	 * @param text, The text that is to be verified whether or not it can be generated by the specified grammar
	 * @param gram, The grammar to which the algorithm is going to be applied on.
	 * <b>post:</b>  The dynamic programming table is filled completely
	 */
	public void repeat(String text, Grammar gram) {
		int n = dp.length;
		for (int j = 1; j < n; j++) {
			for (int i = 0; i < n-j; i++) {
				Set<String> set = new HashSet<>();
				for (int k = 0; k <j; k++) {
					String[] comb= combine(dp[i][k], dp[i+k+1][j-k-1], text,gram).split(",");
					for(String e:comb)
						set.add(e);
				}
				for(String e: set) {
					dp[i][j]+=e+",";
				}
			}
		}
	}

	/**
	 * <b>Description:</b>
	 * This function does the cartesian product between two strings of variables and returns all the uniquely created combinations of the variables.
	 *
	 * @param s1, The first String of variables separated by commas
	 * @param s2, The second String of variables separated by commas
	 * @param text, The text that is to be verified whether or not it can be generated by the specified grammar
	 * @param gram, The grammar to which the algorithm is going to be applied on.
	 * @return A String representing all the unique combinations between the states in s1 and the states in s2
	 */
	public String combine(String s1, String s2, String text, Grammar gram) {
		Set<String> current=new HashSet<String>();
		String[] arr1 = s1.split(",");
		String[] arr2 = s2.split(",");
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr2.length; j++) {
				String ans2 =""+arr1[i]+arr2[j];
				if(gram.getGram().get(ans2)!=null) {
					current.add(gram.getGram().get(ans2));
				}
			}
		}
		String ans="";
		for(String e: current) {
			ans+=e+",";
		}
		return ans;
	}

	/**
	 * <b>Description:</b>
	 * This function checks whether the grammar generates the text that was specified in the CYK constructor
	 *
	 * @param gram, The grammar to which the algorithm was applied on.
	 * @return A boolean that is true if the grammar generates the text that was specified in the CYK constructor or false otherwise.
	 */
	public boolean check(Grammar gram) {
		try {
			String[] spl = dp[0][dp.length-1].split(",");
			boolean ans=false;
			for (int i = 0; i < spl.length && !ans; i++) {
				if(spl[i].equals(gram.getInitial_variable())) {
					ans=true;
				}
				
			}

			return ans;
		}catch(NullPointerException e) {
			return false;
		}
		
	}
}
