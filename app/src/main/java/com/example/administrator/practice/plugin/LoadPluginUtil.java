package com.example.administrator.practice.plugin;


import android.content.Context;

import android.util.Log;
import android.widget.Toast;

import com.example.administrator.practice.MyApplication;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class LoadPluginUtil {

    private static final String apkPath = "/sdcard/wan-debug.apk";

    public static void loadClass(Context context){
        if (!fileIsExists(apkPath)) {
            Toast.makeText(MyApplication.getApplication().context, "加载插件失败，位置不存在！", Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            // 1.获取 pathList 的字段
            Class baseDexClassLoader = Class.forName("dalvik.system.BaseDexClassLoader");
            Field pathListField = baseDexClassLoader.getDeclaredField("pathList");
            pathListField.setAccessible(true);

            /**
             * 获取插件的 dexElements[]
             */
            // 2.获取 DexClassLoader 类中的属性 pathList 的值
            DexClassLoader dexClassLoader = new DexClassLoader(apkPath,
                    context.getCacheDir().getAbsolutePath(), null, context.getClassLoader());
            Object pluginPathList = pathListField.get(dexClassLoader);

            // 3.获取 pathList 中的属性 dexElements[] 的值--- 插件的 dexElements[]
            Class pluginPathListClass = pluginPathList.getClass();
            Field pluginDexElementsField = pluginPathListClass.getDeclaredField("dexElements");
            pluginDexElementsField.setAccessible(true);
            Object[] pluginDexElements = (Object[]) pluginDexElementsField.get(pluginPathList);

            /**
             * 获取宿主的 dexElements[]
             */
            // 4.获取 PathClassLoader 类中的属性 pathList 的值
            PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
            Object hostPathList = pathListField.get(pathClassLoader);
            // 5.获取 pathList 中的属性 dexElements[] 的值--- 宿主的 dexElements[]
            Class hostPathListClass = hostPathList.getClass();
            Field hostDexElementsField = hostPathListClass.getDeclaredField("dexElements");
            hostDexElementsField.setAccessible(true);
            Object[] hostDexElements = (Object[]) hostDexElementsField.get(hostPathList);

            /**
             * 将插件的 dexElements[] 和宿主的 dexElements[] 合并为一个新的 dexElements[]
             */
            // 6.创建一个新的空数组，第一个参数是数组的类型，第二个参数是数组的长度
            Object[] dexElements = (Object[]) Array.newInstance(
                    hostDexElements.getClass().getComponentType(),
                    pluginDexElements.length + hostDexElements.length);

            // 7.将插件和宿主的 dexElements[] 的值放入新的数组中
            System.arraycopy(pluginDexElements, 0, dexElements, 0, pluginDexElements.length);
            System.arraycopy(hostDexElements, 0, dexElements, pluginDexElements.length,
                    hostDexElements.length);

            /**
             * 将生成的新值赋给 "dexElements" 属性
             */
            hostDexElementsField.set(hostPathList, dexElements);
            Log.e("TAG","成功。");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //判断文件是否存在
    public static boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
