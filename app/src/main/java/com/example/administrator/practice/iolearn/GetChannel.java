package com.example.administrator.practice.iolearn;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
    public static  int BSIZE = 1024;
    public static void main(String[] args)throws Exception{
        FileChannel channel = new FileOutputStream("app\\src\\main\\java\\com\\example\\administrator\\practice\\iolearn\\common4").getChannel();
        channel.write(ByteBuffer.wrap("Some text".getBytes()));
        channel.close();
        channel = new RandomAccessFile("app\\src\\main\\java\\com\\example\\administrator\\practice\\iolearn\\common4","rw").getChannel();
        channel.position(channel.size());
        channel.write(ByteBuffer.wrap("Some more".getBytes()));
        channel.close();
        channel = new FileInputStream("app\\src\\main\\java\\com\\example\\administrator\\practice\\iolearn\\common4").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(BSIZE);
        channel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.println((char) byteBuffer.get());
        }
    }
}
