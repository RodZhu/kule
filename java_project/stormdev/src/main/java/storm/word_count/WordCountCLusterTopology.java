package storm.word_count;


import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by rod on 2019/3/19.
 */
public class WordCountCLusterTopology {

    public static void main(String[] args) throws Exception {

        Config config = new Config();

        if (args != null && args.length > 0) {
            Properties properties = new Properties();
            InputStream in = new FileInputStream(new File(args[0]));
            properties.load(in);
            Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<Object, Object> next = iterator.next();
                System.out.println("key: " + next.getKey() + " value: " + next.getValue());
            }
            in.close();
        }


        // 设置work jvm并发度
        config.setNumWorkers(2);
        WordCountSpout wcs = new WordCountSpout();
        SplitBolt sb = new SplitBolt();
        WordCountBolt wcb = new WordCountBolt();
        PrintBolt pb = new PrintBolt();

        TopologyBuilder tb = new TopologyBuilder();
        tb.setSpout("spout", wcs);
        // 意味每个开启2个线程共同运行4个task，平均每一个线程运行2个task。
        //tb.setSpout("spout", wcs, 2).setNumTasks(4);
        tb.setBolt("splitbolt", sb).globalGrouping("spout");
        // 设置两个线程，运行wordcount bolt，每个线程运行2个 word count task。总共4个task.s
        tb.setBolt("wordcount", wcb, 2).setNumTasks(4).globalGrouping("splitbolt");

        tb.setBolt("printbolt", pb).globalGrouping("wordcount");

        StormTopology topology = tb.createTopology();

        // 本地集群模式
        // LocalCluster lc = new LocalCluster();
        // lc.submitTopology("wordcounttopology", config, topology);

        StormSubmitter ss = new StormSubmitter();
        ss.submitTopology("wordcounttopology", config, topology);
    }
}
