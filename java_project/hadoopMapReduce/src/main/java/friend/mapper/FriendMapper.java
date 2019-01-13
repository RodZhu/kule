package friend.mapper;

import model.FriendDemo;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by rod on 2019/1/12.
 */
public class FriendMapper extends Mapper<LongWritable,Text, Text, Text>{

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String valueStr = value.toString();

        String[] split = value.toString().split(" ");
        context.write(new Text(split[0]), new Text(split[1]));
    }
}
