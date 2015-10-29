//Lets code!
import java.util.Scanner;

public class FindMyself {
	//This methods will print our position on Oxy system if we input a specific string with directions
	//We have the path, our start counter, our coordinates and the version of the system
	public static void findCoordinates(String navigation, int start, int x,int y,boolean systemType){
		if (start == navigation.length()) {
			System.out.println("("+x+","+y+")");
			return;
		}
		if(systemType){
			switch (navigation.charAt(start)) {
			
			case '>':
				x++;
				break;
			case '<':
				x--;
				break;
			case '^':
				y--;
				break;
			case 'V':
				y++;
				break;
			case '~':
				systemType=false;
				break;
			}
		}
		else{	
			switch (navigation.charAt(start)) {
			
			case '>':
				x--;
				break;
			case '<':
				x++;
				break;
			case '^':
				y++;
				break;
			case 'V':
				y--;
				break;
			case '~':
				systemType=true;
				break;
			}
		}
		//Recursively find ourselves
		findCoordinates(navigation, start+1, x, y, systemType);
		
	}
	
	
	public static void main(String[] args) {
		//Example
		Scanner input = new Scanner(System.in);
		String navigation = input.nextLine();
		findCoordinates(navigation, 0, 0, 0, true);
		input.close();
	}

}
