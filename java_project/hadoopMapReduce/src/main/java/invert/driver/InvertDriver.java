package invert.driver;

import invert.combiner.InvertCombiner;
import invert.mapper.InvertMapper;
import invert.reduce.InvertReduce;
import model.InvertDemo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by rod on 2019/1/12.
 */
public class InvertDriver {

    public static void main(String[] args) throws Exception{
        Configuration c= new Configuration();

        Job job = Job.getInstance(c);

        job.setJarByClass(InvertDriver.class);

        job.setMapperClass(InvertMapper.class);
        job.setMapOutputValueClass(InvertDemo.class);
        job.setMapOutputKeyClass(Text.class);

        job.setCombinerClass(InvertCombiner.class);

        job.setReducerClass(InvertReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 设置输入路径
        FileInputFormat.setInputPaths(job, new Path("hdfs://10.199.164.183:9000/invert/"));
        // 设置输出路径
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.199.164.183:9000/invert/result"));

        job.waitForCompletion(true);
    }
}
