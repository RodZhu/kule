package storm.word_count;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * Created by rod on 2019/3/19.
 */
public class WordCountTopology {

    public static void main(String[] args) throws Exception {
        Config config = new Config();
//        List<URL> resources = Utils.findResources("/Users/rod/bigdata_jar/storm.properties");
        WordCountSpout wcs = new WordCountSpout();
        SplitBolt sb = new SplitBolt();
        WordCountBolt wcb = new WordCountBolt();
        PrintBolt pb = new PrintBolt();

        TopologyBuilder tb = new TopologyBuilder();
        tb.setSpout("spout", wcs);

        tb.setBolt("splitbolt", sb).globalGrouping("spout");
        // tb.setBolt("splitbolt", sb).fieldsGrouping("spout",new Fields("dddd"));
        tb.setBolt("wordcount", wcb).globalGrouping("splitbolt");

        tb.setBolt("printbolt", pb).globalGrouping("wordcount");

        StormTopology topology = tb.createTopology();

        LocalCluster lc = new LocalCluster();

        lc.submitTopology("wordcounttopology", config, topology);
    }
}
