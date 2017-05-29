package datastruc.cs.openu.ac.il;

/**
 * Class LinkedList is a simple implementation of a doubly linked list. 
 * Class will be used as list for clients with overdraft accounts and designed 
 * specially to receive KeyClient as data.
 * 
 * @author Yizhar
 */
public class LinkedList {

	private Cell start;
	private Cell end;
	private int size;

	/**
	 * Constructor - creates an empty LinkedList
	 */
	public LinkedList() {
		size = 0;
		start = null;
		end = null;
	}

	/**
	 * @return true if LinkedList instance is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return start == null;
	}

	
	/**
	 * method inserts cell to the linked list at the end of the list
	 * 
	 * @param val
	 */
	public void InsertAtEnd(KeyClient Key) {
		Cell x = new Cell(Key);
		size++;
		if (isEmpty()){
			end = x;
			start=end;
		} else {
			x.setPrev(end);
			end.setNext(x);
			end = x;
		}
	}

	/**
	 * method deletes the cell from the linked list
	 * 
	 * @param cell
	 */
	public void Delete(Cell cell) {
		if (cell.getPrev() != null) {
			cell.getPrev().setNext(cell.getNext());
		} else {
			start = cell.getNext();
		}
		if (cell.getNext() != null) {
			cell.getNext().setPrev(cell.getPrev());
		}
		size--;
	}
	
	public String toString() {
		if (size==0){
			return "No Clients with a negative balance";
		}
		Cell x = start;
		StringBuilder sb = new StringBuilder();
		while (x != null) {
			sb.append(x.toString() + "\t");
			x = x.getNext();
		}
		return sb.toString();
	}

	/**
	 * a getter for list's size
	 * 
	 * @return this.size
	 */
	public int getSize() {
		return size;
	}

	public Cell getEnd() {
		return end;
	}
	
}
