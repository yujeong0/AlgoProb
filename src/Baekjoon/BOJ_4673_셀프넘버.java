package Baekjoon;

public class BOJ_4673_셀프넘버 {
	static boolean[] nums = new boolean[10001];
	public static void main(String[] args) {
		int n = 1;
		while(n <= 10000) {
			int result = devide10(n);
			if(result <= 10000) {
				nums[result] = true;
			}
			n++;
		}
		
		for (int i = 1; i <= 10000; i++) {
			if(!nums[i]) System.out.println(i);
		}
		
	} // main
	
	static int devide10(int n) {
		int sum = n;
		while(n >= 1) {
			sum += n % 10;
			n /= 10;
		}
		return sum;
	}
}
