package cn.edu.gdmec.android.reader.Http;

import java.util.concurrent.TimeUnit;

import cn.edu.gdmec.android.reader.Bean.MoviesBean;
import cn.edu.gdmec.android.reader.Bean.NewsBean;
import cn.edu.gdmec.android.reader.Bean.TodayBean;
import cn.edu.gdmec.android.reader.Bean.TodayContentBean;
import cn.edu.gdmec.android.reader.Bean.VideoUrlBean;
import cn.edu.gdmec.android.reader.City;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by apple on 18/5/22.
 */

public class RetrofitHelper {
    private static OkHttpClient okHttpClient;
    private RetrofitService retrofitService;
   public RetrofitHelper(String host){
       Retrofit retrofit = new Retrofit.Builder().baseUrl(host).client(getOkHttpClient()).addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
               .build();
       retrofitService = retrofit.create(RetrofitService.class);
    }
    public Observable<NewsBean> getNews(String type, String id, int startPage){
       return retrofitService.getNews(type,id,startPage);
    }
    public Observable<MoviesBean> getMovies(String type,int start){
        return retrofitService.getMovies(type,start);
    }


    public OkHttpClient getOkHttpClient(){
       if (okHttpClient == null){
           okHttpClient = new OkHttpClient.Builder().retryOnConnectionFailure(true).connectTimeout(30, TimeUnit.SECONDS).build();
       }
       return okHttpClient;
    }

    public Observable<TodayBean> getToday(String category){
        return retrofitService.getToday(category);
    }

    public Observable<VideoUrlBean> getVideoUrl(String api){
        return retrofitService.getVideoUrl(api);
    }
    public Observable<City> getCityWeather(Integer city){
        return retrofitService.getCity(city);
    }
}
