package com.example.administrator.practice.iolearn;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Redirecting {

    public static void main(String[] args)throws IOException {
        PrintStream console = System.out;
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream("app\\src\\main\\java\\com\\example\\administrator\\practice\\iolearn\\Redirecting.java"));
        PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream("app\\src\\main\\java\\com\\example\\administrator\\practice\\iolearn\\common3")));
        //重定向输入输出流（io 重定向操作的是字节流）
        System.setIn(stream);
        System.setOut(printStream);
        System.setErr(printStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = reader.readLine()) != null){
            System.out.println(s);
        }
        printStream.close();
        System.setOut(console);
    }
}
