package com.example.administrator.practice.iolearn;
import com.example.administrator.practice.R;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OSExecute {
    public static void command(String command){
        boolean err = false;
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = reader.readLine()) != null){
                System.out.println(s);
            }
            BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((s = errReader.readLine()) != null){
                System.err.println(s);
                err = true;
            }
        }catch (Exception e){
            if(!command.startsWith("CMD /C")){
                command("CMD /C"+command);
            }else {
                throw new RuntimeException(e);
            }
        }
        if(err) throw new OSExecuteException("Errors executing "+command);
    }

    public static void main(String[] args){
        OSExecute.command("javap Users\\Administrator\\AndroidStudioProjects\\practice\\app\\src\\main\\java\\com\\example\\administrator\\practice\\iolearn\\OSExecute.java");
    }
}
