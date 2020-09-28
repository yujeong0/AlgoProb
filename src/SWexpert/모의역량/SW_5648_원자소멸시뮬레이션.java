package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class SW_5648_원자소멸시뮬레이션 {
	static class Atom {
		double x, y; 
		int d, energy;
		
		Atom(double x, double y, int d, int energy) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.energy = energy;
		}
		Atom(Atom a){
			this.x = a.x;
			this.y = a.y;
			this.d = a.d;
			this.energy = a.energy;
		}

		@Override
		public String toString() {
			return "Atom [x=" + x + ", y=" + y + ", d=" + d + ", energy=" + energy + "]";
		}
		
		
	}
	
	// { x, y } 상/하/좌/우
	static double[][] dir = { {0, 0.5}, {0, -0.5}, {-0.5, 0}, {0.5, 0} };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int SUM, N;
		int x, y, d, energy;
		List<Atom> atomList = new ArrayList<>();
		List<Integer> crushedList = new ArrayList<>();
		
		for (int testcase = 1; testcase <= T; testcase++) {
			atomList.clear();
			SUM = 0;
			N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				energy = Integer.parseInt(st.nextToken());
				
				atomList.add(new Atom(x, y, d, energy));
			}	// end of input
			
			Atom me, you;
			boolean crush, remove;
			Iterator<Atom> iter1, iter2; 
			Iterator<Integer> iter3;
			int i, j;
			
			while(true) {
				if(atomList.size() == 0 || atomList.size() == 1) break;	// 원자 없으면 끝! 한 개여도 충돌 x
				
				crushedList.clear();
				iter1 = atomList.iterator();
				i = 0;
				while(iter1.hasNext()) {
					remove = false;
					crush = false;
					me = iter1.next();
					me.x = me.x + dir[me.d][0];
					me.y = me.y + dir[me.d][1];
					atomList.set(i, me);
					
					if(me.x > 1000 || me.x < -1000 || me.y > 1000 || me.y < -1000) {
						iter1.remove(); 	//영원히 소멸 안 되는 것들 지우기
						remove = true;
						continue;
					}

					iter2 = atomList.iterator();
					j = 0;
					while(iter2.hasNext()) {
						if(i == j) {
							iter2.next();
							j++;
							continue;
						}
						
						you = iter2.next();
						
						if(me.x == (you.x + dir[you.d][0]) && me.y == (you.y + dir[you.d][1])) {
							crush = true;
							crushedList.add(j);
						}
						j++;
					}
					
					if(crush) crushedList.add(i);
					
					if(!remove)	i++;	// 1000넘어가서 지웠으면 인덱스 +1 안해도 된다.
				}	// 0.5초 끝
				
				// 모든  충돌 다 고려했으니 소멸하는 친구들 없애기
				iter3 = crushedList.iterator();
				int n;
				while(iter3.hasNext()) {
					n = iter3.next();
					SUM += atomList.remove(n).energy;
				}
				
			}	// 0.5초 이동 반복
			
			sb.append("#"+ testcase + " " + SUM +"\n");
		}	// end of tc
		
	}	// end of main
}
