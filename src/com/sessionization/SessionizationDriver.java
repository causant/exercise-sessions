package com.sessionization;

import org.apache.hadoop.util.ProgramDriver;

public class SessionizationDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {
			pgd.addClass("sessionization", Sessionization.class, "extract sessions info");
			pgd.addClass("hourly_stats", HourlyStats.class, "calculate sessions hourly stats");
			exitCode = pgd.run(args);
		}
		catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(exitCode);
	}

}
