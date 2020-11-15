package programmers.level2;

public class 타겟넘버 {

	public int solution(int[] numbers, int target) {
        solve(numbers, 0, target, 0);
        
        return TOTAL;
    }
	
	static int TOTAL = 0;
	public static void solve(int[] numbers, int cur, int target, int sum) {
		if(cur == numbers.length) {
			if(sum == target) TOTAL++;
			return;
		}
		
		solve(numbers, cur+1, target, sum+numbers[cur]);
		solve(numbers, cur+1, target, sum-numbers[cur]);
	}
	
}
