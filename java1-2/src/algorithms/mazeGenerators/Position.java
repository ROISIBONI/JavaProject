package algorithms.mazeGenerators;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * The Class Position.
 */
@SuppressWarnings("serial")
public class Position implements Serializable {

	/**  Members. */
	private int x;
	private int y;
	private int z;

	/**
	 * Ctor
	 * Instantiates a new position.
	 */
	public Position() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	/**
	 * Ctor
	 * Instantiates a new position.
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Position( int y, int z) {
		this.x = 0;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Copy Ctor
	 * Instantiates a new position.
	 * @param copyPosition the copy position
	 */
	public Position(Position copyPosition){
		this.x = copyPosition.x;
		this.y = copyPosition.y;
		this.z = copyPosition.z;
	}

	/**
	 * Gets the x.
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y.
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gets the z.
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Sets the x.
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Sets the y.
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets the z.
	 * @param z the new z
	 */
	public void setZ(int z) {
		this.z = z;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return (new String (x+","+y+","+z));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o){
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;		
		if (((Position)o).x==this.x && ((Position)o).y==this.y && ((Position)o).z==this.z) {
			return true;
		}
		return false;
	}
	
	/**
	 * Sets the position.
	 * @param p the new position
	 */
	public void setPosition (Position p)
	{
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
	}
	
	public byte[] toByteArray (){
		ByteBuffer arr = ByteBuffer.allocate(Integer.BYTES*3);
		arr.putInt(x);
		arr.putInt(y);
		arr.putInt(z);
		return arr.array();	
	}
}
