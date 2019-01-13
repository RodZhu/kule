package flow.driver;

import flow.mapper.FlowMapper;
import flow.reduce.FLowReduce;
import model.FlowDemo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by rod on 2019/1/10.
 */
public class FLowDriver {
    public static void main(String[] args) throws Exception {
        Configuration c = new Configuration();

        // 获取job对象实例
        Job job = Job.getInstance(c);

        // 设置job方法的入口驱动类
        job.setJarByClass(FLowDriver.class);

        // 设置mapper组件类
        job.setMapperClass(FlowMapper.class);

        // 设置输出key类型
        job.setMapOutputKeyClass(Text.class);
        // 设置输出value类型
        job.setMapOutputValueClass(FlowDemo.class);

        // 设置reduce类
        job.setReducerClass(FLowReduce.class);
        // 设置reduce 输出key 类
        job.setOutputKeyClass(Text.class);
        // 设置reduce value类
        job.setOutputValueClass(FlowDemo.class);

        // 设置输入路径
        FileInputFormat.setInputPaths(job, new Path("hdfs://10.199.164.183:9000/flow/flow.txt"));
        // 设置输出路径
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.199.164.183:9000/flow/result"));

        // 提交job
        job.waitForCompletion(true);
    }
}
