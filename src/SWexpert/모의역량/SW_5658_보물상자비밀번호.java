package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class SW_5658_보물상자비밀번호 {
	
	static int N, K;
	static char[] num;
	static List<Character> list = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int testcase = 1; testcase <= T; testcase++) {
			list.clear();
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// num 개수
			K = Integer.parseInt(st.nextToken());	// K번째로 큰 수
			
			num = br.readLine().toCharArray();
			for (int i = 0; i < num.length; i++) 
				list.add(num[i]);
			
			sb.append("#" + testcase + " " + solve() + "\n");
		}	// end of tc
		
		System.out.println(sb.toString());
		
	} // end of main
	
	private static int solve() {
		String[] numbers = new String[N];
		int i = 0, j = 0, count = 0;
		char c;
		
		while(count < N/4) {
			j = 0;
			while(true) {
				if(j != 0 && j % (N/4) == 0) i++;
				if(j == N) break;
				
				c = list.get(j);
				if(numbers[i] == null) numbers[i] = Character.toString(c);
				else numbers[i] += c;
				j++;
			}
			
			list.add(0, list.get(list.size()-1));
			list.remove(list.size()-1);
			count++;
		}
		
		Set<String> set = Arrays.stream(numbers).collect(Collectors.toSet());
		List<String> list1 = new ArrayList<>(set);
		Collections.sort(list1, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Integer.parseInt(o2, 16)-Integer.parseInt(o1, 16);	// 내림차순
			}
		});
		
		return Integer.parseInt(list1.get(K-1), 16);
	}
	
}	// end of class
