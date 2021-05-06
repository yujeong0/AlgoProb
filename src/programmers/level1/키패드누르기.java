package programmers.level1;

import java.util.*;

public class 키패드누르기 {
	public String solution(int[] numbers, String hand) {
		StringBuilder answer = new StringBuilder();
		Map<Integer, int[]> position = new HashMap<>();
		int num = 1;
		out: for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (num == 10) {
					num = 0;
					continue;
				}
				position.put(num++, new int[] { i, j });
				if (num == 1)
					break out;
			}
		}

		// 초기 위치
		int[] left = { 3, 0 }, right = { 3, 2 };

		for (int i = 0; i < numbers.length; i++) {
			int n = numbers[i];
			if (n == 1 || n == 4 || n == 7) {
				answer.append("L");
				left = position.get(n);
			} else if (n == 3 || n == 6 || n == 9) {
				answer.append("R");
				right = position.get(n);
			} else {
				int[] nPos = position.get(n);
				int leftD = Math.abs(left[0] - nPos[0]) + Math.abs(left[1] - nPos[1]);
				int rightD = Math.abs(right[0] - nPos[0]) + Math.abs(right[1] - nPos[1]);
				if (leftD == rightD) {
					if (hand.equals("right")) {
						answer.append("R");
						right = position.get(n);
					} else {
						answer.append("L");
						left = position.get(n);
					}
				} else if (leftD > rightD) {
					answer.append("R");
					right = position.get(n);
				} else {
					answer.append("L");
					left = position.get(n);
				}
			}

		}

		return answer.toString();
	}
}
