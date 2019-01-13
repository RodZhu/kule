package invert.combiner;

import model.InvertDemo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by rod on 2019/1/12.
 */
public class InvertCombiner extends Reducer<Text, InvertDemo, Text, InvertDemo>{

    @Override
    protected void reduce(Text key, Iterable<InvertDemo> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        InvertDemo temp = null;
        for (InvertDemo id : values) {
            count = count + id.getCount();
            temp = id;
        }
        temp.setCount(count);
        context.write(key, temp);
    }
}
