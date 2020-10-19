package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// 시간초과
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
	static double[][] dir = { {0, 1}, {0, -1}, {-1, 0}, {1, 0} };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int SUM, N;
		int x, y, d, energy;
		List<Atom> atomList = new ArrayList<>();
		Set<Integer> crushedList = new HashSet<>();
		
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
			boolean crush;
			Iterator<Atom> iter1; 
			int i;
			while(true) {
				if(atomList.size() <= 1) break;	// 원자 없으면 끝! 한 개여도 충돌 x
				
				crushedList.clear();
				iter1 = atomList.iterator();
				i = 0;
				while(iter1.hasNext()) {
					crush = false;
					me = iter1.next();
					me.x = me.x + dir[me.d][0];
					me.y = me.y + dir[me.d][1];
					atomList.set(i, me);
					
					if(me.x > 1000 || me.x < -1000 || me.y > 1000 || me.y < -1000) {
						iter1.remove(); 	//영원히 소멸 안 되는 것들 지우기
						continue;
					}

					for(int j = i+1; j < atomList.size(); j++) {
						you = atomList.get(j);
						
						if(me.x == (you.x + dir[you.d][0]) && me.y == (you.y + dir[you.d][1])) {
							crush = true;
							crushedList.add(j);
						}
					}
					
					if(crush) crushedList.add(i);
					
					i++;	
				}	// 1초 끝
				
				// 모든  충돌 다 고려했으니 소멸하는 친구들 없애기
				if(crushedList.size() > 0) {
					for(int n = crushedList.size()-1; n >= 0; n--) {
						SUM += atomList.remove(n).energy;
					}
				}
			}	// 0.5초 이동 반복
			
			sb.append("#"+ testcase + " " + SUM +"\n");
		}	// end of tc
		
		System.out.println(sb.toString());
		
	}	// end of main
}
