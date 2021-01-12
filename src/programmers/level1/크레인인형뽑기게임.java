package programmers.level1;

import java.util.ArrayList;
import java.util.List;

public class 크레인인형뽑기게임 {
    public int solution(int[][] board, int[] moves) {
    	int N = board.length;
    	int answer = 0;
    	List<Integer> list = new ArrayList<>();
    	
    	for (int i = 0; i < moves.length; i++) {
    		for (int i2 = 0; i2 < N; i2++) {
				if(board[i2][moves[i]-1] == 0) continue;
				
				list.add(board[i2][moves[i]-1]);
				board[i2][moves[i]-1] = 0;
				break;
			}
    		if(list.size() > 1 && list.get(list.size()-1) == list.get(list.size()-2)) {
    			list.remove(list.size()-1);
    			list.remove(list.size()-1);
    			answer += 2;
    		}
		}
    	
        return answer;
    }
}
