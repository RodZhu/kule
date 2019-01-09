package distinct.reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by rod on 2019/1/9.
 */
public class DistinctReduce extends Reducer<Text, IntWritable, Text, Text>{

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for(IntWritable iw : values) {
           sum = sum + iw.get();
        }
        context.write(key, new Text(String.valueOf(sum)));
    }
}
