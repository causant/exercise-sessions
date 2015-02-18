package com.sessionization;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

//Class used to represent outputvalues produced by SessionizationReducer
public class SessionTimestampsWritable implements Writable {
	    long val1 = 0;
	    long val2 = 0;

	    public SessionTimestampsWritable() {}

	    public SessionTimestampsWritable(long val1, long val2) {
	        this.val1 = val1;
	        this.val2 = val2;
	    }

	    @Override
	    public void readFields(DataInput in) throws IOException {
	        val1 = in.readLong();
	        val2 = in.readLong();
	    }

	    @Override
	    public void write(DataOutput out) throws IOException {
	        out.writeLong(val1);
	        out.writeLong(val2);
	    }

	    public void merge(SessionTimestampsWritable other) {
	        this.val1 += other.val1;
	        this.val2 += other.val2;
	    }

	    @Override
	    public String toString() {
	        return this.val1 + "," + this.val2;
	    }
}
