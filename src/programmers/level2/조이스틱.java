package programmers.level2;

public class 조이스틱 {
	
	static int N;
	static int[] word;
	public static int solution(String name) {
		N = name.length();
		word = new int[N];
		int[] me = new int[N];
		for (int i = 0; i < N; i++) {
			me[i] = 65;
		}
		for (int i = 0; i < N; i++) {
			word[i] = name.charAt(i);
		}
		dfs(me, 0, 0);
        return MIN;
    }
	
	static int MIN = Integer.MAX_VALUE;
	static void dfs(int[] curWord, int cur, int cnt) {
		if(90 - word[cur]+1 < word[cur]-65) {
			cnt += 90 - word[cur]+1;
		}
		else cnt += word[cur]-65;
		
		curWord[cur] = word[cur];
		
		boolean isEnd = true;
		for (int i = 0; i < N; i++) {
			if(curWord[i] != word[i]) {
				isEnd = false;
				break;
			}
		}
		if(isEnd) {
			if(MIN > cnt) MIN = cnt;
			return;
		}
		
		int rIdx = cur+1;
		int rCnt = 1;
		while(true) {
			if(rIdx >= N) rIdx -= N;
			if(word[rIdx] != curWord[rIdx]) break;
			rIdx++;
			rCnt++;
		}
		int lIdx = cur-1;
		int lCnt = 1;
		while(true) {
			if(lIdx < 0) {
				lIdx += N;
			}
			if(word[lIdx] != curWord[lIdx]) break;
			lIdx--;
			lCnt++;
		}
		
		if(rCnt < lCnt) {
			int[] copy = new int[N];
			for (int i = 0; i < N; i++) {
				copy[i] = curWord[i];
			}
			dfs(copy, rIdx, cnt+rCnt);
		}
		else if(rCnt > lCnt) {
			int[] copy = new int[N];
			for (int i = 0; i < N; i++) {
				copy[i] = curWord[i];
			}
			dfs(copy, lIdx, cnt+lCnt);
		}
		else {
			int[] copy = new int[N];
			for (int i = 0; i < N; i++) {
				copy[i] = curWord[i];
			}
			dfs(copy, rIdx, cnt+rCnt);
			int[] copy2 = new int[N];
			for (int i = 0; i < N; i++) {
				copy2[i] = curWord[i];
			}
			dfs(copy2, lIdx, cnt+lCnt);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(solution("ABAAAAABAB"));
	}
}
