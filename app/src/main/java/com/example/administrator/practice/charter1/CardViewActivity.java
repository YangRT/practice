package com.example.administrator.practice.charter1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.RemoteViews;
import android.widget.SeekBar;

import com.example.administrator.practice.R;

public class CardViewActivity extends AppCompatActivity {
    private CardView mCardView;
    private SeekBar sb1;
    private SeekBar sb2;
    private SeekBar sb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        assignView();
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        //普通通知
        Notification.Builder builder = new Notification.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,mIntent,0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentText("777");
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background));
        builder.setAutoCancel(true);
        builder.setContentTitle("普通通知");
        manager.notify(1,builder.build());
        //折叠式通知
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.charter1_view_fold);
        Notification.Builder builder1 = new Notification.Builder(this);
        Intent mIntent1 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://baidu.com"));
        PendingIntent pendingIntent1 = PendingIntent.getActivity(this,0,mIntent,0);
        builder1.setContentIntent(pendingIntent1);
        builder1.setSmallIcon(R.drawable.ic_launcher_background);
        builder1.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background));
        builder1.setAutoCancel(true);
        //设置显示等级：VISIBILITY_PUBLIC:任何情况下都会显示通知。VISIBILITY_PRIVATE:只有在没有锁屏情况下显示通知。VISIBILITY_SECRET:在pin,password等安全锁和没有锁屏情况下才显示
        builder1.setVisibility(Notification.VISIBILITY_PUBLIC);
        builder1.setContentTitle("折叠式通知");
        Notification notification = builder1.build();
        notification.bigContentView = remoteViews;
        manager.notify(2,notification);

    }

    public void assignView(){
        mCardView = findViewById(R.id.charter1_card_view);
        sb1 = findViewById(R.id.sb_1);
        sb2 = findViewById(R.id.sb_2);
        sb3 = findViewById(R.id.sb_3);
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCardView.setRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCardView.setCardElevation(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCardView.setContentPadding(progress,progress,progress,progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
