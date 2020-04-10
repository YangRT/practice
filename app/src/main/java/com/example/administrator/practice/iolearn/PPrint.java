package com.example.administrator.practice.iolearn;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
//工具类，将大型集合换行打印，便于阅读
public class PPrint {
    public static String pformat(Collection<?> c){
        if(c.size() == 0){
            return "[]";
        }
        StringBuilder result = new StringBuilder("[");
        for (Object o:c){
            if(c.size() != 1){
                result.append("\n ");
                result.append(o);
            }
        }
        if(c.size() != 1){
            result.append("\n ");
        }
        result.append("]");
        return result.toString();
    }
    public static void pprint(Collection<?> c){
        System.out.println(pformat(c));
    }

    public static void pprint(Object[] c){
        System.out.println(pformat(Arrays.asList(c)));
    }
}
