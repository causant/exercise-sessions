package com.sessionization;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Partitioner;



 public class SessionizationPartitioner extends Partitioner<CompositeKey, LongWritable> {
        
	 @Override
        public int getPartition(CompositeKey key, LongWritable val, int numPartitions) {
            int hash = key.getIp().hashCode();
            int partition = hash & Integer.MAX_VALUE % numPartitions;
            return partition;
        }
	 
 }
