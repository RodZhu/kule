package storm.trident;

import org.apache.storm.trident.operation.BaseFilter;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Fields;

import java.util.Iterator;

/**
 * Created by rod on 2019/4/9.
 */
public class PrintFilter extends BaseFilter {

    public boolean isKeep(TridentTuple input) {

//        String name = input.getStringByField("name");
//        int age = input.getIntegerByField("age");
//        System.out.println("name: " + name + " age: " + age);

        // 由于上面注释部分需要知道key的实际值，该写法不通用。因此用更通用的写法如下:
        // =======================================================
        Fields fields = input.getFields();
        Iterator<String> iterator = fields.iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            Object valueByField = input.getValueByField(key);
            System.out.println(key + ": " + valueByField);
        }
        return false;
    }
}
