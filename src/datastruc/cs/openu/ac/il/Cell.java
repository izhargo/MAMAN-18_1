package datastruc.cs.openu.ac.il;

/**
 * A class which represent a cell in a doubly linked list.
 * Class is design specially for holding instances of KeyClient as its data.
 * Cell is used for the list of clients with overdraft accounts.
 * @author Yizhar
 *
 */
public class Cell{
	
	
	private KeyClient data;
	private Cell next;
	private Cell prev;
	
	/**
	 * Constructor - instantiate a cell with given key as data 
	 * @param data
	 */
	public Cell(KeyClient key){
		data = key;
		next = null;
		prev = null;
	}
	/**
	 * method returns the cell's important data as a string 
	 */
	public String toString() {
		StringBuilder a = new StringBuilder();
		a.append(data.getmName() + " ");
		a.append(data.getmClientNum() + " ");
		a.append(data.getmBalance());
		return a.toString();
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
	
	public Cell getNext() {
		return next;
	}
	public void setNext(Cell next) {
		this.next = next;
	}
	public KeyClient getData() {
		return data;
	}
	public void setData(KeyClient data) {
		this.data = data;
	}
}

