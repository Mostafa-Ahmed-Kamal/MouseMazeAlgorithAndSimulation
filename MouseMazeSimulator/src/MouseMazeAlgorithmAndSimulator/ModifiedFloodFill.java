package MouseMazeAlgorithmAndSimulator;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ModifiedFloodFill extends Thread{
	private MazeSimulator2 maze;
	int[][] AStarMaze;
	int[] startingPoint;
	public Stack<int[]> sNodes = new Stack<int[]>();
	GridCell[][] initialMaze;
	public ModifiedFloodFill(MazeSimulator2 maze) {
		this.maze = maze;
		int[] dimensions = maze.mazeDimensions();
		AStarMaze = new int[dimensions[0]][dimensions[1]];
		startingPoint = maze.startingPoint;
		initialMaze = new GridCell[dimensions[0]][dimensions[1]];
		for(int i=0; i<initialMaze.length;i++) {
			for(int j=0; j<initialMaze[0].length;j++) {
				initialMaze[i][j] = new GridCell(false,false,false,false);
			}
		}
	}
	public ModifiedFloodFill(int[] dimensions) {
		AStarMaze = new int[dimensions[0]][dimensions[1]];
		initialMaze = new GridCell[dimensions[0]][dimensions[1]];
		for(int i=0; i<initialMaze.length;i++) {
			for(int j=0; j<initialMaze[0].length;j++) {
				initialMaze[i][j] = new GridCell(false,false,false,false);
			}
		}
	}
	/*public void constructAStarMaze() {
		int[][] target = {{AStarMaze.length/2-1, AStarMaze[0].length/2-1},
																			{AStarMaze.length/2   , AStarMaze[0].length/2} };
		
		for(int i=0 ; i<AStarMaze.length;i++) {
			for(int j=0 ; j<AStarMaze[0].length; j++) {
				int minI = Math.abs(i-target[0][0])<Math.abs(i-target[1][0])?Math.abs(i-target[0][0]):Math.abs(i-target[1][0]);
				int minJ = Math.abs(j-target[0][1])<Math.abs(j-target[1][1])?Math.abs(j-target[0][1]):Math.abs(j-target[1][1]);
				AStarMaze[i][j] = minI+minJ;
			}
		}
		System.out.println(Arrays.deepToString(AStarMaze));
	}*/
	public void constructAStarMaze(int[][] endNodes) {
		Queue<int[]> q = new LinkedList<int[]>();
		for(int i=0; i<AStarMaze.length ; i++) {
			for(int j=0; j<AStarMaze[0].length;j++) {
				AStarMaze[i][j] = -1;
			}
		}
		for(int i=0 ; i< endNodes.length ; i++) {
			q.add(endNodes[i]);
			AStarMaze[endNodes[i][0]][endNodes[i][1]] = 0;
		}
		while(!q.isEmpty()) {
			int[] node = q.poll();
			if(!(node[0]+1<0 || node[1]<0 || node[0]+1>=AStarMaze.length || node[1]>=AStarMaze[0].length 
					|| initialMaze[node[0]][node[1]].isBotWall())) {
				if(AStarMaze[node[0]+1][node[1]]==-1) {
					AStarMaze[node[0]+1][node[1]] = AStarMaze[node[0]][node[1]] + 1;
					q.add(new int[] {node[0]+1,node[1]});
				}
			}
			if(!(node[0]-1<0 || node[1]<0 || node[0]-1>=AStarMaze.length || node[1]>=AStarMaze[0].length 
					|| initialMaze[node[0]][node[1]].isTopWall())) {
				if(AStarMaze[node[0]-1][node[1]]==-1) {
					AStarMaze[node[0]-1][node[1]] = AStarMaze[node[0]][node[1]] + 1;
					q.add(new int[] {node[0]-1,node[1]});
				}
			}
			if(!(node[0]<0 || node[1]+1<0 || node[0]>=AStarMaze.length || node[1]+1>=AStarMaze[0].length 
					|| initialMaze[node[0]][node[1]].isRightWall())) {
				if(AStarMaze[node[0]][node[1]+1]==-1) {
					AStarMaze[node[0]][node[1]+1] = AStarMaze[node[0]][node[1]] + 1;
					q.add(new int[] {node[0],node[1]+1});
				}
			}
			if(!(node[0]<0 || node[1]-1<0 || node[0]>=AStarMaze.length || node[1]-1>=AStarMaze[0].length 
					|| initialMaze[node[0]][node[1]].isLeftWall())) {
				if(AStarMaze[node[0]][node[1]-1]==-1) {
					AStarMaze[node[0]][node[1]-1] = AStarMaze[node[0]][node[1]] + 1;
					q.add(new int[] {node[0],node[1]-1});
				}
			}
		}
		//printMaze();
		//System.out.println();
	}
	public int[] FloodFill(int color, boolean considerVisited) {
		int[] node = startingPoint;
		while(AStarMaze[node[0]][node[1]]!=0) {
			//System.out.println("current node is :" +node[0] + " "+  node[1]);
			maze.maze[node[0]][node[1]].setColor(5);
			maze.maze[node[0]][node[1]].setVisited(true);
			maze.repaint(100+node[1]*30,100+node[0]*30,30,30);
			//Scanner myObj = new Scanner(System.in);
			//String userName = myObj.nextLine();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int[] newNode = getMinAndAdjustWalls(node , considerVisited);
			maze.maze[node[0]][node[1]].setColor(color);
			maze.repaint(100+node[1]*30,100+node[0]*30,30,30);
			if(newNode==null)break;
			if(AStarMaze[newNode[0]][newNode[1]]!=AStarMaze[node[0]][node[1]]-1) {
				Stack<int[]> s = new Stack<int[]>();
				s.add(node);
				while(!s.isEmpty()) {
					int[] n = s.pop();
					int m = getMin(n);
					if(AStarMaze[n[0]][n[1]]!= m+1) {
						AStarMaze[n[0]][n[1]] = m+1;
						addNeighbores(s, n);
					}
				}
				node = getMinAndAdjustWalls(node, considerVisited);
			}
			else {
				node = newNode;
				
			}
		}
		//printMaze();
		//System.out.println();
		addNeighbores(sNodes, node);
		/*for(int i=0; i<maze.maze.length; i++) {
			for(int j=0 ; j<maze.maze[0].length; j++) {
				maze.maze[i][j].setColor(1);
				maze.repaint(100+j*30,100+i*30,30,30);
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}*/
		maze.maze[node[0]][node[1]].setVisited(true);
		return node;
	}
	public int getMin(int[] node) {
		int min = Integer.MAX_VALUE;
		if(!(node[0]+1<0 || node[1]<0 || node[0]+1>=maze.maze.length || node[1]>=maze.maze[0].length 
				|| initialMaze[node[0]][node[1]].isBotWall())) {

				if(AStarMaze[node[0]+1][node[1]]<min) {
					min = AStarMaze[node[0]+1][node[1]];
				}
			}
		if(!(node[0]-1<0 || node[1]<0 || node[0]-1>=maze.maze.length || node[1]>=maze.maze[0].length 
				|| initialMaze[node[0]][node[1]].isTopWall() )) {
				
			if(AStarMaze[node[0]-1][node[1]]<min) {
					min = AStarMaze[node[0]-1][node[1]];
				}
		}
		
		if(!(node[0]<0 || node[1]+1<0 || node[0]>=maze.maze.length || node[1]+1>=maze.maze[0].length 
				|| initialMaze[node[0]][node[1]].isRightWall())) {
				
			if(AStarMaze[node[0]][node[1]+1]<min) {
					min = AStarMaze[node[0]][node[1]+1];
				}
		}
		if(!(node[0]<0 || node[1]-1<0 || node[0]>=maze.maze.length || node[1]-1>=maze.maze[0].length 
			|| initialMaze[node[0]][node[1]].isLeftWall()	)) {
			
				if(AStarMaze[node[0]][node[1]-1]<min) {
					min = AStarMaze[node[0]][node[1]-1];
				}
		}
		return min;
	}
	public int[] getMinNode(int[] node ) {
		int min = Integer.MAX_VALUE;
		int[] n = null;
		if(!(node[0]+1<0 || node[1]<0 || node[0]+1>=maze.maze.length || node[1]>=maze.maze[0].length 
				|| initialMaze[node[0]][node[1]].isBotWall())) {

				if(AStarMaze[node[0]+1][node[1]]<min) {
					min = AStarMaze[node[0]+1][node[1]];
					n = new int[] {node[0]+1,node[1]};
				}
			}
		if(!(node[0]-1<0 || node[1]<0 || node[0]-1>=maze.maze.length || node[1]>=maze.maze[0].length 
				|| initialMaze[node[0]][node[1]].isTopWall() )) {
				
			if(AStarMaze[node[0]-1][node[1]]<min) {
					min = AStarMaze[node[0]-1][node[1]];
					n = new int[] {node[0]-1,node[1]};
				}
		}
		
		if(!(node[0]<0 || node[1]+1<0 || node[0]>=maze.maze.length || node[1]+1>=maze.maze[0].length 
				|| initialMaze[node[0]][node[1]].isRightWall())) {
				
			if(AStarMaze[node[0]][node[1]+1]<min) {
					min = AStarMaze[node[0]][node[1]+1];
					n = new int[] {node[0],node[1]+1};
				}
		}
		if(!(node[0]<0 || node[1]-1<0 || node[0]>=maze.maze.length || node[1]-1>=maze.maze[0].length 
			|| initialMaze[node[0]][node[1]].isLeftWall()	)) {
			
				if(AStarMaze[node[0]][node[1]-1]<min) {
					min = AStarMaze[node[0]][node[1]-1];
					n = new int[] {node[0],node[1]-1};
				}
		}
		return n;
	}
	public void addNeighbores(Stack<int[]> s, int[] node) {
		if(!(node[0]+1<0 || node[1]<0 || node[0]+1>=maze.maze.length || node[1]>=maze.maze[0].length 
				|| initialMaze[node[0]][node[1]].isBotWall())) {

				s.add(new int[]{node[0]+1,node[1]});
			}
		if(!(node[0]-1<0 || node[1]<0 || node[0]-1>=maze.maze.length || node[1]>=maze.maze[0].length 
				|| initialMaze[node[0]][node[1]].isTopWall() )) {
				
			s.add(new int[]{node[0]-1,node[1]});
		}
		
		if(!(node[0]<0 || node[1]+1<0 || node[0]>=maze.maze.length || node[1]+1>=maze.maze[0].length 
				|| initialMaze[node[0]][node[1]].isRightWall())) {
				
			s.add(new int[]{node[0],node[1]+1});
		}
		if(!(node[0]<0 || node[1]-1<0 || node[0]>=maze.maze.length || node[1]-1>=maze.maze[0].length 
			|| initialMaze[node[0]][node[1]].isLeftWall()	)) {
				
			s.add(new int[]{node[0],node[1]-1});
		}
	}
	public int[] getMinAndAdjustWalls(int[] node , boolean considerVisited) {
		int min=Integer.MAX_VALUE;
		//boolean addedNode = false;
		int[] newNode = null;
		if(!(node[0]+1<0 || node[1]<0 || node[0]+1>=maze.maze.length || node[1]>=maze.maze[0].length 
				 )) {
			///
			if(!considerVisited || maze.maze[node[0]+1][node[1]].isVisited()==false) {
				///
			if( !maze.maze[node[0]][node[1]].isBotWall()) {
				if(AStarMaze[node[0]+1][node[1]]<min) {
					min = AStarMaze[node[0]+1][node[1]];
					newNode = new int[]{node[0]+1,node[1]};
					
				}
				else if(AStarMaze[node[0]+1][node[1]]==min && !maze.maze[node[0]+1][node[1]].isVisited()) {
					sNodes.add(new int[]{node[0]+1,node[1]});
				}
			}
			else {
				initialMaze[node[0]][node[1]].setBotWall(true);
				initialMaze[node[0]+1][node[1]].setTopWall(true);
			}
			
		}
		}
		if(!(node[0]-1<0 || node[1]<0 || node[0]-1>=maze.maze.length || node[1]>=maze.maze[0].length 
				)) {
			///
			if(!considerVisited || maze.maze[node[0]-1][node[1]].isVisited()==false) {
				///
			if(!maze.maze[node[0]][node[1]].isTopWall()) {
				/*if(newNode!=null && !addedNode) {
					sNodes.add(node);
					addedNode = true;
				}*/
				if(AStarMaze[node[0]-1][node[1]]<min) {
					if(newNode != null && !maze.maze[newNode[0]][newNode[1]].isVisited()) {
						sNodes.add(newNode);
					}
					min = AStarMaze[node[0]-1][node[1]];
					newNode = new int[]{node[0]-1,node[1]};
				}
				else if(AStarMaze[node[0]-1][node[1]]==min && !maze.maze[node[0]-1][node[1]].isVisited()) {
					sNodes.add(new int[]{node[0]-1,node[1]});
				}
			}
			else {
				initialMaze[node[0]][node[1]].setTopWall(true);
				initialMaze[node[0]-1][node[1]].setBotWall(true);
			}
		}
		}
		
		if(!(node[0]<0 || node[1]+1<0 || node[0]>=maze.maze.length || node[1]+1>=maze.maze[0].length 
				)) {
			///
			if(!considerVisited || maze.maze[node[0]][node[1]+1].isVisited()==false) {
				///
			if(!maze.maze[node[0]][node[1]].isRightWall()) {
				/*if(newNode!=null && !addedNode) {
					sNodes.add(node);
					addedNode = true;
				}*/
				if(AStarMaze[node[0]][node[1]+1]<min) {
					if(newNode != null && !maze.maze[newNode[0]][newNode[1]].isVisited()) {
						sNodes.add(newNode);
					}
					min = AStarMaze[node[0]][node[1]+1];
					newNode = new int[]{node[0],node[1]+1};
				}
				else if(AStarMaze[node[0]][node[1]+1]==min && !maze.maze[node[0]][node[1]+1].isVisited()) {
					sNodes.add(new int[]{node[0],node[1]+1});
				}
			}
			else {
				initialMaze[node[0]][node[1]].setRightWall(true);
				initialMaze[node[0]][node[1]+1].setLeftWall(true);
			}
			}
		}
		if(!(node[0]<0 || node[1]-1<0 || node[0]>=maze.maze.length || node[1]-1>=maze.maze[0].length 
				)) {
			///
			if(!considerVisited || maze.maze[node[0]][node[1]-1].isVisited()==false) {
				///
			if(!maze.maze[node[0]][node[1]].isLeftWall()) {
				/*if(newNode!=null && !addedNode) {
					sNodes.add(node);
					addedNode = true;
				}*/
				if(AStarMaze[node[0]][node[1]-1]<min) {
					if(newNode != null && !maze.maze[newNode[0]][newNode[1]].isVisited()) {
						sNodes.add(newNode);
					}
					min = AStarMaze[node[0]][node[1]-1];
					newNode = new int[]{node[0],node[1]-1};
				}
				else if(AStarMaze[node[0]][node[1]-1]==min && !maze.maze[node[0]][node[1]-1].isVisited()) {
					sNodes.add(new int[]{node[0],node[1]-1});
				}
			}
			else {
				initialMaze[node[0]][node[1]].setLeftWall(true);
				initialMaze[node[0]][node[1]-1].setRightWall(true);
			}
			}
		}
		return newNode;
	}
	public void printMaze() {
		for(int i=0 ; i<AStarMaze.length;i++) {
			for(int j=0; j<AStarMaze[0].length;j++) {
				System.out.print("   "+AStarMaze[i][j]+"   ");
			}
			System.out.println();
		}
	}
	/*public static void main(String[] args) {
		ModifiedFloodFill t = new ModifiedFloodFill(new int[]{6,6});
		t.constructAStarMaze();
	}*/
	public void MazeSolver(int color) {
		startingPoint = new int[] {0,0};
		int[][] target = {{AStarMaze.length/2-1, AStarMaze[0].length/2-1},{AStarMaze.length/2, AStarMaze[0].length/2-1},
				{AStarMaze.length/2   , AStarMaze[0].length/2},{AStarMaze.length/2-1, AStarMaze[0].length/2} };
		sNodes.add(startingPoint);
		resetTargetColors(target);
		int s[];
		int t[];
		constructAStarMaze(target);
		s = FloodFill(color, false);
		//optimization 1:
		//Scanner myObj = new Scanner(System.in);
		//String userName = myObj.nextLine();
		while(!sNodes.isEmpty()) {
			t = sNodes.pop();
			
			int minNode[] = getMinNode(t);
			if(!maze.maze[t[0]][t[1]].isVisited() && !maze.maze[minNode[0]][minNode[1]].isVisited()) {
			
			startingPoint = s;
			constructAStarMaze(new int[][] {t});
			s = FloodFill(1 ,false);
			startingPoint = s;
			constructAStarMaze(target);
			s = FloodFill(1 , true);
			}
		}
		startingPoint = s;
		constructAStarMaze(new int[][] {{0,0}});
		FloodFill(1, false);
	}
	public void resetTargetColors(int[][] target) {
		for(int i=0 ; i< target.length ; i++) {
			maze.maze[target[i][0]][target[i][1]].setColor(3);
			maze.repaint(100+target[i][1]*30,100+target[i][0]*30,30,30);
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		//Scanner myObj = new Scanner(System.in);
		//String userName = myObj.nextLine();
		MazeSolver(1);
		//Scanner myObj = new Scanner(System.in);
		//String userName = myObj.nextLine();
		//MazeSolver(2);
		//userName = myObj.nextLine();
		startingPoint = new int[] {0,0};
		int[][] target = {{AStarMaze.length/2-1, AStarMaze[0].length/2-1},{AStarMaze.length/2, AStarMaze[0].length/2-1},
				{AStarMaze.length/2   , AStarMaze[0].length/2},{AStarMaze.length/2-1, AStarMaze[0].length/2} };
		constructAStarMaze(target);
		resetTargetColors(target);
		FloodFill(2,false);
		//userName = myObj.nextLine();
		//resetTargetColors(target);
		//FloodFill(4,false);
		//userName = myObj.nextLine();
		//resetTargetColors(target);
		//FloodFill(5,false);
		
		System.out.println("done");
	}
	
	public void constructMaze(boolean[][][] mazeForm) {
		for(int i=0; i<initialMaze.length;i++) {
			for(int j=0; j<initialMaze[0].length;j++) {
				initialMaze[i][j] = new GridCell(mazeForm[i][j][1],mazeForm[i][j][2],mazeForm[i][j][0],mazeForm[i][j][3]);
			}
		}
	}
	/*public static void main(String[] args) {
		ModifiedFloodFill t = new ModifiedFloodFill(new int[] {20,20});
		t.constructMaze(new boolean[][][] {{{true, true, false, false}, {true, false, false, true}, {true, false, false, false}, {true, false, false, false}, {true, false, true, true}, {true, true, false, false}, {true, false, false, false}, {true, false, true, true}, {true, true, false, false}, {true, false, false, true}, {true, false, false, false}, {true, false, false, true}, {true, false, true, true}, {true, true, false, false}, {true, false, false, true}, {true, false, false, true}, {true, false, false, false}, {true, false, false, true}, {true, false, false, false}, {true, false, true, false}}, {{false, true, false, false}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}, {true, false, false, true}, {false, false, true, true}, {false, true, false, true}, {true, false, false, true}, {false, false, false, true}, {true, false, true, true}, {false, true, false, false}, {true, false, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, false, false}, {true, false, true, true}, {false, true, false, false}, {true, false, false, true}, {false, false, false, false}, {false, false, true, false}}, {{false, true, false, false}, {false, false, false, false}, {false, false, false, true}, {true, false, false, false}, {true, false, true, true}, {true, true, false, false}, {true, false, false, true}, {true, false, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, true, true}, {false, true, false, false}, {true, false, true, true}, {false, true, true, false}, {true, true, false, false}, {false, false, false, true}, {true, false, false, false}, {false, false, true, false}, {false, true, true, false}}, {{false, true, true, false}, {false, true, false, false}, {true, false, true, false}, {false, true, true, true}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {true, false, false, false}, {true, false, true, false}, {false, true, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, false, false}, {false, false, true, true}, {false, true, false, true}, {true, false, false, true}, {false, false, false, false}, {false, false, false, false}, {false, false, true, false}}, {{false, true, false, false}, {false, false, true, false}, {false, true, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, true, false}, {false, true, true, true}, {false, true, true, false}, {false, true, true, false}, {true, true, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, true, false}, {false, true, true, false}, {true, true, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}, {false, false, true, false}}, {{false, true, true, false}, {false, true, false, false}, {true, false, true, false}, {false, true, true, false}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}, {false, true, false, true}, {true, false, true, false}, {false, true, true, false}, {true, true, false, false}, {false, false, true, true}, {false, true, false, true}, {true, false, false, true}, {true, false, false, true}, {false, false, true, false}, {false, true, false, false}, {true, false, true, false}, {false, true, true, false}}, {{false, true, true, false}, {false, true, true, true}, {false, true, true, false}, {false, true, false, true}, {false, false, true, true}, {true, true, true, false}, {false, true, false, false}, {true, false, true, false}, {true, true, true, false}, {false, true, false, true}, {false, false, true, false}, {false, true, false, true}, {true, false, true, false}, {true, true, false, false}, {true, false, false, false}, {true, false, true, true}, {false, true, false, false}, {false, false, false, false}, {false, false, true, false}, {false, true, true, false}}, {{false, true, false, true}, {true, false, true, false}, {false, true, false, false}, {true, false, false, true}, {true, false, true, false}, {false, true, true, false}, {false, true, true, false}, {false, true, true, false}, {false, true, false, false}, {true, false, false, true}, {false, false, true, true}, {true, true, true, false}, {false, true, true, false}, {false, true, true, true}, {false, true, false, true}, {true, false, false, true}, {false, false, false, true}, {false, false, true, false}, {false, true, false, false}, {false, false, true, false}}, {{true, true, false, false}, {false, false, true, true}, {false, true, false, true}, {true, false, true, true}, {false, true, true, false}, {false, true, false, false}, {false, false, true, true}, {false, true, true, false}, {false, true, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}, {true, false, false, true}, {true, false, false, true}, {true, false, true, false}, {true, true, false, false}, {false, false, false, true}, {false, false, false, true}, {false, false, true, false}}, {{false, true, true, false}, {true, true, false, false}, {true, false, false, false}, {true, false, false, true}, {false, false, true, true}, {false, true, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, true, false}, {true, true, false, false}, {false, false, true, false}, {false, true, true, false}, {true, true, false, false}, {true, false, false, true}, {true, false, false, true}, {false, false, false, false}, {false, false, false, true}, {true, false, true, false}, {true, true, false, false}, {false, false, true, true}}, {{false, true, true, false}, {false, true, true, true}, {false, true, false, true}, {true, false, true, false}, {true, true, false, false}, {true, false, true, true}, {false, true, true, false}, {true, true, true, false}, {false, true, false, true}, {false, false, false, false}, {false, false, false, true}, {false, false, true, false}, {false, true, false, true}, {true, false, true, false}, {true, true, false, false}, {false, false, false, false}, {true, false, false, true}, {false, false, false, false}, {false, false, false, true}, {true, false, true, false}}, {{false, true, true, false}, {true, true, false, false}, {true, false, false, true}, {false, false, true, true}, {false, true, false, true}, {true, false, false, true}, {false, false, false, true}, {false, false, true, false}, {true, true, true, false}, {false, true, true, false}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}, {false, true, false, true}, {false, false, true, false}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}}, {{false, true, true, false}, {false, true, false, true}, {true, false, false, true}, {true, false, false, false}, {true, false, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, true, false}, {false, true, true, false}, {false, true, true, false}, {false, true, true, false}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, true, false}}, {{false, true, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, true, true}, {true, true, false, false}, {true, false, false, true}, {false, false, true, true}, {false, true, false, true}, {false, false, true, false}, {false, true, true, false}, {false, true, true, true}, {false, true, false, true}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}, {false, false, true, true}, {true, true, true, false}, {false, true, false, true}, {true, false, true, false}, {false, true, true, false}}, {{true, true, false, false}, {true, false, false, false}, {false, false, false, false}, {true, false, false, true}, {false, false, true, true}, {true, true, true, false}, {true, true, false, false}, {true, false, false, true}, {false, false, true, true}, {false, true, true, false}, {true, true, false, false}, {true, false, false, true}, {false, false, true, true}, {false, true, true, false}, {true, true, false, false}, {true, false, true, false}, {false, true, false, false}, {true, false, true, false}, {false, true, false, true}, {false, false, true, false}}, {{false, true, false, true}, {false, false, true, false}, {false, true, false, false}, {true, false, false, true}, {true, false, false, false}, {false, false, false, true}, {false, false, true, false}, {true, true, false, false}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}, {true, false, true, false}, {true, true, false, true}, {false, false, true, false}, {false, true, true, true}, {false, true, false, true}, {false, false, true, true}, {false, true, true, false}, {true, true, false, false}, {false, false, true, true}}, {{true, true, false, false}, {false, false, true, false}, {false, true, true, false}, {true, true, true, false}, {false, true, false, false}, {true, false, false, true}, {false, false, false, false}, {false, false, true, true}, {false, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {true, false, false, true}, {true, false, false, true}, {false, false, true, false}, {false, true, true, false}, {true, true, true, false}}, {{false, true, true, false}, {false, true, true, false}, {false, true, false, true}, {false, false, true, false}, {false, true, false, false}, {true, false, false, false}, {false, false, false, false}, {true, false, true, false}, {false, true, true, false}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, false}, {true, true, true, false}, {true, true, false, false}, {false, false, true, true}, {false, true, true, false}, {false, true, true, false}}, {{false, true, false, false}, {false, false, true, false}, {true, true, false, false}, {false, false, false, false}, {false, false, false, true}, {false, false, false, false}, {false, false, false, false}, {false, false, true, false}, {false, true, true, true}, {false, true, false, true}, {true, false, false, true}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}, {false, true, true, false}, {false, true, false, true}, {false, false, true, true}, {true, true, false, false}, {false, false, true, false}, {false, true, true, false}}, {{false, true, false, true}, {false, false, false, true}, {false, false, false, true}, {false, false, false, true}, {true, false, false, true}, {false, false, true, true}, {false, true, false, true}, {false, false, false, true}, {true, false, false, true}, {true, false, false, true}, {true, false, false, true}, {true, false, false, true}, {false, false, false, true}, {true, false, true, true}, {false, true, false, true}, {true, false, false, true}, {true, false, false, true}, {false, false, false, true}, {false, false, false, true}, {false, false, true, true}}}
				);
		t.buildAStarMaze(new int[][] {{0,0}});
		
	}*/
}


