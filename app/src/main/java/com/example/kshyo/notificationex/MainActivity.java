package com.example.kshyo.notificationex;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.alarmBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NotificationManager notificationManager = (NotificationManager) MainActivity.this.getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
                Intent intent1 = new Intent(MainActivity.this.getApplicationContext(), MainActivity.class); //인텐트 생성.


                final Notification.Builder builder = new Notification.Builder(getApplicationContext());
                intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                PendingIntent pendingNotificationIntent = PendingIntent.getActivity(MainActivity.this, 0, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
                //PendingIntent는 일회용 인텐트 같은 개념입니다.
                // FLAG_UPDATE_CURRENT - > 만일 이미 생성된 PendingIntent가 존재 한다면, 해당 Intent의 내용을 변경함.
                //       FLAG_CANCEL_CURRENT - .이전에 생성한 PendingIntent를 취소하고 새롭게 하나 만든다.
                //      FLAG_NO_CREATE -> 현재 생성된 PendingIntent를 반환합니다.
                //FLAG_ONE_SHOT - >이 플래그를 사용해 생성된 PendingIntent는 단 한번밖에 사용할 수 없습니다

                builder.setSmallIcon(R.drawable.ic_launcher_background).setTicker("HETT").setWhen(System.currentTimeMillis())
                        .setNumber(1).setContentTitle("푸쉬 제목").setContentText("푸쉬내용")
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingNotificationIntent).setAutoCancel(true).setOngoing(true);
                //해당 부분은 API 4.1버전부터 작동합니다.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    builder.setPriority(Notification.PRIORITY_HIGH);
                     builder.setVibrate(new long[0]).setAutoCancel(true);

                }
//setSmallIcon - > 작은 아이콘 이미지
//setTicker - > 알람이 출력될 때 상단에 나오는 문구.
//setWhen -> 알림 출력 시간.
//setContentTitle-> 알림 제목
//setConentText->푸쉬내용
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    notificationManager.notify(1, builder.build());// Notification send

                }
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       notificationManager.cancel(1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            builder.setPriority(Notification.PRIORITY_DEFAULT);
                          notificationManager.notify(1,builder.build());
                        }
                    }
                },2000);

            }
        });

    }
}
