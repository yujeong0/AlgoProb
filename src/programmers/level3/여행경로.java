package programmers.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 여행경로 {
	static int N;
	static boolean[] visited;
	static List<String> routes = new ArrayList<>();
	static boolean END = false;
	public static String[] solution(String[][] tickets) {
		N = tickets.length;
		visited = new boolean[N];
		Arrays.sort(tickets, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				if(o1[0].compareTo(o2[0]) == 0) {
					return o1[1].compareTo(o2[1]);
				}
				return o1[0].compareTo(o2[0]);
			}
		});
		
		for (int i = 0; i < N; i++) {
			if(tickets[i][0].equals("ICN")) {
				routes.add(tickets[i][0]);
				visited[i] = true;
				dfs(tickets, i);
				
				if(routes.size() == N+1) break;
				else {
					Arrays.fill(visited, false);
					routes.clear();
				}
			}
		}
		
        String[] answer = new String[routes.size()];
        
        int i = 0;
        for(String s : routes) {
        	answer[i++] = s;
        }
        return answer;
    }
	
	public static void dfs(String[][] tickets, int cur) {
		routes.add(tickets[cur][1]);
		
		for (int i = 0; i < N; i++) {
			if(!visited[i] && tickets[i][0].equals(tickets[cur][1])) {
				visited[i] = true;
				dfs(tickets, i);
				
				if(routes.size() != N+1) {
					visited[i] = false;
					routes.remove(routes.size()-1);
				}
				else break;
			}
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(
				solution(new String[][] {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}}
			)));
	}

}
