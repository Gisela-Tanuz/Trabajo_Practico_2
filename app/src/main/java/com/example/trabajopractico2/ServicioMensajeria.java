package com.example.trabajopractico2;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Date;

public class ServicioMensajeria extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        VerMensajes();

        return START_STICKY;
    }
    //return super.onStartCommand(intent, flags, startId);


    private void VerMensajes() {
        Uri mensaje = Uri.parse("content://sms");
        ContentResolver cr = getContentResolver();

        new Thread() {
            public void run() {
                while (true) {
                    Log.d("salida", Thread.currentThread().getName() + " Comenzar tarea");


                    Cursor cursor = cr.query(mensaje, null, null, null, null);
                    String id = "";
                    String nro = "";
                    String fecha = "";
                    String mensajes = "";

                    while (cursor.moveToNext() && cursor.getPosition() < 5) {
                        id = cursor.getString(0);
                        nro = cursor.getString(2);
                        fecha = cursor.getString(4);
                        mensajes = cursor.getString(12);

                        Log.d("id", id);
                        Log.d("nro", nro);
                        Log.d("fecha", fecha);
                        Log.d("mensajes", mensajes);

                        //muestro el nombre de las columnas para saber el orden e indice
                        for (int a = 0; a < cursor.getColumnCount(); a++) {
                            Log.d(cursor.getColumnName(a) + "", "(" + a + ")" + cursor.getString(a) + "");

                    /*/com.example.trabajopractico2 D/_id: (0)160
                    /com.example.trabajopractico2 D/thread_id: (1)4
                    /com.example.trabajopractico2 D/address: (2)+542664799935
                    /com.example.trabajopractico2 D/person: (3)null
                    /com.example.trabajopractico2 D/date: (4)1615234599682
                   /com.example.trabajopractico2 D/date_sent: (5)1615234598000
                   /com.example.trabajopractico2 D/protocol: (6)0
                    /com.example.trabajopractico2 D/read: (7)1
                   /com.example.trabajopractico2 D/status: (8)-1
                   /com.example.trabajopractico2 D/type: (9)1
                   /com.example.trabajopractico2 D/reply_path_present: (10)0
                    /com.example.trabajopractico2 D/subject: (11)null
                   /com.example.trabajopractico2 D/body: (12)El nro 2664799935 llamo el 08/03 17:16 hs. Para llamarlo presiona Send
                    /com.example.trabajopractico2 D/service_center: (13)+5434990135
                    /com.example.trabajopractico2 D/locked: (14)0
                   /com.example.trabajopractico2 D/error_code: (15)-1
                   etc*/
                        }
                    }

                    try {

                        Thread.sleep(9000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            }
        }.start();
    }





    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
