package com.sessionization;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//The Reducer sums up all the active sessions during the hour of the day (given by the input key)
public class HourlyStatsReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	
	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		
		int counter = 0;
		for (IntWritable val: values){
				counter += val.get();
			}
		
		context.write(key, new IntWritable(counter));
	}
}