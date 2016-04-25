
public class Stack {
	private Node top;
	private int totalEntries;
	
	/**Adds to the top of the stack. Nodes can only be added to the top.*/
	public void push(Object data)
	{
		Node newNode = new Node(data);
		
		if (top != null) //if the stack is not empty
		{
			newNode.setNextNode(top); //reassign the previous top node, to keep it in the stack
			top = newNode;  //the new node becomes the top of the stack
		} 
		
		if (top == null) //if the stack is empty
		{			//no need to reassign the previous top, as there isn't one
			top = newNode; //the new node becomes the top of the stack (currently the only entry)
		}
		
		if (top.getDataElement() == newNode.getDataElement()) //checking to make sure the add was successful
			//the above condition does not work to check if the add was successful if two identical entries
			//are pushed in a row. Therefore, I was thinking of doing this instead:
			//if(top == newNode)
			//Does this improve my verifying condition? If not, consider just moving the incrementation and
			//getting rid of this.
			totalEntries++;		//and if it was, the size of the stack is incremented for isEmpty() and size()
	}
	
	/**Removes the first node, and returns it.
	 * 
	 * @return removedNode		the previously top node, which is now removed
	 */
	public Object pop()
	{
		Node removedNode = new Node();
		if(top != null) //if the stack is not empty...
		{
			removedNode.setDataElement(top.getDataElement());		
			top=top.getNextNode();
			totalEntries--;//don't want to decrement to negative int if list is empty
							//so decrement is within the if
			
		}
		//no else needed, as if the list is empty, there is nothing to remove
		return removedNode.getDataElement(); //removedNode will be null if list was empty when called
	}
	
	/**Returns the top node, so that it can be viewed. Only the top node can be seen.
	 * 
	 * @return top		the node on the top of the stack
	 */
	public Object peek()
	{
		if(isEmpty())
			return null;
		
		return top.getDataElement();
	}
	
	/**Checks if the stack is empty. Helpful for adding, deleting, etc.
	 * 
	 * @return true if the number of entries is zero.
	 */
	public boolean isEmpty()
	{
		return (totalEntries == 0);
	}
	
	/**Gives the number of entries in the stack.
	 * 
	 * @return totalEntries		the int that counts entries in the stack as they are added or deleted.
	 */
	public int size()
	{
		return totalEntries;
	}
}
