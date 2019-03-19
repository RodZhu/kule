package input;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import output.AuthOutputFormat;

public class LineDriver {
	
	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		
		job.setJarByClass(LineDriver.class);
		job.setMapperClass(LineMapper.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		//--设置自定义的格式输入组件，此组件决定了Mapper的输入key和输入value
		//--此组件，如果不设定，默认用的是:TextInputFormat
		job.setInputFormatClass(LineNumberInputFormat.class);
		
		//--设置自定义的格式输出组件，如果不设定，默认有一个类：
		//--TextOutFormat，此类的格式：kv分隔符是tab制表符，行与行是换行符
		job.setOutputFormatClass(AuthOutputFormat.class);
		
		//--如果是完全分布式，路径写的是active状态的namenode地址
		FileInputFormat.setInputPaths(job,
				new Path("hdfs://192.168.150.137:9000/input"));
		
		FileOutputFormat.setOutputPath(job,
				new Path("hdfs://192.168.150.137:9000/input/result"));
		
		job.waitForCompletion(true);
	}

}
