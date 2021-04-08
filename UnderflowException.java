/****************************************************
* Program Title: UnderflowException.java 			*
* Author: Mark Allen Weiss (since code was given)	* 
* Class: CSCI3320, Spring 2021 						*
* Assignment #2 									* 
*****************************************************/

/** Exception class for access in empty containers (stacks, queues etc). */

public class UnderflowException extends RuntimeException
{
	/** 
	 * Constructs UnderflowException structure for use 
	 * when d-ary heap is empty
	 */
	public UnderflowException( String message )
	{
		super(message);
	} // end UnderflowException( String message ) constructor
	
	public UnderflowException()
    {
        super();
    } // end UnderflowException() constructor 
} // end UnderflowException class
