package codecheck;
import java.util.*;
import java.lang.String;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
	public static void main(String[] args) {
		if(args.length < 4){
			return ;
		}else{
			String AI1 = args[0];
			String AI2 = args[1];
			//System.out.println(AI1);
			//System.out.println(AI2);
			String startWord = args[2];
			Vector<String> listWord = new Vector<String>();
			for(int i = 3; i< args.length; i++){
				listWord.add(args[i]);
			}
			
			//execute AI program and get output
			int executeTime = 0;
			boolean ok = true;
			ProcessBuilder pb1 = null;
			ProcessBuilder pb2 = null;
			List<String> command = new ArrayList<>();
			while(ok){
				command.clear();
				if(executeTime % 2 == 0){
					command.add(AI1);
				}else {
					command.add(AI2);
				}
				
				/*if(executeTime > 0){
					//remove previous answer from listWord
					
				}*/
				command.add(startWord);
				for(int i = 0; i< listWord.size(); i++){
					command.add(listWord.get(i));
				}
				//execute AI program
				try{
					//System.out.println(command);
					 pb1 = new ProcessBuilder(command);
					 Process p = pb1.start();
					 BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
					 String line = null;
					 startWord = "";
					 while ((line = reader.readLine()) != null)
					 {
						startWord += line;
						//System.out.println(line);
					 }
					 
				}catch (Exception e) {
					// print error
					//e.printStackTrace();
					if(executeTime % 2 == 0){
					//AI1 error => AI2 win
						System.out.println("WIN - SECOND");
					}else{
						//AI2 error => AI1 win
						System.out.println("WIN - FIRST");
					}
				} 
				
				//check answer is correct or not
				if(executeTime % 2 == 0){
					//execute AI1 
					System.out.print("FIRST ");
				}else{
					//execute AI2
					System.out.print("SECOND ");
				}
				
				boolean correctAnswer = false;
				for(int i = 0; i< listWord.size(); i++){
					if( startWord.equals(listWord.get(i)) ){
						listWord.removeElementAt(i);
						correctAnswer = true;
						break;
					}
				}
				// print answer is correct or not
				if(correctAnswer){
					System.out.print("(OK): ");
				}else{
					System.out.print("(NG): ");
				}
				// print answer
				System.out.println(startWord);
				if(!correctAnswer){
					if(executeTime % 2 == 0){
						//AI1 give incorrect answer => AI2 win
						System.out.println("WIN - SECOND");
					}else{
						//AI2 give incorrect answer => AI1 win
						System.out.println("WIN - FIRST");
					}
					// end loop
					ok = false;
				}
				
				executeTime++;
			}
		}
	}
}
