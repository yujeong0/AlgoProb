package NHN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class test01 {
	private static void solution(int numOfAllPlayers, int numOfQuickPlayers, char[] namesOfQuickPlayers, int numOfGames, int[] numOfMovesPerGame) {
		int[] sulrae = new int[numOfAllPlayers];	// 각 플레이어별로 몇 번 술래 했는지 저장
		
		int[] playersPos = new int[numOfAllPlayers];	// 인덱스0 : A의 위치, 인덱스1: B의 위치...
		playersPos[0] = 0;	// A도 0부터 시작
		for (int i = 1; i < numOfAllPlayers; i++) {
			playersPos[i] = i-1;
		}
		
		int curPlayer = 0;	// 'A' 부터 술래
		sulrae[curPlayer]++;
		
		int nextPlayer = -1;
		int nextPos;
		for (int game = 0; game < numOfGames; game++) {
			if(numOfMovesPerGame[game] < 0) numOfMovesPerGame[game] += numOfAllPlayers-1;
			nextPos = (playersPos[curPlayer] + numOfMovesPerGame[game]) % numOfAllPlayers;	// 수건 놓은 위치
			
			for (int i = 0; i < numOfAllPlayers; i++) {	// nextPos 에 위치하는 플레이어 찾기
				if(playersPos[i] == nextPos) {
					nextPlayer = i;
					break;
				}
			}
			
			boolean isFast = false;
			for (int i = 0; i < numOfQuickPlayers; i++) {
				if((namesOfQuickPlayers[i] - 65) == nextPlayer) {	// 수건 받은 사람이 빠른 사람이면
					sulrae[curPlayer]++;
					playersPos[curPlayer] = playersPos[nextPlayer];
					isFast = true;
					break;
				}
			}

			if(!isFast) {	// 수건 받은 사람이 술래됨
				playersPos[curPlayer] = playersPos[nextPlayer]; 	// 수건 받은 사람 위치에 원래 술래 들어가고
				curPlayer = nextPlayer;
				sulrae[curPlayer]++;	// 수건받은사람술래카운트+1
			}
			
		} // game

		StringBuilder sb = new StringBuilder();
		
		List<Player> list = new ArrayList<>();
		for (int i = 0; i < numOfAllPlayers; i++) {
			list.add(new Player((char)(i+65), playersPos[i]));
		}
		Collections.sort(list, new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				return o1.pos- o2.pos;
			}
		});
		
		Player sul = null;
		for (Player p : list) {
			if(curPlayer == p.name-65) {
				sul = new Player(p.name, p.pos);
				continue;
			}
			
			sb.append(p.name + " " + sulrae[p.name-65]+ "\n");
		}
		sb.append(sul.name + " " + sulrae[sul.name-65]+ "\n");
		
		System.out.println(sb.toString());
	}

	private static class Player{
		char name;
		int pos;
		public Player(char name, int pos) {
			this.name = name;
			this.pos = pos;
		}
	}
	
	private static class InputData {
		int numOfAllPlayers;
		int numOfQuickPlayers;
		char[] namesOfQuickPlayers;
		int numOfGames;
		int[] numOfMovesPerGame;
	}

	private static InputData processStdin() {
		InputData inputData = new InputData();

		try (Scanner scanner = new Scanner(System.in)) {
			inputData.numOfAllPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

			inputData.numOfQuickPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
			inputData.namesOfQuickPlayers = new char[inputData.numOfQuickPlayers];
			System.arraycopy(scanner.nextLine().trim().replaceAll("\\s+", "").toCharArray(), 0,
					inputData.namesOfQuickPlayers, 0, inputData.numOfQuickPlayers);

			inputData.numOfGames = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
			inputData.numOfMovesPerGame = new int[inputData.numOfGames];
			String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
			for (int i = 0; i < inputData.numOfGames; i++) {
				inputData.numOfMovesPerGame[i] = Integer.parseInt(buf[i]);
			}
		} catch (Exception e) {
			throw e;
		}

		return inputData;
	}

	public static void main(String[] args) throws Exception {
		InputData inputData = processStdin();

		solution(inputData.numOfAllPlayers, inputData.numOfQuickPlayers, inputData.namesOfQuickPlayers,
				inputData.numOfGames, inputData.numOfMovesPerGame);
	}
}