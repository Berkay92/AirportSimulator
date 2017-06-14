

public class Stack {
	
	private Object[] elements; 
	


	private int current_size;
	private int stack_size;
	
	public Stack(int size)
	{
		elements = new Object[size];
		stack_size = size;
		current_size = -1;
	}
	
	
	
	void push(Object element)
	{
		if(!isFull())
		{
			current_size++;		
			elements[current_size] = element;
		}
		else
		{
			System.err.println("Your stack is full !!");
		}
	}
	
	Object pop()
	{
		if(!isEmpty())
		{
			Object temp = elements[current_size];
			elements[current_size] = null;
			current_size--;
			return temp;
		}
		else
		{
			System.err.println("Your stack is empty !");
			return null;
		}
	}
	
	int size()
	{
		return current_size + 1;
	}
	
	boolean isFull()
	{
		if((current_size + 1) == stack_size)
			return true;
		else
			return false;
	}
	
	boolean isEmpty()
	{
		if(current_size == -1)
			return true;
		else
			return false;
	}
	
	
	Object peek()
	{
		if(!isEmpty())
		{
			return elements[current_size];
		}
		else
		{
			System.err.println("Your stack is empty !");
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	public Object[] getElements() {
		return elements;
	}



	public void setElements(Object[] elements) {
		this.elements = elements;
	}



	public int getCurrent_size() {
		return current_size;
	}



	public void setCurrent_size(int current_size) {
		this.current_size = current_size;
	}

	

}
