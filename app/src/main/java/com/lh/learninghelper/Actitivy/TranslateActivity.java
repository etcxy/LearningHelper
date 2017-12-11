package com.lh.learninghelper.Actitivy;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lh.learninghelper.R;
import com.lh.learninghelper.utils.MD5Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TranslateActivity extends AppCompatActivity {

    private TextView tip;
    private TextView tip2;
    private EditText et_input;
    private EditText et_input2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_utils);

        et_input = findViewById(R.id.et_input);
        et_input2 = findViewById(R.id.et_input2);
        tip = findViewById(R.id.tv_tip);
        tip2 = findViewById(R.id.tv_tip2);

        et_input.addTextChangedListener(new EditChangedListener());
        et_input2.addTextChangedListener(new EditChangedListener());


        AssetManager am = getAssets();
        try {
            AssetFileDescriptor afd = am.openFd("Listening.wma");
            MediaPlayer mPlayer = new MediaPlayer();

            mPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 从网络获取json数据,(String byte[})
     *
     * @param path
     * @return
     */
    public static String getJsonByInternet(String path) {
        try {
            URL url = new URL(path.trim());
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (200 == urlConnection.getResponseCode()) {
                //得到输入流
                InputStream is = urlConnection.getInputStream();
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


    class EditChangedListener implements TextWatcher {
        private CharSequence temp; // 监听前的文本
        private int editStart; // 光标开始位置
        private int editEnd; // 光标结束位置

        // 输入文本之前的状态
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        // 输入文字中的状态，count是一次性输入字符数
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//          if (charMaxNum - s.length() <= 5) {
//              tip.setText("还能输入" + (charMaxNum - s.length()) + "字符");
//          }
//            tip.setText((s.length()) + "/" + charMaxNum);
//            tip.setText("输入中  " + et_input.getText().toString());
        }

        // 输入文字后的状态
        @Override
        public void afterTextChanged(Editable s) {
            /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
//            editStart = editText.getSelectionStart();
//            editEnd = editText.getSelectionEnd();
//            if (temp.length() > charMaxNum) {
////              Toast.makeText(getApplicationContext(), "最多输入10个字符", Toast.LENGTH_SHORT).show();
//                s.delete(editStart - 1, editEnd);
//                editText.setText(s);
//                editText.setSelection(s.length());
//            }
//            tip.setText("结束后  "+et_input.getText().toString());
            final int currentFocusId = TranslateActivity.this.getCurrentFocus().getId();

            Log.v("info0:", currentFocusId + " 00");
            Log.v("info1:", et_input.getId() + " 11");
            Log.v("info2:", et_input2.getId() + " 22");

            new AsyncTask() {

                protected String doInBackground(Object... strings) {

                    String jsonByInternet = "";
                    if (currentFocusId == et_input.getId()) {
                        if (!et_input.getText().toString().equals("")) {
                            jsonByInternet = getJsonByInternet("https://fanyi-api.baidu.com/api/trans/vip/translate?q=" + et_input.getText().toString() +
                                    "&from=zh&to=en&appid=20171107000093239&salt=1435660288&sign=" + MD5Utils.getPwd("20171107000093239" + et_input.getText().toString() + "1435660288UkBBRr9aT5vfsb3QYOq8"));
                        }

                    } else if (currentFocusId == et_input2.getId()) {
                        if (!et_input2.getText().toString().equals("")) {
                            jsonByInternet = getJsonByInternet("https://fanyi-api.baidu.com/api/trans/vip/translate?q=" + et_input2.getText().toString() +
                                    "&from=en&to=zh&appid=20171107000093239&salt=1435660288&sign=" + MD5Utils.getPwd("20171107000093239" + et_input2.getText().toString() + "1435660288UkBBRr9aT5vfsb3QYOq8"));
                        }

                    }


                    System.out.println(jsonByInternet);
                    return jsonByInternet;
                }

                protected void onPostExecute(Object s) {
                    super.onPostExecute(s.toString());
//                    tip.setText(s.toString());
                    String dst = "";
                    try {

                        JSONObject json = JSONObject.parseObject(s.toString());
                        JSONArray trans_result = json.getJSONArray("trans_result");

                        String Strans_result = trans_result.toString().replace("[", "");
                        Strans_result = Strans_result.replace("]", "");
                        JSONObject json2 = JSONObject.parseObject(Strans_result);
                        dst = json2.getString("dst");
                    } catch (Exception e) {

                    }


                    if (currentFocusId == et_input.getId()) {
                        tip.setText(dst);
                    } else if (currentFocusId == et_input2.getId()) {
                        tip2.setText(dst);
                    }
                }
            }.execute("");
        }
    }
}
