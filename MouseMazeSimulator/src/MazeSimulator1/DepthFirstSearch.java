package MazeSimulator1;
public class DepthFirstSearch extends Thread{
	private boolean solved=false;
	private MazeSimulator1 maze;
	public DepthFirstSearch(MazeSimulator1 maze) {
		this.maze = maze;
	}
	public void DFS(int[][] maze,int i,int j) {
		if(solved || i<0 || j<0 || i>=maze.length || j>=maze[0].length)return;
		if(maze[i][j]==2 || maze[i][j]==1) {
			return;
		}
		else if(maze[i][j]==0 || maze[i][j]==3) {
			if(maze[i][j]!=3) {
			maze[i][j]=2;
			}
			
			this.maze.repaint(100+20*j,100+20*i,20,20);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * recurse on:
			 * bot
			 * top
			 * right
			 * left
			 */
			DFS(maze,i+1,j);
			DFS(maze,i-1,j);
			DFS(maze,i,j+1);
			DFS(maze,i,j-1);
		}
		else {
			System.out.println("found the way");
			solved=true;
			return;
		}
		if(solved && maze[i][j]==2) {
				maze[i][j]=5;
				this.maze.repaint(100+20*j,100+20*i,20,20);
			return;
		}
	}
	@Override
	public void run() {
		super.run();
		DFS(maze.maze,maze.startPosition[0],maze.startPosition[1]);
	}
	
}
