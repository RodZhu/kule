package flow.mapper;

import model.FlowDemo;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by rod on 2019/1/10.
 */
public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowDemo>{
    /**
     * 13912345678 zhangsan bj 1234
     * 13912345678 zhangsan wuhan 12345
     * 13912345628 lisi beijing 12345
     * 13912345628 lisi wuhan 12345
     * 13912345638 wangwu shanghai 12345
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] content = value.toString().split(" ");
        FlowDemo demo = new FlowDemo();
        demo.setPhone(content[0]);
        demo.setName(content[1]);
        demo.setAddr(content[2]);
        demo.setFlow(new Integer(content[3]).intValue());
        context.write(new Text(demo.getName()), demo);
    }

}
