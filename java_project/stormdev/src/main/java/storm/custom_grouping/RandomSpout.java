package storm.custom_grouping;


import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Random;

/**
 * Created by rod on 2019/3/20.
 */
public class RandomSpout extends BaseRichSpout {

    private SpoutOutputCollector collector;

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    public void nextTuple() {
        int i = new Random().nextInt(100);
        if (i < 50) {
            // 指定stream id
            collector.emit("lessThan", new Values(i));
        } else {
            collector.emit("moreThan", new Values(i));
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream("lessThan", new Fields("number"));
        declarer.declareStream("moreThan", new Fields("number"));
    }
}
