package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.*;
import java.net.URI;

/**
 * Created by rod on 2018/12/10.
 */
public class HadoopDemo {

    @Test
    public void get() throws Exception{
        Configuration c = new Configuration();
        c.set("dfs.replication", "1");
        FileSystem fs = FileSystem.get(new URI("hdfs://10.199.164.183:9000"), c);

        // h获取输出流
        FSDataInputStream open = fs.open(new Path("/park01/test.txt"));
        OutputStream out = new FileOutputStream(new File("data.txt"));
        org.apache.hadoop.io.IOUtils.copyBytes(open, out, c);
    }

    @Test
    public void put() throws Exception{
        Configuration c = new Configuration();
        c.set("dfs.replication", "1");
        c.set("HADOOP_USER_NMAE", "root");
        FileSystem fs = FileSystem.get(new URI("hdfs://10.199.164.183:9000"), c);

        // h获取输出流
        OutputStream open = fs.create(new Path("/park01/data.txt"));
        InputStream out = new FileInputStream(new File("data.txt"));
        org.apache.hadoop.io.IOUtils.copyBytes(out, open, c);
    }
}
