package com.mygeekbranch.tempapplication.modelWeather;

import java.io.IOException;
import android.os.Handler;
import android.util.Log;

import com.mygeekbranch.tempapplication.MainActivity;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
// // 5-й способ. Получение данных из погодного API через OkHttp
public class OkHttpRequest {
    private final static String TAG = "OkHttpRequest";

    public static void run(String url){
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request =builder.build();

        Call call = client.newCall(request); //Очередь
        call.enqueue(new Callback() {
            final Handler handler = new  Handler();

            @Override
            public void onFailure(Call call, IOException e) {  // При сбое
                Log.e("OkHttpRequest","IOException",e);
                e.printStackTrace();
                MainActivity.getInstance().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.getInstance().showError();
                    }
                });

            }

            // При ответе от сервера
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String answer =response.body().string();
                Log.d(TAG, answer);
                // синхронизируем поток с потоком UI
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        GetWeatherData.getData(answer);
                    }
                });

            }
        });

    }
}
