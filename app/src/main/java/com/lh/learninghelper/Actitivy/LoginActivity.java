package com.lh.learninghelper.Actitivy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.learninghelper.NavigationActivity;
import com.lh.learninghelper.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private EditText et_name;
    private EditText et_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = getSharedPreferences("login", 0);

        et_name = findViewById(R.id.et_name);
        et_num = findViewById(R.id.et_num);

        et_name.setText(sp.getString("name", ""));
        et_num.setText(sp.getString("number", ""));

    }

    /**
     * 提交post请求
     *
     * @param path
     * @return
     */
    public static String PostRequset(String path, String name, String number) {
        try {
            URL url = new URL(path);
            //打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            String data = "name=" + URLEncoder.encode(name, "UTF-8") +
                    "&number=" + URLEncoder.encode(number, "UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();
            conn.connect();

            if (200 == conn.getResponseCode()) {
                //得到输入流
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                return baos.toString("utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void dologin(View view) {

        new AsyncTask<Void, Void, Boolean>() {


            @Override
            protected Boolean doInBackground(Void... params) {

                String mName = et_name.getText().toString();
                String mNumber = et_num.getText().toString();

                String result = PostRequset("http://position.c.zmit.cn/index.php/api/login", mName, mNumber);
                try {
                    System.out.println("resul" + result);
                    JSONObject json = null;
                    try {
                        json = new JSONObject(result);
                    } catch (Exception e) {
                        return false;
                    }

                    String errorCode = json.getString("errorCode");
                    switch (errorCode) {
                        case "0":
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("name", mName);
                            editor.putString("number", mNumber);
                            editor.commit();
                            return true;
                        default:
                            return false;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return false;
            }

            protected void onPostExecute(final Boolean success) {

                if (success) {
                    LoginActivity.this.finish();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("name", et_name.getText().toString());
                    intent.putExtra("number", et_num.getText().toString());
                    startActivity(intent);
                } else {
//                mNumberView.setError(getString(R.string.error_incorrect_password));
//                mNumberView.requestFocus();
                    Toast.makeText(LoginActivity.this, "登陆信息有误或网络出错", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute((Void) null);

    }
}
