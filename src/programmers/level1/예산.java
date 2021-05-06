package programmers.level1;

import java.util.*;

public class 예산 {
	public int solution(int[] d, int budget) {
		int answer = 0;
		int sum = 0;
		Arrays.sort(d);
		for (int i = 0; i < d.length; i++) {
			sum += d[i];
			if (sum > budget) {
				sum -= d[i];
			} else {
				answer++;
			}
		}
		return answer;
	}
}
