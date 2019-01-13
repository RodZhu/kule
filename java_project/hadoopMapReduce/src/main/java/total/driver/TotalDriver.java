package total.driver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import total.mapper.TotalMapper;
import total.partition.TotalPaitition;
import total.reduce.TotalReduce;

import java.io.IOException;

/**
 * Created by rod on 2019/1/12.
 */
public class TotalDriver {

    public static void main(String[] args) throws Exception {
        Configuration c = new Configuration();

        Job job = Job.getInstance(c);

        job.setJarByClass(TotalDriver.class);

        job.setMapperClass(TotalMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(3);
        job.setPartitionerClass(TotalPaitition.class);

        job.setReducerClass(TotalReduce.class);
        job.setOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path("hdfs://10.199.164.183:9000/totalsort/totalsort.txt"));
        // 设置输出路径
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.199.164.183:9000/totalsort/result"));

        // 提交job
        job.waitForCompletion(true);
    }
}
