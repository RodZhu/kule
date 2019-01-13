package model;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by rod on 2019/1/12.
 */
public class FriendDemo implements Writable {

    private String name1;

    private String name2;

    private int relation; // 1 好友 -1: 潜在好友。

    public void readFields(DataInput dataInput) throws IOException {
        this.name1 = dataInput.readUTF();
        this.name2 = dataInput.readUTF();
        this.relation = dataInput.readInt();

    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.name1);
        dataOutput.writeUTF(this.name2);
        dataOutput.writeInt(this.relation);
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }
}
