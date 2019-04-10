package storm.trident;

import org.apache.storm.trident.operation.BaseFilter;
import org.apache.storm.trident.tuple.TridentTuple;

/**
 * Created by rod on 2019/4/9.
 */
public class NameFilter extends BaseFilter {

    public boolean isKeep(TridentTuple input) {

        String name = input.getStringByField("name");

        if (name.equals("tom")) {
            // 当遇见key为tom的时候不选择向下发送。
            return false;
        }

        return true;
    }
}
