package friend.reduce;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by rod on 2019/1/13.
 */
public class FriendReduce2 extends Reducer<Text, Text, Text, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        boolean flag = false;
        for(Text t : values) {
            int f = Integer.parseInt(t.toString());
            if (f == 1) {
                flag = true;
            }
        }

        if (!flag) {
            context.write(key, NullWritable.get());
        }
    }
}
