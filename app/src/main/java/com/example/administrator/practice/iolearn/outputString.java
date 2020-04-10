package com.example.administrator.practice.iolearn;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;

public class outputString {

    public static LinkedList<String> read(String name)throws IOException{
        LinkedList<String> list = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new FileReader(name));
        String t;
        while ((t = reader.readLine()) != null){
            list.add(t);
        }
        reader.close();
        return list;
    }

    public static void main(String[] args)throws IOException{
        LinkedList<String> list = read("app\\src\\main\\java\\com\\example\\administrator\\practice\\iolearn\\common");
        for(int i = list.size() - 1;i >= 0;i--){
            System.out.println(list.get(i));
        }
    }
}
