package programmers.level1;

import java.util.Arrays;

public class K번째수 {
    public int[] solution(int[] array, int[][] commands) {
    	int[] answer = new int[commands.length];
    	
    	for (int i = 0; i < commands.length; i++) {
    		int size = commands[i][1]-commands[i][0]+1;
			int[] arr = new int[size];
			int idx = 0;
			for (int j = commands[i][0]-1; j <= commands[i][1]-1; j++) {
				arr[idx++] = array[j];
			}
			Arrays.sort(arr);
			answer[i] = arr[commands[i][2]-1];
		}
    	
        return answer;
    }
}
