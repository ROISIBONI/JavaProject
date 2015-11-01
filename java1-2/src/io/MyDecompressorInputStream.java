package io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class MyDecompressorInputStream extends InputStream {

	private InputStream in;
	
	public MyDecompressorInputStream(InputStream in) {
		this.in=in;
	}
	
	@Override
	public int read(byte [] arr) throws IOException {
		int index=0;
		byte a;
		byte b;

		while (index!=arr.length) {
			a = (byte) in.read();
			b = (byte) in.read();
			
			for (int i = 0; i < b; i++) {
				arr[index]=a;
				index++;
			}
		}
		return index;
	}

	@Override
	public int read() throws IOException {
		byte [] size = new byte [Integer.BYTES];
		ByteBuffer num = ByteBuffer.allocate(Integer.BYTES);
		in.read(size, 0, Integer.BYTES);
		num.put(size);
		return num.getInt(0);
	}
}
