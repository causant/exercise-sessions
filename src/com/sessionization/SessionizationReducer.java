package com.sessionization;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SessionizationReducer extends Reducer<CompositeKey, LongWritable, Text, SessionTimestampsWritable>{
	
	private final long thirtyMinutesMillisecs = 1000 * 60 * 30;
	
	//Starting from input ordered by ip and access timestamp and grouped by ip, we reconstruct sessions
	@Override
	public void reduce(CompositeKey key, Iterable<LongWritable> values, Reducer<CompositeKey, LongWritable, Text, SessionTimestampsWritable>.Context context) throws IOException, InterruptedException {
		
		boolean firstSession = true;
		long startTime = 0, finishTime = 0;
		for (LongWritable val: values){
			if (firstSession){
				startTime = finishTime = val.get();
				firstSession = false;
			}
			else if (val.get() > (finishTime + thirtyMinutesMillisecs)){
				/* The text says: "Once this period of inactivity is reached, the user is assumed to have left the site 
				/  or stopped using the browser entirely, and the session is ended", so set SESSION_END to the timestamp of
				/  the last access plus 30 minutes 
				*/
				context.write(new Text(key.getIp()), new SessionTimestampsWritable(startTime, finishTime + thirtyMinutesMillisecs));
				startTime = val.get();
				finishTime = val.get();
			}
			else
				finishTime = val.get();
		}
		
		context.write(new Text(key.getIp()), new SessionTimestampsWritable(startTime, finishTime + thirtyMinutesMillisecs));
	}
}