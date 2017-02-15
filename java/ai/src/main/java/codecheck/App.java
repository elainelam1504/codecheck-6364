package codecheck;
import java.io.IOException;
import java.util.*;
import java.lang.String;

public class App {
	public static void main(String[] args) throws Exception {
      Vector<String> Listword = new Vector<String>();
      int len = args.length - 1;
	  String startWord = args[0];
      int[][] vertex = new int[len][len];
	  int[] dimIn = new int[len];
      int[] dimOut = new int[len];
      for (int i = 0; i < len; i++) {
			Listword.add(args[i + 1]);
        	dimIn[i] = dimOut[i] = 0;
	  }
	  
      // create graph from list of word
      for( int i = 0; i< len; i++){
        vertex[i] = new int[len];
      	for(int j = 0; j< len; j++){
          vertex[i][j] = 0;
          if(i == j) continue;
          String si = Listword.get(i);
          String sj = Listword.get(j);
          char lastChar = si.charAt(si.length() - 1);
          char firstChar = sj.charAt(0);
          /*  System.out.print(si);	
          	System.out.print(" ");	
          	System.out.println(sj);
              
            System.out.print(lastChar);	
          	System.out.print(" ");	
          	System.out.println(firstChar);
            */  
        	if(lastChar == firstChar){
              	/*System.out.print(si);	
          		System.out.print(" ");	
          		System.out.println(sj);
              
              	System.out.print(lastChar);	
          		System.out.print(" ");	
          		System.out.println(firstChar);
              */
              	dimIn[j]++;
              	dimOut[i]++;
            	vertex[i][j] = 1;
            }
        }
      }
      if(args.length == 0){
      	IOException e = new IOException();
      	throw e;
      }else{
        boolean ok = false;
		char lastChar = startWord.charAt(startWord.length() - 1);
        for(int i = 0; i < len; i++){
        	/*System.out.print(dimIn[i]);	
          	System.out.print(" ");	
          	System.out.println(dimOut[i]);	*/
          //System.out.print(Listword.get(i));
		  String si = Listword.get(i);
		  char firstChar = si.charAt(0);
          if(lastChar == firstChar){
            	System.out.print(Listword.get(i));
            	ok = true;
            	break;
          }
        }
        if(!ok){
        	System.out.print(Listword.get(0));
            
        }
      }
	}
}
