package programmers.level3;

class 멀리뛰기 {
    public long solution(int n) {
    	if(n == 1 || n == 2) return n;
    	
    	long[] arr = new long[n+1];
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i < arr.length; i++) {
			arr[i] = ((arr[i-2] % 1234567) + (arr[i-1] % 1234567)) % 1234567;
		}
        return arr[n] % 1234567;
    }
}
