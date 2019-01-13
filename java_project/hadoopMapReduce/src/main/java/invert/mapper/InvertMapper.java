package invert.mapper;

import model.InvertDemo;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by rod on 2019/1/12.
 */
public class InvertMapper extends Mapper<LongWritable, Text, Text, InvertDemo> {


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String ss =  value.toString();
        FileSplit split = (FileSplit)context.getInputSplit();
        String filename = split.getPath().getName();
        for(String s : ss.split(" ")) {
            InvertDemo id = new InvertDemo();
            id.setCount(1);
            id.setItem(s);
            id.setFileName(filename);
            context.write(new Text(s), id);
        }
    }
}
