package programmers.level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 오픈채팅방 {
    public String[] solution(String[] record) throws Exception {
		Map<String, String> userMap = new HashMap<>();
		String[] arr;
		int cnt = 0;
		for (int i = 0; i < record.length; i++) {
			arr = record[i].split(" ");
			if(arr[0].equals("Enter")) {
				cnt++;
				userMap.put(arr[1], arr[2]);
			}
			else if(arr[0].equals("Change")) {
				userMap.put(arr[1], arr[2]);
			}
			else {
				cnt++;
			}
		}

		String[] answer = new String[cnt];
		int idx = 0;
		for (int i = 0; i < record.length; i++) {
			arr = record[i].split(" ");
			if(arr[0].equals("Enter")) {
				answer[idx++] = userMap.get(arr[1]) + "님이 들어왔습니다.";
			}
			else if(arr[0].equals("Leave")) {
				answer[idx++] = userMap.get(arr[1]) + "님이 나갔습니다.";
			}
		}
		return answer;
    }
    public static void main(String[] args) throws Exception {
		System.out.println(Arrays.toString(new 오픈채팅방().solution(new String[] 
				{		"Enter uid1234 Muzi", 
						"Enter uid4567 Prodo",
						"Leave uid1234",
						"Enter uid1234 Prodo",
						"Change uid4567 Ryan"})));
	}
}
