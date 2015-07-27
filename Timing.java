/**
 * CSE373
 * 
 * @version Homework#3
 * @author Yi-Ching Oun
 * @since 2015-04-20 
 * 1267202 
 * UW username: youn0001
 * 
 * This TimingTest class examines the running time of BinaryHeap,
 * ThreeHeap, and MyPQ. It test each heap implementations for at least 4
 * different value of N.
 * 
 */
public class Timing {
	public static final int NUM_TIMINGS = 5;

	public static void main(String[] args) {
		// with different N values
		int N = 1000000; // 10, 100, 1000, 10000

		// it is best to do the timing a few times because when Java can appear
		// "slower when it starts", so if you see slower results for the first
		// couple of timing runs, it is reasonable to discard them
		for (int timing = 0; timing < NUM_TIMINGS; ++timing) {
			long startTime = System.nanoTime();

			// ... The code being timed ...
			// Replace this code with your own code:
			// begin code to replace:
			//*****************************************************************

			// Test insert and delete for BinaryHeap, ThreeHeap and MyPQ running time

//			PriorityQueue BH = insertTest(new BinaryHeap(), N);
//			deleteTest(BH);

			PriorityQueue TH = insertTest(new ThreeHeap(), N);
			deleteTest(TH);
//		
//			PriorityQueue MP = insertTest(new MyPQ(), N);
//			deleteTest(MP);

			//******************************************************************
			// end code to replace
			long endTime = System.nanoTime();
			long elapsedTime = endTime - startTime;
			// 1 second = 1000000000 (10^9) nanoseconds.
			System.out.println(elapsedTime + " nanoseconds or " + elapsedTime
					/ (1000000000.0) + " seconds elapsed");

		}
	}

	/*
	 * This tests the insert function in different priority queue
	 */
	public static PriorityQueue insertTest(PriorityQueue heap, int N) {
		for (int i = 0; i < N; i++) {
			// generate the randome number as input
			double randNum = Math.random();
			heap.insert(randNum);
		}
		return heap;
	}

	/*
	 * This tests the deleteMin function in different priority queue
	 */
	public static double deleteTest(PriorityQueue heap) {
		double temp = 0;
		double delete;
		while (!heap.isEmpty()) {
			delete = heap.deleteMin();
			temp += delete;
		}
		return temp;
	}
}
