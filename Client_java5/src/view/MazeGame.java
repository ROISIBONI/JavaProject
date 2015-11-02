/*
 * 
 */
package view;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

// TODO: Auto-generated Javadoc
/**
 * The Class MazeGame.
 */
public class MazeGame {
	
	/** The maze3d. */
	Maze3d maze3d;
	
	/** The character. */
	Position character;
	
	/** The maze displayer. */
	MazeDisplayer mazeDisplayer;
	
	/** The timer. */
	Timer timer;
	
	/** The task. */
	TimerTask task;
	
	/** The key listener. */
	KeyListener keyListener=null;

	/**
	 * Instantiates a new maze game.
	 *
	 * @param mazeDisplayer the maze displayer
	 * @param maze3d the maze3d
	 */
	public MazeGame(MazeDisplayer mazeDisplayer, Maze3d maze3d) {
		this.maze3d = maze3d;
		this.character = maze3d.getStartPosition();
		this.mazeDisplayer = mazeDisplayer;
		this.maze3d.buildArrows(2, 3, 4);
		this.mazeDisplayer.setMazeData(maze3d.getCrossSectionByX(character.getX()));
		this.mazeDisplayer.setCharacterPosition(character.getY(),character.getZ());
		if (character.getX()==maze3d.getGoalPosition().getX()) {
			mazeDisplayer.setGoaldPosition(maze3d.getGoalPosition().getY(), maze3d.getGoalPosition().getZ());
		}
		else {
			mazeDisplayer.setGoaldPosition(-1,-1);
		}
	}

	/**
	 * Play.
	 */
	public void play() {

		keyListener = new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				
				Position position;
				
				switch (e.keyCode) {
				case SWT.ARROW_UP:
					if (mazeDisplayer.moveUp()) {
						character.setY(character.getY()-1);
						if (character.equals(maze3d.getGoalPosition())) {
							winGame();
						}
					}
					break;
				case SWT.ARROW_DOWN:
					if (mazeDisplayer.moveDown()) {
						character.setY(character.getY()+1);
						if (character.equals(maze3d.getGoalPosition())) {
							winGame();
						}
					}
					break;
				case SWT.ARROW_LEFT:
					if (mazeDisplayer.moveLeft()) {
						character.setZ(character.getZ()-1);
						if (character.equals(maze3d.getGoalPosition())) {
							winGame();
						}
					}
					break;
				case SWT.ARROW_RIGHT:
					if (mazeDisplayer.moveRight()) {
						character.setZ(character.getZ()+1);
						if (character.equals(maze3d.getGoalPosition())) {
							winGame();
						}
					}
					break;
				case SWT.PAGE_UP:
					position = new Position(character.getX()+1,character.getY(),character.getZ());
					if (maze3d.valid(position)&& maze3d.getValue(position)!=1) {
						character.setX(character.getX()+1);
						mazeDisplayer.setMazeData(maze3d.getCrossSectionByX(character.getX()));
						if (character.getX()==maze3d.getGoalPosition().getX()) {
							mazeDisplayer.setGoaldPosition(maze3d.getGoalPosition().getY(), maze3d.getGoalPosition().getZ());
						}
						else {
							mazeDisplayer.setGoaldPosition(-1,-1);
						}
						mazeDisplayer.redraw();
						if (character.equals(maze3d.getGoalPosition())) {
							winGame();
						}
					}  
					break;
				case SWT.PAGE_DOWN:
					position = new Position(character.getX()-1,character.getY(),character.getZ());
					if (maze3d.valid(position)&& maze3d.getValue(position)!=1) {
						character.setX(character.getX()-1);
						mazeDisplayer.setMazeData(maze3d.getCrossSectionByX(character.getX()));
						if (character.getX()==maze3d.getGoalPosition().getX()) {
							mazeDisplayer.setGoaldPosition(maze3d.getGoalPosition().getY(), maze3d.getGoalPosition().getZ());
						}
						else {
							mazeDisplayer.setGoaldPosition(-1,-1);
						}
						mazeDisplayer.redraw();
						if (character.equals(maze3d.getGoalPosition())) {
							winGame();
						}
					}
					break;
				default:
					break;
				}
			}
		};
		
		mazeDisplayer.addKeyListener(keyListener);
	}
	
	/**
	 * Pause.
	 */
	public void pause() {
		mazeDisplayer.removeKeyListener(keyListener);
	}
	
	/**
	 * Gets the current position.
	 *
	 * @return the current position
	 */
	public String getCurrentPosition (){
		return character.getX()+" "+character.getY()+" "+character.getZ();
	}
	
	/**
	 * Close.
	 */
	public void close(){
		if (task!=null) {
			task.cancel();
		}
		if (timer!=null) {
			timer.cancel();
		}
	}
	
	/**
	 * Win game.
	 */
	public void winGame(){
		mazeDisplayer.getShell().getShell().setEnabled(false);
		mazeDisplayer.getDisplay().asyncExec(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					mazeDisplayer.getParent().getParent().dispose();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
			
			

	}
	
	/**
	 * Display solution.
	 *
	 * @param s the s
	 */
	public void displaySolution(Solution<Position> s){
		
		ArrayList<Position> solution = s.getMovesSolution();		

		timer=new Timer();
		task=new TimerTask() {
			
			int sumPosition =1;
			
			
			@Override
			public void run() {
				mazeDisplayer.getDisplay().syncExec(new Runnable() {
					@Override
					public void run() {
						// UP
						if (character.getX()<solution.get(sumPosition).getX()) {
							character.setX(character.getX()+1);
							mazeDisplayer.setMazeData(maze3d.getCrossSectionByX(character.getX()));
							if (character.getX()==maze3d.getGoalPosition().getX()) {
								mazeDisplayer.setGoaldPosition(maze3d.getGoalPosition().getY(), maze3d.getGoalPosition().getZ());
							}
							else {
								mazeDisplayer.setGoaldPosition(-1,-1);
							}
							mazeDisplayer.redraw();
						}
						// DOWN
						else if (character.getX()>solution.get(sumPosition).getX()) {
							character.setX(character.getX()-1);
							mazeDisplayer.setMazeData(maze3d.getCrossSectionByX(character.getX()));
							if (character.getX()==maze3d.getGoalPosition().getX()) {
								mazeDisplayer.setGoaldPosition(maze3d.getGoalPosition().getY(), maze3d.getGoalPosition().getZ());
							}
							else {
								mazeDisplayer.setGoaldPosition(-1,-1);
							}
							mazeDisplayer.redraw();
						}
						else {
							character.setY(solution.get(sumPosition).getY());
							character.setZ(solution.get(sumPosition).getZ());
							mazeDisplayer.setCharacterPosition(character.getY(), character.getZ());
							
						}
						
					}
				});
				sumPosition++;
				if (solution.size()==sumPosition) {
					task.cancel();
					timer.cancel();
				}
			}
		};			
		
		timer.scheduleAtFixedRate(task, 0, 120);				
	}

}
