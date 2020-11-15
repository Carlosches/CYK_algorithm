package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CYK {
	
	private String[][] dp;
	
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
	
	public void initialize(String text, Grammar gram) {
		int n = text.length();
		for(int i=0; i<n; i++) {
			String getVar = gram.getGram().get(""+text.charAt(i));
			dp[i][0] = getVar;
		}
		
	}
	
	
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
