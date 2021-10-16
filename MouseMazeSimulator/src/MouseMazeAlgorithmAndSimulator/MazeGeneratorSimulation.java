package MouseMazeAlgorithmAndSimulator;
import java.util.LinkedList;
import java.util.Scanner;

public class MazeGeneratorSimulation extends Thread{
	MazeSimulator2 maze;
	public MazeGeneratorSimulation(MazeSimulator2 maze) {
		this.maze = maze;
	}
	public void MazeGenerator() {
		
		DFSMazeGenerator(0, 0);
		maze.resetCells();
		recolorCells();
		/*
		DFSMazeGenerator(maze.maze.length-1,maze.maze[0].length-1);
		maze.partialResetCells();
		DFSMazeGenerator(maze.maze.length-1,0);
		maze.partialResetCells();
		DFSMazeGenerator(0,maze.maze[0].length-1);
		maze.partialResetCells();
		DFSMazeGenerator(0, 0);
		maze.partialResetCells();
		DFSMazeGenerator(maze.maze.length-1,maze.maze[0].length-1);
		maze.partialResetCells();
		DFSMazeGenerator(maze.maze.length-1,0);
		maze.partialResetCells();
		DFSMazeGenerator(0,maze.maze[0].length-1);
		maze.resetCells();
		*/
	}
	public void DFSMazeGenerator(int i,int j) {
		maze.maze[i][j].setColor(5);
		maze.repaint(100+j*30-2,100+i*30-2,38,38);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		maze.maze[i][j].setColor(1);
		maze.repaint(100+j*30-2,100+i*30-2,38,38);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		maze.maze[i][j].setVisited(true);
		LinkedList<int[]> s = new LinkedList<int[]>();
		
		if(!(i+1<0 || j<0 || i+1>=maze.maze.length || j>=maze.maze[0].length || maze.maze[i+1][j].isVisited())) {
			s.add(new int[]{i+1,j});
		}
		if(!(i-1<0 || j<0 || i-1>=maze.maze.length || j>=maze.maze[0].length || maze.maze[i-1][j].isVisited())) {
			s.add(new int[]{i-1,j});
		}
		if(!(i<0 || j+1<0 || i>=maze.maze.length || j+1>=maze.maze[0].length || maze.maze[i][j+1].isVisited())) {
			s.add(new int[]{i,j+1});
		}
		if(!(i<0 || j-1<0 || i>=maze.maze.length || j-1>=maze.maze[0].length || maze.maze[i][j-1].isVisited())) {
			s.add(new int[]{i,j-1});
		}
		while(!s.isEmpty()) {
			int r = maze.random.nextInt(s.size());
			if(maze.maze[s.get(r)[0]][s.get(r)[1]].isVisited()) {
				s.remove(r);
			}
			else {
			int[] m = s.remove(r);
			
			if(i<m[0] && j==m[1]) {
				maze.maze[i+1][j].setTopWall(false);
				maze.maze[i][j].setBotWall(false);
			}
			else if(i>m[0] && j==m[1]) {
				maze.maze[i][j].setTopWall(false);
				maze.maze[i-1][j].setBotWall(false);
			}
			else if(i==m[0] && j>m[1]) {
				maze.maze[i][j].setLeftWall(false);
				maze.maze[i][j-1].setRightWall(false);
			}
			else if(i==m[0] && j<m[1]) {
				maze.maze[i][j+1].setLeftWall(false);
				maze.maze[i][j].setRightWall(false);
			}
			
			DFSMazeGenerator(m[0], m[1]);
			}
		}
	}
	public void recolorCells() {
		for(int i=0 ;i<maze.maze.length;i++) {
			for(int j=0; j<maze.maze[0].length; j++) {
				if(maze.maze[i][j].isVisited()) {
					maze.maze[i][j].setColor(1);
				}
				else {
					maze.maze[i][j].setColor(0);
				}
			}
		}
		maze.repaint();
	}
	@Override
	public void run() {
		for(int i=0 ;i<maze.maze.length;i++) {
			for(int j=0 ; j<maze.maze[0].length; j++) {
				maze.maze[i][j]=new GridCell(true,true,true,true);
			}
		}
		// TODO Auto-generated method stub
		super.run();
		//Scanner myObj = new Scanner(System.in);
		//String userName = myObj.nextLine();
		MazeGenerator();
	}
}
