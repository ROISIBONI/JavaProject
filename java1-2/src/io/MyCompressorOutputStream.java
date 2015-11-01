package io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out=out;
	}
	
	@Override
	public void write(byte [] arrByte) throws IOException {
		int counter = 1;
		for (int i = 0; i < arrByte.length-1; i++) {
			if ((arrByte[i] == arrByte[i+1]) && counter!=128 ) {
				counter++;
			}
			else {
				out.write(arrByte[i]);
				out.write(counter);
				counter = 1;
			}
		}
		if (arrByte[arrByte.length-1] != arrByte[arrByte.length-2]) {
			out.write(arrByte[arrByte.length-1]);
			out.write(1);
		}
		else {
			out.write(arrByte[arrByte.length-1]);
			out.write(counter);
		}
	}
	
	@Override
	public void write(int num) throws IOException {
		ByteBuffer size = ByteBuffer.allocate(Integer.BYTES);
		size.putInt(num);
		for (int i = 0; i < Integer.BYTES; i++) {
			out.write(size.get(i));
		}
	}
}
