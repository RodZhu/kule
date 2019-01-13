package relation.partition;

import model.RelationDemo;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by rod on 2019/1/10.
 */
public class RelationPartitions extends Partitioner<Text, RelationDemo>{

    @Override
    public int getPartition(Text text, RelationDemo relationDemo, int numPartitions) {
//        text.toString() + relationDemo.getSon()
        if (relationDemo.getSon().equals("皇太极") || relationDemo.getFather().equals("皇太极")) {
            return 0;
        } else if (relationDemo.getSon().equals("福临") || relationDemo.getFather().equals("福临")){
            return 0;
        } else {
            return 1;
        }
    }
}
