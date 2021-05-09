package summercoding2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution1 {
	class INFO {
		int year,month,date,hour; 
		int price;
		public INFO(int year, int month, int date, int hour, int price) {
			this.year = year;
			this.month = month;
			this.date = date;
			this.hour = hour;
			this.price = price;
		}
	}

	public int[] solution(String code, String day, String[] data) {
		int year = Integer.parseInt(day.substring(0, 4));
		int month = Integer.parseInt(day.substring(4, 6));
		int date = Integer.parseInt(day.substring(6, 8));
		
        List<INFO> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
        	String[] info = data[i].split(" ");
        	if(info[1].substring(5).equals(code)) {
        		if(info[2].substring(5, info[2].length()-2).equals(day)) {
        			list.add(new INFO(
        				year,month,date,Integer.parseInt(info[2].substring(13)),
						Integer.parseInt(info[0].substring(6))
        			));
        		}
        	}
		}
        
        Collections.sort(list, new Comparator<INFO> () {
			@Override
			public int compare(INFO o1, INFO o2) {
				if(o1.year == o2.year) {
					if(o1.month == o2.month) {
						if(o1.month == o2.month) {
							return o1.hour - o2.hour;
						}
						else return o1.date - o2.date;
					}
					else return o1.month - o2.month;
				}
				else return o1.year-o2.year;
			}
        });
        int[] answer = new int[list.size()];
        int idx = 0;
        for(INFO info : list) {
        	answer[idx++] = info.price;
        }
        return answer;
    }
}
