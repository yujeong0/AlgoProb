package programmers.level1;

public class 약수의개수와덧셈 {
	public int solution(int left, int right) {
		int answer = 0;
		for (int n = left; n <= right; n++) {
			int d = 2, cnt = 1;
			while (d <= n) {
				if (n % d == 0) {
					cnt++;
				}
				d++;
			}

			if (cnt % 2 == 0)
				answer += n;
			else
				answer -= n;
		}

		return answer;
	}
}
