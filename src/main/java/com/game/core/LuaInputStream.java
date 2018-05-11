package com.game.core;


import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;

@SuppressWarnings("unchecked")
public class LuaInputStream {

	public IoBuffer buff = null;

	public LuaInputStream() {
	};

	public final byte read_byte() throws IOException {
		return buff.get();
	}

	public final boolean read_bool() throws IOException {
		byte b = read_byte();
		return (b == 1);
	}

	public final int read_int() throws IOException {
		byte tag = buff.get();
		if (tag != BIOType.TYPE_INT) {
			throw new IOException("read_int:type error");
		}
		return buff.getInt();
	}

	public final long read_long() throws IOException {
		byte tag = buff.get();
		if (tag != BIOType.TYPE_LONG) {
			throw new IOException("read_int:type error");
		}
		return buff.getLong();
	}

	public final String read_cstr() throws IOException {
		byte tag = buff.get();
		if (tag == BIOType.TYPE_NIL)
			return null;
		else if (tag != BIOType.TYPE_CSTR) {
			throw new IOException("read_cstr:type error");
		}
		return BioHelper.getCStr(buff);
	}

	public final String read_wstr() throws IOException {
		byte tag = buff.get();
		if (tag == BIOType.TYPE_NIL)
			return null;
		else if (tag != BIOType.TYPE_WSTR) {
			throw new IOException("read_wstr:type error");
		}
		return BioHelper.getWStr(buff);
	}

	public final Object read_object() throws IOException {
		byte tag = buff.get();
		switch (tag) {
		case BIOType.TYPE_NIL: {
			return null;
		}
		case BIOType.TYPE_FALSE: {
			return false;
		}
		case BIOType.TYPE_TRUE: {
			return true;
		}
		case BIOType.TYPE_INT: {
			int v = buff.getInt();
			return v;
		}
		case BIOType.TYPE_LONG: {
			long v = buff.getLong();
			return v;
		}
		case BIOType.TYPE_CSTR: {
			String v = BioHelper.getCStr(buff);
			return v;
		}
		case BIOType.TYPE_WSTR: {
			String v = BioHelper.getWStr(buff);
			return v;
		}
		case BIOType.TYPE_TABLE: {
			Map vs = new HashMap();
			int len = buff.getInt();
			for (int i = 0; i < len; i++) {
				Object key = read_object();
				Object var = read_object();
				vs.put(key, var);
			}
			return vs;
		}
		case BIOType.TYPE_BYTES: {
			int len = buff.getInt();
			byte[] v = BioHelper.getBytes(buff, len);
			return v;
		}
		default:
			throw new IOException("read_object:type error" + tag);
		}
	}

	public final Map read_table() throws IOException {
		byte tag = buff.get();
		if (tag == BIOType.TYPE_NIL)
			return null;
		else if (tag != BIOType.TYPE_TABLE)
			throw new IOException("read_table:type error");

		Map vs = new Hashtable();
		int len = buff.getInt();
		for (int i = 0; i < len; i++) {
			Object key = read_object();
			Object var = read_object();
			vs.put(key, var);
		}
		return vs;
	}

	public final byte[] read_bytes() throws IOException {
		int len = buff.getInt();
		return BioHelper.getBytes(buff, len);
	}

	public static void main(String[] args) throws IOException {
		String file = "C:/ishang/r/r.bin";
		LuaInputStream in = BioHelper.fromFile(file);
		Object m = in.read_object();
	}
	
	public void free(){
		buff.free();
	}

}
