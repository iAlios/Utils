package com.alio.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BitUtils {

	public static final boolean[] byte2Bit(byte... data) {
		boolean[] result = new boolean[Byte.SIZE * data.length];
		int offset = 0;
		for (byte b : data) {
			for (int i = 0; i < Byte.SIZE; i++)
				result[i + offset] = (b >> (Byte.SIZE - 1 - i) & 0x1) != 0x0;
			offset += Byte.SIZE;
		}
		return result;
	}

	public static final byte[] bit2Byte(boolean[] data) {
		byte[] result = new byte[data.length / Byte.SIZE
				+ (data.length % Byte.SIZE > 0 ? 1 : 0)];
		int offset = 0;
		for (int i = 0; i < data.length; i++) {
			offset = (Byte.SIZE - 1) - i % Byte.SIZE;
			result[i / Byte.SIZE] |= (data[i] ? 0x1 : 0x0) << offset;
		}
		return result;
	}

	public static final boolean[] toBoolList(ArrayList<Boolean> bitList) {
		boolean[] result = new boolean[bitList.size()];
		int index = 0;
		for (boolean b : bitList) {
			result[index++] = b;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(HexDump.byteToHex((byte) 5));
		System.out.println(HexDump.byteToBitAstring((byte) 5));
		System.out.println(HexDump.bitArrayToString(byte2Bit((byte) 5)));

		System.out.println(HexDump.toHexString(bit2Byte(byte2Bit((byte) 5))));
	}
}
