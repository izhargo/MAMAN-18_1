package datastruc.cs.openu.ac.il;

import java.util.ArrayList;
import java.util.List;
/**
 * A class to represent a Red Black Tree according  to the course's book.
 * Class' algorithms are identical to the book's algorithm.
 * I chose to use the sentinel instead of regular null pointers because it makes
 * removeFixup() easier and more efficient.  When a RedBlackNode is instantiated -
 * all of it's pointers pointed to nil.  The root at all times will have it's
 * parent pointer to nil.  
 * For convenience, I added a "isNil" method, since it's such a common action.
 * 
 * @author Yizhar
 *
 * @param <T>
 */

public class RedBlackTree<T extends Comparable<T>> {

	private RedBlackNode<T> nil = new RedBlackNode<T>();
	private RedBlackNode<T> root = nil;
	
	/**
	 * Constructor initials the Tree as a root directing to NIL 
	 */
    public RedBlackTree() {
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

	/**
	 * Method preforms a Left Rotate at node x.
	 * @param x
	 */
	private void leftRotate(RedBlackNode<T> x){

		RedBlackNode<T> y;
		y = x.right;
		x.right = y.left;
		if (!isNil(y.left)){
			y.left.parent = x;
		}
		y.parent = x.parent;
		if (isNil(x.parent)){
			root = y;
		}
		// x is the left child of it's parent
		else if (x.parent.left == x){
			x.parent.left = y;

		// x is the right child of it's parent.
		}else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}

	/**
	 * Method preforms a Right Rotate at node x. Same as the previous method, just to the right.
	 * @param x
	 */
	private void rightRotate(RedBlackNode<T> y){

        RedBlackNode<T> x = y.left;
        y.left = x.right;

        if (!isNil(x.right)){
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (isNil(y.parent)){
            root = x;
        }
        // y is a right child of its parent.
        else if (y.parent.right == y){
            y.parent.right = x;
        }
        // y is a left child of its parent.
        else{
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
	}

    public void insert(T key) {
        insert(new RedBlackNode<T>(key));
    }

	/**
	 * Insert given node z as leaf to the tree.
	 * @param z
	 */
    private void insert(RedBlackNode<T> z) {

			// Create a reference to root & initialize a node to nil
			RedBlackNode<T> y = nil;
			RedBlackNode<T> x = root;

			
			while (!isNil(x)){ // according to BST rule, find where z should be placed as leaf, keeping "y" as z's parent
				y = x;

				if (z.key.compareTo(x.key) < 0){
					x = x.left;
				} else{
					x = x.right;
				}
			}
			// now, y is actually z's parent
			z.parent = y;

			//setting z as a right or left child to y, according to BST rule
			if (isNil(y))
				root = z;
			else if (z.key.compareTo(y.key) < 0)
				y.left = z;
			else
				y.right = z;

			// Initialize z's children to nil and z's color to red
			z.left = nil;
			z.right = nil;
			z.color = RedBlackNode.RED;

			//we need to order color fixup method in every case of insertion
			insertFixup(z);

	}


	/**
	 * node z was just added and might cause violation of Red Black Tree Rule.
	 * This method solves this problem, should it occur, like the algorithm in the book
	 * @param z
	 */
	private void insertFixup(RedBlackNode<T> z){

		RedBlackNode<T> y = nil;
		// While condition checks the occurence of the violation
		while (z.parent.color == RedBlackNode.RED){

			if (z.parent == z.parent.parent.left){

				y = z.parent.parent.right;

				// Case 1
				if (y.color == RedBlackNode.RED){
					z.parent.color = RedBlackNode.BLACK;
					y.color = RedBlackNode.BLACK;
					z.parent.parent.color = RedBlackNode.RED;
					z = z.parent.parent;
				}
				// Case 2
				else if (z == z.parent.right){

					// leftRotaet around z's parent
					z = z.parent;
					leftRotate(z);
				}

				// Case 3
				else{
					// color and rotate round z's grandfather
					z.parent.color = RedBlackNode.BLACK;
					z.parent.parent.color = RedBlackNode.RED;
					rightRotate(z.parent.parent);
				}
			}

			// same as "if" clause - only to the left
			else{

				y = z.parent.parent.left;

				// Case 1
				if (y.color == RedBlackNode.RED){
					z.parent.color = RedBlackNode.BLACK;
					y.color = RedBlackNode.BLACK;
					z.parent.parent.color = RedBlackNode.RED;
					z = z.parent.parent;
				}

				// Case 2
				else if (z == z.parent.left){
					// rightRotate around z's parent
					z = z.parent;
					rightRotate(z);
				}
				// Case 3
				else{
					// color and rotate around z's grandfather
					z.parent.color = RedBlackNode.BLACK;
					z.parent.parent.color = RedBlackNode.RED;
					leftRotate(z.parent.parent);
				}
			}
		}
	root.color = RedBlackNode.BLACK;

	}

	/**
	 * 
	 * @param node
	 * @return Tree's minimum key, while using the BST rule
	 */
	public RedBlackNode<T> treeMinimum(RedBlackNode<T> node){

		while (!isNil(node.left)){
			node = node.left;
		}
		return node;
	}
	/**
	 * 
	 * @param node
	 * @return Tree's maximum key, while using the BST rule
	 */
	public RedBlackNode<T> treeMaximum(RedBlackNode<T> node){
		
		while (!isNil(node.right)){
			node = node.right;
		}
		return node;
	}
	/**
	 * 
	 * @param x
	 * @return The node with the biggest key of all the key's that are smaller than x's key,
	 * or null if x is tree's minimum
	 */
	public RedBlackNode<T> treePredecessor(RedBlackNode<T> x){

		// if x has a left sub-tree - we return the latter's maximum
		if (!isNil(x.left))
			return treeMaximum(x.right);
		//otherwise we need to find a parent to x for which x is on his right side
		RedBlackNode<T> y = x.parent;

		while (!isNil(y) && x == y.left){
			x = y;
			y = y.parent;
		}
		return y;
	}

	/**
	 * 
	 * @param x
	 * @return The node with the smallest key of all the key's that are bigger than x's key,
	 * or null if x is the tree's maximum.
	 */
	public RedBlackNode<T> treeSuccessor(RedBlackNode<T> x){

		// if x has a right sub-tree - we return the latter's minimum
		if (!isNil(x.right) )
			return treeMinimum(x.right);
		//otherwise we need to find a parent to x for which x is on his left side
		RedBlackNode<T> y = x.parent;

		while (!isNil(y) && x == y.right){
			x = y;
			y = y.parent;
		}
		return y;
	}
	/**
	 * removes a node from accordind to BST rule. Then, orders a RB-Fixup method to keep Red-Black 
	 * Tree Rules, in case y node is Black.
	 * We use a TreeNode and not the key inside the node.
	 * @param z
	 */
	public void remove(RedBlackNode<T> z){
		
		
		// Declare assisting variables
		RedBlackNode<T> x = nil;
		RedBlackNode<T> y = nil;

		// if either one of z's children is nil, then we must remove z
		if (isNil(z.left) || isNil(z.right)){
			y = z;
		}
		// else we must remove the successor of z
		else y = treeSuccessor(z);

		// Let x be the left or right child of y 
		if (!isNil(y.left))
			x = y.left;
		else
			x = y.right;

		
		x.parent = y.parent;

		// If y's parent is nil, then x is the root
		if (isNil(y.parent))
			root = x;

		// else if y is a left child, set x to be y's left sibling
		else if (!isNil(y.parent.left) && y.parent.left == y)
			y.parent.left = x;

		// else if y is a right child, set x to be y's right sibling
		else if (!isNil(y.parent.right) && y.parent.right == y)
			y.parent.right = x;

		// if y != z, trasfer y's satellite data into z.
		if (y != z){
			z.key = y.key;
		}


		// If y's color is black, it is a violation of the
		// RedBlackTree properties so call removeFixup()
		if (y.color == RedBlackNode.BLACK)
			removeFixup(x);
		
	}


	/**
	 * Fix the tree after the removal of a node, maintains RB Tree Rule according to the 
	 * 4 cases presented it the book.
	 * @param x the child of the deleted node from remove(RedBlackNode v)
	 */
	private void removeFixup(RedBlackNode<T> x){

		RedBlackNode<T> w;

		while (x != root && x.color == RedBlackNode.BLACK){

			
			if (x == x.parent.left){

				
				w = x.parent.right;

				// Case 1
				if (w.color == RedBlackNode.RED){
					w.color = RedBlackNode.BLACK;
					x.parent.color = RedBlackNode.RED;
					leftRotate(x.parent);
					w = x.parent.right;
				}

				// Case 2
				if (w.left.color == RedBlackNode.BLACK &&
							w.right.color == RedBlackNode.BLACK){
					w.color = RedBlackNode.RED;
					x = x.parent;
				}
				// Case 3 / Case 4
				else{
					// Case 3, w's right child is black
					if (w.right.color == RedBlackNode.BLACK){
						w.left.color = RedBlackNode.BLACK;
						w.color = RedBlackNode.RED;
						rightRotate(w);
						w = x.parent.right;
					}
					// Case 4, w = black, w.right = red
					w.color = x.parent.color;
					x.parent.color = RedBlackNode.BLACK;
					w.right.color = RedBlackNode.BLACK;
					leftRotate(x.parent);
					x = root;
				}
			}
			// same as the "if" clause only to the left
			else{

				
				w = x.parent.left;

				// Case 1
				if (w.color == RedBlackNode.RED){
					w.color = RedBlackNode.BLACK;
					x.parent.color = RedBlackNode.RED;
					rightRotate(x.parent);
					w = x.parent.left;
				}

				// Case 2
				if (w.right.color == RedBlackNode.BLACK &&
							w.left.color == RedBlackNode.BLACK){
					w.color = RedBlackNode.RED;
					x = x.parent;
				}

				// Case 3 / Case 4
				else{
					// Case 3, w's left child is black
					 if (w.left.color == RedBlackNode.BLACK){
						w.right.color = RedBlackNode.BLACK;
						w.color = RedBlackNode.RED;
						leftRotate(w);
						w = x.parent.left;
					}

					// Case 4, w = black, and w.left = red
					w.color = x.parent.color;
					x.parent.color = RedBlackNode.BLACK;
					w.left.color = RedBlackNode.BLACK;
					rightRotate(x.parent);
					x = root;
				}
			}
		}

		// set x to black to ensure there is no violation of
		// RedBlack tree Properties
		x.color = RedBlackNode.BLACK;
	}


	/**
	 * 
	 * @param key
	 * @return the node which holds this key, or null if key isn't in the tree. We use the BST rule in our search.
	 * Notice that search is made only by comparing values of given key with tree's key, using their 
	 * Comparable feature. We are not checking equaling between objects. 
	 * if two objects 
	 */
	public RedBlackNode<T> search(T key){

		RedBlackNode<T> current = root;

		// going "down" towards the leaves of the tree
		while (!isNil(current)){

			
			if (current.key.compareTo(key) == 0)
				return current;

			
			else if (current.key.compareTo(key) < 0)
				current = current.right;
			else
				current = current.left;
		}

		// we have not found the given key
		return null;

	}

	/**
	 * We often check if a node is pointing at the sentinel. 
	 * @param node
	 * @return true of node is nil and false otherwise
	 */
	private boolean isNil(RedBlackNode node){

		return node == nil;

	}

	public RedBlackNode<T> getRoot() {
		return root;
	}
	
	public void InorderWalk(RedBlackNode<T> root){
		if (!isNil(root)){
			InorderWalk(root.left);
			System.out.println(root.key.toString());
			InorderWalk(root.right);
		}
		
	}
	
}
