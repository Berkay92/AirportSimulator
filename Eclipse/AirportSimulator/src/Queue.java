
public class Queue {

	private Object[] elements; 
	private int start;
	private int end;
	
	public Queue(int capacity)
	{
		elements = new Object[capacity];
		start = 0;
		end = -1;
	}
	
	
	
	void enqueue(Object element)
	{
		if(!isFull())
		{
			end++;		
			elements[end] = element;
		}
		else
		{
			System.err.println("Your queue is full !!");
		}
	}
	
	Object dequeue()
	{
		if(!isEmpty())
		{
			Object temp = elements[start];
			elements[start] = null;
			start++;
			return temp;
		}
		else
		{
			System.err.println("Your queue is empty !");
			return null;
		}
	}
	
	int size()
	{
		return end - start + 1;
	}
	
	boolean isFull()
	{
		return (end + 1 == elements.length);
	}
	
	boolean isEmpty()
	{
		return end < start;
	}
	
	
	Object peek()
	{
		if(!isEmpty())
		{
			return elements[start];
		}
		else
		{
			System.err.println("Your queue is empty !");
			return null;
		}
	}
	
	
}
