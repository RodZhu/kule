package friend.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by rod on 2019/1/13.
 */
public class FriendMapper2 extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String tmp = value.toString();
        String[] ss = null;
        if (tmp.indexOf(" ") > 0) {
            ss = tmp.split(" ");
        } else {
            ss = tmp.split("\t");
        }
        context.write(new Text(ss[0]), new Text(ss[1]));
    }
}
