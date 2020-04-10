package com.example.administrator.practice.iolearn;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TextFile extends ArrayList<String> {

    public static String read(String name) {
        StringBuilder br = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(name).getAbsoluteFile()));
            try {
                String s;
                while ((s = reader.readLine()) != null) {
                    br.append(s);
                    br.append("\n");
                }
            } finally {
                reader.close();
            }
        } catch (IOException e){
                throw new RuntimeException(e);
            }
        return br.toString();
    }

    public static void write(String name,String text){
        try{
            PrintWriter writer = new PrintWriter(new File(name).getAbsoluteFile());
            try{
                writer.print(text);
            }finally {
                writer.close();
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public TextFile(String name,String splitter){
        super(Arrays.asList(read(name).split(splitter)));
        if(get(0).equals("")) remove(0);
    }

    public TextFile(String name){
        this(name,"\n");
    }

    public void write(String name){
            try{
                PrintWriter writer = new PrintWriter(new File(name).getAbsoluteFile());
                try{
                    for(String item:this){
                        writer.println(item);
                    }
                }finally {
                    writer.close();
                }
            } catch (IOException e){
                throw new RuntimeException(e);
            }
    }

    public static void main(String[] args){
        //将文件复制到另一文件
        String file = read("app\\src\\main\\java\\com\\example\\administrator\\practice\\iolearn\\TextFile.java");
        write("app\\src\\main\\java\\com\\example\\administrator\\practice\\iolearn\\common",file);
        TextFile textFile = new TextFile("app\\src\\main\\java\\com\\example\\administrator\\practice\\iolearn\\common");
        textFile.write("app\\src\\main\\java\\com\\example\\administrator\\practice\\iolearn\\common2");
    }
}
