package com.jar.asm.example;

import java.io.IOException;

public class Test_08 {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String smsTmplate = "%s, 说明: %s, 组织: %s, 描述: %s";
        System.out.println(String.format(smsTmplate,"Spark - " + "stpark" + " 被自动触发,", "请登陆Ai平台查明触发原因，系统默认每隔5分钟触发一次直至该任务状态为Running结束。", "ai-platform", null));
    }


}