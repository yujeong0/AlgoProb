package programmers.level2;

import java.util.ArrayList;
import java.util.List;

public class 방문길이 {
    public int solution(String dirs) {
        int answer = 0;
        char[] DIRS = dirs.toCharArray();
        List<int[]> list = new ArrayList<>();
        int startx = 0, starty = 0;
        int[][] dir = new int[][]{{-1, 0},{1, 0},{0, 1},{0, -1}};
        int curDir = -1;
        for (int i = 0; i < DIRS.length; i++) {
        	switch(DIRS[i]) {
        	case 'U':
        		curDir = 0;
        		break;
        	case 'D':
        		curDir = 1;
        		break;
        	case 'R':
        		curDir = 2;
        		break;
        	case 'L':
        		curDir = 3;
        		break;
        	}
        	if(isInBound(startx+dir[curDir][0], starty+dir[curDir][1])) {
	        	boolean same = false;
	        	for(int[] arr : list) {
	        		if(arr[0] == startx && arr[1] == starty) {
	        			if(arr[2] == startx+dir[curDir][0] && arr[3] == starty+dir[curDir][1]) {
	        				same = true;
	        				break;
	        			}
	        		}
	        		if(arr[0] == startx+dir[curDir][0] && arr[1] == starty+dir[curDir][1]) {
	        			if(arr[2] == startx && arr[3] == starty) {
	        				same = true;
	        				break;
	        			}
	        		}
	        	}
	        	if(!same) {
	        		list.add(new int[]{startx, starty, startx+dir[curDir][0], starty+dir[curDir][1]});
	        		answer++;
	        	}
	        	startx += dir[curDir][0];
	        	starty += dir[curDir][1];
        	}
    		else continue;
        	
		}
        
        return answer;
    }
    boolean isInBound(int x, int y) {
    	if(x > 5 || y > 5 || x < -5 || y < -5)
    		return false;
    	return true;
    }
    public static void main(String[] args) {
		System.out.println(new 방문길이().solution("ULURRDLLU"));
	}
}
