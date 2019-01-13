package flow.partition;

import model.FlowDemo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by rod on 2019/1/10.
 */
public class FlowPartition extends Partitioner<Text, FlowDemo>{

    @Override
    public int getPartition(Text text, FlowDemo flowDemo, int numPartitions) {
        return (flowDemo.getAddr().hashCode() & Integer.MAX_VALUE) % numPartitions;
    }
}
