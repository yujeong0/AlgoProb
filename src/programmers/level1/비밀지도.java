package programmers.level1;

public class 비밀지도 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        char[][] map1 = new char[n][n];
        char[][] map2 = new char[n][n];
        
        for (int i = 0; i < n; i++) {
        	String s1 = Integer.toBinaryString(arr1[i]);
        	StringBuilder sb = new StringBuilder();
        	while(s1.length()+sb.length() < n) {
        		sb.append("0");
        	}
        	sb.append(s1);
        	
        	String s2 = Integer.toBinaryString(arr2[i]);
        	StringBuilder sb2 = new StringBuilder();
        	while(s2.length()+sb2.length() < n) {
        		sb2.append("0");
        	}
        	sb2.append(s2);
        	map1[i] = sb.toString().toCharArray();
        	map2[i] = sb2.toString().toCharArray();
		}
        
        for (int i = 0; i < n; i++) {
        	StringBuilder sb = new StringBuilder();
			for (int j = 0; j < n; j++) {
				if(map1[i][j] == '1' || map2[i][j] == '1') sb.append("#");
				else if(map1[i][j] == '0' || map2[i][j] == '0') sb.append(" ");
			}
			answer[i] = sb.toString();
		}
        
        return answer;
    }
    
}
