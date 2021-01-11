package programmers.level2;

public class 피보나치수 {
    int[] fibo = new int[100001];
	public int solution(int n) {
		fibo[1] = 1;
        return fibonacci(n) % 1234567;
    }
	public int fibonacci(int n) {
		if(n == 0 || n == 1) return n;
		if(fibo[n] != 0) return fibo[n];
		return fibo[n] = fibonacci(n-2) % 1234567 + fibonacci(n-1) % 1234567;
	}
}
