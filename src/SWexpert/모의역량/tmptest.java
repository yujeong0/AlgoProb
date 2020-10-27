package SWexpert.모의역량;

public class tmptest {

	public static void main(String[] args) {
		int[] arr = {1,2,3};
		
		func(arr);
		
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
	}
	
	public static void func(int[] ary) {
		ary[1] = 0;
		
	}
}
