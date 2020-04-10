package com.example.administrator.practice.iolearn;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BinaryFile {

    public static byte[] read(File file) throws IOException{
        BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
        try {
            byte[] data = new byte[stream.available()];
            stream.read(data);
            return data;
        } finally {
            stream.close();
        }
    }

    public static byte[] read(String name)throws IOException{
        return read(new File(name).getAbsolutePath());
    }
}
