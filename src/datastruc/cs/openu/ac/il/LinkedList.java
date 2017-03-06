package datastruc.cs.openu.ac.il;

/**
 * Class LinkedList is a simple implementation of a doubly linked list. Class
 * will ne used as a substance for the ListHeap data structure.
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
	public void InsertAtEnd(int val) {
		Cell x = new Cell(val);
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
	/**
	 * Method returns the index number of the given cell in the LinkedList.
	 * 
	 * @param cell
	 * @return If cell is in the LinkedList - returns its place in the list. Otherwise, 
	 * if this cell isn't in the LinkedList - method returns -1.
	 */
	public int getCellIndex (Cell cell){
		Cell x = start;
		for (int i=1;i<=getSize();i++){
			if (x==cell){
				return i;
			}
			x = x.getNext();
		}
		return -1;
		
	}
	/**
	 * Given a value, the method searches for the value in the linked list.
	 * 
	 * @param data
	 * @return Cell x if its data is the same as the parameter's, null otherwise
	 */
	public Cell Search(int data) {
		Cell x = start;
		while ((x != null) && (x.getData() != data)) {
			x = x.getNext();
		}
		return x;
	}

	/**
	 * Given a place in the list, the method return the cell located in that
	 * place in the list
	 * 
	 * @param i
	 * @return if 1<=i<=list's size then return the i'th cell in the list.
	 *         Otherwise, return null.
	 */
	public Cell getCell(int i) {
		if ((i >= 1) && (i <= size)) {
			Cell x = start;
			for (int j = 1; j < i; j++) {
				x = x.getNext();
			}
			return x;
		} else {
			return null;
		}
	}

	/**
	 * Swap between two list values according to their place in the list.
	 * If one the indices is not in the proper range - method does nothing.
	 * @param index1 - list "index" for first value
	 * @param index2 - list "index" for second value
	 */
	public void swap(int index1, int index2) {
		if ((index1 < 1) || (index1 > size) || (index2 < 1) || (index2 > size)){
			return;
		}
		Cell cell1 = getCell(index1);
		Cell cell2 = getCell(index2);
		Cell temp = new Cell(cell1.getData());
		temp.setNext(cell1.getNext());
		temp.setPrev(cell1.getPrev());
		
		//setting up next of the previous nodes for both cell1 and cell2 
		cell1.getPrev().setNext(cell2);
		cell2.getPrev().setNext(cell1);
		//setting up next current nodes cell1, cell2
		cell1.setNext(cell2.getNext());
		cell2.setNext(temp.getNext());
		cell1.setPrev(cell2.getPrev());
		cell2.setPrev(temp.getPrev());
		//setting up prev of the next nodes for both cell1 and cell2 
		cell1.getNext().setPrev(cell1);
		cell2.getNext().setPrev(cell2);
	
	}

	/**
	 * Returns the list's values, from first to last as a String object.
	 */
	public String toString() {
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
}
