package cn.edu.gdmec.android.reader.News.Model;

import cn.edu.gdmec.android.reader.Api;
import cn.edu.gdmec.android.reader.Bean.NewsBean;
import cn.edu.gdmec.android.reader.Http.RetrofitHelper;
import cn.edu.gdmec.android.reader.IOnLoadListener;
import cn.edu.gdmec.android.reader.News.Model.INewsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 18/5/22.
 */

public class NewsModel implements INewsModel {
    @Override
    public void loadNews(final String hostType,final int startPage,final String id, final IOnLoadListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.NEWS_HOST);
       retrofitHelper.getNews(hostType,id,startPage)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeOn(Schedulers.io())
               .subscribe(new Subscriber<NewsBean>() {
                   @Override
                   public void onCompleted() {

                   }

                   @Override
                   public void onError(Throwable e) {
                     iOnLoadListener.fail(e);
                   }

                   @Override
                   public void onNext(NewsBean newsBean) {

                       iOnLoadListener.success(newsBean);
                   }
               });
    }
}
