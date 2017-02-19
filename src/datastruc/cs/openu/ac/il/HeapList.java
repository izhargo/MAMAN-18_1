package datastruc.cs.openu.ac.il;

/**
 * 
 * @author Yizhar
 * 
 */
public class HeapList extends LinkedList{
	
	
	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}
	/**
	 * Method inserts a new key to heap, while maintaining the rule of heap data
	 * structure.
	 * 
	 * @param val
	 *            - new value to be inserted
	 */
	@Override
	public void InsertAtEnd(int val) {
		super.InsertAtEnd(Integer.MIN_VALUE);
		MaxHeapIncreaseKey(super.getSize(), val);
	}
	/**
	 * Method deletes the given cell from the heap, while
	 * maintaining the rule of heap data structure.
	 * 
	 * @param cell
	 *            - cell designated to deletion.
	 */
	@Override
	public void Delete(Cell cell) {
		int val = cell.getData();
		Cell newNode = super.getCell(super.getSize());
		super.Delete(newNode);
		if (val == newNode.getData()) {
			return;
		} else if (val > newNode.getData()) {
			cell.setData(newNode.getData());
			MaxHeapify(index);
		} else {
			MaxHeapIncreaseKey(index, newNode.getData());
		}
	}

	@Override
	public Cell Search(int data) {
		// TODO Auto-generated method stub
		return super.Search(data);
	}

	@Override
	public Cell getCell(int i) {
		// TODO Auto-generated method stub
		return super.getCell(i);
	}

	@Override
	public void swap(int index1, int index2) {
		// TODO Auto-generated method stub
		super.swap(index1, index2);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return super.getSize();
	}

	/**
	 * Constructor creates an empty HeapList
	 */
	public HeapList() {
		heap = new LinkedList();
	}

	private int Left(int parent) {
		return 2 * parent;
	}

	private int Right(int parent) {
		return (2 * parent) + 1;
	}

	private int Parent(int child) {
		return (int) Math.floor(child / 2);
	}

	/**
	 * The Main method of the class, which maintains the data organized in
	 * accordance with the heap data structure rule.
	 * 
	 * @param root
	 *            - the node we need to heapify
	 */
	private void MaxHeapify(int root) {
		int largest;
		int i = root;
		int l = Left(root);
		int r = Right(root);
		if ((l <= heap.getSize())
				&& (heap.getListItem(l).getData() > heap.getListItem(i)
						.getData())) {
			largest = l;
		} else {
			largest = i;
		}
		if ((r <= heap.getSize())
				&& (heap.getListItem(r).getData() > heap.getListItem(i)
						.getData())) {
			largest = r;
		}
		if (largest != i) {
			heap.swap(i, largest);
			MaxHeapify(largest);
		}
	}

	/**
	 * This method is used to increase a certain key in the heap, so that the
	 * data will maintain the rule of heap data structure.
	 * 
	 * @param index
	 *            - the place of the cell whose key is about to be increased
	 * @param NewKey
	 *            - the new value for the cell in the list. If NewKey is smaller
	 *            than the current key - method does nothing.
	 */
	public void MaxHeapIncreaseKey(int index, int NewKey) {
		if (NewKey < heap.getListItem(index).getData()) {
			return;
		}
		heap.getListItem(index).setData(NewKey);
		while ((index > 1)
				&& (heap.getListItem(index).getData() > heap.getListItem(
						Parent(index)).getData())) {
			heap.swap(index, Parent(index));
			index = Parent(index);
		}
	}

	

	/**
	 * Method deletes the key placed in the given index from the heap, while
	 * maintaining the rule of heap data structure.
	 * 
	 * @param index
	 *            - index of the value designated to deletion.
	 */
	public void MaxHeapDelete(int index) {
		int val = heap.getListItem(index).getData();
		Cell newNode = heap.getListItem(heap.getSize());
		heap.listDelete(newNode);
		if (val == newNode.getData()) {
			return;
		} else if (val > newNode.getData()) {
			heap.getListItem(index).setData(newNode.getData());
			MaxHeapify(index);
		} else {
			MaxHeapIncreaseKey(index, newNode.getData());
		}
	}
	public String toString(){
		return heap.toString();
	}
}