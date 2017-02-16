package datastruc.cs.openu.ac.il;

/**
 * Class represent a cell in a linked list. 
 * Cells are constructed by number input from the user.
 * @author Yizhar
 *
 */
public class Cell {
	
	private int data;
	private Cell next;
	private Cell prev;
	
	/**
	 * Constructor - instantiate a cell with given parameter 
	 * @param data
	 */
	public Cell(int val){
		this.data = val;
		this.next = null;
		this.prev = null;
	}
	/**
	 * method returns the cell's data as a string 
	 */
	public String toString() {
		return Integer.toString(data);
	}
	/**
	 * Following is getters and setters for each of the class' field
	 */
	public Cell getPrev() {
		return prev;
	}
	public void setPrev(Cell prev) {
		this.prev = prev;
	}
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Cell getNext() {
		return next;
	}
	public void setNext(Cell next) {
		this.next = next;
	}	
}

