package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PushNotoficationActivity extends BaseActivity {

    EditText edtTitle, edtDescription;
    Button btnEnter;
    String title, description;
    public String CHANNEL_ID = "PORBANDAR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notofication);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        createNotificationChannel();
        initView();
        ClickEventButton();
    }
    @Override
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(PushNotoficationActivity.this, Welcome_Main.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
    private void ClickEventButton() {

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                title = edtTitle.getText().toString().trim();
                description = edtDescription.getText().toString().trim();
                try
                {
                    if (title.isEmpty() || description.isEmpty())
                    {
                        showToast("Please Enter All Details");
                    }
                    else
                    {
                        Log.e("LOG","LOG 1");
                        Intent i = new Intent(PushNotoficationActivity.this,WelcomeActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent  pi = PendingIntent.getActivity(getApplicationContext(), 0, i, PendingIntent.FLAG_IMMUTABLE);
                        Log.e("LOG","LOG 2");
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.googlelogo);
                        Log.e("LOG","LOG 3");

                        // For sound
                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                .setSmallIcon(R.drawable.googlelogo)
                                .setContentTitle(title)
                                .setContentText(description)
                                .setContentIntent(pi)
                                .setSound(alarmSound)
                                .setStyle(new NotificationCompat.BigPictureStyle()
                                        .bigPicture(bitmap)
                                        .bigLargeIcon(null))
                                .setLargeIcon(bitmap)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                        Log.e("LOG","LOG 4");
                        //For vibration
                        Vibrator phone = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            phone.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26
                            phone.vibrate(500);
                        }
                        // For sound
                    /*    Notification notification = builder.build();
                        notification.sound = Uri.parse("android.resource://"
                                + getApplicationContext().getPackageName() + "/" + R.raw.sound2);

                        notification.defaults |= Notification.DEFAULT_SOUND;
*/
                        NotificationManagerCompat notif = NotificationManagerCompat.from(getApplicationContext());
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            showToast("Not Permission Granted");
                            return;
                        }
                        notif.notify(0, builder.build());
                        Log.e("LOG","LOG 5");

                        edtTitle.setText("");
                        edtDescription.setText("");
                    }
                }
                catch (Exception ex)
                {
                    showToast("Exception "+ex);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i =new Intent(PushNotoficationActivity.this,Welcome_Main.class);
        startActivity(i);
        finish();
    }
    private void initView() {
        edtTitle = findViewById(R.id.editTextTitle);
        edtDescription = findViewById(R.id.editTextDescription);
        btnEnter = findViewById(R.id.EnterButton);
    }
    private void createNotificationChannel() {

        try {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = getString(R.string.CHANNEL_NAME);
                String description = getString(R.string.CHANNEL_DESCRIPTION);
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
                showToast("Channel " + channel);
            }
        }
        catch (Exception ex)
        {
            showToast("Exception "+ex);
        }
    }
}