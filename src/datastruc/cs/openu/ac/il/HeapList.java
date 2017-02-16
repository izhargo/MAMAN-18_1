package datastruc.cs.openu.ac.il;

/**
 * 
 * @author Yizhar
 * 
 */
public class HeapList {

	private LinkedList list;

	/**
	 * Constructor creates an empty HeapList
	 */
	public HeapList() {
		list = new LinkedList();
	}

	private int Left(int parent) {
		return 2 * parent;
	}

	private int Right(int parent) {
		return 2 * parent + 1;
	}

	private int parent(int child) {
		return (int) Math.floor(child);
	}

	private void MaxHeapify(int root) {

	}
}