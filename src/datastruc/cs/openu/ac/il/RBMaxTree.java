package datastruc.cs.openu.ac.il;
/**
 * This data structure is used in order to keep track of the bank's client who holds the biggest
 * bank account.
 * It is designed so that the maintenance of this maximum client is done with every insertion/removal of a 
 * bank client and with every deposit/withdrawl of money made in the bank. 
 * 
 * @author Yizhar
 *
 */
public class RBMaxTree<T extends Comparable<T>> extends RedBlackTree{
	
	private RedBlackNode<T> MaxClient;
	
	public RBMaxTree(){
		super();
		MaxClient = null;
	}
	/**
	 * In addition to the usual insertion, we maintain the MaxClient upon the insertion of a key to the 
	 * tree.
	 */
	@Override
	public void insert(Comparable key) {
		super.insert(key);
		FindMax();
	}
	/**
	 * In addition to the usual removal, we maintain the MaxClient upon the removal of a key to the 
	 * tree.
	 */
	@Override
	public void remove(RedBlackNode z) {
		super.remove(z);
		FindMax();
	}
	/**
	 * Maintenance method in order to keep track of the MaxValue.
	 */
	public void FindMax(){
		MaxClient = treeMaximum(getRoot());
	}

	public String getMaxClient() {
		return MaxClient.toString();
	}
	

}
