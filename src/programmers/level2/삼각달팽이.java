package programmers.level2;

import java.util.Arrays;

public class 삼각달팽이 {
    public int[] solution(int n) {
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
			arr[i] = new int[i+1];
		}
        
        int[][] dir = { {1, 0}, {0, 1}, {-1, -1}};
        int num = 1, i = 0, j = 0, d = 0, count = 0;
        while(true) {
        	if(count == n*(n+1)/2) break;
        	
        	if(i >= n || i < 0 || arr[i].length <= j || arr[i][j] != 0) {
        		i -= dir[d][0];
        		j -= dir[d][1];
        		d++;
        		d %= 3;
        		i += dir[d][0];
            	j += dir[d][1];
        	}
        	
        	arr[i][j] = num++;

        	i += dir[d][0];
        	j += dir[d][1];
        	
        	count++;
        }
        
        int[] answer = new int[num-1];
        i = 0;
        for (int k = 0; k < arr.length; k++) {
			for (int k2 = 0; k2 < arr[k].length; k2++) {
				answer[i++] = arr[k][k2];
			}
		}
        
        return answer;
    }
    public static void main(String[] args) {
		new 삼각달팽이().service();
	}
    public void service() {
    	System.out.println(Arrays.toString(solution(5)));
    }
}
