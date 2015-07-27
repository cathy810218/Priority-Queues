/**
 * CSE373 
 * @version Homework#3
 * @author Yi-Ching Oun
 * @since 2015-04-20
 * 1267202
 * UW username: youn0001
 * 
 * This MyPQ class implements PriorityQueue interface.
 * It allows the user to build heap where each node can take up to 4 children.
 * The top node has the highest priority and every other nodes are less important
 * than their parents. 
 * 
 */
public class MyPQ implements PriorityQueue{
	private int dataSize;
	private double[] array;
	
	/*
	 * The constructor initializes the fields
	 */
	public MyPQ() {
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
		if ( dataSize == array.length-1){
			double[] newArray = new double[array.length * 2];
			for (int i = 0; i <= dataSize; i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
		dataSize++;
		//percolate up
		int NewIndex = percolateUp(dataSize,x); // pass in the last index and inserted value -> return the correct location
		array[NewIndex] = x; 
	}
	
	/*
	 * This method reorders the heap by comparing the value of the child and its parent,
	 * if the child has the higher priority, then swap them.
	 * @param hole This is the index location of the last value
	 * @param x This is the value that we want to insert into the array
	 * @return int This returns the index of the location where to insert the value
	 */
	public int percolateUp(int hole, double x) {
		while (hole > 1 && x < array[(hole+2)/4]) { // when the inserted x is not at the top of the tree, 
												// and x has higher priority than its parents 
			array[hole] = array[(hole+2)/4];		// DO: swap the child with parent
			hole = (hole+2)/4;	// update the index location
		}
		return hole; 
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
	 * This method rearranges the order of the heap by taking in the index of the first priority 
	 * and the element from the right bottom (the last element of the array) and 
	 * returns a new index location for the last element.
	 * @param hole This is the index of the node which has the highest priority
	 * @param x This is the last value of the array
	 * @return int This returns the index of the location where to delete the value
	 */
	public int percolateDown(int hole, double x){ 
		int target;
		while ((4*hole)-2 <= dataSize) { // while there are children 
			int first = 4*hole-2; 
			int second = first + 1; 
			int third = second + 1;
			int fourth = third + 1;
			
			// for each different conditions, find the target node.
			if ((second > dataSize) || (fourth > dataSize && array[first] <= array[second] && array[first] <= third) || (third > dataSize && array[first]<=array[second]) 
					|| (array[first] <= array[second] && array[first] <= array[third] && array[first] <= array[fourth])) {
				target = first;
			} else if ((third > dataSize && array[second] <= array[first]) || (fourth > dataSize && array[second] <= array[first] && array[second] <= array[third])
					|| (array[second] <= array[first] && array[second] <= array[third] && array[second] <= array[fourth])) {
				target = second;
			} else if ((fourth > dataSize && array[third] <= array[second] && array[third] <= array[first]) || 
					(array[third] <= array[first] && array[third] <= array[second] && array[third] <= array[fourth])) {
				target = third;
			} else {
				target = fourth;
			}
			
			// once find the target node, check it's value with the last node and swap.
			if (array[target] < x) {
				array[hole] = array[target];
				hole = target;
			} else
				break;		
		}
		return hole;
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
	 * This toString method is used to print out the array that has been created
	 * in MyPQ.
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
