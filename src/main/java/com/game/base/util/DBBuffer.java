package com.game.base.util;

import java.nio.charset.Charset;
import java.util.Date;

import org.apache.mina.core.buffer.IoBuffer;


public final class DBBuffer {
	
	private IoBuffer b = IoBuffer.allocate(512).setAutoExpand(true);
	public static Charset charset = Charset.forName("UTF-8");

	private DBBuffer() {
	}

	public static DBBuffer warp(byte[] src) {
		DBBuffer db = new DBBuffer();
		db.b = IoBuffer.wrap(src);
		return db;
	}

	public static DBBuffer allocate() {
		DBBuffer db = new DBBuffer();
		return db;
	}

	public void putInt(int in) {
		b.putInt(in);
	}

	public void putLong(long in) {
		b.putLong(in);
	}

	public void putString(String in) {
		byte[] inarray = in.getBytes(charset);
		b.putInt(inarray.length);
		b.put(inarray);
	}

	public void putDate(Date d) {
		b.putLong(d.getTime());
	}

	public void putBytes(byte[] bytes) {
		b.putInt(bytes.length);
		b.put(bytes);
	}

	public byte[] toBytes() {
		b.shrink();
		return b.array();
	}

	public int getInt() {
		if (b.hasRemaining())
			return b.getInt();
		else
			return 0;
	}

	public long getLong() {
		if (b.hasRemaining())
			return b.getLong();
		else
			return 0;
	}

	public String getString() {
		if (b.hasRemaining()) {
			byte[] temp = new byte[b.getInt()];
			b.get(temp);
			return new String(temp, charset);
		} else
			return "";

	}

	public Date getDate() {
		if (b.hasRemaining()) {
			return new Date(b.getLong());
		} else
			return null;

	}

	public byte[] getBytes() {
		if (b.hasRemaining()) {
			byte[] temp = new byte[b.getInt()];
			b.get(temp);
			return temp;
		} else
			return null;

	}

	public void putByte(byte in) {
		b.put(in);
	}

	public byte getByte() {
		if (b.hasRemaining())
			return b.get();
		else
			return 0;
	}

	public void putBoolean(boolean in) {
		b.put((byte) (in ? 1 : 0));
	}

	public boolean getBoolean() {
		if (b.hasRemaining())
			return b.get() == 1 ? true : false;
		else{
			return false;
		}
	}

	public void free() {
		b.free();
	}
	
}
