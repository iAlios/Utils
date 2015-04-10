
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

	/**
	 * 浮点转换为字节
	 * 
	 * @param f
	 * @return
	 */
	public static byte[] float2byte(float f) {
		// 把float转换为byte[]
		int fbit = Float.floatToIntBits(f);
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[i] = (byte) (fbit >> (24 - i * 8));
		}
		// 翻转数组
		int len = b.length;
		// 建立一个与源数组元素类型相同的数组
		byte[] dest = new byte[len];
		// 为了防止修改源数组，将源数组拷贝一份副本
		System.arraycopy(b, 0, dest, 0, len);
		byte temp;
		// 将顺位第i个与倒数第i个交换
		for (int i = 0; i < len / 2; ++i) {
			temp = dest[i];
			dest[i] = dest[len - i - 1];
			dest[len - i - 1] = temp;
		}
		return dest;
	}

	public static float byteArrayToFloat(byte[] b) {
		int l = b[0];
		l &= 0xff;
		l |= ((long) b[1] << 8);
		l &= 0xffff;
		l |= ((long) b[2] << 16);
		l &= 0xffffff;
		l |= ((long) b[3] << 24);
		return Float.intBitsToFloat(l);
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

		System.out.println(HexDump.dumpHexString(float2byte(dd * 0.1f)));
		System.out.println(byteArrayToFloat(float2byte(dd * 0.1f)));
	}

}
