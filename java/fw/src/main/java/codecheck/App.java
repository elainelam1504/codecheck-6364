package codecheck;
import java.util.*;
import java.lang.String;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
	public static int main(String[] args) {
		/*for (int i = 0, l = args.length; i < l; i++) {
			System.out.println(args[i]);
		}*/
		if(args.length != 4){
			return 0;
		}else{
			String AI1 = args[0];
			String AI2 = args[1];
			String startWord = args[2];
			Vector<String> listWord = new Vector<String>();
			for(int i = 0; i< args.length; i++){
				listWord.add(args[i + 3]);
			}
			//execute AI program and get output
			 try{
				 ProcessBuilder pb = new ProcessBuilder(AI1, startWord, listWord.get(0));
				 Process p = pb.start();
				 BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				 String line = null;
				 while ((line = reader.readLine()) != null)
				 {
					System.out.println(line);
				 }
				 return 1;
			}catch (Exception e) {
				// print error
				e.printStackTrace();
				return 0;
			} 
		}
	}
}
