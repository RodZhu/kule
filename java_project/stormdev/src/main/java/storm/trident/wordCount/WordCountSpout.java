package storm.trident.wordCount;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by rod on 2019/4/9.
 */
public class WordCountSpout extends BaseRichSpout{

    private String[] maps = new String[] {
            "hello 1804",
            "hello world",
            "hello vip",
            "hello java",
            "java world",
            "hello 1804",
            "hello 1804"
    };

    private SpoutOutputCollector spout;

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.spout = collector;
    }

    public void nextTuple() {

//        while(true) {
            for(String str : maps) {
                spout.emit(new Values(str));
                try {
                    Thread.currentThread().sleep(500);
                }catch (Exception e) {

                }
            }
//        }
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("line"));
    }
}
