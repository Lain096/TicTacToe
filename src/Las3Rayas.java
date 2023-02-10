import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Las3Rayas {

// modificacion desde master 

	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	static List<List> winning = new ArrayList<List>();

	public static void main(String[] args) {

		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);

		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);

		List botRightDiag = Arrays.asList(1, 5, 9);
		List botLeftDiag = Arrays.asList(3, 5, 7);

		// añadimos las listas dentro de otras listas para comprobar todas a la vez y no
		// una a una

		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(rightCol);
		winning.add(midCol);
		winning.add(botRightDiag);
		winning.add(botLeftDiag);

		// hacemos un array 2D, por eso el [][]
		char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };

		// llamamos al metodo para pintar el tablero
		printGameBoard(gameBoard);

		do {
			Scanner scan = new Scanner(System.in);

			System.out.println("Introduce tu posicion (1-9)");

			int playerPos = scan.nextInt();

			while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {

				System.out.println("Posición cogida, elige otra");
				playerPos = scan.nextInt();
			}

			positionBoard(gameBoard, playerPos, "player");

			String result = checkWinner();

			if (result.length() > 0) {
				System.out.println(result);

			}

			Integer nextCPUPosition = null;

			int cpuPos;

			if (nextCPUPosition == null) {
				Random maquina = new Random();

				cpuPos = maquina.nextInt(1, 9);
				while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
					cpuPos = maquina.nextInt(1, 9);
				}

			} else {

				cpuPos = nextCPUPosition;
			}

			positionBoard(gameBoard, cpuPos, "CPU");

			printGameBoard(gameBoard);

			result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);

			}
		} while (true);

	}

	// con este metodo creamos el tablero
	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();

		}

	}

	// con este metodo marcamos las posiciones
	public static void positionBoard(char[][] gameBoard, int position, String user) {

		// como si fuera default
		char symbol = ' ';

		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(position);

		} else if (user.equals("CPU")) {
			symbol = 'O';

			cpuPositions.add(position);
		}

		switch (position) {

		case 1:
			gameBoard[0][0] = symbol;
			break;

		case 2:
			gameBoard[0][2] = symbol;
			break;

		case 3:
			gameBoard[0][4] = symbol;
			break;

		case 4:
			gameBoard[2][0] = symbol;
			break;

		case 5:
			gameBoard[2][2] = symbol;
			break;

		case 6:
			gameBoard[2][4] = symbol;
			break;

		case 7:
			gameBoard[4][0] = symbol;
			break;

		case 8:
			gameBoard[4][2] = symbol;
			break;

		case 9:
			gameBoard[4][4] = symbol;
			break;

		default:
			break;

		}
	}

	public static String checkWinner() {

		// hacemos una serie de listas para indicar que, si en las posiciones
		// los symbol son iguales, se declare un ganador

		// para la lista que contiene todas las listas
		for (List l : winning) {

			// checkea si la posicion del jugador estan contenidas en una de esas listas
			if (playerPositions.containsAll(l)) {
				return "Enhorabuena Jugador, has ganado";
			} else if (cpuPositions.containsAll(l)) {
				return "has perdido";
				// medimos la cantidad de todos los symbols utilizados, y en caso de que de
				// igual a 9 será empate
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "EMPATE";
			}
		}

		return " ";

	}

}
