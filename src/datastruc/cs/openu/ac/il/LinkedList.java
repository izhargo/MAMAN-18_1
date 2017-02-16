package datastruc.cs.openu.ac.il;

/**
 * Class LinkedList is a simple implementation of a doubly linked list.
 * Class will ne used as a substance for the ListHeap data structure.
 * @author Yizhar
 */
public class LinkedList {
	
	private Cell start;
	private int size;
	
	/**
	 * Constructor - creates an empty LinkedList
	 */
	public LinkedList(){
		size=0;
		start=null;
	}
	/**
	 * @return true if LinkedList instance is empty, false otherwise.
	 */
	public boolean isEmpty(){
		return start == null;
	}
	/**
	 * method inserts cell to the linked list at the start of the list
	 * @param cell
	 */
	public void listInsert (int val){
		Cell x = new Cell(val);
		x.setNext(start);
		if (!isEmpty()) {
			start.setPrev(x);
		}
		start=x;
		x.setPrev(null);
		size++;
	}
	/**
	 * method deletes the cell from the linked list
	 * @param cell
	 */
	public void listDelete(Cell cell){
		if (cell.getPrev() != null){
			(cell.getPrev()).setNext(cell.getNext());
		}
		else{
			start = cell.getNext();
		}
		if (cell.getNext() != null){
			(cell.getNext()).setPrev(cell.getPrev());
		}	
		size--;
	}
	/**
	 * method searches for data in the linked list. 
	 * @param data
	 * @return Cell x if its data is the same as the parameter's, null otherwise
	 */
	public Cell listSearch(int data){
		Cell x = start;
		while ((x != null) && (x.getData() != data)) {
			x = x.getNext();
		}
		return x;
	}
	public String toString() {
		Cell x = start;
		StringBuilder sb = new StringBuilder();
		while (x != null) {
			sb.append(x.toString()+"\t");
			x=x.getNext();
		}
		return sb.toString();
	}
	/**
	 * a getter for list's size
	 * @return this.size
	 */
	public int getSize() {
		return size;
	}
}
