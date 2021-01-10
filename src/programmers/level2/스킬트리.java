package programmers.level2;

import java.util.Arrays;

public class 스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int[] arr = new int[skill.length()];
        
        for (int i = 0; i < skill_trees.length; i++) {
        	Arrays.fill(arr, Integer.MAX_VALUE);
        	for (int j = 0; j < skill.length(); j++) {
        		for (int k = 0; k < skill_trees[i].length(); k++) {
					if(skill.charAt(j) == skill_trees[i].charAt(k)) {
						arr[j] = k;
						break;
					}
				}
			}
        	
        	boolean success = true;
        	for (int j = 0; j < skill.length()-1; j++) {
    			if(arr[j] > arr[j+1]) {
    				success = false;
    				break;
    			}
			}
        	if(success) answer++;
		}
        
        return answer;
    }
    public static void main(String[] args) {
		new 스킬트리().service();
	}
    public void service() {
    	System.out.println(solution("CBD", new String[] {"BACDE", "CBADF", "AECB", "BDA"}));
    }
}
