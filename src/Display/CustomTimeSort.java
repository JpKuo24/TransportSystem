package Display;

public class CustomTimeSort implements Comparable<CustomTimeSort>{
	
	int CompareNumber;
	String S[] = new String[10];
	
	public CustomTimeSort(String s0, String s1, String s2, String s3, String s4, String s5, String s6,String s7, String s8) {
		String[] s = s1.split(":");
		CompareNumber = Integer.parseInt( (s[0].concat(s[1])) );
		S[0] = s0;  
		S[1] = s1; 
		S[2] = s2; 
		S[3] = s3; 
		S[4] = s4; 
		S[5] = s5; 
		S[6] = s6; 
		S[7] = s7; 
		S[8] = s8; 
	}

	public int compareTo(CustomTimeSort o) {
		if(this.CompareNumber==o.CompareNumber) return 0;
		else if (this.CompareNumber>=o.CompareNumber) return 1;
		else return -1;
	}

}
