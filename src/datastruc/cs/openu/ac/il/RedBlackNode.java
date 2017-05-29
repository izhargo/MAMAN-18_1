package datastruc.cs.openu.ac.il;

/**
 * 
 * @author Yizhar
 * A basic class to represent a node in a Red Black binary search tree.
 * Class will be used in order represent a single bank account in a RB tree.
 * 
 * @param <T>
 */
class RedBlackNode<T extends Comparable<T>> {

    //node's colors are represented by 0 or 1
    public static final int BLACK = 0; 
    public static final int RED = 1; 
	public T key;

    RedBlackNode<T> parent;
    RedBlackNode<T> left;
    RedBlackNode<T> right;
    
    public int color; // node's actual color
    /**
     * Class basic constructor, without any parameters.
     * Sets initial color to BLACK
     */
    RedBlackNode(){
        color = BLACK;
        parent = null;
        left = null;
        right = null;
    }

	/**
	 * Second constructor, sets a a node with given key
	 * @param key
	 */
	RedBlackNode(T key){
        this();
        this.key = key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public T getKey() {
		return key;
	}

	@Override
	public String toString() {
		return key.toString();
	}
	
}

