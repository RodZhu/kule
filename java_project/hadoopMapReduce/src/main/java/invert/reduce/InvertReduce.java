package invert.reduce;

import model.InvertDemo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by rod on 2019/1/12.
 */
public class InvertReduce extends Reducer<Text, InvertDemo, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<InvertDemo> values, Context context) throws IOException, InterruptedException {
        String result = "";
        for(InvertDemo id :  values) {
           result = result + ", " +  id.toString();
        }
        context.write(key, new Text(result));
    }
}
