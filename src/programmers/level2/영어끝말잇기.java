package programmers.level2;

import java.util.HashSet;
import java.util.Set;

public class 영어끝말잇기 {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        
        Set<String> set = new HashSet<>();
        int round = 1, order = 1;
        for (int i = 0; i < words.length; i++) {
			if(set.contains(words[i])) {
				answer[0] = order;
				answer[1] = round;
				break;
			}
			if(i > 0 && words[i-1].charAt(words[i-1].length()-1) != words[i].charAt(0)) {
				answer[0] = order;
				answer[1] = round;
				break;
			}
			set.add(words[i]);
			order++;
			if(order > n) {
				order = 1;
				round++;
			}
		}
        
        return answer;
    }
}
