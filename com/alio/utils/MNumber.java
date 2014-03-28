package com.alio.utils;

public class MNumber {

	/**
	 * 数值溢出
	 */
	public static final int OVER_FLOW = -1;

	public static final int CONTAIN = 0x3;

	public static final int LEFT_ACROSS = 0x5;

	public static final int RIGHT_ACROSS = 0x7;

	public static final int LEFT = 0x9;

	public static final int RIGHT = 0x11;

	public static final int INCLUDE = 0x13;

	private int min = 0;
	private int max = 0;
	private int multiple = 1; // default

	private int index = 0;

	/**
	 * 1-10 <br/>
	 * [10-100]/10
	 */
	public MNumber(String sNum) {
		if (sNum.contains("-")) {
			String[] tempArr = null;
			if (sNum.startsWith("[")) {
				tempArr = sNum.split("/");
				multiple = Integer.valueOf(tempArr[1]);
				sNum = tempArr[0];
				sNum = sNum.substring(1, sNum.length() - 1);
			}
			sNum = clearBlank(sNum);
			tempArr = sNum.split("-");
			min = Integer.valueOf(tempArr[0]);
			max = Integer.valueOf(tempArr[1]);
		} else {
			min = max = Integer.valueOf(sNum);
			multiple = 0;
		}
	}

	public boolean hasSubNum(int curNum) {
		if (multiple == 0) {
			return false;
		}
		if (validateData(curNum)) {
			return true;
		}
		return false;
	}

	public int next(int curNum) {
		curNum = formatNum(curNum);
		if (curNum > max) {
			return OVER_FLOW;
		}
		if (hasSubNum(curNum)) {
			curNum = curNum + multiple;
			if (curNum > max) {
				return OVER_FLOW;
			}
			return curNum;
		}
		return OVER_FLOW;
	}

	public int prev(int curNum) {
		curNum = formatNum(curNum);
		if (curNum < min) {
			return OVER_FLOW;
		}
		if (hasSubNum(curNum)) {
			curNum = curNum - multiple;
			if (curNum < min) {
				return OVER_FLOW;
			}
			return curNum;
		}
		return OVER_FLOW;
	}

	public boolean validateData(int num) {
		if (num <= max && num >= min) {
			if (multiple == 0 || multiple == 1 || num % multiple == 0) {
				return true;
			}
		}
		return false;
	}

	public int formatNum(int num) {
		num = num > max ? max : num;
		num = num < min ? min : num;
		if (multiple == 0 || multiple == 1) {
			return num;
		} else {
			int overflow = num % multiple;
			if (overflow >= (multiple / 2)) {
				return num += multiple - overflow;
			} else {
				return num -= overflow;
			}
		}
	}

	public int compareWith(int num) {
		if (multiple > 0) {
			if (min <= num && num <= max) {
				return 0; // 包含
			} else if (num < min) {
				return 1; // 小于
			} else {
				return -1;// 大于
			}
		} else {
			int diff = max - num;
			if (diff > 0) {
				return 1;
			} else if (diff < 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	public int compareWith(MNumber num) {
		if (max > num.max) {
			if (min <= num.min) {
				return CONTAIN;
			} else if (min > num.min && min < num.max) { // 右交叉
				return RIGHT_ACROSS;
			} else { // 右边
				return RIGHT;
			}
		} else if (max == num.max) {
			if (min <= num.min) {
				return CONTAIN;
			} else { // 右边
				return INCLUDE;
			}
		} else if (max < num.max && max > num.min) { // 左交叉
			if (min >= num.min) {
				return INCLUDE;
			}
			return LEFT_ACROSS;
		} else {
			return LEFT;
		}
	}

	private String clearBlank(String src) {
		StringBuffer result = new StringBuffer();
		char c = ' ';
		for (int index = 0, size = src.length(); index < size; index++) {
			c = src.charAt(index);
			if (src.charAt(index) == ' ') {
				continue;
			} else {
				result.append(c);
			}
		}
		return result.toString();
	}

	/**
	 * 获取最小值
	 * 
	 * @return 最小值
	 */
	public int getMin() {
		return min;
	}

	/**
	 * 设置最小值
	 * 
	 * @return 最小值
	 */
	public void setMin(int min) {
		this.min = min;
	}

	/**
	 * 获取最大值
	 * 
	 * @return 最大值
	 */
	public int getMax() {
		return max;
	}

	/**
	 * 设置最大值
	 * 
	 * @return 最大值
	 */
	public void setMax(int max) {
		this.max = max;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
