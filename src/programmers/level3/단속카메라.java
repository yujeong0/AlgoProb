package programmers.level3;

import java.util.Arrays;
import java.util.Comparator;

public class 단속카메라 {
	public void service() {
		System.out.println(solution(new int[][] { {-20, 15},{-14, -5},{-18, -13},{-5, -3}}));
	}
    public int solution(int[][] routes) {
    	Arrays.sort(routes, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
    	});
    	
    	int answer = 0;
    	int N = routes.length;
    	boolean[] camera = new boolean[N];
    	for (int i = 0; i < N; i++) {
    		if(camera[i]) continue;
    		
    		camera[i] = true;
    		int pos = routes[i][1];
    		answer++;
    		
    		for (int j = i+1; j < N; j++) {
				if(routes[j][0] <= pos && pos <= routes[j][1]) {
					camera[j] = true;
				}
			}
		}
    	
        return answer;
    }
	
	public static void main(String[] args) {
		new 단속카메라().service();
	}
}
