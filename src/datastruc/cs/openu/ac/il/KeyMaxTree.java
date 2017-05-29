package datastruc.cs.openu.ac.il;
/**
 * This class represent the key in RBMaxTree. The key by which we compare between two objects is mBalance.
 * @author Yizhar
 *
 */
public class KeyMaxTree implements Comparable<KeyMaxTree>{

	private int mBalance;
	private String mName;
	private int mClientNum;
	
	public KeyMaxTree(String Name, int ClientNum, int Balance){
		mBalance = Balance;
		mName = Name;
		mClientNum = ClientNum;
	}
	/**
	 * The method we use in order to compare between two objects
	 */
	@Override
	public int compareTo(KeyMaxTree o) {
		if (this.mBalance < o.mBalance){
			return -1;
		}
		if (this.mBalance > o.mBalance){
			return 1;
		}
		return 0;
	}
	
	public void setmBalance(int mBalance) {
		this.mBalance = mBalance;
	}

	public int getmBalance() {
		return mBalance;
	}

	public String getmName() {
		return mName;
	}

	public int getmClientNum() {
		return mClientNum;
	}

	@Override
	public String toString() {
		return "The client with the biggest bank account is " + mName + " client number: " + mClientNum + 
				" who holds " + mBalance + "NIS";
	}
	
	
}
