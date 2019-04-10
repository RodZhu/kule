package storm.word_count_ha;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by rod on 2019/3/19.
 */
public class WordCountSpout extends BaseRichSpout {

    private SpoutOutputCollector collector;

    private int index = 0;

    private Map<String, Values> tupleMap = new HashMap<String, Values>();

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
        Values objects = new Values(line);
        String id =  UUID.randomUUID().toString();
        collector.emit(objects, id);
        tupleMap.put(id, objects);
        try {
            Thread.currentThread().sleep(1000);
        }catch (Exception e) {
            System.out.println(e);
        }
        if (index == coments.length) {
            index = 0;
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

        outputFieldsDeclarer.declare(new Fields("line"));
    }

    @Override
    public void ack(Object msgId) {
        tupleMap.remove(msgId.toString());
    }

    @Override
    public void fail(Object msgId) {
        System.err.println("出现错误 我必须重发了 ---------->>> " + msgId);
        collector.emit(tupleMap.get(msgId.toString()), msgId);
    }
}
