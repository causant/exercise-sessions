package com.sessionization;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SessionizationMapper extends Mapper <Object, Text, CompositeKey, LongWritable>{
	
	//The mapper maps the input into <K,V> where K is a composite key, composed by the ip and the access timestamp, while
	// V is a LongWritable containing the access timestamp
	//We use a composite key in order to perform a secondary sort on the output values of this mapper to sort it by ip and timestamp as explained
	// here: http://codingjunkie.net/secondary-sort/
	@Override
	public void map(Object key, Text value, Mapper<Object, Text, CompositeKey, LongWritable>.Context context) throws IOException, InterruptedException{
		
		String line = value.toString();
		// Starting to parse the line
		String [] fields = line.split("\\s");
		
		// We extract the request IP address 
		String ip = fields[0];
		
		// We instantiate a SimpleDateFormat object to extract the date of a request  
		SimpleDateFormat sdf = new SimpleDateFormat("[dd:HH:mm:ss]");		
		
		// We extract the date of the request
		long access_timestamp = 0;
		try {
			Date access_date = sdf.parse(fields[1]);
			// In order to extract a proper timestamp for this exercise, we need to set the date and timezone related to the example log
			Calendar cal = Calendar.getInstance();
			cal.setTime(access_date);
			cal.set(Calendar.YEAR, 1995);
			cal.set(Calendar.MONTH,Calendar.AUGUST);
			cal.setTimeZone(TimeZone.getTimeZone("GMT-04:00"));
			access_timestamp = cal.getTimeInMillis();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}	
		
		// We write the output of Map()
		context.write(new CompositeKey(ip,access_timestamp), new LongWritable(access_timestamp));
	}
}
