package ui;

import java.util.HashMap;

import model.*;

public class Main {

	public static void main(String[] args) {
		/*HashMap<String,String> mimap = new HashMap<>();
		mimap.put("BA", "S,C");
		mimap.put("AC", "S");
		mimap.put("CC", "A");
		mimap.put("b", "A");
		mimap.put("AB", "B");
		mimap.put("a", "B,C");
		Grammar g = new Grammar(mimap, "S");
		
		CYK cyk = new CYK("bbab",g);
		System.out.println(cyk.check(g));*/
		
		HashMap<String,String> mimap2 = new HashMap<>();
		mimap2.put("AB", "S");
		mimap2.put("AA", "A");
		mimap2.put("a", "A");;
		mimap2.put("b", "B");
		Grammar g = new Grammar(mimap2, "S");
		CYK cyk = new CYK("aba",g);
		System.out.println(cyk.check(g));
		
	}

}
