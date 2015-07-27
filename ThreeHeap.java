/**
 * CSE373 
 * @version Homework#3
 * @author Yi-Ching Oun
 * @since 2015-04-20
 * 1267202
 * UW username: youn0001
 * 
 * This ThreeHeap class implements PriorityQueue interface.
 * It allows the user to build heap where each node can take up to 3 children.
 * The top node has the highest priority and every other nodes are less important
 * than their parents. 
 */
public class ThreeHeap implements PriorityQueue{
	private int dataSize;
	private double[] array;

	/*
	 * The constructor initializes the fields
	 */
	public ThreeHeap(){
		this.array = new double[10];
		this.dataSize = 0;
	}
	
	/*
	 * {@inheritDoc}
	 * @see PriorityQueue#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return dataSize == 0;
	}

	/*
	 * {@inheritDoc}
	 * @see PriorityQueue#size()
	 */
	@Override
	public int size() {
		return dataSize;
	}

	/*
	 * {@inheritDoc}
	 * @see PriorityQueue#findMin()
	 */
	@Override
	public double findMin() {
		if (isEmpty()) {
			throw new EmptyPQException("This is an empty heap");
		}
		return array[1];
	}

	/*
	 * {@inheritDoc}
	 * @see PriorityQueue#insert(double)
	 */
	@Override
	public void insert(double x) {
		if ( dataSize == array.length-1){
			double[] newArray = new double[array.length * 2];
			for (int i = 0; i <= dataSize; i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
		dataSize ++;
		
		//percolate up
		int NewIndex = percolateUp(dataSize,x); // pass in the last index and inserted value -> return the correct location
		array[NewIndex] = x; 
	}

	/*
	 * {@inheritDoc}
	 * @see PriorityQueue#deleteMin()
	 */
	@Override
	public double deleteMin() {
		if (isEmpty()){
			throw new EmptyPQException("This is an empty heap");
		}
		int hole;
		double minValue = array[1];
		hole = percolateDown(1,array[dataSize]); 
		array[hole] = array[dataSize];
		dataSize--;
		return minValue;
	}

	/*
	 * {@inheritDoc}
	 * @see PriorityQueue#makeEmpty()
	 */
	@Override
	public void makeEmpty() {
		dataSize = 0;
	}

	/*
	 * This method reorders the heap by comparing the value of the child and its parent,
	 * if the child has the higher priority, then swap them.
	 * @param hole This is the index location of the last value
	 * @param x This is the value that we want to insert into the array
	 * @return int This returns the index of the location where to insert the value
	 */
	public int percolateUp(int hole, double x) {
		while (hole > 1 && x < array[(hole+1)/3]) { // when the inserted x is not at the top of the tree, 
												// and x has higher priority than its parents 
			array[hole] = array[(hole+1)/3];		// DO: swap the child with parent
			hole = (hole+1)/3;	// update the index location
		}
		return hole; 
	}
	
	/*
	 * This method rearranges the order of the heap by taking in the index of the first priority 
	 * and the element from the right bottom (the last element of the array) and 
	 * returns a new index location for the last element.
	 * @param hole This is the index of the node which has the highest priority
	 * @param x This is the last value of the array
	 * @return int This returns the index of the location where to delete the value
	 */
	public int percolateDown(int hole, double x){ 
		int target;
		while ((3*hole)-1 <= dataSize) { // while there are children 
			int left = 3*hole-1; 
			int mid = left+1; 
			int right = mid+1;
			
			if ((mid > dataSize) || (right > dataSize && array[left] <= array[mid]) || array[left]<=array[mid] && array[left]<=array[right]) {
				target = left;
			} else if ((right > dataSize && array[mid] <= array[left]) || (array[mid] <= array[left] && array[mid] <= array[right])) {
				target = mid;
			} else {
				target = right;
			}
			
			if (array[target] < x) {
				array[hole] = array[target];
				hole = target;
			} else
				break;		
		}
		return hole;
	}
   
	/*
	 * This toString method is used to print out the array that has been created
	 * in ThreeHeap.
	 * @return String The string array that has been created in BinaryHeap
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String temp = "";
		for (int i = 1; i < dataSize + 1; i++) {
			temp = temp + array[i] + "  ";
		}
		return temp;
	}

}
