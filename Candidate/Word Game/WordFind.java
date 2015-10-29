//Lets code!
import java.util.Scanner;

public class WordFind {

	//Here are some directions we will need in order to find the word
	enum Directions {UP,DOWN,LEFT,RIGHT,DOWNLEFT,DOWNRIGHT,UPLEFT,UPRIGHT};
	static int counter = 0; //counting how many times we will meet the word
	
	public static void getNumberOfWord(char[][] matrix, String word){
		char firstLetter = word.charAt(0); //We actually search for the first letter of the word
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j]==firstLetter) {
					//Checking in all directions
					checkWord(matrix,word,i,j,Directions.UP,0);
					checkWord(matrix,word,i,j,Directions.UPLEFT,0);
					checkWord(matrix,word,i,j,Directions.UPRIGHT,0);
					checkWord(matrix,word,i,j,Directions.DOWN,0);
					checkWord(matrix,word,i,j,Directions.DOWNLEFT,0);
					checkWord(matrix,word,i,j,Directions.DOWNRIGHT,0);
					checkWord(matrix,word,i,j,Directions.LEFT,0);
					checkWord(matrix,word,i,j,Directions.RIGHT,0);
				}
			}
		}
		System.out.println(counter);//Printing counter
	}
	
	//Checking word in a specific direction with recursion
	private static void checkWord(char[][] matrix,String word,int row, int col, Directions direction, int start) {
		//Our bottom in case we find the word
		if (start==word.length()) {
			counter++;
			return;
		}
		//And another bottom if we get outside the matrix or the letters don't match
		if (row<0||col<0||row>=matrix.length||col>=matrix[0].length||matrix[row][col]!=word.charAt(start)) {
			return;
		}
		//Checks, checks and more checks
		switch (direction) {
		case UP: checkWord(matrix, word, row-1, col, Directions.UP, start+1); break;
		case UPLEFT: checkWord(matrix, word, row-1, col-1, Directions.UPLEFT, start+1); break;
		case UPRIGHT: checkWord(matrix, word, row-1, col+1, Directions.UPRIGHT, start+1); break;
		case DOWN: checkWord(matrix, word, row+1, col, Directions.DOWN, start+1); break;
		case DOWNLEFT: checkWord(matrix, word, row+1, col-1, Directions.DOWNLEFT, start+1); break;
		case DOWNRIGHT: checkWord(matrix, word, row+1, col+1, Directions.DOWNRIGHT, start+1); break;
		case LEFT: checkWord(matrix, word, row, col-1, Directions.LEFT, start+1); break;
		case RIGHT: checkWord(matrix, word, row, col+1, Directions.RIGHT, start+1); break;
		}
	}
	
	public static void main(String[] args) {
		//Example:
		Scanner input = new Scanner(System.in);
		String word = input.next();
		char[][] matrix={
				{'i','v','a','n','b'},
				{'v','s','n','j','g'},
				{'a','i','a','v','i'},
				{'n','o','v','a','v'},
				{'t','a','i','a','a'},
				{'p','o','v','o','n'},
				{'p','o','a','i','p'}
		};
		getNumberOfWord(matrix, word);
		input.close();
	}
/*Example inputs:
 * iva - 8
 * ivan - 6
*/
}
