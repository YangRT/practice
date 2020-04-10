package com.example.administrator.practice.iolearn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//缓存读取文件
public class BufferedInputFile {

    public static String read(String name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(name));
        StringBuilder builder = new StringBuilder();
        String t;
        while((t=reader.readLine()) != null){
            builder.append(t).append("\n");
        }
        reader.close();
        return builder.toString();
    }

    public static void main(String[] args) throws IOException{
        System.out.println(read("app\\src\\main\\java\\com\\example\\administrator\\practice\\iolearn\\BufferedInputFile.java"));

    }
}
