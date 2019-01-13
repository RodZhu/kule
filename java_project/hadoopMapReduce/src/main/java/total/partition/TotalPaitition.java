package total.partition;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by rod on 2019/1/12.
 */
public class TotalPaitition extends Partitioner<IntWritable,IntWritable>{

    @Override
    public int getPartition(IntWritable intWritable, IntWritable intWritable2, int numPartitions) {
        int i = intWritable.get();
        if(i < 100) {
            return 0;
        } else if (i  < 1000) {
            return 1;
        } else {
            return 2;
        }
    }
}
