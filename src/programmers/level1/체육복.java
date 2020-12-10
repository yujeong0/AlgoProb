package programmers.level1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class 체육복 {
	
	public int solution(int n, int[] lost, int[] reserve) {
		boolean[] sad = new boolean[n+1];	// sad 가 false인 애들이 수업듣기~
		List<Integer> lostList = new ArrayList<>();
		List<Integer> reserveList = new ArrayList<>();
		for (int i = 0; i < lost.length; i++) {
			lostList.add(lost[i]);
			sad[lost[i]] = true;
		}
		for (int i = 0; i < reserve.length; i++) {
			reserveList.add(reserve[i]);
		}
		
		Iterator iter = lostList.iterator();
		while(iter.hasNext()) {
			int num = (int)iter.next();
			for (int i = 0; i < reserveList.size(); i++) {
				if(reserveList.get(i) == num) {
					reserveList.remove(i);
					iter.remove();
					sad[num] = false;
					break;
				}
			}
		}
		
		iter = reserveList.iterator();
		while(iter.hasNext()) {
			int num = (int) iter.next();
			for (int j = 0; j < lostList.size(); j++) {
				if(num-1 == lostList.get(j)) {
					sad[lostList.get(j)] = false;
					lostList.remove(j);
					iter.remove();	// reserve 에서 없애기
					break;
				}
				if(num+1 == lostList.get(j)) {
					sad[lostList.get(j)] = false;
					lostList.remove(j);
					iter.remove();	// reserve 에서 없애기
					break;
				}
			}
		}
		
		int answer = 0;
		for (int i = 1; i <= n; i++) {
			if(sad[i] == false) answer++;
		}
        return answer;
    }
	
	
}
