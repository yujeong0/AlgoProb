package programmers.level3;

public class 단어변환 {
	boolean[] selected;
	int N;
    public int solution(String begin, String target, String[] words) {
    	N = words.length;
    	selected = new boolean[N];
    	for (int i = 0; i < N; i++) {
    		if(isDiffOne(begin, words[i])) {
    			selected[i] = true;
    			dfs(1, words[i], target, words);
    			selected[i] = false;
    		}
		}
    	if(MIN == Integer.MAX_VALUE) return 0;
        return MIN;
    }
    int MIN = Integer.MAX_VALUE;
    public void dfs(int cnt, String begin, String target, String[] words) {
    	if(begin.equals(target)) {
    		if(MIN > cnt) MIN = cnt;
    		return;
    	}
    	
    	for (int i = 0; i < N; i++) {
			if(selected[i]) continue;
			if(isDiffOne(begin, words[i])) {
				selected[i] = true;
				dfs(cnt+1, words[i], target, words);
				selected[i] = false;
			}
		}
    }
    public boolean isDiffOne(String a, String b) {
    	int cnt = 0;
    	for (int i = 0; i < a.length(); i++) {
			if(a.charAt(i) != b.charAt(i)) cnt++;
			if(cnt > 1) return false;
		}
    	return true;
    }
    public static void main(String[] args) {
		new 단어변환().service();
	}
    public void service() {
    	System.out.println(solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"}));
    }
}
