package com.example.administrator.practice.iolearn;

import java.io.File;

public class MakeDirectories {
    private static void usage(){
        System.err.println(
                "Usage:MakeDirectories path....\n"+
                        "Creates each path\n" +
                        "Usage:MakeDirectories - d path1...\n" +
                        "Deletes each path\n"+
                        "Usage:MakeDirectories -r path1 path2\n" +
                        "Rename from path1 to path2"
        );
        System.exit(1);
    }

    private static void fileData(File file){
        System.out.println("Absolute path:"+file.getAbsolutePath()+"\n Can read:"+file.canRead()+"\n Can write:"+file.canWrite()+"\n getName:"+file.getName()+
                "\n getParent:"+file.getParent()+"\n getPath:"+file.getPath()+"\n length:"+file.length()+"\n lastModified:"+file.lastModified());
        if(file.isFile()){
            System.out.println("It is a file");
        }else if (file.isDirectory()){
            System.out.println("It is a directory");
        }
    }

    public static void main(String[] args){
        if(args.length < 1) usage();
        if(args[0].equals("-r")){
            if(args.length != 3) usage();
            File old = new File(args[1]);
            File rname = new File(args[2]);
            old.renameTo(rname);
            fileData(old);
            fileData(rname);
            return;
        }
        int count=0;
        boolean delete = false;
        if(args[0].equals("-d")){
            count++;
            delete = true;
        }
        count--;
        while (++count < args.length){
            File f = new File(args[count]);
            if(f.exists()){
                System.out.println(f+"exists");
                if(delete){
                    System.out.println("deleting..."+f);
                    f.delete();
                }
            }else{
                if(!delete){
                    f.mkdir();
                    System.out.println("created "+f);
                }
            }
            fileData(f);
        }
    }
}
