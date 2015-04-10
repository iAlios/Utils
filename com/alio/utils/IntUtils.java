
package com.alio.utils;

public class IntUtils {

	/**
	 * short 转 byte[] (由高位到低位)
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] shortToByteArray(short i) {
		byte[] result = new byte[2];
		// 由高位到低位
		result[0] = (byte) ((i >> 8) & 0xFF);
		result[1] = (byte) (i & 0xFF);
		return result;
	}

	/**
	 * byte[]转 short (由高位到低位)
	 * 
	 * @param bytes
	 * @return
	 */
	public static short byteArrayToShort(byte[] bytes) {
		short value = 0;
		int shift = 0;
		// 由高位到低位
		for (int i = 0; i < 2; i++) {
			shift = (2 - 1 - i) * 8;
			value += (bytes[i] & 0x000000FF) << shift;// 往高位游
		}
		return value;
	}

	/**
	 * short 转 byte[](由低位到高位)
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] shortToByteArrayL(short i) {
		byte[] result = new byte[2];
		// 由低位到高位
		result[1] = (byte) ((i >> 8) & 0xFF);
		result[0] = (byte) (i & 0xFF);
		return result;
	}

	/**
	 * byte[]转  short (由低位到高位)
	 * 
	 * @param bytes
	 * @return
	 */
	public static int byteArrayToShortL(byte[] bytes) {
		int value = 0;
		// 由低位到高位
		int shift = 0;
		for (int i = 0; i < 2; i++) {
			shift = i * 8;
			value += (bytes[i] & 0x000000FF) << shift;// 往高位游
		}
		return value;
	}

	/**
	 * int转 byte[] (由高位到低位)
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] intToByteArray(int i) {
		byte[] result = new byte[4];
		// 由高位到低位
		result[0] = (byte) ((i >> 24) & 0xFF);
		result[1] = (byte) ((i >> 16) & 0xFF);
		result[2] = (byte) ((i >> 8) & 0xFF);
		result[3] = (byte) (i & 0xFF);
		return result;
	}

	/**
	 * byte[]转int (由高位到低位)
	 * 
	 * @param bytes
	 * @return
	 */
	public static int byteArrayToInt(byte[] bytes) {
		int value = 0;
		int shift = 0;
		// 由高位到低位
		for (int i = 0; i < 4; i++) {
			shift = (4 - 1 - i) * 8;
			value += (bytes[i] & 0x000000FF) << shift;// 往高位游
		}
		return value;
	}

	/**
	 * int 转 byte[](由低位到高位)
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] intToByteArrayL(int i) {
		byte[] result = new byte[4];
		// 由低位到高位
		result[3] = (byte) ((i >> 24) & 0xFF);
		result[2] = (byte) ((i >> 16) & 0xFF);
		result[1] = (byte) ((i >> 8) & 0xFF);
		result[0] = (byte) (i & 0xFF);
		return result;
	}

	/**
	 * byte[]转int (由低位到高位)
	 * 
	 * @param bytes
	 * @return
	 */
	public static int byteArrayToIntL(byte[] bytes) {
		int value = 0;
		// 由低位到高位
		int shift = 0;
		for (int i = 0; i < 4; i++) {
			shift = i * 8;
			value += (bytes[i] & 0x000000FF) << shift;// 往高位游
		}
		return value;
	}

	public static void main(String[] args) {
		short dd = 2;
		System.out.println(HexDump.dumpHexString(shortToByteArray(dd)));
		System.out.println(byteArrayToShort(shortToByteArray(dd)));
		System.out.println(HexDump.dumpHexString(shortToByteArrayL(dd)));
		System.out.println(byteArrayToShortL(shortToByteArrayL(dd)));

		System.out.println(HexDump.dumpHexString(intToByteArray(dd)));
		System.out.println(byteArrayToInt(intToByteArray(dd)));
		System.out.println(HexDump.dumpHexString(intToByteArrayL(dd)));
		System.out.println(byteArrayToIntL(intToByteArrayL(dd)));

	}

}
