package data_structure;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class _01_Stack_arr {
	public static void main(String[] args) throws Exception {
		new _01_Stack_arr().solve();
	}
	private void solve() throws Exception {
		Stack<Integer> stack = new Stack<Integer>(5);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			int a = Integer.parseInt(br.readLine());
			
			switch(a) {
			case 1: {
				int b = Integer.parseInt(br.readLine());
				stack.push(b);
				break;
			}
			case 2: {
				System.out.println(stack.pop());
				break;
			}
			case 3: {
				System.out.println(stack.peek());
				break;
			}
			}
		}
	
	}
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
				return;
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
