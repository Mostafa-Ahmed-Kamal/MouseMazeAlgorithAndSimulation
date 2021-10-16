package MouseMazeAlgorithmAndSimulator;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class ArduinoMouseSimulatorTest {
	public ArduinoMouseSimulatorTest() {
		
		for(int i=0;i<16;i++) {
			for(int j =0; j<16 ;j++) {
				walls[i][j]=0;
				visited[i][j]=false;
			}
		}
		mazeWalls = new int[][] {{3, 1, 5, 9, 7, 5, 5, 1, 5, 5, 9, 3, 1, 1, 1, 9}, {2, 4, 9, 10, 3, 5, 9, 6, 1, 9, 2, 0, 0, 8, 2, 8}, {2, 5, 12, 10, 10, 11, 6, 9, 6, 0, 4, 12, 2, 0, 0, 12}, {2, 1, 13, 6, 12, 6, 9, 6, 5, 12, 3, 9, 2, 0, 4, 9}, {2, 0, 1, 5, 1, 9, 2, 1, 5, 5, 12, 2, 8, 10, 3, 12}, {2, 12, 2, 9, 10, 2, 12, 14, 3, 5, 1, 4, 8, 10, 6, 9}, {14, 3, 12, 6, 8, 14, 3, 5, 8, 11, 2, 9, 14, 10, 3, 8}, {3, 12, 7, 1, 12, 3, 8, 3, 0, 8, 2, 8, 3, 12, 10, 10}, {2, 5, 9, 10, 3, 12, 2, 0, 0, 12, 10, 2, 12, 3, 8, 10}, {10, 11, 2, 0, 8, 3, 0, 8, 10, 3, 4, 0, 5, 12, 10, 10}, {6, 8, 6, 0, 8, 10, 6, 4, 4, 12, 3, 8, 3, 9, 10, 10}, {3, 8, 11, 10, 10, 6, 1, 1, 1, 13, 2, 0, 12, 6, 12, 10}, {10, 2, 12, 2, 4, 5, 12, 10, 14, 3, 12, 10, 7, 1, 9, 10}, {6, 0, 1, 0, 9, 7, 5, 8, 3, 12, 7, 4, 9, 10, 6, 8}, {3, 8, 10, 2, 4, 1, 5, 12, 10, 3, 5, 9, 2, 4, 1, 8}, {6, 4, 4, 4, 5, 4, 5, 5, 4, 12, 7, 4, 4, 5, 4, 12}}
; 
		int[][] target = {{8,8}};
		ConstructDistanceMaze(target);
		Direction = 0; 
	}
	
	/*  
	North:0
	West:1
	South:2
	East:3
	*/
	int[] currentLocation = {0,0} ;
	int Direction = 0; // North initially
	Queue<int[]> q = new LinkedList<int[]>();
	Stack<int[]> s = new Stack<int[]>();
	Stack<int[]> sNodes = new Stack<int[]>();
	/*
	 *  topWall & 1
	 *  leftWall & 2
	 *  BotWall & 4
	 *  RightWall & 8
	 */
	int[][] walls = new int[16][16];
	int[][] Distances = new int[16][16];
	boolean[][] visited = new boolean[16][16];
	// for testing won't exist in the actual code , will be replaced by sensor readings;
	int[][] mazeWalls = new int[16][16];
	
	public void ConstructDistanceMaze(int[][] target) {
		
		for(int i=0; i<Distances.length ; i++) {
			for(int j=0; j<Distances[0].length;j++) {
				Distances[i][j] = 300;
			}
		}
		for(int i=0;i<target.length; i++) {
			q.add(target[i]);
			Distances[target[i][0]][target[i][1]] = 0;
		}
		while(!q.isEmpty()) {
			int[] node = q.poll();
			
			// for debugging purposes
			/*
		    System.out.println("walls Value = "+ walls[node[0]][node[1]] );
			if((walls[node[0]][node[1]] | 1) == walls[node[0]][node[1]]) {
				System.out.print("  top wall");
			}
			if((walls[node[0]][node[1]] | 2) == walls[node[0]][node[1]]) {
				System.out.print("  left wall");
			}
			if((walls[node[0]][node[1]] | 4) == walls[node[0]][node[1]]) {
				System.out.print("  bot wall");
			}
			if((walls[node[0]][node[1]] | 8) == walls[node[0]][node[1]]) {
				System.out.print("  right wall");
			}
			System.out.println();
		    System.out.println("disctance value = " + Distances[node[0]][node[1]]);
			PrintMaze();
			System.out.println();
			System.out.println();
			Scanner myObj = new Scanner(System.in);
			String userName = myObj.nextLine();
			*/
			
			
			if(!(node[0]+1<0 || node[1]<0 || node[0]+1>=Distances.length || node[1]>=Distances[0].length 
					|| (walls[node[0]][node[1]] | 4) == walls[node[0]][node[1]])) {
				if(Distances[node[0]+1][node[1]]==300) {
					Distances[node[0]+1][node[1]] = Distances[node[0]][node[1]] + 1;
					q.add(new int[] {node[0]+1,node[1]});
				}
			}
			if(!(node[0]-1<0 || node[1]<0 || node[0]-1>=Distances.length || node[1]>=Distances[0].length 
					|| (walls[node[0]][node[1]] | 1) == walls[node[0]][node[1]])) {
				if(Distances[node[0]-1][node[1]]==300) {
					Distances[node[0]-1][node[1]] = Distances[node[0]][node[1]] + 1;
					q.add(new int[] {node[0]-1,node[1]});
				}
			}
			if(!(node[0]<0 || node[1]+1<0 || node[0]>=Distances.length || node[1]+1>=Distances[0].length 
					|| (walls[node[0]][node[1]] | 8) == walls[node[0]][node[1]])) {
				if(Distances[node[0]][node[1]+1]==300) {
					Distances[node[0]][node[1]+1] = Distances[node[0]][node[1]] + 1;
					q.add(new int[] {node[0],node[1]+1});
				}
			}
			if(!(node[0]<0 || node[1]-1<0 || node[0]>=Distances.length || node[1]-1>=Distances[0].length 
					|| (walls[node[0]][node[1]] | 2) == walls[node[0]][node[1]])) {
				if(Distances[node[0]][node[1]-1]==300) {
					Distances[node[0]][node[1]-1] = Distances[node[0]][node[1]] + 1;
					q.add(new int[] {node[0],node[1]-1});
				}
			}
		}
	}
	
	public void PrintMaze(int[][] walls) {
		if(Direction == 0) {
			System.out.println("Heading North");
		}
		else if(Direction == 1) {
			System.out.println("Heading West");
		}
		else if(Direction == 2) {
			System.out.println("Heading South");
		}
		else if(Direction == 3) {
			System.out.println("Heading East");
		}
		
		for(int j=0 ; j<16 ; j++) {
				System.out.print("-----");
		}
		for(int i=0; i<16; i++) {
			System.out.println();
			System.out.print("|");
			for(int j=0 ; j<16 ; j++) {
				if(i == currentLocation[0] && j == currentLocation[1]) {
					System.out.print(" * ");
				}
				else if(Distances[i][j]/10==0) {
					System.out.print(" "+Distances[i][j]+" ");
				}
				else if(Distances[i][j]/100==0) {
					System.out.print(Distances[i][j]+" ");
				}
				else if(Distances[i][j]/1000==0) {
					System.out.print(Distances[i][j]);
				}
				if((walls[i][j] | 8) == walls[i][j]) {
					System.out.print(" |");
				}
				else {
					System.out.print("  ");
				}
			}
			System.out.println();
			for(int j=0 ; j<16 ; j++) {
				if((walls[i][j] | 4) == walls[i][j]) {
					System.out.print("-----");
				}
				else {
					System.out.print("     ");
				}
			}
		}
	}
	public boolean nextNode(boolean considerVisited) {
		// will be replaced by sensor readings later on;
		addWallsToMaze(mazeWalls[currentLocation[0]][currentLocation[1]]);
		
		visited[currentLocation[0]][currentLocation[1]]= true;
		
		int multiOptionsNode = 0;
		int[] node = null;
		int min = 300;
		int newDirection = Direction;
		if(!(currentLocation[0]+1<0 || currentLocation[1]<0 || currentLocation[0]+1>=Distances.length || currentLocation[1]>=Distances[0].length 
				|| (walls[currentLocation[0]][currentLocation[1]] | 4) == walls[currentLocation[0]][currentLocation[1]])) {
			if(!visited[currentLocation[0]+1][currentLocation[1]]) {
				multiOptionsNode+=1;
			}
			if(Distances[currentLocation[0]+1][currentLocation[1]]<min) {
				node = new int[] {currentLocation[0] + 1 , currentLocation[1]};
				min = Distances[currentLocation[0]+1][currentLocation[1]];
				newDirection= 2;
			}
		}
		if(!(currentLocation[0]-1<0 || currentLocation[1]<0 || currentLocation[0]-1>=Distances.length || currentLocation[1]>=Distances[0].length 
				|| (walls[currentLocation[0]][currentLocation[1]] | 1) == walls[currentLocation[0]][currentLocation[1]])) {
			if(!visited[currentLocation[0]-1][currentLocation[1]]) {
				multiOptionsNode+=1;
			}
			if(Distances[currentLocation[0]-1][currentLocation[1]]<min) {
				node = new int[] {currentLocation[0] - 1 , currentLocation[1]};
				min = Distances[currentLocation[0]-1][currentLocation[1]];
				newDirection= 0;
			}
		}
		if(!(currentLocation[0]<0 || currentLocation[1]+1<0 || currentLocation[0]>=Distances.length || currentLocation[1]+1>=Distances[0].length 
				|| (walls[currentLocation[0]][currentLocation[1]] | 8) == walls[currentLocation[0]][currentLocation[1]])) {
			if(!visited[currentLocation[0]][currentLocation[1]+1]) {
				multiOptionsNode+=1;
			}
			if(Distances[currentLocation[0]][currentLocation[1]+1]<min) {
				node = new int[] {currentLocation[0]  , currentLocation[1]+1};
				min = Distances[currentLocation[0]][currentLocation[1]+1];
				newDirection= 3;
			}
		}
		if(!(currentLocation[0]<0 || currentLocation[1]-1<0 || currentLocation[0]>=Distances.length || currentLocation[1]-1>=Distances[0].length 
				|| (walls[currentLocation[0]][currentLocation[1]] | 2) == walls[currentLocation[0]][currentLocation[1]])) {
			if(!visited[currentLocation[0]][currentLocation[1]-1]) {
				multiOptionsNode+=1;
			}
			if(Distances[currentLocation[0]][currentLocation[1]-1]<min) {
				node = new int[] {currentLocation[0]  , currentLocation[1] - 1};
				min = Distances[currentLocation[0]][currentLocation[1]-1];
				newDirection= 1;
			}
		}
		if((multiOptionsNode>0 && visited[node[0]][node[1]]) || multiOptionsNode>1) {
			sNodes.add(new int[] {currentLocation[0],currentLocation[1]});
		}
		if(considerVisited && visited[node[0]][node[1]]) {
			return false;
		}
		System.out.println(Arrays.toString(node));
		System.out.println(Arrays.toString(currentLocation));

		if(Distances[currentLocation[0]][currentLocation[1]]!=min+1) {
			floodFill(currentLocation);
		}
		else {
			Direction = newDirection;
			currentLocation[0] = node[0];
			currentLocation[1] = node[1];
		}
		
		return true;
	}
	public int getMin(int[] node) {
		int min = 300;
		if(!(node[0]+1<0 || node[1]<0 || node[0]+1>=Distances.length || node[1]>=Distances[0].length 
				|| (walls[node[0]][node[1]] | 4) == walls[node[0]][node[1]])) {
			if(Distances[node[0]+1][node[1]]<min) {
				min = Distances[node[0]+1][node[1]];
			}
		}
		if(!(node[0]-1<0 || node[1]<0 || node[0]-1>=Distances.length || node[1]>=Distances[0].length 
				|| (walls[node[0]][node[1]] | 1) == walls[node[0]][node[1]])) {
			if(Distances[node[0]-1][node[1]]<min) {
				min = Distances[node[0]-1][node[1]];
			}
		}
		if(!(node[0]<0 || node[1]+1<0 || node[0]>=Distances.length || node[1]+1>=Distances[0].length 
				|| (walls[node[0]][node[1]] | 8) == walls[node[0]][node[1]])) {
			if(Distances[node[0]][node[1]+1]<min) {
				min = Distances[node[0]][node[1]+1];
			}
		}
		if(!(node[0]<0 || node[1]-1<0 || node[0]>=Distances.length || node[1]-1>=Distances[0].length 
				|| (walls[node[0]][node[1]] | 2) == walls[node[0]][node[1]])) {
			if(Distances[node[0]][node[1]-1]<min) {
				min = Distances[node[0]][node[1]-1];
			}
		}
		return min;
	}
	public int[] getMinNode(int[] node) {
		int min = 300;
		int[] n = node;
		if(!(node[0]+1<0 || node[1]<0 || node[0]+1>=Distances.length || node[1]>=Distances[0].length 
				|| (walls[node[0]][node[1]] | 4) == walls[node[0]][node[1]])) {
			if(Distances[node[0]+1][node[1]]<min) {
				min = Distances[node[0]+1][node[1]];
				n = new int[] {node[0]+1 , node[1]};
			}
		}
		if(!(node[0]-1<0 || node[1]<0 || node[0]-1>=Distances.length || node[1]>=Distances[0].length 
				|| (walls[node[0]][node[1]] | 1) == walls[node[0]][node[1]])) {
			if(Distances[node[0]-1][node[1]]<min) {
				min = Distances[node[0]-1][node[1]];
				n = new int[] {node[0]-1 , node[1]};
			}
		}
		if(!(node[0]<0 || node[1]+1<0 || node[0]>=Distances.length || node[1]+1>=Distances[0].length 
				|| (walls[node[0]][node[1]] | 8) == walls[node[0]][node[1]])) {
			if(Distances[node[0]][node[1]+1]<min) {
				min = Distances[node[0]][node[1]+1];
				n = new int[] {node[0] , node[1]+1};
			}
		}
		if(!(node[0]<0 || node[1]-1<0 || node[0]>=Distances.length || node[1]-1>=Distances[0].length 
				|| (walls[node[0]][node[1]] | 2) == walls[node[0]][node[1]])) {
			if(Distances[node[0]][node[1]-1]<min) {
				min = Distances[node[0]][node[1]-1];
				n = new int[] {node[0] , node[1]-1};
			}
		}
		return n;
	}
	public void addNeighbores(int[] node) {
		if(!(node[0]+1<0 || node[1]<0 || node[0]+1>=Distances.length || node[1]>=Distances[0].length 
				|| (walls[node[0]][node[1]] | 4) == walls[node[0]][node[1]])) {
			s.add(new int[] {node[0]+1 , node[1]});
		}
		if(!(node[0]-1<0 || node[1]<0 || node[0]-1>=Distances.length || node[1]>=Distances[0].length 
				|| (walls[node[0]][node[1]] | 1) == walls[node[0]][node[1]])) {
			s.add(new int[] {node[0]-1 , node[1]});
		}
		if(!(node[0]<0 || node[1]+1<0 || node[0]>=Distances.length || node[1]+1>=Distances[0].length 
				|| (walls[node[0]][node[1]] | 8) == walls[node[0]][node[1]])) {
			s.add(new int[] {node[0] , node[1]+1});
		}
		if(!(node[0]<0 || node[1]-1<0 || node[0]>=Distances.length || node[1]-1>=Distances[0].length 
				|| (walls[node[0]][node[1]] | 2) == walls[node[0]][node[1]])) {
			s.add(new int[] {node[0] , node[1]-1});
		}
	}
	public void floodFill(int[] node) {
		s.add(node);
		while(!s.empty()) {
			int[] n = s.pop();
			int min = getMin(n);
			if( min+1 != Distances[n[0]][n[1]]) {
				Distances[n[0]][n[1]] = min+1;
				addNeighbores(n);
			}
		}
	}
	public void addWallsToMaze(int wallsValue) {
		walls[currentLocation[0]][currentLocation[1]] = wallsValue;
		if((wallsValue | 1) == wallsValue && currentLocation[0]-1>=0) {
			walls[currentLocation[0]-1][currentLocation[1]]|=4;
		}
		if((wallsValue | 2) == wallsValue && currentLocation[1]-1>=0) {
			walls[currentLocation[0]][currentLocation[1]-1]|=8;
		}
		if((wallsValue | 4) == wallsValue && currentLocation[0]+1<walls.length) {
			walls[currentLocation[0]+1][currentLocation[1]]|=1;
		}
		if((wallsValue | 8) == wallsValue && currentLocation[1]+1<walls.length) {
			walls[currentLocation[0]][currentLocation[1]+1]|=2;
		}
	}
	public void AdjustWallsRotation(int reading) {
		if(Direction!=0) {
		int temp = reading>>(4-Direction);
		int t = (int) (reading% Math.pow(2, 4-Direction));
		reading = (t<<Direction) + temp;
		reading &= 15;
		}
		System.out.println(reading);
	}
	public int getWallsReadings(boolean topWall, boolean leftWall , boolean rightWall) {
		int wallsValue = 0;
		if(topWall) {
			wallsValue|=1;
		}
		if(leftWall) {
			wallsValue|=2;
		}
		if(rightWall) {
			wallsValue|=8;
		}
		return wallsValue;
	}
	// for debugging
	public void walls(int wall) {
		if((wall | 1) == wall) {
			System.out.print("top wall ");
		}
		if((wall | 2) == wall) {
			System.out.print("Left wall ");
		}
		if((wall | 4) == wall) {
			System.out.print("Bot wall ");
		}
		if((wall | 8) == wall) {
			System.out.print("Right wall ");
		}
		System.out.println();
	}
	public void Algo(boolean considerVisited) {
		while(Distances[currentLocation[0]][currentLocation[1]]!=0) {
			boolean t = nextNode(considerVisited);
			if(!t) {
				break;
				}
			Scanner myObj = new Scanner(System.in);
			String userName = myObj.nextLine();
			PrintMaze(walls);
		}
	}
	public void MazeSolver() {
		Algo(false);
		int[] t ;
		while(!sNodes.isEmpty()) {
			t = getMinNode(sNodes.pop());
			System.out.println(sNodes.size());
			
			if(!visited[t[0]][t[1]]) {
				ConstructDistanceMaze(new int[][] {t});
				Algo(false);
				
				ConstructDistanceMaze(new int[][] {{8,8}});
				Algo(true);
			}
		}
		ConstructDistanceMaze(new int[][] {{0,0}});
		Algo(false);
		
		System.out.println("2nd attempt starts now:");
		ConstructDistanceMaze(new int[][] {{8,8}});
		Algo(false);
	}
	public static void main(String[] args) {
		ArduinoMouseSimulatorTest t = new ArduinoMouseSimulatorTest();
		t.PrintMaze(t.mazeWalls);
		t.PrintMaze(t.walls);
		t.MazeSolver();
		
		/*
		//0000
		t.transformReadingsToWalls(0); //0000
		//0001
		t.transformReadingsToWalls(1); //1000
		//0010
		t.transformReadingsToWalls(2); //0001
		//0011
		t.transformReadingsToWalls(3); //1001
		//1000
		t.transformReadingsToWalls(8); //0100
		//1001
		t.transformReadingsToWalls(9); //1100
		//1010
		t.transformReadingsToWalls(10); //0101
		//1011
		t.transformReadingsToWalls(11); //1101
		*/
		/*
		System.out.println(t.getWallsReadings(false, false, false));
		System.out.println(t.getWallsReadings(true, false, false));
		System.out.println(t.getWallsReadings(false, true, false));
		System.out.println(t.getWallsReadings(true, true, false));
		System.out.println(t.getWallsReadings(false, false, true));
		System.out.println(t.getWallsReadings(true, false, true));
		System.out.println(t.getWallsReadings(false, true, true));
		System.out.println(t.getWallsReadings(true, true, true));
		 */
	}
}
