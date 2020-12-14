package programmers.level2;

import java.util.Arrays;

// 시간 줄인다고 넘 힘들었다... 참고 코드 듣고 겨우 풀었음 세상에....
public class 구명보트 {
	public static int solution(int[] people, int limit) {
    	int N = people.length;
		
		Arrays.sort(people);		
		int count = N;
		int answer = 0;
		int front = 0, end = N-1;
		while(count > 0) {
			if(people[front]+ people[end] > limit) {
				answer++;
				count--;
				end--;
			}
			else {
				answer++;
				count-=2;
				front++;
				end--;
			}
		}
    	
        return answer;
    }
	
	public static void main(String[] args) {
		System.out.println(solution(new int[] {100, 80, 40, 20}, 100));
	}
}