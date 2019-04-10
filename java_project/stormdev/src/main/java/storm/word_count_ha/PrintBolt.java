package storm.word_count_ha;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import java.util.Map;

/**
 * Created by rod on 2019/3/19.
 */
public class PrintBolt extends BaseRichBolt {


    private OutputCollector collector;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
    }

    public void execute(Tuple input) {
        try{
            String word = input.getStringByField("word");
            Integer count = input.getIntegerByField("count");
            System.out.println("word: " + word + " count: " + count.toString() );
            collector.ack(input);
        } catch(Exception e) {
            collector.fail(input);
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
