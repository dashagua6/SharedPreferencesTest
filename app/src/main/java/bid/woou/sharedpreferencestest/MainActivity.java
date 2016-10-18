package bid.woou.sharedpreferencestest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("crazyit", MODE_PRIVATE);
        editor = preferences.edit();
        Button read = (Button) findViewById(R.id.read);
        Button write = (Button) findViewById(R.id.write);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = preferences.getString("time", null);
                int randNum = preferences.getInt("random", 0);
                String result = time == null ? "你暂时还未写入数据" : "写入时间为："
                        + time + "\n上次生成的随机数为：" + randNum;
                // 使用Toast提示信息
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 " +
                 "hh:mm:ss");
                editor.putString("time", sdf.format(new Date()));
                editor.putInt("random", (int) (Math.random() * 1000));
                editor.commit();
            }
        });
    }
}
