/**
 * CSE373 
 * @version Homework#3
 * @author Yi-Ching Oun
 * @since 2015-04-20
 * 1267202
 * UW username: youn0001
 * 
 * This TestingHeap class is used to test all the edge cases for each
 * priority queue: BinaryHeap, ThreeHeap, MyPQ.
 * Please enter the number 1 for testing BinaryHeap, 2 for testing ThreeHeap and 3 for MyPQ.
 * The result will print out each testing code, and if the code works, it'll print out "Pass"
 * otherwise it'll print out "Fail".
 * 
 */
import java.util.Scanner;

public class TestingHeap {
	
	public static void main(String[] args) {
		welcomeWords();
		getUserInput();
	}

	/*
	 * Test BinaryHeap, ThreeHeap and MyPQ class for edge cases
	 */
	public static void test(PriorityQueue heap) {

		// **********************************Test Empty case******************************************
		System.out.println("Test empty case: " + heap.isEmpty());
		if(heap.isEmpty()) {
			System.out.println("**Pass!**");
		} else {
			System.out.println("Fail...");
		}
		System.out.println("After inserting one node, check again");
		heap.insert(5.0);
		System.out.println("Test empty case: " + heap.isEmpty());
		if(!heap.isEmpty()) {
			System.out.println("**Pass!**");
		} else {
			System.out.println("Fail...");
		}
		System.out.println();
		
		// **********************************Test makeEmpty function**********************************
		System.out.println("Test makeEmpty function: ");
		System.out.println("Current data size: " + heap.size());
		heap.makeEmpty();
		System.out.println("After makeEmpty data size: " + heap.size());
		int size = heap.size();
		if (size == 0) {
			System.out.println("**Pass!**");
		} else {
			System.out.println("Fail...");
		}
		System.out.println();

		// **********************************Test insert & deleteMin function**********************************
		System.out.println("Test insert and deleteMin function: ");
		System.out.println("Insert: 10.0, 8.0, 20.0, 3.0, 6.0, 5.0, 12.0");
		double[] insertArray = {10.0, 8.0, 20.0, 3.0, 6.0, 5.0, 12.0};
		for (int ii = 0; ii < insertArray.length; ii++)	{
			heap.insert(insertArray[ii]);
		}
		String insertTest = heap.toString();
		System.out.println("Inserted output: " + insertTest);		
		System.out.println("Current Size: " + heap.size());
		System.out.print("Deleted order: ");
		double[] temp = new double[heap.size()];
		boolean sorted = true;
		for (int ii = 1; ii<= insertArray.length; ii++){
			temp[ii-1] = heap.deleteMin();
		}
		for (double node : temp) {
			System.out.print(node+" ");
		}
		for (int i = 0; i < temp.length -1; i++) {
			if (temp[i] > temp[i+1]) {
				sorted = false;
			}
		}
		System.out.println("");
		if (sorted == true) {
			System.out.println("**Pass!**");
		} else {
			System.out.println("Fail...");
		}
		System.out.println();
		heap.makeEmpty();
	
		// **********************************Test duplicate case**********************************
		System.out.println("Test duplicate case: ");
		System.out.println("Insert 7.0, 3.0, 6.0, 3.0, 10.0, 9.0, 6.0, 19.0, 15.0, 6.0");
		double[] testDup = {7.0, 3.0, 6.0, 3.0, 10.0, 9.0, 6.0, 19.0, 15.0, 6.0};
		for (int ii = 0; ii < testDup.length; ii++)	{
			heap.insert(testDup[ii]);
		}
		double[] temp2 = new double[heap.size()];
		sorted = true;
		for (int ii = 1; ii<= testDup.length; ii++){
			temp2[ii-1] = heap.deleteMin();
		}
			System.out.println("Deleted order: ");
		for (double node : temp2) {
			System.out.print(node+" ");
		}
		// check if the array get deleted in the right order
		for (int i = 0; i < temp2.length -1; i++) {
			if (temp2[i] > temp2[i+1]) {
				sorted = false;
			}
		}
		System.out.println("");
		if (sorted == true) {
			System.out.println("**Pass!**");
		} else {
			System.out.println("Fail...");
		}
		System.out.println();
		
		// **********************************Test full case - re-size**********************************
		System.out.println("Test full array case: ");
		System.out.println("Current Size: " + heap.size());
		for (int i = 0; i < 10; i++) {
			heap.insert(2.0);
		}
		System.out.println("A full array size: " + heap.size());
		System.out.println("After adding an extra node, check if it resizes to avoid Null Pointer Exception");
		heap.insert(2.0);
		System.out.println("New Size: " + heap.size());
		int reSize = heap.size();
		if (reSize == 11) {
			System.out.println("**Pass!**");
		} else {
			System.out.println("Fail....");
		}	
		System.out.println();
		System.out.println();

	}

	private static void welcomeWords(){
		System.out.println("Please insert the number of the priority queue that you want to test:");
		System.out.println("1. Binary Heap");
		System.out.println("2. Three Heap");
		System.out.println("3. My Priority Queue");
		System.out.println("4. Quit Testing");
	}
	
	private static void getUserInput() {
		int choice;
		Scanner input = new Scanner(System.in);
		choice = input.nextInt();
		if (choice == 1) {
			// test binary queue
			System.out.println("[ Testing BinaryHeap ... ]");
			System.out.println();
			test(new BinaryHeap());
			welcomeWords();
			getUserInput();
		} else if (choice == 2) {
			// test three heap
			System.out.println("[ Testing ThreeHeap ... ]");
			System.out.println();
			test(new ThreeHeap());
			welcomeWords();
			getUserInput();
		} else if (choice == 3){
			// test myPQ
			System.out.println("[ Testing MyPQ ... ]");
			System.out.println();
			test(new MyPQ());
			welcomeWords();
			getUserInput();
		} else {
			System.out.println("Thank you for testing!");
		}
		
	}
}
