package scoreinput;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ScoreInputDriver {
	
	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		
		job.setJarByClass(ScoreInputDriver.class);
		job.setMapperClass(ScoreInputMapper.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		//--设置自定义的格式输入组件，此组件决定了Mapper的输入key和输入value
		//--此组件，如果不设定，默认用的是:TextInputFormat
		job.setInputFormatClass(ScoreInputFormat.class);
		
		//--如果是完全分布式，路径写的是active状态的namenode地址
		FileInputFormat.setInputPaths(job,
				new Path("hdfs://192.168.150.137:9000/scoreinput"));
		
		FileOutputFormat.setOutputPath(job,
				new Path("hdfs://192.168.150.137:9000/scoreinput/result"));
		
		job.waitForCompletion(true);
	}

}
