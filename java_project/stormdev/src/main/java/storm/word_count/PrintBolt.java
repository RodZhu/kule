package storm.word_count;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rod on 2019/3/19.
 */
public class PrintBolt extends BaseRichBolt {


    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
    }

    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        Integer count = input.getIntegerByField("count");
        System.out.println("word: " + word + " count: " + count.toString() );
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
