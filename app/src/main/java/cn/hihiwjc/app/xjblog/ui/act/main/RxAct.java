package cn.hihiwjc.app.xjblog.ui.act.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.hihiwjc.app.xjblog.R;
import cn.hihiwjc.app.xjblog.biz.model.Posts;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * *******************************************************************************
 * <br/>
 * 程序描述：
 * <br/>
 * *******************************************************************************
 * <br/>
 * 修改历史：<br/>
 * Date：************** by：*****************Reason：<br/>
 * 2016/3/22 0022********** hihiwjc************Initial Version
 * <br/>
 * *******************************************************************************
 */
public class RxAct extends Activity {
    private EditText mEdv;
    private Retrofit retrofit;
    private RxService rxService;
    private List<Subscription> subscriptions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rx);
        mEdv = (EditText) findViewById(R.id.edv_text);
        retrofit = new Retrofit.Builder().baseUrl("http://hihiwjc.cn/wp-json/wp/v2/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        rxService = retrofit.create(RxService.class);
    }

    public void clickEvent(View view) {
        Toast.makeText(this, "请求网络", Toast.LENGTH_SHORT).show();
        queryData();
    }


    private void queryData() {
        double random = Math.random();
        int id = (int) (random * 20 + 1);
        subscriptions.add(rxService.getPosts("" + id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Posts>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(RxAct.this, "请求网络完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxAct.this, "请求网络错误" + e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Posts posts) {
                        fillData(posts.toString());
                    }
                }));
    }

    private void fillData(String text) {
        if (mEdv == null || text == null) {
            return;
        }
        mEdv.setText(text);
    }

    public interface RxService {
        @GET("posts/{id}")
        Observable<Posts> getPosts(@Path("id") String id);
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (Subscription sub : subscriptions) {
            if (!sub.isUnsubscribed()) {
                sub.unsubscribe();
            }
        }
    }
}
