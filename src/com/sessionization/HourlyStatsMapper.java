package com.sessionization;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HourlyStatsMapper extends Mapper <Object, Text, Text, IntWritable>{
	
	//The mapper maps the input into <K,V> where K is a Text containing the day and hour of an access, while
	// V is a IntWritable always set to 1 that indicates that a session was active during that hour of the day
	@Override
	public void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException{
		
		String line = value.toString();
		// Starting to parse the line: IP,start,end
		String [] fields = line.split(",");
		
		// We extract the session_start; 
		Long session_start = Long.parseLong(fields[1]);
		// We extract the session_end;
		Long session_end = Long.parseLong(fields[2]);
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("GMT-04:00"));
		cal.set(1995, Calendar.AUGUST, 29, 23, 00);
		cal.set(Calendar.SECOND, 0);
		
		//We set 25 hours, because we consider hourly stats from August 29 23:00 to August 30 23:59
		for (int i=0; i<25; i++){
			Long hour_start = cal.getTimeInMillis();
			Long hour_end = hour_start + 1000*60*59 + 1000*59; // start + 59 minutes + 59 seconds
			if ((session_start <= hour_start && session_end >= hour_end) 
					|| (session_start >= hour_start && session_start <= hour_end)
					|| (session_end >= hour_start && session_end <= hour_end))
				context.write(new Text(cal.get(Calendar.DAY_OF_MONTH) + "," +cal.get(Calendar.HOUR_OF_DAY)), new IntWritable(1));
			
			cal.add(Calendar.HOUR_OF_DAY, 1);
		}
		
	}
}
