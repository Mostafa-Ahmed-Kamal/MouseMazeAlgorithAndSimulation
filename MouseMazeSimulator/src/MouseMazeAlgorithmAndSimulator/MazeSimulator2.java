package MouseMazeAlgorithmAndSimulator;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class MazeSimulator2 extends JFrame{
	public MazeSimulator2(boolean customMaze) {
		setTitle("Maze");
		setSize(1024,1280);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//IF YOU WANT TO INSERT AN OLD MAZE
		 if(customMaze) {
		 constructMaze(new boolean[][][] {{{true, true, false, false}, {true, false, false, true}, {true, false, false, false}, {true, false, false, false}, {true, false, true, true}, {true, true, false, false}, {true, false, false, false}, {true, false, true, true}, {true, true, false, false}, {true, false, false, true}, {true, false, false, false}, {true, false, false, true}, {true, false, true, true}, {true, true, false, false}, {true, false, false, true}, {true, false, false, true}, {true, false, false, false}, {true, false, false, true}, {true, false, false, false}, {true, false, true, false}}, {{false, true, false, false}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}, {true, false, false, true}, {false, false, true, true}, {false, true, false, true}, {true, false, false, true}, {false, false, false, true}, {true, false, true, true}, {false, true, false, false}, {true, false, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, false, false}, {true, false, true, true}, {false, true, false, false}, {true, false, false, true}, {false, false, false, false}, {false, false, true, false}}, {{false, true, false, false}, {false, false, false, false}, {false, false, false, true}, {true, false, false, false}, {true, false, true, true}, {true, true, false, false}, {true, false, false, true}, {true, false, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, true, true}, {false, true, false, false}, {true, false, true, true}, {false, true, true, false}, {true, true, false, false}, {false, false, false, true}, {true, false, false, false}, {false, false, true, false}, {false, true, true, false}}, {{false, true, true, false}, {false, true, false, false}, {true, false, true, false}, {false, true, true, true}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {true, false, false, false}, {true, false, true, false}, {false, true, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, false, false}, {false, false, true, true}, {false, true, false, true}, {true, false, false, true}, {false, false, false, false}, {false, false, false, false}, {false, false, true, false}}, {{false, true, false, false}, {false, false, true, false}, {false, true, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, true, false}, {false, true, true, true}, {false, true, true, false}, {false, true, true, false}, {true, true, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, true, false}, {false, true, true, false}, {true, true, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}, {false, false, true, false}}, {{false, true, true, false}, {false, true, false, false}, {true, false, true, false}, {false, true, true, false}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}, {false, true, false, true}, {true, false, true, false}, {false, true, true, false}, {true, true, false, false}, {false, false, true, true}, {false, true, false, true}, {true, false, false, true}, {true, false, false, true}, {false, false, true, false}, {false, true, false, false}, {true, false, true, false}, {false, true, true, false}}, {{false, true, true, false}, {false, true, true, true}, {false, true, true, false}, {false, true, false, true}, {false, false, true, true}, {true, true, true, false}, {false, true, false, false}, {true, false, true, false}, {true, true, true, false}, {false, true, false, true}, {false, false, true, false}, {false, true, false, true}, {true, false, true, false}, {true, true, false, false}, {true, false, false, false}, {true, false, true, true}, {false, true, false, false}, {false, false, false, false}, {false, false, true, false}, {false, true, true, false}}, {{false, true, false, true}, {true, false, true, false}, {false, true, false, false}, {true, false, false, true}, {true, false, true, false}, {false, true, true, false}, {false, true, true, false}, {false, true, true, false}, {false, true, false, false}, {true, false, false, true}, {false, false, true, true}, {true, true, true, false}, {false, true, true, false}, {false, true, true, true}, {false, true, false, true}, {true, false, false, true}, {false, false, false, true}, {false, false, true, false}, {false, true, false, false}, {false, false, true, false}}, {{true, true, false, false}, {false, false, true, true}, {false, true, false, true}, {true, false, true, true}, {false, true, true, false}, {false, true, false, false}, {false, false, true, true}, {false, true, true, false}, {false, true, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}, {true, false, false, true}, {true, false, false, true}, {true, false, true, false}, {true, true, false, false}, {false, false, false, true}, {false, false, false, true}, {false, false, true, false}}, {{false, true, true, false}, {true, true, false, false}, {true, false, false, false}, {true, false, false, true}, {false, false, true, true}, {false, true, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, true, false}, {true, true, false, false}, {false, false, true, false}, {false, true, true, false}, {true, true, false, false}, {true, false, false, true}, {true, false, false, true}, {false, false, false, false}, {false, false, false, true}, {true, false, true, false}, {true, true, false, false}, {false, false, true, true}}, {{false, true, true, false}, {false, true, true, true}, {false, true, false, true}, {true, false, true, false}, {true, true, false, false}, {true, false, true, true}, {false, true, true, false}, {true, true, true, false}, {false, true, false, true}, {false, false, false, false}, {false, false, false, true}, {false, false, true, false}, {false, true, false, true}, {true, false, true, false}, {true, true, false, false}, {false, false, false, false}, {true, false, false, true}, {false, false, false, false}, {false, false, false, true}, {true, false, true, false}}, {{false, true, true, false}, {true, true, false, false}, {true, false, false, true}, {false, false, true, true}, {false, true, false, true}, {true, false, false, true}, {false, false, false, true}, {false, false, true, false}, {true, true, true, false}, {false, true, true, false}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}, {false, true, false, true}, {false, false, true, false}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}}, {{false, true, true, false}, {false, true, false, true}, {true, false, false, true}, {true, false, false, false}, {true, false, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, true, false}, {false, true, true, false}, {false, true, true, false}, {false, true, true, false}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}, {true, false, true, false}, {false, true, false, true}, {true, false, true, false}}, {{false, true, false, true}, {true, false, false, true}, {true, false, true, false}, {false, true, true, true}, {true, true, false, false}, {true, false, false, true}, {false, false, true, true}, {false, true, false, true}, {false, false, true, false}, {false, true, true, false}, {false, true, true, true}, {false, true, false, true}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}, {false, false, true, true}, {true, true, true, false}, {false, true, false, true}, {true, false, true, false}, {false, true, true, false}}, {{true, true, false, false}, {true, false, false, false}, {false, false, false, false}, {true, false, false, true}, {false, false, true, true}, {true, true, true, false}, {true, true, false, false}, {true, false, false, true}, {false, false, true, true}, {false, true, true, false}, {true, true, false, false}, {true, false, false, true}, {false, false, true, true}, {false, true, true, false}, {true, true, false, false}, {true, false, true, false}, {false, true, false, false}, {true, false, true, false}, {false, true, false, true}, {false, false, true, false}}, {{false, true, false, true}, {false, false, true, false}, {false, true, false, false}, {true, false, false, true}, {true, false, false, false}, {false, false, false, true}, {false, false, true, false}, {true, true, false, false}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}, {true, false, true, false}, {true, true, false, true}, {false, false, true, false}, {false, true, true, true}, {false, true, false, true}, {false, false, true, true}, {false, true, true, false}, {true, true, false, false}, {false, false, true, true}}, {{true, true, false, false}, {false, false, true, false}, {false, true, true, false}, {true, true, true, false}, {false, true, false, false}, {true, false, false, true}, {false, false, false, false}, {false, false, true, true}, {false, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {true, false, false, true}, {true, false, false, true}, {false, false, true, false}, {false, true, true, false}, {true, true, true, false}}, {{false, true, true, false}, {false, true, true, false}, {false, true, false, true}, {false, false, true, false}, {false, true, false, false}, {true, false, false, false}, {false, false, false, false}, {true, false, true, false}, {false, true, true, false}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}, {true, true, false, false}, {false, false, true, false}, {true, true, true, false}, {true, true, false, false}, {false, false, true, true}, {false, true, true, false}, {false, true, true, false}}, {{false, true, false, false}, {false, false, true, false}, {true, true, false, false}, {false, false, false, false}, {false, false, false, true}, {false, false, false, false}, {false, false, false, false}, {false, false, true, false}, {false, true, true, true}, {false, true, false, true}, {true, false, false, true}, {false, false, true, true}, {true, true, false, false}, {false, false, true, true}, {false, true, true, false}, {false, true, false, true}, {false, false, true, true}, {true, true, false, false}, {false, false, true, false}, {false, true, true, false}}, {{false, true, false, true}, {false, false, false, true}, {false, false, false, true}, {false, false, false, true}, {true, false, false, true}, {false, false, true, true}, {false, true, false, true}, {false, false, false, true}, {true, false, false, true}, {true, false, false, true}, {true, false, false, true}, {true, false, false, true}, {false, false, false, true}, {true, false, true, true}, {false, true, false, true}, {true, false, false, true}, {true, false, false, true}, {false, false, false, true}, {false, false, false, true}, {false, false, true, true}}}
		 );
		 }
		//IF YOU WANT TO GENERATE A NEW RANDOM MAZE AND SOLVE IT
		 else {
		for(int i=0; i<maze.length; i++) {
			for(int j=0; j<maze[0].length; j++) {
				maze[i][j]=new GridCell(true,true,true,true);
			}
		}
		MazeGenerator();
		 }
		convertToArduinoWalls();
		//startingPoint
		maze[0][0].setColor(2);
		startingPoint = new int[]{0,0};
		//
		maze[maze.length/2][maze[0].length/2].setColor(3);
		maze[maze.length/2-1][maze[0].length/2].setColor(3);
		maze[maze.length/2][maze[0].length/2-1].setColor(3);
		maze[maze.length/2-1][maze[0].length/2-1].setColor(3);
		
		//Remove walls from the target as in the actual maze
		maze[maze.length/2][maze[0].length/2].setTopWall(false);
		maze[maze.length/2][maze[0].length/2].setLeftWall(false);
		maze[maze.length/2-1][maze[0].length/2].setBotWall(false);
		maze[maze.length/2-1][maze[0].length/2].setLeftWall(false);
		maze[maze.length/2][maze[0].length/2-1].setRightWall(false);
		maze[maze.length/2][maze[0].length/2-1].setTopWall(false);
		maze[maze.length/2-1][maze[0].length/2-1].setBotWall(false);
		maze[maze.length/2-1][maze[0].length/2-1].setRightWall(false);
		
		printMaze();
		
	}
	Random random = new Random();
	public GridCell[][] maze = new GridCell[20][20];
	public int[] startingPoint;
	public void MazeGenerator() {
		DFSMazeGenerator(0, 0);
		partialResetCells();
		DFSMazeGenerator(maze.length-1,maze[0].length-1);
		partialResetCells();
		DFSMazeGenerator(maze.length-1,0);
		partialResetCells();
		DFSMazeGenerator(0,maze[0].length-1);
		partialResetCells();
		DFSMazeGenerator(0, 0);
		partialResetCells();
		DFSMazeGenerator(maze.length-1,maze[0].length-1);
		partialResetCells();
		DFSMazeGenerator(maze.length-1,0);
		partialResetCells();
		DFSMazeGenerator(0,maze[0].length-1);
		resetCells();
		
	}
	public void DFSMazeGenerator(int i,int j) {
		maze[i][j].setVisited(true);
		LinkedList<int[]> s = new LinkedList<int[]>();
		
		if(!(i+1<0 || j<0 || i+1>=maze.length || j>=maze[0].length || maze[i+1][j].isVisited())) {
			s.add(new int[]{i+1,j});
		}
		if(!(i-1<0 || j<0 || i-1>=maze.length || j>=maze[0].length || maze[i-1][j].isVisited())) {
			s.add(new int[]{i-1,j});
		}
		if(!(i<0 || j+1<0 || i>=maze.length || j+1>=maze[0].length || maze[i][j+1].isVisited())) {
			s.add(new int[]{i,j+1});
		}
		if(!(i<0 || j-1<0 || i>=maze.length || j-1>=maze[0].length || maze[i][j-1].isVisited())) {
			s.add(new int[]{i,j-1});
		}
		while(!s.isEmpty()) {
			int r = random.nextInt(s.size());
			if(maze[s.get(r)[0]][s.get(r)[1]].isVisited()) {
				s.remove(r);
			}
			else {
			int[] m = s.remove(r);
			
			if(i<m[0] && j==m[1]) {
				maze[i+1][j].setTopWall(false);
				maze[i][j].setBotWall(false);
			}
			else if(i>m[0] && j==m[1]) {
				maze[i][j].setTopWall(false);
				maze[i-1][j].setBotWall(false);
			}
			else if(i==m[0] && j>m[1]) {
				maze[i][j].setLeftWall(false);
				maze[i][j-1].setRightWall(false);
			}
			else if(i==m[0] && j<m[1]) {
				maze[i][j+1].setLeftWall(false);
				maze[i][j].setRightWall(false);
			}
			
			DFSMazeGenerator(m[0], m[1]);
			}
		}	
	}
	public void resetCells() {
		for(int i=0 ; i<maze.length;i++) {
			for(int j=0; j<maze[0].length;j++) {
				maze[i][j].setVisited(false);
			}
		}
	}
	public void partialResetCells() {
		for(int i=0 ; i<maze.length;i++) {
			for(int j=0; j<maze[0].length;j++) {
				maze[i][j].setVisited(random.nextBoolean());
			}
		}
	}
	public int[] mazeDimensions() {
		return new int[] {maze.length, maze[0].length};
	}
	public void printMaze() {
		boolean[][][] mazeForm = new boolean[maze.length][maze[0].length][4];
		for(int i=0;i<maze.length;i++) {
			for(int j=0; j<maze[0].length;j++) {
				mazeForm[i][j][0]=maze[i][j].isTopWall();
				mazeForm[i][j][1]=maze[i][j].isLeftWall();
				mazeForm[i][j][2]=maze[i][j].isRightWall();
				mazeForm[i][j][3]=maze[i][j].isBotWall();
			}
		}
		System.out.println(Arrays.deepToString(mazeForm));
	}
	public void constructMaze(boolean[][][] mazeForm) {
		for(int i=0; i<maze.length;i++) {
			for(int j=0; j<maze[0].length;j++) {
				maze[i][j] = new GridCell(mazeForm[i][j][1],mazeForm[i][j][2],mazeForm[i][j][0],mazeForm[i][j][3]);
			}
		}
	}
	public void convertToArduinoWalls() {
		int[][] m = new int[maze.length][maze[0].length];
		for(int i=0 ; i<maze.length; i++) {
			for(int j=0 ;j<maze[0].length; j++) {
				int k=0;
				if(maze[i][j].isTopWall()) {
					k+=1;
				}
				if(maze[i][j].isLeftWall()) {
					k+=2;
				}
				if(maze[i][j].isBotWall()) {
					k+=4;
				}
				if(maze[i][j].isRightWall()) {
					k+=8;
				}
				m[i][j] = k;
			}
		}
		System.out.println(Arrays.deepToString(m));
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.translate(100, 100);
		Color color;
		// 0:white "open"
		// 1:yellow "visited"
		// 2:green "starting node"
		// 3:red "target node"
		// 4:orange "solution path"
		// 5:pink "2nd attempt"
		// 6:blue "3rd attempt"
		// 7:darkGray "deadEnd"
		for(int i=0; i<maze.length; i++) {
			for(int j=0; j<maze[0].length; j++) {
				switch(maze[i][j].getColor()) {
					case 0:
						color = Color.WHITE;
						break;
					case 1:
						color = Color.YELLOW;
						break;
					case 2:
						color = Color.GREEN;
						break;
					case 3:
						color = Color.RED;
						break;
					case 4:
						color = Color.ORANGE;
						break;
					case 5:
						color = Color.PINK;
						break;
					case 6:
						color = Color.BLUE;
						break;
					default: // deadEnd
						color = Color.darkGray;
						break;
				}
				g.setColor(color);
				g.fillRect(30*j, 30*i , 30 , 30);
				g.setColor(color);
				g.drawRect(30*j, 30*i , 30 , 30);
			if(maze[i][j].isRightWall()) {
				g.setColor(Color.BLACK);
				g.fillRect(30*j+28, 30*i , 2 , 30);
				g.setColor(Color.BLACK);
				g.drawRect(30*j+28, 30*i , 2 , 30);
			}
			if(maze[i][j].isBotWall()) {
				g.setColor(Color.BLACK);
				g.fillRect(30*j, 30*i+28 , 30 , 2);
				g.setColor(Color.BLACK);
				g.drawRect(30*j, 30*i+28 , 30 , 2);
			}
			if(maze[i][j].isLeftWall()) {
				g.setColor(Color.BLACK);
				g.fillRect(30*j, 30*i , 2 , 30);
				g.setColor(Color.BLACK);
				g.drawRect(30*j, 30*i , 2 , 30);
			}
			if(maze[i][j].isTopWall()) {
				g.setColor(Color.BLACK);
				g.fillRect(30*j, 30*i , 30 , 2);
				g.setColor(Color.BLACK);
				g.drawRect(30*j, 30*i , 30 , 2);
			}	
			}
		}
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MazeSimulator2 maze = new MazeSimulator2(true);
				maze.setVisible(true);
				ModifiedFloodFill t = new ModifiedFloodFill(maze);
				t.start();
				
				// maze generator simulation
				
				/*MazeGeneratorSimulation t = new MazeGeneratorSimulation(maze);
				t.start();*/
				
			}
		});
	}
}




