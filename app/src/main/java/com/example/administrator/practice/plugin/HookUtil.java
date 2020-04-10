package com.example.administrator.practice.plugin;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.example.administrator.practice.ProxyActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookUtil {

    private static final String TARGET_INTENT = "target_intent";

    /**
     * 进入AMS之前，把插件的intent 换成 proxy 的intent
     */
    public static void hookAMS() {

        try {
            // 获取 singleton 对象
            Class<?> clazz = Class.forName("android.app.ActivityManager");
            Field singletonField = clazz.getDeclaredField("IActivityManagerSingleton");
            singletonField.setAccessible(true);
            Object singleton = singletonField.get(null);

            // 获取Instance 对象  --》 IActivityManager
            Class<?> singletonClass = Class.forName("android.util.Singleton");
            Field mInstanceField = singletonClass.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            final Object mInstance = mInstanceField.get(singleton);

            Class<?> iActivityManagerClass = Class.forName("android.app.IActivityManager");
            Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class[]{iActivityManagerClass}, new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            /**
                             * int result = ActivityManager.getService()
                             *                 .startActivity(whoThread, who.getBasePackageName(), intent,
                             *                         intent.resolveTypeIfNeeded(who.getContentResolver()),
                             *                         token, target != null ? target.mEmbeddedID : null,
                             *                         requestCode, 0, null, options);
                             */
                            if ("startActivity".equals(method.getName())) {

                                int index = 0;
                                for (int i = 0; i < args.length; i++) {
                                    if (args[i] instanceof Intent) {
                                        index = i;
                                        break;
                                    }
                                }
                                // 启动插件的 intent
                                Intent intent = (Intent) args[index];

                                // 创建启动代理的 intent
                                Intent proxyIntent = new Intent();
                                proxyIntent.setClassName("com.enjoy.leo_plugin",
                                        ProxyActivity.class.getName());

                                // 保存插件的 intent，后面要复原
                                proxyIntent.putExtra(TARGET_INTENT, intent);

                                // 替换为 proxy 的intent
                                args[index] = proxyIntent;
                            }

                            return method.invoke(mInstance, args);
                        }
                    });
            // 替换移动的 mInstance 对象
            mInstanceField.set(singleton, proxyInstance);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void hookHandler() {
        try {
            Class<?> clazz = Class.forName("android.app.ActivityThread");
            // 获取 ActivityThread 的对象
            Field sCurrentActivityThreadField = clazz.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThreadField.setAccessible(true);
            Object activityThread = sCurrentActivityThreadField.get(null);


            // 获取 Handler 的对象
            Field mHField = clazz.getDeclaredField("mH");
            mHField.setAccessible(true);
            Object mH = mHField.get(activityThread);


            // 获取Callback 对象
            Field mCallbackField = Handler.class.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);
            mCallbackField.set(mH, new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    switch (msg.what) {
                        case 100:
                            // 将 proxy 的Intent 换成 插件的Intent
                            try {
                                Field intentField = msg.obj.getClass().getDeclaredField("intent");
                                intentField.setAccessible(true);
                                // 代理的 Intent
                                Intent proxyIntent = (Intent) intentField.get(msg.obj);

                                // 插件的Intent
                                Intent intent = proxyIntent.getParcelableExtra(TARGET_INTENT);
                                // 插件的 intent 替换 代理的 intent
                                proxyIntent.setComponent(intent.getComponent());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
