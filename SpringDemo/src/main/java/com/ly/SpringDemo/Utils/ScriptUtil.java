package com.ly.SpringDemo.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ScriptUtil {
    //根据文件路径执行脚本（shell）
    static public String execScript(String filePath) {
        try {
            Process process = Runtime.getRuntime().exec(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String s;
            while ((s = reader.readLine()) != null) {
                builder.append(s);
            }
            return builder.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }
}
