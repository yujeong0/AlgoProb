//package programmers.level2;
//
//public class 삼각달팽이 {
//    public int[] solution(int n) {
//    	int[][] arr = new int[n][];
//    	boolean[][] visited = new boolean[n][];
//    	int x = 1;
//    	for (int i = 0; i < arr.length; i++) {
//			arr[i] = new int[x++];
//			visited[i] = new boolean[x++];
//		}
//    	
//    	int i = 0, j = 0, num = 1;
//    	int mode = 0;
//    	while(true) {
//    		if(i == n) {
//    			i--;
//    			j++;
//    			mode = 1;
//    		}
//    		else if(j == arr[i].length) {
//    			j--;
//    			mode = 2;
//    			i--;
//    		}
//    		else if(visited[i][j]) {
//    			switch(mode) {
//    			case 0:
//    				i--;
//    				break;
//    			case 1:
//    				j--;
//    				break;
//    			case 2:
//    				i++;
//    				break;
//    			}
//    			mode = (mode+1)%3;
//    		}
//    		arr[i][j] = num++;
//    		visited[i][j] = true;
//    		if(mode == 0) i++;
//    		else if(mode == 1) j++;
//    		else if(mode == 2) i--;
//    	}
//    	
//        int[] answer = {};
//        return answer;
//    }
//}
