package flow.reduce;

import model.FlowDemo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by rod on 2019/1/10.
 */
public class FLowReduce extends Reducer<Text, FlowDemo, Text, FlowDemo> {

    @Override
    protected void reduce(Text key, Iterable<FlowDemo> values, Context context) throws IOException, InterruptedException {

        int sum = 0;

        for(FlowDemo tmp : values) {
            sum = sum + tmp.getFlow();
        }
        FlowDemo fs = new FlowDemo();
        fs.setFlow(sum);
        context.write(key, fs);
    }
}
