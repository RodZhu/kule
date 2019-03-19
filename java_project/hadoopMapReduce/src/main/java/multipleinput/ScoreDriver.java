package multipleinput;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import output.AuthOutputFormat;
import scoreinput.ScoreInputFormat;


public class ScoreDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		
		job.setJarByClass(ScoreDriver.class);
		
		//--设置多输入源组件
		MultipleInputs.addInputPath(
				job,new Path("hdfs://192.168.150.137:9000/scoreinput/part-r-00000"),
				TextInputFormat.class,ScoreMapper1.class);
		
		MultipleInputs.addInputPath(
				job,new Path("hdfs://192.168.150.137:9000/scoreinput/score.txt"),
				ScoreInputFormat.class,ScoreMapper2.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		
		
		//--设置多输出源组件
		job.setReducerClass(ScoreReducer.class);
		MultipleOutputs.addNamedOutput(job,"tomfile",TextOutputFormat.class,Text.class,Text.class);
		MultipleOutputs.addNamedOutput(job,"rosefile",TextOutputFormat.class,Text.class,Text.class);
		MultipleOutputs.addNamedOutput(job,"jimfile", AuthOutputFormat.class,Text.class,Text.class);
		
		FileOutputFormat.setOutputPath(job,new Path("hdfs://192.168.150.137:9000/scoreinput/result"));
		
		job.waitForCompletion(true);
	}
}
