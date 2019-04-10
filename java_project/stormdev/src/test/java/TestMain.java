import java.util.UUID;

/**
 * Created by rod on 2019/4/8.
 */
public class TestMain {
    public static void main(String[] args) {

    Integer recordId = 2988; //record.getId().toString();
    String orgName = "ai-platform"; // record.getOrgName();
    StringBuilder sb = new StringBuilder(String.format("%s?opsservice=%s#/search?", "https://log-gd9.tools.vipshop.com/", "ai-noah.vip.vip.com"));

    sb.append("_g=(time:(from:now-30m,mode:quick,to:now))&_a=(query:'%28biztype%3A%22flink%22%29+AND+%28namespace%3A%22").
            append(orgName).append("%22%29+AND+%28bizdomain%3A*.").
            append("flink-" + recordId).
            append("." + orgName).
            append("%29+AND+%28pod%3A%22").
            append("jm-databoard-flink-2988-k6eqq9-655467f7bf-w8x9s").
            append("%22%29+AND+%28path%3A*%2Fgc.log.0.current%29',auto:true)");
        System.out.println(UUID.randomUUID().toString());
    }
}
