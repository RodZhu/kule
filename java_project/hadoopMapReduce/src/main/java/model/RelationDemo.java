package model;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by rod on 2019/1/10.
 */
public class RelationDemo implements Writable{

    private String father;

    private String son;


    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(father);
        dataOutput.writeUTF(son);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.father = dataInput.readUTF();
        this.son = dataInput.readUTF();
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getSon() {
        return son;
    }

    public void setSon(String son) {
        this.son = son;
    }
}
