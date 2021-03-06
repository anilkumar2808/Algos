package com.flipkart.programs;


public class SubStringSearch {

	/**
	 * @param args
	 */
	public static final String SEARCH = "FINDINAHAYSTACKNEEDLEINA";
	public static final String PATTERN = "NEEDLE";
	static int start =-1; static int end =-1;
	
	//O(M*N) can be O(N^2)
	public static void search(){
		int n = SEARCH.length();
		int m = PATTERN.length();
		for(int i = 0;i<n-m;i++){
			for(int j =0;j<m ; j++){
				System.out.println(SEARCH.charAt(j+i) +" "+PATTERN.charAt(j));
				if(SEARCH.charAt(j+i)!=PATTERN.charAt(j)){
					System.out.println("didnt match");break;
				}
				if(j==PATTERN.length()-1) {System.out.println("Matched "+i+" to "+ (i+PATTERN.length()-1)); start = i; end = (i+PATTERN.length()-1); return;}
			}
		}
			end= n-1;
	}
	
	public static int searchBoyerMayer(){
		int R = 256;
		int S = SEARCH.length();
		int P = PATTERN.length();
		
		int [] right = new int[R];
		for(int i =0; i<R; i++){
			right[i]=-1;
		}
		
		//Precompute a right most occurance of char c in pattern -1 if not in pattern
		for(int i =0; i<PATTERN.length(); i++){
			right[PATTERN.charAt(i)] = i;    // NEEDLE right[]
		}
		
		/*
		 * (1) if we try matching a character that is not in p then we can jump forward Pn characters:
		 * (2) if we try matching a character whose last position is k from the end of p then we can jump forward k characters:
		 * 
		 * Note right now has -1 for all chars that arent in pattern.
		 * (1) rule 1 if the char we are matching in text isnt found in pattern (-1 in right) skip=p chars or skip pattern length. 
		 * (2) Say the char we are matching in text is available in pattern we try to allign or skip k chars where 
		 * s = WHICH FINALLY HALTS.  AT THAT POINT...
		   p = AT THAT
		   
		   WHICH FINALLY HALTS.  AT THAT POINT...
		          AT THAT   (here " " doesnt match T but exists in our pattern at 4(k) chars from right in pattern
		   after skip k ->
		   WHICH FINALLY HALTS.  AT THAT POINT...
		              AT THAT      (Note " " alligned)
		   
		   
		   O (n/m)  worst case O(nm) if not found
		   
		 */
		System.out.println();
		int skip = 0;
		for(int i =0; i <= S-P ; i+=skip){
			skip = 0;
			for (int j = P-1; j >=0; j--){
				System.out.println((i+j)+"-"+SEARCH.charAt(i+j)+"  "+j+"-" +PATTERN.charAt(j));
				if(SEARCH.charAt(i+j)!=PATTERN.charAt(j)){
					skip = Math.max(1, j - right[SEARCH.charAt(i+j)]);
					System.out.println("Skip:"+ skip);
					break;
				}
			}
			if (skip == 0) return i;
		}
		return S-1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		search();
//		//VALIDATE
//		for(int i = start;i<=end;i++){
//			System.out.print(SEARCH.charAt(i));
//		}
		start = searchBoyerMayer();
		for(int i = start;i<=start + PATTERN.length()-1;i++){
			System.out.print(SEARCH.charAt(i));
		}		

	}

}
