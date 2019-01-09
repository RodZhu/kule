package map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by rod on 2018/12/12.
 */

/**
 *  1，job的MapperTask如何处理数据是有mapper组件决定的，此类代码需要继承Mapper。由程序员自己开发
 *  2，继承Mapper的四个范性，第一个范型对应输入key，第二个范型对应输入value，第三个范型对应输出key，第四个输出value。
 */
public class WordCountMapper  extends Mapper<LongWritable, Text, Text, IntWritable>{

    /**
     * 该map方法传入输入key，输出map，多少行输出该map方法被调用多少次。
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        String s = value.toString();
        String[] split = s.split(" ");

        for(String tmp :  split) {
            context.write(new Text(tmp), new IntWritable(1));
        }
    }
}
