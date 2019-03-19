package storm.word_count;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 * Created by rod on 2019/3/19.
 */
public class WordCountSpout extends BaseRichSpout{

    private SpoutOutputCollector collector;

    private int index = 0;

    private String[] coments = new String[] {
            "hello world",
            "hello storm",
            "hello 2019",
            "hello vipshop"
    };

    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {

        this.collector = spoutOutputCollector;
    }

    public void nextTuple() {
        String line = coments[index];
        index++;
        collector.emit(new Values(line));
        try {
            Thread.currentThread().sleep(1000);
        }catch (Exception e) {

        }
        if (index == coments.length) {
            index = 0;
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

        outputFieldsDeclarer.declare(new Fields("line"));
    }
}
