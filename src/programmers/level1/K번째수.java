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
    public static void main(String[] args) {
		new K번째수().service();
	}
    public void service() {
    	System.out.println(Arrays.toString(solution(new int[] {1, 5, 2, 6, 3, 7, 4}, 
    			new int[][] {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}})));
    }
}
