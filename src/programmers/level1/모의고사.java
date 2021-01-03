package programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 모의고사 {
    public int[] solution(int[] answers) {
    	int[][] score = new int[3][2];
    	for (int i = 0; i < 3; i++) {
			score[i][0] = i;
		}
    	
    	int[] per1 = {1,2,3,4,5};
    	int[] per2 = {2,1,2,3,2,4,2,5};
    	int[] per3 = {3,3,1,1,2,2,4,4,5,5};
    	
    	int idx1 = 0, idx2 = 0, idx3 = 0;
    	for (int i = 0; i < answers.length; i++) {
			if(idx1 == 5) idx1 = 0;
			if(idx2 == 8) idx2 = 0;
			if(idx3 == 10) idx3 = 0;
			if(answers[i] == per1[idx1++]) score[0][1]++;
			if(answers[i] == per2[idx2++]) score[1][1]++;
			if(answers[i] == per3[idx3++]) score[2][1]++;
		}
    	
    	Arrays.sort(score, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[1]-o1[1];
			}
    	});
    	
    	List<Integer> list = new ArrayList<>();
    	int max = score[0][1];
    	list.add(score[0][0]);
    	for (int i = 1; i <= 2; i++) {
			if(max == score[i][1]) list.add(score[i][0]);
			else break;
    	}
    	int[] answer = new int[list.size()];
    	for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i)+1;
		}
    	return answer;
    }
    public static void main(String[] args) {
		new 모의고사().service();
	}
    public void service() {
    	System.out.println(Arrays.toString(solution(new int[] {1,3,2,4,2})));
    }
}
