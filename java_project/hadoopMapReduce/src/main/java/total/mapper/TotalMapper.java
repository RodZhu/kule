package total.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by rod on 2019/1/11.
 */
public class TotalMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable>{

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] ss = value.toString().split(" ");
        for(String s : ss) {
            context.write(new IntWritable(Integer.parseInt(s)), new IntWritable(1));
        }
    }
}
