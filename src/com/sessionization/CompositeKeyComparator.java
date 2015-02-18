package com.sessionization;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CompositeKeyComparator extends WritableComparator {

	protected CompositeKeyComparator() {
		super(CompositeKey.class, true);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {

		CompositeKey k1 = (CompositeKey) w1;
		CompositeKey k2 = (CompositeKey) w2;

		int result = k1.getIp().compareTo(k2.getIp());
		if (0 == result) {
			result = k1.getTimestamp().compareTo(k2.getTimestamp());
		}
		return result;
	}

}
