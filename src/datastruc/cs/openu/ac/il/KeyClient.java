package datastruc.cs.openu.ac.il;
/**
 * This class represent a key for RBClientTree.
 * The key by which we compare between two objects is mBalance. 
 * We also use pointers from every node to its equivalent nodes in RBMaxTree  
 * and the MinusList
 * @author Yizhar
 *
 */
public class KeyClient implements Comparable<KeyClient>{

	private int mClientNum;
	private int mID;
	private int mBalance;
	private String mName;
	private Cell mMinusListCell;
	private RedBlackNode<KeyMaxTree> mMaxPointer;
	
	public KeyClient(String Name, int ID, int ClientNum,int Balance){
		mClientNum = ClientNum;
		mID = ID;
		mBalance = Balance;
		mName = Name;
		mMinusListCell = null;
		mMaxPointer = null;
	}
	/**
	 * We use mClientNum as the key to order nodes in the tree.
	 */
	@Override
	public int compareTo(KeyClient o) {
		if (this.mClientNum < o.mClientNum){
			return -1;
		}
		if (this.mClientNum > o.mClientNum){
			return 1;
		}
		return 0;
	}
	public void setmMinusListCell(Cell mMinusListCell) {
		this.mMinusListCell = mMinusListCell;
	}
	
	public void setmBalance(int mBalance) {
		this.mBalance = mBalance;
	}
	public void setmMaxPointer(RedBlackNode<KeyMaxTree> mMaxPointer) {
		this.mMaxPointer = mMaxPointer;
	}
	public RedBlackNode<KeyMaxTree> getmMaxPointer() {
		return mMaxPointer;
	}
	
	public int getmClientNum() {
		return mClientNum;
	}
	public int getmID() {
		return mID;
	}
	
	public int getmBalance() {
		return mBalance;
	}
	public String getmName() {
		return mName;
	}
	public Cell getmMinusListCell() {
		return mMinusListCell;
	}
	@Override
	public String toString() {
		return "Client Name=" + mName + " Client Number=" + mClientNum + ",ID Number=" + mID
				+ ",  with Balance of =" + mBalance;
	}
	
}
