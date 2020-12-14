package programmers.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class 섬연결하기 {
    public static int solution(int n, int[][] costs) {
    	N = n;
    	List<int[]> adjList = new ArrayList<>();
    	for (int i = 0; i < costs.length; i++) {
    		adjList.add(new int[] {costs[i][0], costs[i][1], costs[i][2]});
    	}

    	makeSet();
    	
    	Collections.sort(adjList, new Comparator<int[]>() {	// 가중치 작은 순으로 정렬
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2]-o2[2];
			}
    	});
    	
    	int answer = 0, count = n;
    	for(int[] arr : adjList) {
    		if(union(arr[0], arr[1])) {
    			answer += arr[2];
    			count--;
    		}
    		if(count == 0) break;
    	}
    	
        return answer;
    }
    
    public static int find(int a) {
    	if(parents[a] == a) return a;
    	return parents[a] = find(parents[a]);
    }
    
    public static boolean union(int a, int b) {
    	int aRoot = find(a);
    	int bRoot = find(b);
    	if(aRoot == bRoot) return false;
    	
    	parents[bRoot] = aRoot;
    	return true;
    }
    
    
    static int N;
    static int[] parents;
    public static void makeSet() {
    	parents = new int[N];
    	for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
    }
    
	public static void main(String[] args) {
		System.out.println(solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}}));
	}
}
