package com.avso;

import com.test.avro.User;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by rod on 2018/12/3.
 */
public class AvsoTest {

    @Test
    public void write() throws IOException {
        User u1 = new User();
        u1.setAge(10);
        u1.setName("eee");
        DatumWriter<User> dw = new SpecificDatumWriter<>(User.class);
        DataFileWriter<User> dfw = new DataFileWriter<>(dw);
        dfw.create(u1.getSchema(), new File("user.txt"));
        dfw.append(u1);
        dfw.close();
    }

    @Test
    public void read() throws IOException {
        User u1 = new User();
        u1.setAge(10);
        u1.setName("eee");
        DatumReader<User> dw = new SpecificDatumReader<>(User.class);
        DataFileReader<User> dfw = new DataFileReader<>(new File("user.txt"),dw);

        while(dfw.hasNext()) {
            System.out.println(dfw.next());
        }
    }
}
