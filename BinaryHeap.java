/**
 * CSE373 
 * @version Homework#3
 * @author Yi-Ching Oun
 * @since 2015-04-20
 * 1267202
 * UW username: youn0001
 * 
 * This BinaryHeap class implements PriorityQueue interface.
 * It allows the user to create a binary-kind of tree where the top
 * node has the highest priority and every other nodes are less important
 * than their parents. Each node can at most have 2 children.
 */
public class BinaryHeap implements PriorityQueue {
	private int dataSize;
	private double[] array;
	
	/*
	 * The constructor initializes the fields
	 */
	public BinaryHeap() {
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
		if (isEmpty()){
			throw new EmptyPQException("This is an empty binary heap");
		}
		return array[1]; 
	}

	/*
	 * {@inheritDoc}
	 * @see PriorityQueue#insert(double)
	 */
	@Override
	public void insert(double x) {
		// check if the array is full, if it is then we have to re-size
		if (dataSize == (array.length-1)){
			double[] newArray = new double[array.length * 2];
			for (int i = 0; i <= dataSize; i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
		dataSize++;	
		//percolate up
		int index = percolateUp(dataSize,x); // pass in the last index and inserted value -> return the correct location
		array[index] = x; 
	}

	/*
	 * {@inheritDoc}
	 * @see PriorityQueue#deleteMin()
	 */
	@Override
	public double deleteMin() {
		if(isEmpty()){
			// throw exception
			throw new EmptyPQException("This is an empty binary heap");
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
		array = new double[10];
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
		while (hole > 1 && x < array[hole/2]) { // when the inserted x is not at the top of the tree, 
												// and x has higher priority than its parents 
			array[hole] = array[hole/2];		// DO: swap the child with parent
			hole = hole /2;	// update the index location
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
		while (2*hole <= dataSize) { 
			int left = 2*hole; 
			int right = left + 1; 
			
			if (right > dataSize ||  array[left] <= array[right]) { // check which child has higher priority
				target = left;
			} else {
				target = right;
			}
			if (array[target] < x) {
				array[hole] = array[target]; // swap
				hole = target;
			} else
				break;		
		}
		return hole; 
	}

	/*
	 * This toString method is used to print out the array that has been created
	 * in BinaryHeap.
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
