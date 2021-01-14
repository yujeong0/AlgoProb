package programmers.level1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class 두개뽑아서더하기 {
    public int[] solution(int[] numbers) {
    	Set<Integer> set = new HashSet<>();
    	for (int i = 0; i < numbers.length; i++) {
    		for (int j = i+1; j < numbers.length; j++) {
				set.add(numbers[i] + numbers[j]);
			}
		}
    	int[] answer = new int[set.size()];
    	Iterator iter = set.iterator();
    	int i = 0;
    	while(iter.hasNext()) {
    		answer[i++] = (int) iter.next();
    	}
    	Arrays.sort(answer);
        return answer;
    }
    public static void main(String[] args) {
		new 두개뽑아서더하기().service();
	}
    public void service() {
    	System.out.println(Arrays.toString(solution(new int[] {2,1,3,4,1})));
    }
}
