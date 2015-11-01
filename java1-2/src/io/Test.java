package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.DFS1Maze3dGenerator;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.PrimMaze3dGenerator;


public class Test {

	public static void main(String[] args) throws IOException {
		Maze3dGenerator dfs= new DFS1Maze3dGenerator(); 
		Maze3dGenerator prim= new PrimMaze3dGenerator();
		
		Maze3d maze1 = dfs.generate(3, 3, 3);
		Maze3d maze2 = prim.generate(3, 3, 3);
		
		// save it to a file
		OutputStream out=new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		out.write(maze1.toByteArray().length);
		out.write(maze1.toByteArray());
		out.write(maze2.toByteArray().length);
		out.write(maze2.toByteArray());
		out.flush();
		out.close();
		
		//maze1.print();
		//maze2.print();
		
		// load it to a file
		InputStream in=new MyDecompressorInputStream(new FileInputStream("1.maz"));
		byte [] a= new byte [in.read()]; // maze1.toByteArray().length
		in.read(a);
		byte [] b= new byte [in.read()]; // maze2.toByteArray().length
		in.read(b);
		in.close();
		
		Maze3d loaded1=new Maze3d(a);
		Maze3d loaded2=new Maze3d(b);
		
		System.out.println(loaded1.equals(maze1));
		System.out.println(loaded2.equals(maze2));		
	}
}
