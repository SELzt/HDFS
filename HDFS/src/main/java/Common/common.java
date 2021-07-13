package Common;

import org.apache.hadoop.conf.Configuration;

public class common {
	public static Configuration conf = new Configuration();
	static {
		conf.set("fs.defaultFS", "hdfs://192.168.17.132:9000");
	}
}
