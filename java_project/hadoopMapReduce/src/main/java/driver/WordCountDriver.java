package driver;

import map.WordCountMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by rod on 2018/12/12.
 */
public class WordCountDriver {

    public static void main(String[] args) throws Exception {
        Configuration c = new Configuration();

        // 获取job对象实例
        Job job = Job.getInstance(c);

        // 设置job方法的入口驱动类
        job.setJarByClass(WordCountDriver.class);

        // 设置mapper组件类
        job.setMapperClass(WordCountMapper.class);

        // 设置输出key类型
        job.setMapOutputKeyClass(LongWritable.class);
        // 设置输出value类型
        job.setMapOutputValueClass(Text.class);

        // 设置输入路径
        FileInputFormat.setInputPaths(job, new Path("hdfs://10.199.164.183:9000/park01/test.txt"));
        // 设置输出路径
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.199.164.183:9000/park01/result"));

        // 提交job
        job.waitForCompletion(true);
    }
}
