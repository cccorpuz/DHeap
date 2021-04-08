/****************************************************
* Program Title: DHeap.java 						*
* Author: Crispin Corpuz							* 
* Class: CSCI3320, Spring 2021 						*
* Assignment #2 									* 
*****************************************************/

import java.util.Scanner;

public class DHeapDriver {

    /**
     * Driver program similar to given command line outputs on assignment document.
     * User friendly way to handle d-ary heap manipulation
     * @param args command line arguments (none of which are used here)
     */
    @SuppressWarnings("deprecation")
	public static void main( String [ ] args )
    {
		// Define variables that will later be instantiated
		DHeap<Integer> dHeap;
		String inputString;
		String[] inputHeap;
		Integer[] heapArray;
		int d;
		
		// Getting input for Heap
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter heap elements: ");
		inputString = scanner.nextLine();
		
		// Instantiate DHeap based on what elements were given
		if ( inputString.equals("") )
		{
			dHeap = new DHeap<Integer>();
		}
		else 
		{
			// Splitting space delimited numbers into array of string of numbers
			inputHeap = inputString.split(" ");

			// Make Integer array with correct size
			heapArray = new Integer[inputHeap.length];
	    	
			// Use Integer constructor with String parameter to prepare input to DHeap
			for ( int i = 0; i < inputHeap.length; i++ )
			{
				heapArray[i] = new Integer( inputHeap[i] ) ; 
			}
			
			// Prepare d value for input to DHeap constructor
			System.out.println();
			System.out.print("Enter d: ");
			d = scanner.nextInt();
			
			// Create DHeap object from input parameters
			dHeap = new DHeap<Integer>(heapArray, d);
		}
    	
		// Result of building heap from given heap input 
		System.out.print("Output: Heap (d=" + dHeap.getD() + "): ");
		dHeap.print();
		System.out.println();
		
		// Looping through different operations on heap based on input choice
    	int choice;
    	do {
    		System.out.println("Press 1) for insert, 2) for deleteMin, 3) for buildHeap with new d value, 4) to quit");
    		System.out.print("Enter choice: ");
    		choice = scanner.nextInt();
    		switch (choice)
    		{
    			case 1:
    				// Get input
    				System.out.print("Enter element to insert: ");
    				int input = scanner.nextInt();
    				Integer toInsert = new Integer(input); // turn to Integer object
    				
    				// Carry out insert and output result
    				dHeap.insert(toInsert);
    				System.out.print("Output: Heap (d=" + dHeap.getD() + "): ");
    				dHeap.print();
    				break;
    			case 2:
    				// Carry out deleteMin and output result
    				dHeap.deleteMin();
    				System.out.print("Output: Heap (d=" + dHeap.getD() + "): ");
    				dHeap.print();
    				break;
    			case 3:
    				// Get input
    				System.out.print("Enter d: ");
    				d = scanner.nextInt();
    				
    				// Carry out changing d value and output result
    				dHeap.setD(d);
    				System.out.print("Output: Heap (d=" + dHeap.getD() + "): ");
    				dHeap.print();
    				break;
    			case 4:
    				System.out.println("Program Terminated");
    				break;
    			default:
    				System.out.println("\"" + choice + "\" is not a valid option.");
    				break;
    		}
    		System.out.println();
    	} while (choice != 4); // end choice looping
    	
    	scanner.close();
    } // end main method of driver
} // end DHeapDriver class
