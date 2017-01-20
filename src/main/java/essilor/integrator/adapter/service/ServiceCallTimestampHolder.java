package essilor.integrator.adapter.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceCallTimestampHolder {

	private static final ThreadLocal<Long> holder = new ThreadLocal<Long>();
	private static final ThreadLocal<DateFormat> format = new ThreadLocal<DateFormat>() {
		protected DateFormat initialValue() {
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return f;
		};
	};

	public static void setTimestamp(long timestamp) {
		holder.set(timestamp);
	}

	public static long getAsLong() {
		Long timestamp = holder.get();
		if (timestamp == null) {
			throw new IllegalStateException("Service call timestamp null");
		} else {
			return timestamp;
		}
	}

	public static String getAsDateTime() {
		return format.get().format(new Date(getAsLong()));
	}
}
