package datastruc.cs.openu.ac.il;
/**
 * This is the main class of our data structure.
 * It's the RB-Tree that holds all the clients of the bank with all their data.
 * Each node has pointers to the other data structures in order to keep the bank's data management efficient 
 * as possible.
 * 
 * @author Yizhar
 *
 */
public class RBClientTree<T extends Comparable<T>> extends RedBlackTree {
	
	
	public RBClientTree(){
		super();
		
	}
	
}
