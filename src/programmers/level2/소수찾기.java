package programmers.level2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 소수찾기 {
	int N;
	char[] nums;
	boolean[] selected;
	List<Integer> list;
	int answer = 0;
	Set<Integer> set = new HashSet<>();
	
	public int solution(String numbers) {
		N = numbers.length();
		selected = new boolean[N];
		nums = numbers.toCharArray();
		
        subset(0);
        return set.size();
    }
	
	public void subset(int cnt) {
		if(cnt == N) {
			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if(selected[i]) continue; 
				list.add(nums[i]-'0');
			}
			
			size = list.size();
			permu_selected = new boolean[size];
			selected_numbers = new int[size];
			permu(0);
			return;
		}
		
		selected[cnt] = true;
		subset(cnt+1);
		selected[cnt] = false;
		subset(cnt+1);
	}
	
	int size;
	int[] selected_numbers;
	boolean[] permu_selected;
	public void permu(int cnt) {
		if(cnt == size) {
			addPrime();
			return;
		}
		
		for (int i = 0; i < size; i++) {
			if(permu_selected[i]) continue;
			permu_selected[i] = true;
			selected_numbers[cnt] = list.get(i);
			permu(cnt+1);
			permu_selected[i] = false;
		}
	}
	
	public void addPrime() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(selected_numbers[i]);
		}
		
		if(sb.length() != 0) {
			int num = Integer.parseInt(sb.toString());
			if(num == 1 || num == 0) return;
			for (int i = 2; i < num; i++) {
				if(num % i == 0) return;
			}
			set.add(num);
		}
	}
	
	public static void main(String[] args) {
		new 소수찾기().service();
	}
	public void service() {
		System.out.println(solution("17"));
	}
}
