package programmers.level2;

import java.util.*;

public class 압축 {

	public int[] solution(String msg) {
		List<Integer> answer = new ArrayList<>();

		// 사전 만들기
		Map<String, Integer> dict = new HashMap<>();
		char c = 'A';
		for (int i = 1; i <= 26; i++) {
			dict.put(Character.toString(c), i);
			c++;
		}
		int dictIdx = 27;
		int index;
		for (int i = 0; i < msg.length(); i++) {
			index = dict.get(Character.toString(msg.charAt(i)));
			int idx = i + 2;
			while (true) {
				if (idx - 1 >= msg.length())
					break;
				String s = msg.substring(i, idx);
				Integer val = dict.get(s);
				if (val == null) {
					dict.put(s, dictIdx++);
					break;
				}
				idx++;
				index = val;
			}
			answer.add(index);
			i = idx - 2;
		} // i

		int[] arr = new int[answer.size()];
		int i = 0;
		for (Integer n : answer) {
			arr[i++] = n;
		}
		return arr;
	}
}
