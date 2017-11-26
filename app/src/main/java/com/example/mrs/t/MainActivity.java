package com.example.mrs.t;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity
{
    private Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        upload = (Button) findViewById(R.id.upload);
    }
    public void upload(View view){

        new Thread(){
            public void run(){
                Log.d("TAG","1");
                uploadFile();
            }
        }.start();
    }

    public void uploadFile()
    {
        String requestUrl ="http://www.freedream.xyz/testUpload1/UploadHandleServlet";
        String BOUNDARY = String.valueOf(UUID.randomUUID()); //边界标识
        String PREFIX = "--";
        String LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form_data";

        File file = new File("/storage/emulated/0/DCIM/Camera", "cc.jpg");
        try
        {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //准备协议头
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", "UTF-8");//设置编码格式
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);

            //写入文件格式
            if (file != null)
            {
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                sb.append("Content-Disposition: form-data; name=\"fileName\";filename=\"" + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: image/jpg"+LINE_END);
              //  sb.append("Content_Type: application/octet-stream;charset=" + "UTF-8" + LINE_END);
                sb.append(LINE_END);

                dos.write(sb.toString().getBytes());//前几行写入流
                InputStream is = new FileInputStream(file);//读取文件
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1)
                {
                    dos.write(bytes, 0, len);//边读边写
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();

                /** 得到相应的结果*/
                int res = conn.getResponseCode();
                if (res == 200)
                {
                    Log.d("test", "Sucess");
                    Log.d("fafa","fdsf");
                }
                else
                {
                    Log.d("test", "file failure");
                }
            }

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
