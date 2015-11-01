package algorithms.mazeGenerators;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * The Class Maze3d.
 */
@SuppressWarnings("serial")
public class Maze3d implements Serializable {

	/** Members */
	private Position start;
	private Position goal;
	private int [][][] maze;

	/**
	 * Ctor
	 * Instantiates a new maze3d.
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Maze3d(int x, int y, int z)  {
		this.start = new Position();
		this.goal = new Position();
		this.maze = new int [x][y][z];
	}
	
	public Maze3d(Position start, Position goal, int [][][] maze)  {
		this.start = start;
		this.goal = goal;
		this.maze = maze;
	}
	
	public Maze3d(Position start, Position goal, int [][] maze)  {
		this.start = start;
		this.goal = goal;
		this.maze = new int [1][1][1];
		this.maze[0] = maze;
	}
	
	public Maze3d(byte [] arr) {
		ByteBuffer buff = ByteBuffer.wrap(arr);
		this.maze = new int [buff.getInt()][buff.getInt()][buff.getInt()];
		this.start = new Position(buff.getInt(),buff.getInt(),buff.getInt());
		this.goal = new Position(buff.getInt(),buff.getInt(),buff.getInt());
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				for (int k = 0; k < maze[0][0].length; k++) {
					maze[i][j][k]= (int) buff.get();
				}
			}
		}
	}
	
	/**
	 * Copy Ctor
	 * Instantiates a new maze3d.
	 * @param copyMaze the copy maze
	 */
	public Maze3d(Maze3d copyMaze) {
		start=new Position(copyMaze.start);
		goal=new Position(copyMaze.goal);
		maze=new int [copyMaze.maze.length][copyMaze.maze[0].length][copyMaze.maze[0][0].length];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				for (int k = 0; k < maze[0][0].length; k++) {
					maze[i][j][k]= copyMaze.maze[i][j][k];
				}
			}
		}
	}
	
	/**
	 * Walls maze3d.
	 * @param num the num
	 */
	public void wallsMaze3d (int num) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				for (int k = 0; k < maze[i][j].length; k++) {
					maze [i][j][k] =num;
				}
			}
		}
	}
	
	/**
	 * Valid.
	 * @param p the p
	 * @return the boolean
	 */
	public Boolean valid (Position p)
	{
		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();
		
		if (x>=0 && y>=0 && z>=0 &&  x<maze.length && y<maze[x].length && z<maze[x][y].length ){
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the start position.
	 * @return the start position
	 */
	public Position getStartPosition() {
		return start;
	}

	/**
	 * Sets the start position.
	 * @param p the new start position
	 */
	public void setStartPosition(Position p) {
		this.start.setPosition(p);
	}
	
	/**
	 * Gets the goal position.
	 * @return the goal position
	 */
	public Position getGoalPosition() {
		return goal;
	}

	/**
	 * Sets the goal position.
	 * @param p the new goal position
	 */
	public void setGoalPosition(Position p) {
		this.goal.setPosition(p);
	}
	
	/**
	 * Sets the value.
	 * @param p the p
	 * @param num the num
	 */
	public void setValue(Position p, int num) {
		this.maze[p.getX()][p.getY()][p.getZ()] = num;
	}
	
	/**
	 * Gets the value.
	 * @param p the p
	 * @return the value
	 */
	public int getValue(Position p) {
		return this.maze[p.getX()][p.getY()][p.getZ()];
	}
	
	/**
	 * Gets the possible moves.
	 * @param p the p
	 * @return the possible moves
	 */
	public String [] getPossibleMoves (Position p)
	{
		if (!valid(p)) {
			return null;
		}
		
		ArrayList<String> move=new ArrayList<String>();
		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();

		Position s = new Position (p);
		
		s.setY(y+1);
		if (valid(s) && getValue(s)==0) {
			move.add(s.toString());
		}
		s.setY(y-1);
		if (valid(s) && getValue(s)==0) {
			move.add(s.toString());
		}
		s.setY(y);
		s.setX(x+1);
		if (valid(s) && getValue(s)==0) {
			move.add(s.toString());
		}
		s.setX(x-1);
		if (valid(s) && getValue(s)==0) {
			move.add(s.toString());
		}
		s.setX(x);
		s.setZ(z+1);
		if (valid(s) && getValue(s)==0) {
			move.add(s.toString());
		}
		s.setZ(z-1);
		if (valid(s) && getValue(s)==0) {
			move.add(s.toString());
		}
		String [] allMove = new String [move.size()];
		for (int i = 0; i < allMove.length; i++) {
			allMove[i] = move.get(i);
		}
		return allMove;	
	}
	
	/**
	 * Gets the all possible moves.
	 * @param p the p
	 * @return the all possible moves
	 */
	public ArrayList<Position> getAllPossibleMoves (Position p)
	{
		if (!valid(p)) {
			return null;
		}
		
		ArrayList<Position> move=new ArrayList<Position>();
		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();

		Position s = new Position (p);
		
		s.setY(y+1);
		if (valid(s) && getValue(s)==0) {
			move.add(new Position(s));
		}
		s.setY(y-1);
		if (valid(s) && getValue(s)==0) {
			move.add(new Position(s));
		}
		s.setY(y);
		s.setX(x+1);
		if (valid(s) && getValue(s)==0) {
			move.add(new Position(s));
		}
		s.setX(x-1);
		if (valid(s) && getValue(s)==0) {
			move.add(new Position(s));
		}
		s.setX(x);
		s.setZ(z+1);
		if (valid(s) && getValue(s)==0) {
			move.add(new Position(s));
		}
		s.setZ(z-1);
		if (valid(s) && getValue(s)==0) {
			move.add(new Position(s));
		}
		return move;	
	}

	/**
	 * Gets the cross section by x.
	 * @param num the num
	 * @return the cross section by x
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int [][] getCrossSectionByX (int num) throws IndexOutOfBoundsException
	{
		if (num<0) { 
			throw new IndexOutOfBoundsException("Error: getCrossSectionByX - x<0");
		}		
		int [][] array = new int [maze[0].length][maze[0][0].length];
		  for (int i = 0; i < array.length; i++) {
			  for (int j = 0; j < array[0].length; j++) {
				array[i][j]= maze[num][i][j];
			}
		  }
		  return array;
	}
	
	/**
	 * Gets the cross section by y.
	 * @param num the num
	 * @return the cross section by y
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int [][] getCrossSectionByY (int num) throws IndexOutOfBoundsException
	{
		if (num<0) { 
			throw new IndexOutOfBoundsException("Error: getCrossSectionByY - y<0");
		}	
	  int [][] array = new int [maze.length][maze[0][0].length];
	  for (int i = 0; i < array.length; i++) {
		  for (int j = 0; j < array[0].length; j++) {
			array[i][j]= maze[i][num][j];
		}
	  }
	  return array;
	}
	
	/**
	 * Gets the cross section by z.
	 * @param num the num
	 * @return the cross section by z
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public int [][] getCrossSectionByZ (int num) throws IndexOutOfBoundsException
	{
		if (num<0) { 
			throw new IndexOutOfBoundsException("Error: getCrossSectionByZ - z<0");
		}	
		  int [][] array = new int [maze.length][maze[0].length];
		  for (int i = 0; i < array.length; i++) {
			  for (int j = 0; j < array[0].length; j++) {
				array[i][j]= maze[i][j][num];
			}
		  }
		  return array;
	}

	/**
	 * Random position.
	 * @return the position
	 */
	protected Position randomPosition ()
	{
		int x = maze.length;
		int y = maze[0].length;
		int z = maze[0][0].length;
		int px = (int) (Math.random()*(x));
		int py = (int) (Math.random()*(y));
		int pz = (int) (Math.random()*(z));
		
		int rand = (int) (Math.random()*(6));
		switch (rand) {
			case 0: px=0; break;
			case 1: px=x-1; break;
			case 2: py=0; break;
			case 3: py=y-1; break;
			case 4: pz=0; break;
			case 5: pz=z-1; break;
			default:break;}
		
		return (new Position(px,py,pz));
	}
	
	/**
	 * Num around position.
	 * @param p the p
	 * @param num the num
	 * @return the int
	 */
	public int numAroundPosition (Position p, int num) 
	{
		int i = 0;
		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();
		
		Position s = new Position(p);
		
		s.setY(y+1);
		if (valid(s) && getValue(s)==num) {
			i++;
		}
		s.setY(y-1);
		if (valid(s) && getValue(s)==num) {
			i++;
		}
		s.setY(y);
		s.setX(x+1);
		if (valid(s) && getValue(s)==num) {
			i++;
		}
		s.setX(x-1);
		if (valid(s) && getValue(s)==num) {
			i++;
		}
		s.setX(x);
		s.setZ(z+1);
		if (valid(s) && getValue(s)==num) {
			i++;
		}
		s.setZ(z-1);
		if (valid(s) && getValue(s)==num) {
			i++;
		}
		return i;
	}
	
	/**
	 * Prints the.
	 */
	public void print()
	{
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				for (int k = 0; k < maze[0][0].length; k++) {
					System.out.print(maze[i][j][k]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	public byte[] toByteArray (){
		byte [] start = this.start.toByteArray();
		byte [] goal = this.goal.toByteArray();
		int mazeSize = maze.length*maze[0].length*maze[0][0].length ;
		ByteBuffer arr = ByteBuffer.allocate(start.length+goal.length+(Integer.BYTES*3)+mazeSize);
		
		arr.putInt(maze.length);
		arr.putInt(maze[0].length);
		arr.putInt(maze[0][0].length);
		arr.put(start);
		arr.put(goal);
		
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				for (int k = 0; k < maze[0][0].length; k++) {
					arr.put((byte)(maze[i][j][k]));
				}
			}
		}
		return arr.array();	
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;		
		Maze3d m = (Maze3d)obj;
		if (m.maze.length!=maze.length || m.maze[0].length != maze[0].length || m.maze[0][0].length != maze[0][0].length) {
			return false;
		}
		if (!m.start.equals(this.start) || !m.goal.equals(this.goal)) {
			return false;
		}
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				for (int k = 0; k < maze[0][0].length; k++) {
					if (m.maze[i][j][k] != maze [i][j][k]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public int[][][] getMaze() {
		return maze;
	}
	
	@Override
	public String toString() {
		
		StringBuilder arr = new StringBuilder ();
		arr.append(maze.length);
		arr.append(maze[0].length);
		arr.append(maze[0][0].length);
		arr.append(start.toString());
		arr.append(goal.toString());
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				for (int k = 0; k < maze[0][0].length; k++) {
					arr.append(maze[i][j][k]);
				}
			}
		}
		return arr.toString();
	}
	
	public void buildArrows (int up, int down, int upDown){
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				for (int k = 0; k < maze[0][0].length; k++) {
					if (maze[i][j][k]==0) {
						boolean upOK =(valid(new Position(i+1,j,k)) && maze[i+1][j][k]!=1);
						boolean downOK =(valid(new Position(i-1,j,k)) && maze[i-1][j][k]!=1);
						if (upOK && downOK) {
							maze[i][j][k]=upDown;
						}
						else if (upOK) {
							maze[i][j][k]=up;	
						}
						else if (downOK){
							maze[i][j][k]=down;
						}
					}
				}
			}
		}
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
}
