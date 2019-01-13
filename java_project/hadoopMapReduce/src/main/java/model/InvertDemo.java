package model;

import invert.mapper.InvertMapper;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by rod on 2019/1/12.
 */
public class InvertDemo implements WritableComparable<InvertDemo> {

    private String item;

    private String fileName;

    private int count;


    public void readFields(DataInput dataInput) throws IOException {
        this.item = dataInput.readUTF();
        this.fileName = dataInput.readUTF();
        this.count = dataInput.readInt();
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(item);
        dataOutput.writeUTF(fileName);
        dataOutput.writeInt(count);
    }

    public int compareTo(InvertDemo o) {
        return this.count - o.count;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return fileName + " : " + count;
    }
}
