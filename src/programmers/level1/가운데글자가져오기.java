package programmers.level1;

import java.util.*;

public class 가운데글자가져오기 {
	public String solution(String s) {
		if (s.length() % 2 == 0)
			return Character.toString(s.charAt(s.length() / 2 - 1)) + Character.toString(s.charAt(s.length() / 2));

		return Character.toString(s.charAt(s.length() / 2));
	}
}
