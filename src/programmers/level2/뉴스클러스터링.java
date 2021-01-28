package programmers.level2;

import java.util.ArrayList;
import java.util.List;

public class 뉴스클러스터링 {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        List<String> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        String tmp;
        for (int i = 0; i < str1.length()-1; i++) {
			tmp = str1.substring(i, i+2);
			if(!(tmp.charAt(0) >= 97 && tmp.charAt(0) <= 122) || !(tmp.charAt(1) >= 97 &&tmp.charAt(1) <= 122)) continue;
			if(tmp.charAt(0) == ' ' || tmp.charAt(1) == ' ') continue;
        	list1.add(tmp);
		}
        for (int i = 0; i < str2.length()-1; i++) {
        	tmp = str2.substring(i, i+2);
        	if(!(tmp.charAt(0) >= 97 && tmp.charAt(0) <= 122) || !(tmp.charAt(1) >= 97 &&tmp.charAt(1) <= 122)) continue;
        	if(tmp.charAt(0) == ' ' || tmp.charAt(1) == ' ') continue;
        	list2.add(tmp);
        }
        
        double answer = 0;
        if(list1.size() == 0 && list2.size() == 0) answer = 1;
        else {
	        List<String> same = new ArrayList<>();
	        boolean[] isSelected = new boolean[list2.size()];
	        for (int i = 0; i < list1.size(); i++) {
	        	for (int j = 0; j < list2.size(); j++) {
	        		if(!isSelected[j] && list1.get(i).equals(list2.get(j))) {
	        			same.add(list1.get(i));
	        			isSelected[j] = true;
	        			break;
	        		}
				}
			}
	        
	        answer = (double)same.size()/(double)((list1.size() + list2.size()) - same.size());
        }
        return (int) (answer * 65536);
    }
    public static void main(String[] args) {
		System.out.println(new 뉴스클러스터링().solution("FRANCE", "french"));
	}
}
