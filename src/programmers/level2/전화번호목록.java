package programmers.level2;

public class 전화번호목록 {
    public boolean solution(String[] phone_book) {
    	int N = phone_book.length;
    	
    	String a, b;
    	for (int i = 0; i < N; i++) {
			a = phone_book[i];
    		for (int j = 0; j < N; j++) {
    			if(i == j) continue;
    			b = phone_book[j];
    			boolean end = true;
				for (int k = 0; k < a.length(); k++) {
					if(k >= b.length()) {
						end = false;
						break;
					}
					if(a.charAt(k) != b.charAt(k)) {
						end = false;
						break;
					}
				}
				if(end) return false;
			}
		}
        return true;
    }
    public static void main(String[] args) {
		new 전화번호목록().service();
	}
    public void service() {
    	System.out.println(solution(new String[] {"123", "456", "789"}));
    }
}
