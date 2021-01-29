package programmers.level2;

import java.util.ArrayList;
import java.util.List;

public class 캐시 {
	public int solution(int cacheSize, String[] cities) {
		int answer = 0;
		if (cacheSize > 0) {
			List<String> list = new ArrayList<>();
			for (int i = 0; i < cities.length; i++) {
				cities[i] = cities[i].toLowerCase();
				if (list.contains(cities[i])) {
					list.remove(cities[i]);
					list.add(cities[i]);
					answer++;
				} else {
					if (list.size() == cacheSize)
						list.remove(0);
					list.add(cities[i]);
					answer += 5;
				}
			}
		} else {
			answer += cities.length * 5;
		}
		return answer;
	}

//	public int solution(int cacheSize, String[] cities) {
//		int answer = 0;
//		if (cacheSize > 0) {
//			String[] cache = new String[cacheSize];
//			for (int i = 0; i < cacheSize; i++) {
//				cache[i] = "";
//			}
//			for (int i = 0; i < cities.length; i++) {
//				boolean isFind = false;
//				for (int j = 0; j < cacheSize; j++) {
//					if (cache[j].equalsIgnoreCase(cities[i])) {
//						answer++;
//						for (int k = j; k >= 1; k--) {
//							cache[k] = cache[k - 1];
//						}
//						cache[0] = cities[i];
//						isFind = true;
//						break;
//					}
//				}
//				if (!isFind) {
//					answer += 5;
//					for (int k = cacheSize - 1; k >= 1; k--) {
//						cache[k] = cache[k - 1];
//					}
//					cache[0] = cities[i];
//				}
//			}
//		} else {
//			answer += cities.length * 5;
//		}
//		return answer;
//	}

	public static void main(String[] args) {
		System.out.println(new 캐시().solution(3,
				new String[] { "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul" }));
	}
}
