package programmers.level2;

import java.util.Arrays;
import java.util.Comparator;

public class 파일명정렬 {
    public String[] solution(String[] files) {
        Arrays.sort(files, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int idx1 = -1, idx2 = -1;
				for (int i = 0; i < o1.length(); i++) {
					if('0' <= o1.charAt(i) && o1.charAt(i) <= '9') {
						idx1 = i;
						break;
					}
				}
				for (int i = 0; i < o2.length(); i++) {
					if('0' <= o2.charAt(i) && o2.charAt(i) <= '9') {
						idx2 = i;
						break;
					}
				}
				String tmp1 = o1.substring(0, idx1);
				String tmp2 = o2.substring(0, idx2);
				
				if(!tmp1.equalsIgnoreCase(tmp2))
					return tmp1.compareToIgnoreCase(tmp2);
				
				int cnt1 = 0;
				for (int i = idx1; i < idx1+5; i++) {
					if(i == o1.length()) break;
					if(cnt1 == 5) break;
					if('0' <= o1.charAt(i) && o1.charAt(i) <= '9') {
						cnt1++;
					}
					else break;
				}
				int cnt2 = 0;
				for (int i = idx2; i < idx2+5; i++) {
					if(i == o2.length()) break;
					if(cnt2 == 5) break;
					if('0' <= o2.charAt(i) && o2.charAt(i) <= '9') {
						cnt2++;
					}
					else break;
				}
				
				return 
						Integer.parseInt(o1.substring(idx1, idx1+cnt1))
						- Integer.parseInt(o2.substring(idx2, idx2+cnt2));
			}
        });
        
        return files;
    }
    public static void main(String[] args) {
		System.out.println(
				Arrays.toString(
				new 파일명정렬().solution(new String[] {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})
		));
    }
}
