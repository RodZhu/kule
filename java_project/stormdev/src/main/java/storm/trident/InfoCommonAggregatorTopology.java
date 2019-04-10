package storm.trident;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.testing.FixedBatchSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 * Created by rod on 2019/4/9.
 */
public class InfoCommonAggregatorTopology {
    public static void main(String[] args) {
        Config config = new Config();

        // -- 利用trident提供数据源，可以批量发送tuple
        // -- 参数：1 tuple的key字段，2 每批发送最大条数， 3 数据
        FixedBatchSpout spout = new FixedBatchSpout(new Fields("name", "age"), 2,
                new Values("tom", 23),
                new Values("rose", 25),
                new Values("jary", 28),
                new Values("jim", 35));

        TridentTopology topology = new TridentTopology();

        /**
         * baseAggregator组建是按次合并。每接收到一个tuple合并一次
         */
        topology.newStream("spout", spout).partitionAggregate(new Fields("name", "age"),
                new AgeAggregator(), new Fields("info","ageSum")).each(new Fields("info", "ageSum"), new PrintFilter());


        LocalCluster lc = new LocalCluster();
        lc.submitTopology("info_topology", config, topology.build());
    }
}
