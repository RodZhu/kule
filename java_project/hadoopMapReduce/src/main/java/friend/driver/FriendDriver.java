package friend.driver;

import friend.mapper.FriendMapper;
import friend.mapper.FriendMapper2;
import friend.reduce.FriendReduce;
import friend.reduce.FriendReduce2;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * Created by rod on 2019/1/13.
 */
public class FriendDriver {

    public static void main(String[] args) throws Exception{
        Configuration c = new Configuration();

        Job job = Job.getInstance(c);

        job.setJarByClass(FriendDriver.class);

        job.setMapperClass(FriendMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setReducerClass(FriendReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path("hdfs://10.199.164.183:9000/friend/fridend.txt"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://10.199.164.183:9000/friend/result"));

        // Hadoop 提供了job链机制。
        if (job.waitForCompletion(true)) {
            Job job2 = Job.getInstance(c);
//            job2.set
            job2.setJarByClass(FriendDriver.class);
            job2.setMapperClass(FriendMapper2.class);
            job2.setMapOutputKeyClass(Text.class);
            job2.setMapOutputValueClass(Text.class);

            job2.setReducerClass(FriendReduce2.class);
            job2.setOutputKeyClass(Text.class);
            job2.setOutputValueClass(NullWritable.class);

            FileInputFormat.setInputPaths(job2, new Path("hdfs://10.199.164.183:9000/friend/result"));
            FileOutputFormat.setOutputPath(job2, new Path("hdfs://10.199.164.183:9000/friend/result2"));
            job2.waitForCompletion(true);
        }
    }
}
