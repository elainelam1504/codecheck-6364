package codecheck;
import java.io.IOException;
import java.util.*;
import java.lang.String;

public class App {
	public static void main(String[] args) throws Exception {
      Vector<String> Listword = new Vector<String>();
      int len = args.length;
      int[][] vertex = new int[len][len];
	  int[] dimIn = new int[len];
      int[] dimOut = new int[len];
      for (int i = 0; i < len; i++) {
			Listword.add(args[i]);
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
        for(int i = len-1; i >= 0; i--){
        	/*System.out.print(dimIn[i]);	
          	System.out.print(" ");	
          	System.out.println(dimOut[i]);	*/
          //System.out.print(Listword.get(i));
          if(dimOut[i] == 0){
            	System.out.print(Listword.get(i));
            	ok = true;
            	break;
          }
        }
        if(!ok){
        	for(int i = len-1; i >= 0; i--){
           	System.out.print(Listword.get(i));
            }
        }
      }
	}
}
