package data_structure;

public class _01_Stack_arr {
	public class Stack<T> {
		T[] stack;
		int top;
		int capacity;
		
		public Stack(int capacity) {
			this.capacity = capacity;
			stack = (T[]) (new Object[capacity]);
			top = -1;
		}
		
		public void push(T element) {
			if(isFull()) {
				System.out.println("stack Full");
			}
			stack[++top] = element;
		}
		
		public T pop() {
			if(isEmpty()) {
				System.out.println("empty stack");
				return null;
			}
			return stack[top--];
		}

		public T peek() {
			if(isEmpty()) {
				System.out.println("empty stack");
				return null;
			}
			return stack[top];
		}
		
		public void clear() {
			if(isEmpty()) {
				return;
			}
			top = -1;
			stack = (T[]) (new Object[capacity]);
		}
		
		public int size() {
			return top+1;
		}
		
		public boolean isEmpty() {
			return top == -1;
		}
		
		public boolean isFull() {
			return top == capacity-1;
		}
		
	}
}
