/****************************************************
* Program Title: DHeap.java 						*
* Author: Crispin Corpuz							* 
* Class: CSCI3320, Spring 2021 						*
* Assignment #2 									* 
*****************************************************/

/**
 * Implements a d-ary heap with a starting index of 1 and not 0.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss and Crispin Corpuz
 */
public class DHeap<AnyType extends Comparable<? super AnyType>>
{
    
	// Instance variables relevant to d-ary heap (or d-heap)
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_D = 2;

    private int currentSize;	// Number of elements in heap
    private int d;				// Number of branches in heap
    private AnyType [ ] array; 	// The heap array
	
	/**
     * Construct the d-ary heap at default capacity.
     */
    public DHeap( )
    {
        this( DEFAULT_CAPACITY, DEFAULT_D );
    } // end DHeap() constructor

    /**
     * Construct the d-ary heap.
     * @param capacity the capacity of the D heap.
     */
	@SuppressWarnings("unchecked")
	public DHeap( int capacity, int d )
    {
        this.currentSize = 0;
        this.d = d;
        this.array = (AnyType[]) new Comparable[ capacity + 1 ];
    } // end DHeap( int capacity, int d )  constructor
    
    /**
     * Construct the d-ary heap given an array of items.
     */
    @SuppressWarnings("unchecked")
	public DHeap( AnyType [ ] items, int d )
    {
        this.currentSize = items.length;
        this.d = d;
        this.array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];
        
        int i = 1;
        for( AnyType item : items )
            this.array[ i++ ] = item;
        buildHeap();
    } // end DHeap ( AnyType [ ] items, int d ) constructor

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        if( currentSize == this.array.length - 1 )
            enlargeArray( this.array.length * this.d + 1 );

        // Percolate up
        int hole = ++currentSize; // last element in array
        
        // Continue comparing to get proper placement of inserted element 
        for( ; hole > 1 && x.compareTo( array[ getParent(hole) ] ) < 0; hole = getParent(hole) )
            this.array[ hole ] = this.array[ getParent(hole) ];
        this.array[ hole ] = x;
    } // end insert( AnyType x )

    /**
     * As long as newSize is larger than current array, increase length of current array
     * @param newSize the item to insert.
     */
    @SuppressWarnings("unchecked")
	private void enlargeArray( int newSize )
    {
        if (newSize > this.array.length)
        {
		AnyType [] old = this.array;
        	this.array = (AnyType []) new Comparable[ newSize ];
        	for( int i = 0; i < old.length; i++ )
            		array[ i ] = old[ i ];        
		}
    } // end enlargeArray( int newSize )
    
    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return array[ 1 ];
    } // end findMin()

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    } // end deleteMin()

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    } // end buildHeap()

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    } // end isEmpty()

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    } // end makeEmpty()
    
    /**
     * Get the d value of the current DHeap
     * @return d value of DHeap
     */
    public int getD()
    {
    	return this.d;
    } // end getD()
    
    /**
     * Change the D value of the heap, rebuilding heap as well
     * @param newD The new D value of the heap
     */
    public void setD( int newD )
    {
    	this.d = newD;
    	buildHeap();
    } // end setD( int newD )

    /** 
     * Prints out the current DHeap as a space 
     * delimited string to the console/terminal
     */
    public void print( )
    {
        for( int i = 0; i < currentSize; i++ )
            System.out.printf("%d ",  array[i+1]);
        System.out.println();
    } // end print()

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int holeIndex )
    {
        int child;
        int minIndex;
        AnyType tmp = this.array[ holeIndex ]; // object to percolate down

        // Looking down one subtree
        for( ; getChild(holeIndex) <= currentSize; holeIndex = minIndex)
        {
            child = getChild(holeIndex); // smallest index of children
            minIndex = child; // temporary index of smallest value of children
            
            // Checking each child's value and retaining index of smallest child
            for ( int i = 0; i < this.d; i++ )
            {
            	if ( child + i <= currentSize )
            		if ( this.array[ child + i ].compareTo( this.array[ minIndex ] ) < 0 )
            			minIndex = child + i;
            }
            
            // Check if smallest child value is smaller than parent (where hole is)
            // If so, replace it.
            if( this.array[ minIndex ].compareTo( tmp ) < 0 )
                this.array[ holeIndex ] = this.array[ minIndex ];
            else
                break;
        }
        this.array[ holeIndex ] = tmp; // Replaces what is being percolated down
    } // end percolateDown( int holeIndex )

    /**
     * Internal method to percolate up in the heap.
     * The logic behind this expression is based on the fact that division of integers 
     * in Java returns an integer.  Since this maps multiple numbers to a single number,
     * it is simple to make the smallest child index of a set of children of a parent 
     * large enough to be mapped to the correct parent index. 
     * @param index the index at which the child exists in the array
     */
    private int getParent( int childIndex )
    {
    	int parentIndex = (childIndex + this.d - 2) / this.d;
    	return parentIndex;
    } // end getParent( int childIndex )

    /**
     * Internal method to percolate down in the heap.
     * Based on getParent as an inverse function.
     * @param index the index at which the parent exists in the array
     */
    private int getChild( int parentIndex )
    {
    	int childIndex = (parentIndex - 1) * this.d + 2;
    	return childIndex;
    } // end getChild( int parentIndex )
} // end DHeap class

