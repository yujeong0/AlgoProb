package programmers.level3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 불량사용자 {
	static String[] user, banned;
    public int solution(String[] user_id, String[] banned_id) {
    	N = banned_id.length;
    	user = user_id;
    	banned = banned_id;
    	
    	dfs(0, new boolean[user.length], new HashSet<>());
    	
        return answer;
    }
    
    static int N, answer = 0;
    static List<Set<String>> list = new ArrayList<>();
    public void dfs(int cnt, boolean selected[], Set<String> set) {
    	if(cnt == N) {
    		for(Set<String> newSet : list) {
    			if(newSet.containsAll(set)) {
    				return;
    			}
    		}
    		
    		list.add(set);
    		answer++;
    		return;
    	}
    	
		int ban_len = banned[cnt].length();
		for (int j = 0; j < user.length; j++) {
			if(selected[j]) continue;
			if(ban_len != user[j].length()) continue;
			
			boolean same = true;
			for (int k = 0; k < ban_len; k++) {
				if(banned[cnt].charAt(k) == '*') continue;
				if(banned[cnt].charAt(k) != user[j].charAt(k)) {
					same = false;
					break;
				}
			}
			if(same) {
				boolean[] copy = new boolean[user.length];
				for (int k = 0; k < copy.length; k++) {
					copy[k] = selected[k];
				}
				copy[j] = true;
				Set<String> copySet = new HashSet<>();
				for(String s : set) {
					copySet.add(s);
				}
				copySet.add(user[j]);
				dfs(cnt+1, copy, copySet);
			}
		}
    	
    }
	public static void main(String[] args) {
		new 불량사용자().service();
	}
	public void service() {
		System.out.println(solution(
				new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"},
				new String[] {"*rodo", "*rodo", "******"})
		);
	}
}
