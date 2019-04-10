package storm.custom_grouping;


import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import java.util.Map;

/**
 * Created by rod on 2019/3/20.
 */
public class LessThanBolt extends BaseRichBolt {

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {

    }

    public void execute(Tuple input) {
        Integer number = input.getIntegerByField("number");
        System.out.println("Less: " + number);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}
