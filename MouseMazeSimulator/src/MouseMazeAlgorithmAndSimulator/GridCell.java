package MouseMazeAlgorithmAndSimulator;

public class GridCell {
	private boolean LeftWall=false,RightWall=false,TopWall=false,BotWall=false;
	private int color=0; //initially white
	private int rootLength = Integer.MAX_VALUE; // length from this cell to target
	private boolean visited = false;
	public GridCell(boolean LeftWall, boolean RightWall, boolean TopWall, boolean BotWall) {
		this.LeftWall = LeftWall;
		this.RightWall  = RightWall;
		this.TopWall = TopWall;
		this.BotWall = BotWall;
	}
	public GridCell() {
		
	}
	public void set_resetWalls(int wall, boolean set) {
		/*
		 * 0:top
		 * 1:left
		 * 2:bottom
		 * 3:right
		 */
		switch(wall) {
		case 0:
			setTopWall(set);
			break;
		case 1:
			setLeftWall(set);
			break;
		case 2:
			setBotWall(set);
			break;
		case 3:
			setRightWall(set);
			break;
		}
	}
	public boolean isWalls(int wall) {
		/*
		 * 0:top
		 * 1:left
		 * 2:bottom
		 * 3:right
		 */
		switch(wall) {
		case 0:
			return isTopWall();
		case 1:
			return isLeftWall();
		case 2:
			return isBotWall();
		case 3:
			return isRightWall();
		}
		return false;
	}
	
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getRootLength() {
		return rootLength;
	}

	public void setRootLength(int rootLength) {
		this.rootLength = rootLength;
	}

	public boolean isLeftWall() {
		return LeftWall;
	}

	public void setLeftWall(boolean leftWall) {
		LeftWall = leftWall;
	}

	public boolean isRightWall() {
		return RightWall;
	}

	public void setRightWall(boolean rightWall) {
		RightWall = rightWall;
	}

	public boolean isTopWall() {
		return TopWall;
	}

	public void setTopWall(boolean topWall) {
		TopWall = topWall;
	}

	public boolean isBotWall() {
		return BotWall;
	}

	public void setBotWall(boolean botWall) {
		BotWall = botWall;
	}
	
}
