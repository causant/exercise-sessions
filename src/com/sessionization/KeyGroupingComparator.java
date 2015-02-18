package com.sessionization;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class KeyGroupingComparator extends WritableComparator {
    protected KeyGroupingComparator() {
        super(CompositeKey.class, true);
    }   
    @SuppressWarnings("rawtypes")
    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        CompositeKey k1 = (CompositeKey)w1;
        CompositeKey k2 = (CompositeKey)w2;

        return k1.getIp().compareTo(k2.getIp());
    }

}
