package com.alio.utils;

import java.util.ArrayList;

public class MNumberList extends ArrayList<MNumber> {

	private static final long serialVersionUID = 1L;

	public MNumberList() {
		super();
	}

	/**
	 * String num1 = "1-10,15,20,50"; <br/>
	 * String num2 = "[10-100]/10,[100-5000]/100";
	 * 
	 * @param numbers
	 */
	public MNumberList(String numbers) {
		super();
		String[] tempArr = numbers.split(",");
		for (String number : tempArr) {
			add(new MNumber(number));
		}
	}

	public int next(int curNum) {
		curNum = formatNum(curNum);
		MNumber number = indexMax(curNum);
		if (number.hasSubNum(curNum)) {
			curNum = number.next(curNum);
			if (curNum != MNumber.OVER_FLOW) {
				return curNum;
			}
		}
		int index = number.getIndex();
		if (index + 1 >= size()) {
			return number.getMax();
		} else {
			return get(index + 1).getMin();
		}

	}

	public int prev(int curNum) {
		curNum = formatNum(curNum);
		MNumber number = indexMin(curNum);
		if (number.hasSubNum(curNum)) {
			curNum = number.prev(curNum);
			if (curNum != MNumber.OVER_FLOW) {
				return curNum;
			}
		}
		int index = number.getIndex();
		if (index - 1 < 0) {
			return number.getMin();
		} else {
			return get(index - 1).getMax();
		}
	}

	public int formatNum(int curNum) {
		int diff = 0;
		MNumber maxMNumber = getMaxMNumber();
		MNumber minMNumber = getMinMNumber();
		for (MNumber cNumber : this) {
			diff = cNumber.compareWith(curNum);
			if (diff == 0) {
				return cNumber.formatNum(curNum);
			} else if (diff > 0) {
				maxMNumber = getMinMNumber(maxMNumber, cNumber);
			} else {
				minMNumber = getMaxMNumber(minMNumber, cNumber);
			}
		}
		int max = maxMNumber.formatNum(curNum);
		int min = minMNumber.formatNum(curNum);
		diff = (max - curNum) - (curNum - min);
		if (diff >= 0) {
			return min;
		} else {
			return max;
		}
	}

	public MNumber getMaxMNumber() {
		MNumber mNumber = null;
		for (MNumber cMNumber : this) {
			if (mNumber == null) {
				mNumber = cMNumber;
			} else {
				mNumber = getMaxMNumber(mNumber, cMNumber);
			}
		}
		return mNumber;
	}

	public MNumber getMinMNumber() {
		MNumber mNumber = null;
		for (MNumber cMNumber : this) {
			if (mNumber == null) {
				mNumber = cMNumber;
			} else {
				mNumber = getMinMNumber(mNumber, cMNumber);
			}
		}
		return mNumber;
	}

	private MNumber getMinMNumber(MNumber mNumber, MNumber cNumber) {
		int result = mNumber.compareWith(cNumber);
		if (result == MNumber.INCLUDE || result == MNumber.LEFT_ACROSS
				|| result == MNumber.LEFT) {
			return mNumber;
		} else {
			return cNumber;
		}
	}

	private MNumber getMaxMNumber(MNumber mNumber, MNumber cNumber) {
		int result = mNumber.compareWith(cNumber);
		if (result == MNumber.INCLUDE || result == MNumber.LEFT_ACROSS
				|| result == MNumber.LEFT) {
			return cNumber;
		} else {
			return mNumber;
		}
	}

	public MNumber indexMin(int curNum) {
		MNumber minMNumber = null;
		for (MNumber cNumber : this) {
			if (cNumber.compareWith(curNum) == 0) {
				if (minMNumber == null) {
					minMNumber = cNumber;
				} else {
					minMNumber = getMinMNumber(minMNumber, cNumber);
				}
			}
		}
		if (minMNumber == null) {
			return get(0);
		} else {
			return minMNumber;
		}
	}

	public MNumber indexMax(int curNum) {
		MNumber maxMNumber = null;
		for (MNumber cNumber : this) {
			if (cNumber.compareWith(curNum) == 0) {
				if (maxMNumber == null) {
					maxMNumber = cNumber;
				} else {
					maxMNumber = getMaxMNumber(maxMNumber, cNumber);
				}
			}
		}
		if (maxMNumber == null) {
			return get(0);
		} else {
			return maxMNumber;
		}
	}

	public boolean add(MNumber cMNumber) {
		if (contains(cMNumber)) {
			return false;
		}
		int index = size();
		super.add(index, cMNumber);
		cMNumber.setIndex(index);
		return true;
	}

}
