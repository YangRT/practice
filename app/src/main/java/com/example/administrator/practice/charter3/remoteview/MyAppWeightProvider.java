package com.example.administrator.practice.charter3.remoteview;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.administrator.practice.R;

public class MyAppWeightProvider extends AppWidgetProvider {
    public final static String TAG = "AppWeightProvider";
    public final static String CLICK_ACTION = "com.example.administrator.practice.charter3.remoteview.action.CLICK";

    public MyAppWeightProvider(){
        super();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i(TAG,"onReceive:action="+intent.getAction());
        if(intent.getAction().equals(CLICK_ACTION)){
            Toast.makeText(context,"click it!",Toast.LENGTH_LONG).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap srcbsBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.grid_head_1);
                    AppWidgetManager manager = AppWidgetManager.getInstance(context);
                    for(int i = 0;i < 37;i++){
                        float degree = (i * 10) % 360;
                        RemoteViews remoteView = new RemoteViews(context.getPackageName(),R.layout.wight);
                        remoteView.setImageViewBitmap(R.id.wight_image,rotateBitmap(context,srcbsBitmap,degree));
                        Intent intentClick = new Intent();
                        intentClick.setAction(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intentClick,0);
                        remoteView.setOnClickPendingIntent(R.id.wight_image,pendingIntent);
                        manager.updateAppWidget(new ComponentName(context,MyAppWeightProvider.class),remoteView);
                        SystemClock.sleep(30);
                    }

                }
            }).start();
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG,"onUpdate");
         final int counter = appWidgetIds.length;
         for(int i = 0;i < counter; i++){
             int appweightId = appWidgetIds[i];
             onAppWidgetOptionsChanged(context,appWidgetManager,appweightId,null);
         }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        Log.i(TAG,"appWightId="+appWidgetId);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.wight);
        Intent intentClick = new Intent();
        intentClick.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intentClick,0);
        remoteViews.setOnClickPendingIntent(R.id.wight_image,pendingIntent);
        appWidgetManager.updateAppWidget(new ComponentName(context,MyAppWeightProvider.class),remoteViews);
    }

    private Bitmap rotateBitmap(Context c, Bitmap srvbBitmap, float degree){
        Matrix matrix = new Matrix();
        matrix.reset();;
        matrix.setRotate(degree);
        Bitmap tepBitmap = Bitmap.createBitmap(srvbBitmap,0,0,srvbBitmap.getWidth(),srvbBitmap.getHeight(),matrix,true);
        return tepBitmap;
    }
}
