
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
	 * byte[]转 short (由低位到高位)
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
	public static byte[] intToByteArray(int fbit) {
		// 由高位到低位
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (fbit >> ((4 - 1 - i) * 8));
		}
		return b;
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
	public static byte[] intToByteArrayL(int fbit) {
		byte[] result = new byte[4];
		for (int i = 0; i < 4; i++) {
			result[i] = (byte) (fbit >> (i * 8));
		}
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

	/**
	 * 浮点转换为字节
	 * 
	 * @param f
	 * @return
	 */
	public static byte[] floatToByteArray(float f) {
		int fbit = Float.floatToIntBits(f);
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (fbit >> (i * 8));
		}
		return b;
	}

	public static float byteArrayToFloat(byte[] bytes) {
		int value = 0;
		// 由低位到高位
		int shift = 0;
		for (int i = 0; i < 4; i++) {
			shift = i * 8;
			value += ((int) bytes[i] & 0x000000FF) << shift;// 往高位游
		}
		return Float.intBitsToFloat(value);
	}

	/**
	 * 将64位的long值放到8字节的byte数组
	 * 
	 * @param num
	 * @return 返回转换后的byte数组
	 */
	public static byte[] longToByteArray(long num) {
		byte[] result = new byte[8];
		for (int i = 0; i < 8; i++) {
			result[i] = (byte) (num >> (i * 8));
		}
		return result;
	}

	/**
	 * 将8字节的byte数组转成一个long值
	 * 
	 * @param byteArray
	 * @return 转换后的long型数值
	 */
	public static long byteArrayToLong(byte[] byteArray) {
		long value = 0;
		int shift = 0;
		for (int i = 0; i < 8; i++) {
			shift = i * 8;
			value += ((long) byteArray[i] & 0xFF) << shift;
		}
		return value;
	}

	/**
	 * double转换byte
	 * 
	 * @param x
	 */
	public static byte[] doubleToByteArray(double x) {
		byte[] b = new byte[8];
		long l = Double.doubleToLongBits(x);
		for (int i = 0; i < 8; i++) {
			b[i] = (byte) (l >> (i * 8));
		}
		return b;
	}

	/**
	 * 通过byte数组取得float
	 * 
	 * @param b
	 * @return
	 */
	public static double byteArrayToDouble(byte[] bytes) {
		long value = 0;
		int shift = 0;
		for (int i = 0; i < 8; i++) {
			shift = i * 8;
			value += ((long) bytes[i] & 0xFF) << shift;
		}
		return Double.longBitsToDouble(value);
	}

	public static void main(String[] args) {
		short dd = 2;
		System.out.println("=========================SHORT========================");
		System.out.println(HexDump.dumpHexString(shortToByteArray(dd)));
		System.out.println(byteArrayToShort(shortToByteArray(dd)));
		System.out.println(HexDump.dumpHexString(shortToByteArrayL(dd)));
		System.out.println(byteArrayToShortL(shortToByteArrayL(dd)));

		System.out.println("=========================INT========================");
		System.out.println(HexDump.dumpHexString(intToByteArray(dd)));
		System.out.println(byteArrayToInt(intToByteArray(dd)));
		System.out.println(HexDump.dumpHexString(intToByteArrayL(dd)));
		System.out.println(byteArrayToIntL(intToByteArrayL(dd)));

		System.out.println("=========================LONG========================");
		System.out.println(HexDump.dumpHexString(longToByteArray(dd * 1l)));
		System.out.println(byteArrayToLong(longToByteArray(dd * 1l)));

		System.out.println("=========================FLOAT========================");
		System.out.println(HexDump.dumpHexString(floatToByteArray(dd * 0.1f)));
		System.out.println(byteArrayToFloat(floatToByteArray(dd * 0.1f)));

		System.out.println("=========================DOUBLE========================");
		System.out.println(HexDump.dumpHexString(doubleToByteArray(dd * 0.1d)));
		System.out.println(byteArrayToDouble(doubleToByteArray(dd * 0.1d)));
	}

}
