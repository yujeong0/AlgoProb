package programmers.level2;

public class 소수만들기 {
    public int solution(int[] nums) {
        N = nums.length;
        selected = new boolean[N];
        combi(0, 0, nums);
        return answer;
    }
    
    boolean isPrime(int num) {
    	for (int i = 2; i < num; i++) {
			if(num % i == 0) return false;
		}
    	return true;
    }
    
    int answer = 0;
    int N;
    boolean[] selected;
    void combi(int start, int cnt, int[] nums) {
    	if(cnt == 3) {
    		int sum = 0;
    		for (int i = 0; i < N; i++) {
				if(selected[i]) sum += nums[i];
			}
    		if(isPrime(sum)) answer++;
    		return;
    	}
    	
    	for (int i = start; i < N; i++) {
			selected[i] = true;
			combi(i+1, cnt+1, nums);
			selected[i] = false;
    	}
    }
    public static void main(String[] args) {
		System.out.println(new 소수만들기().solution(new int[] {1,2,3,4}));
	}
}
