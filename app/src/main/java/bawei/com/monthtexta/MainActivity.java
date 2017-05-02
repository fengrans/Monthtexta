package bawei.com.monthtexta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import bawei.com.monthtexta.utils.GlideCacheUtil;
import okhttp3.Call;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private String str="http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=10&gender=2&ts=1871746850&page=1";
    private TextView tv_text;
    private String cacheSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        tv_text = (TextView) findViewById(R.id.tv_text);
        tv_text.setOnClickListener(this);


        getdata();
    }

    private void getdata() {

        OkHttpUtils.get().url(str).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Myinfo myinfo = gson.fromJson(response, Myinfo.class);
                List<Myinfo.DataBean> data = myinfo.getData();
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                MyAdapter mm = new MyAdapter(data, MainActivity.this);
                recyclerView.setAdapter(mm);
                cacheSize = GlideCacheUtil.getInstance().getCacheSize(MainActivity.this);
                tv_text.setText(cacheSize);
            }
        });
    }

    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(getApplicationContext()).clearDiskCache();
            }
        }).start();

    }
}
