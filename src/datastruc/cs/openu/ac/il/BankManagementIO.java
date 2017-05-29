package datastruc.cs.openu.ac.il;


/**
 * This class is the brain of the bank management.
 * It receives the input . All inquerys and actions are given to this class and this class handles  
 * them
 * @author Yizhar
 *
 */
public class BankManagementIO {
	
	RBClientTree<KeyClient> mBank;
	RBMaxTree<KeyMaxTree> mBankMax;
	LinkedList mMinusList;
	/**
	 * Constructor to initialize this data structure. 
	 */
	public BankManagementIO(){
		mBank = new RBClientTree<KeyClient>();
		mBankMax = new RBMaxTree<KeyMaxTree>();
		mMinusList = new LinkedList();
	}
	/**
	 * method receives a string input and delivers it as String array to decide which action to take. 
	 * @param input
	 */
	public void ReceiveInput(String input){
		InputManager(input.split(" "));
	}
	/**
	 * receives a string array and decides with action / query should be made
	 * @param input
	 */
	public void InputManager(String [] input){
		
		if (input[0].equals("+")){
			InsertClient(input);
		}
		else if (input[0].equals("-")){
			RemoveClient(input);
		}
		else if (input[0].equals("?")){
			Query(input);
		}
		else{
			DepositWithdrawl(input);
		}
	}
	/**
	 * Inserts a new client to the data structure. That means, a compatible node to the main RBClientTree
	 * and equivalent node to RBMaxTree.
	 * @param input
	 */
	public void InsertClient(String [] input){
		String Name = input[1] + " " + input [2];
		KeyClient key = new KeyClient(Name, Integer.parseInt(input[3]), 
				Integer.parseInt(input[4]), Integer.parseInt(input[5]));
		mBank.insert(key);
		KeyMaxTree keyMax = new KeyMaxTree(Name,Integer.parseInt(input[4]), Integer.parseInt(input[5]));
		mBankMax.insert(keyMax);
		KeyClient newKey = key;
		newKey.setmMaxPointer(mBankMax.search(keyMax));
		System.out.println("Client name: " + key.getmName() + " , with client number: " + key.getmClientNum()
				+ " was added to the bank");
	}
	/**
	 * This method deletes a client from the bank. Meaning, deletion from two RB trees - 
	 * RbClientTree and RBMaxTree.
	 * @param input
	 */
	public void RemoveClient(String [] input){
		KeyClient searchKey = new KeyClient(" ", 0, Integer.parseInt(input[1]), 0);
		RedBlackNode<KeyClient> clientNode = mBank.search(searchKey);
		mBank.remove(clientNode);
		RedBlackNode<KeyMaxTree> keyMaxNode = clientNode.getKey().getmMaxPointer();
		mBankMax.remove(keyMaxNode);
		System.out.println("Client name: " + clientNode.getKey().getmName()+ " , with client number: " 
					+ clientNode.getKey().getmClientNum() + " has left the bank");
	}
	/**
	 * Receives an inquery and decides which one of possible 3 inqueries is to be handeled
	 * @param input
	 */
	public void Query(String [] input){
		if (input[1].equals("MAX")){
			Max();
		}else if (input[1].equals("MINUS")){
			Minus();
		}else {
			SearchClient(Integer.parseInt(input[1]));
		}	
	}
	/**
	 * prints richest client
	 */
	public void Max(){
		System.out.println(mBankMax.getMaxClient().toString());
	}
	/**
	 * prints list of in-debt clients
	 */
	public void Minus(){
		System.out.println(mMinusList.toString());
	}
	/**
	 * returns a client according to its Client Number
	 * @param ClientNumber
	 */
	public void SearchClient(Integer ClientNumber){
		KeyClient searchKey = new KeyClient(" ", 0, ClientNumber, 0);
		RedBlackNode<KeyClient> Client = mBank.search(searchKey);
		System.out.println("Client - " + Client.getKey().getmName() +  " has a bank account of: " + 
				Client.getKey().getmBalance()+ " NIS");
	}
	/**
	 * Handles all deposits an withdrawls of the bank
	 * Updates all Rb Trees and if necessery, the in-debt clients list as well
	 * @param input
	 */
	public void DepositWithdrawl(String [] input){
		// first, we set the new balance in a new key
		KeyClient searchKey = new KeyClient(" ", 0, Integer.parseInt(input[2]), 0);
		RedBlackNode<KeyClient> oldClient = mBank.search(searchKey);
		RedBlackNode<KeyMaxTree> pointer = oldClient.getKey().getmMaxPointer();
		KeyClient oldClientkey = new KeyClient(oldClient.getKey().getmName(),oldClient.getKey().getmID(),
				oldClient.getKey().getmClientNum(), oldClient.getKey().getmBalance()); 
		KeyClient newKey = oldClient.getKey();
		newKey.setmBalance(oldClient.getKey().getmBalance() + Integer.parseInt(input[3]));
		//than we delete the relevant node in BankMax and insert the new node
		//with the new client's balance
		mBankMax.remove(pointer);
		KeyMaxTree newKeyMax = new KeyMaxTree(newKey.getmName(),newKey.getmClientNum(), newKey.getmBalance());
		mBankMax.insert(newKeyMax);
		newKey.setmMaxPointer(mBankMax.search(newKeyMax));
		//second, we check if we need to do more changes to the bank client's node and if we 
		//need to update mMinusList
		if (oldClientkey.getmBalance() >=0){
			if (oldClientkey.getmBalance() + Integer.parseInt(input[3]) < 0){
				// this client entered overdraft just now, so we'll add him to the list 
				mMinusList.InsertAtEnd(newKey); 
				newKey.setmMinusListCell(mMinusList.getEnd());
			}
		} else {// this client was already in debt
			if (oldClientkey.getmBalance() + Integer.parseInt(input[3]) >= 0){
				// this client got out of overdraft just now, so we'll delete him from the list
				mMinusList.Delete(newKey.getmMinusListCell());
				newKey.setmMinusListCell(null);	
			} else {
				newKey.getmMinusListCell().setData(newKey);
			}
		}
		System.out.println("Client: " + newKey.getmName() + ", Client Number: " + newKey.getmClientNum() + 
				" has a balance of: " + newKey.getmBalance() + " NIS");
	}	
}
