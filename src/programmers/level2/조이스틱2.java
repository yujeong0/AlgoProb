package programmers.level2;

import java.util.ArrayList;
import java.util.List;

public class 조이스틱2 {
	static List<Integer> list = new ArrayList<>();	// A가 아닌 위치들 넣는 list
	static int N;
	static int[] word, me;
	static int MIN = Integer.MAX_VALUE;
	public static int solution(String name) {
		N = name.length();
		word = new int[N];
		me = new int[N];
		for (int i = 0; i < N; i++) {
			me[i] = 65;
		}
		
		for (int i = 0; i < N; i++) {
			word[i] = name.charAt(i);
			if(word[i] != 65) list.add(i);
		}
		int remain = list.size();
		
		dfs(0, 0, remain);
		
        return MIN;
    }
	
	private static void dfs(int cur, int cnt, int remain) {
		if(remain == 0) {
			if(MIN > cnt) MIN = cnt;
			return;
		}
		
		if(word[cur] == me[cur]) {
			dfs(cur+1, cnt+1, remain);
			dfs(cur-1, cnt+1, remain);
		}
		else {
			dfs(cur+1, cnt+90 - word[cur]+1, remain-1);
			dfs(cur-1, cnt+90 - word[cur]+1, remain-1);
			dfs(cur+1, cnt+word[cur]-65, remain-1);
			dfs(cur-1, cnt+word[cur]-65, remain-1);
		}
		
	}

	public static void main(String[] args) {
		System.out.println(solution("ABABAAAAABA"));
	}
}
