package com.example.administrator.practice.iolearn;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;
//实现FilenameFilter接口，可自定义目录过滤
public class DirFilter implements FilenameFilter {
    private Pattern pattern;
    String regex;
    DirFilter(String regex){
        this.regex = regex;
        pattern = Pattern.compile(regex);
    }
    //其中File为指定目录，name为目录下的文件名称，所以文件都会被比较
    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(regex);
    }
}
