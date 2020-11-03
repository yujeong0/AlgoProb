package programmers.level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// 2020-11-03 10:03 ~ 11:09
// 코드 너무 막 짠것 같은데 ... ㅠ
public class 베스트앨범 {
	
	static class Genre {
		String name;
		int play = 0;
		List<int[]> musics = new ArrayList<>();
		public Genre(String name, int play, List<int[]> musics) {
			this.name = name;
			this.play = play;
			this.musics = musics;
		}
		
	}

	public static int[] solution(String[] genres, int[] plays) {
        int N = plays.length;
        List<Genre> myGenres = new ArrayList<>();
        for (int i = 0; i < N; i++) {
			if(myGenres.size() == 0) {
				List<int[]> list = new ArrayList<>();
				list.add(new int[] {i, plays[i]});
				myGenres.add(new Genre(genres[i], plays[i], list));
			}
			else {
				boolean isSame = false;
				for (int j = 0; j < myGenres.size(); j++) {
					if(myGenres.get(j).name.equalsIgnoreCase(genres[i])) {
						myGenres.get(j).musics.add(new int[] {i, plays[i]});
						myGenres.get(j).play += plays[i];
						isSame = true;
						break;
					}
				}
				if(!isSame) {
					List<int[]> list = new ArrayList<>();
					list.add(new int[] {i, plays[i]});
					myGenres.add(new Genre(genres[i], plays[i], list));
				}
			}
		}
        
        Collections.sort(myGenres, new Comparator<Genre>() {
			@Override
			public int compare(Genre o1, Genre o2) {
				return o2.play - o1.play;
			}
        });
        
        int[] answer = new int[2*myGenres.size()];
        int cnt = 0;
        for (int i = 0; i < myGenres.size(); i++) {
			Collections.sort(myGenres.get(i).musics, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o2[1] - o1[1];
				}
			});
			int twoCnt = 0;
			for (int[] n : myGenres.get(i).musics) {
				twoCnt++;
				answer[cnt++] = n[0];
				if(twoCnt >= 2) break;
			}
		}
        
        if(cnt < answer.length) {
        	int[] ans = new int[cnt];
        	for (int i = 0; i < cnt; i++) {
				ans[i] = answer[i];
			}
        	return ans;
        }
        
        else return answer;
    }
    
    public static void main(String[] args) {
		
//    	int[] ans = solution(new String[] {"classic", "pop", "classic", "classic", "pop"}, new int[] {500, 600, 150, 800, 2500});
//    	int[] ans = solution(new String[] {"a", "b", "a", "b", "c"}, new int[] {100, 200, 300, 400, 500});
//    	int[] ans = solution(new String[] {"a"}, new int[] {1});
//    	int[] ans = solution(new String[] {"a", "a", "a"}, new int[] {1, 1, 1});
//    	int[] ans = solution(new String[] {"a", "b", "c", "b", "c", "d"}, new int[] {1000, 1, 2, 3, 4, 5});
//    	int[] ans = solution(new String[] {"a", "a", "b", "b", "c", "c", "d", "d"}, new int[] {1, 1, 1, 1, 1, 1, 1, 1});
//    	int[] ans = solution(new String[] {"classic", "pop", "classic", "pop", "classic", "classic"}, new int[] {400, 600, 150, 2500, 500, 500});
//    	int[] ans = solution(new String[] {"a", "a", "b", "c", "a", "a"}, new int[] {1, 2, 2, 2, 2, 2});
//    	int[] ans = solution(new String[] {"a", "a", "d", "d", "c", "b"}, new int[] {5, 5, 3, 4, 500, 6});
    	int[] ans = solution(new String[] {"classic", "pop", "classic", "classic", "pop"}, new int[] {500, 600, 501, 800, 900});
    	for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
     	
	} // main
	
	
	
} // class
