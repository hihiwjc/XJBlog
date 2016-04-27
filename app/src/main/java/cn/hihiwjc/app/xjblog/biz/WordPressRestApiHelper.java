package cn.hihiwjc.app.xjblog.biz;

import java.util.List;

import cn.hihiwjc.app.xjblog.biz.mod.Post;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 89716 on 2016/4/27.
 *
 * 用于封装访问api的方法的类
 */
public class WordPressRestApiHelper {

    //单例
    private static WordPressRestApiHelper instance = new WordPressRestApiHelper();
    //声明Retrofit以访问网络
    private static Retrofit retrofit;
    //Retrofit接口集合
    private static WordPressRestInterface wpService;

    private WordPressRestApiHelper(){
        //初始化Retrofit
        retrofit = new Retrofit.Builder().baseUrl("http://hihiwjc.cn/wp-json/wp/v2/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        //初始化wpService以获取访问网络的方法
        wpService = retrofit.create(WordPressRestInterface.class);
    }

    public static WordPressRestApiHelper getInstance(){
        return instance;
    }

    /**
     * 获取文章列表（不含文章内容）
     * @param observer 自定义观察者,绑定在主线程
     */
    public void getPosts(Observer<List<Post>> observer){
        wpService.getPosts()                                    //Retrofit自动实现的方法
                 .subscribeOn(Schedulers.io())                  //被观察者线程类型，访问网络时为io模式
                 .unsubscribeOn(Schedulers.io())                //用于解绑
                 .observeOn(AndroidSchedulers.mainThread())     //观察者线程类型，此处为主线程以适配界面数据
                 .subscribe(observer);                          //绑定至观察者
    }

}
