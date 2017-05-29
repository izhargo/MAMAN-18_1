package datastruc.cs.openu.ac.il;
/**
 * This is the class which allows the input in order to set up the bank's data.
 */
import java.util.Scanner;


public class InputBank {
	public static void main(String[] args) {
		
		BankManagementIO MyBank = new BankManagementIO();
		System.out.println("Start entering bank's clients details and queries:");
		Scanner s = new Scanner(System.in);
		String line = s.nextLine();
		while (true){
			if (line.equals("quit")){
				break;
			}
			MyBank.ReceiveInput(line);
			line = s.nextLine();
		}
		s.close();
	}
}
	
