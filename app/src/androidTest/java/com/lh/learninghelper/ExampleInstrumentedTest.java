package com.lh.learninghelper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.lh.learninghelper", appContext.getPackageName());
    }

    @Test
    public void run() throws IOException {
        byte[] responseBody = null;//响应体
        URL url = null;//请求的URL地址
        url = new URL("https://fanyi-api.baidu.com/api/trans/vip/translate?q=苹果&from=zh&to=en&appid=20171107000093239&salt=1435660288&sign=934f44c72d8a3cf0ade2bfcbd658134b");
        HttpURLConnection  conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        InputStream is = conn.getInputStream();
        System.out.println(is);

    }

    /**
     * 从网络获取json数据,(String byte[})
     * @param path
     * @return
     */
    public static String getJsonByInternet(String path){
        try {
            URL url = new URL(path.trim());
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if(200 == urlConnection.getResponseCode()){
                //得到输入流
                InputStream is =urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while(-1 != (len = is.read(buffer))){
                    baos.write(buffer,0,len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Test
    public void run2(){
        getJsonByInternet("\"https://fanyi-api.baidu.com/api/trans/vip/translate?q=苹果&from=zh&to=en&appid=20171107000093239&salt=1435660288&sign=934f44c72d8a3cf0ade2bfcbd658134b\"");

    }

    @Test
    public void run3(){

    }
}
