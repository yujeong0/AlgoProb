package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 모의SW_2383_점심식사시간 {
	static class Pos {
		int x, y;
		public Pos() {}
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static class Person {
		int num, remainTime;
		public Person() {}
		public Person(int num, int remainTime) {
			this.num = num;
			this.remainTime = remainTime;
		}
	}
	static int N;
	static int[] stairLen = new int[2];
	static Pos[] stairs = new Pos[2];
	static List<Pos> people;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MIN = Integer.MAX_VALUE;
			people = new ArrayList<>();
			for (int i = 0; i < 2; i++) {
				stairs[i] = new Pos();
			}
			N = Integer.parseInt(br.readLine());
			
			int idx=  0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if(num == 1) {
						people.add(new Pos(i, j));
					}
					else if(num >= 2) {
						stairLen[idx] = num;
						stairs[idx].x = i;
						stairs[idx++].y = j;
					}
				}
			}
			peopleStairNum = new int[people.size()];
			
			subset(0);
			
			System.out.println("#" + testcase + " " + MIN);
		}
	} //main
	
	private static int[] peopleStairNum;
	private static int MIN;
	private static void subset(int cnt) {
		if(cnt == people.size()) {
			int time = solve();
			if(MIN > time-1) MIN = time-1;
			return;
		}
		
		peopleStairNum[cnt] = 0;
		subset(cnt+1);
		peopleStairNum[cnt] = 1;
		subset(cnt+1);
	} // subset
	
	private static int solve() {
		Queue<Person> stair1 = new LinkedList<>();
		Queue<Person> stair2 = new LinkedList<>();
		Queue<Person> wait1 = new LinkedList<>();
		Queue<Person> wait2 = new LinkedList<>();
		
		int t = 0, cnt = 0;
		while(true) {
			if(cnt == people.size() && stair1.size() == 0 && stair2.size() == 0 && wait1.size() == 0 && wait2.size() == 0) break;
			
			int size = wait1.size();
			while(size > 0) {
				Person p = wait1.poll();
				p.remainTime--;
				wait1.add(p);
				size--;
			}
			size = wait2.size();
			while(size > 0) {
				Person p = wait2.poll();
				p.remainTime--;
				wait2.add(p);
				size--;
			}
			
			if(cnt < people.size()) {
				for (int i = 0; i < peopleStairNum.length; i++) {
					if(peopleStairNum[i] == 0) {
						if(t == Math.abs(people.get(i).x - stairs[0].x) + Math.abs(people.get(i).y - stairs[0].y)) {
							wait1.add(new Person(i, 1));
							cnt++;
						}
					}
					else if(peopleStairNum[i] == 1) {
						if(t == Math.abs(people.get(i).x - stairs[1].x) + Math.abs(people.get(i).y - stairs[1].y)) {
							wait2.add(new Person(i, 1));
							cnt++;
						}
					}
				}
			}
			
			size = stair1.size();
			while(size > 0) {
				Person p = stair1.poll();
				p.remainTime--;
				if(p.remainTime > 0) stair1.add(p); 
				size--;
			}
			size = wait1.size();
			while(size > 0) {
				if(stair1.size() == 3) break;
				if(wait1.peek().remainTime <= 0) {
					Person p = wait1.poll();
					stair1.add(new Person(p.num, stairLen[0]));
				}
				size--;
			}
			size = stair2.size();
			while(size > 0) {
				Person p = stair2.poll();
				p.remainTime--;
				if(p.remainTime > 0) stair2.add(p); 
				size--;
			}
			size = wait2.size();
			while(size > 0) {
				if(stair2.size() == 3) break;
				if(wait2.peek().remainTime <= 0) {
					Person p = wait2.poll();
					stair2.add(new Person(p.num, stairLen[1]));
				}
				size--;
			}
			
			t++;
		} // while
		
		return t;
	} //solve
}
