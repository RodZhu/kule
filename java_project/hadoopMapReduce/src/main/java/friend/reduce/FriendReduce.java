package friend.reduce;

import model.FriendDemo;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rod on 2019/1/12.
 */
public class FriendReduce extends Reducer<Text, Text, Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String keyname = key.toString();
        List<String> names = new ArrayList<String>();
        for(Text s : values) {
            String temp = s.toString();
            names.add(temp);
            if (keyname.compareTo(temp) < 0) {
                context.write(new Text(keyname + "-" + temp), new IntWritable(1));
            } else {
                context.write(new Text( temp+ "-" + keyname), new IntWritable(1));
            }
        }

        for (int i = 0; i < names.size(); i++) {
            String name1 = names.get(i);

            for(int j = 0; j < names.size(); j++) {
                String name2 = names.get(j);
                if (name1.compareTo(name2) < 0) {
                    context.write(new Text(name1 + "-" + name2), new IntWritable(2));
                }
            }
        }
    }
}
