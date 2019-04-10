package storm.trident.wordCount;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.tuple.Fields;

/**
 * Created by rod on 2019/4/10.
 */
public class WordCountTridentTopology {

    public static void main(String[] args) {
        Config config = new Config();
        TridentTopology topology = new TridentTopology();
        WordCountSpout wordCountSpout = new WordCountSpout();
        topology.newStream("spout", wordCountSpout).
                partitionAggregate(new Fields("line"), new SplitAggregator(), new Fields("word")).
                each(new Fields("word"), new CountFunction(), new Fields("wordStr", "count")).
                partitionAggregate(new Fields("wordStr", "count"), new PrintAggregator(), new Fields("str"));

        LocalCluster lc = new LocalCluster();
        lc.submitTopology("info_topology", config, topology.build());
    }
}
