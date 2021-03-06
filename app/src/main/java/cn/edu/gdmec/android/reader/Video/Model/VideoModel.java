package cn.edu.gdmec.android.reader.Video.Model;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.reader.Api;
import cn.edu.gdmec.android.reader.Bean.TodayBean;
import cn.edu.gdmec.android.reader.Http.RetrofitHelper;
import cn.edu.gdmec.android.reader.Bean.TodayContentBean;
import cn.edu.gdmec.android.reader.Bean.VideoUrlBean;
import cn.edu.gdmec.android.reader.Video.Presenter.VideoPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 18/6/12.
 */

public class VideoModel implements IVideoModel {
    @Override
    public void loadVideo(String category, final boolean flag, final IVideoLoadListener iVideoLoadListener) {
        final List<VideoUrlBean> videoList = new ArrayList<>();
        final List<TodayContentBean> contentBeans = new ArrayList<>();
        final RetrofitHelper retrofitHelper = new RetrofitHelper(Api.TODAY_HOST);


        retrofitHelper.getToday(category)
                .flatMap(new Func1<TodayBean, Observable<VideoUrlBean>>() {
                    @Override
                    public Observable<VideoUrlBean> call(TodayBean todayBean) {
                        //return retrofitHelper.getVideoUrl(a)
                        return Observable.from(todayBean.getData())
                                .flatMap(new Func1<TodayBean.DataBean, Observable<VideoUrlBean>>() {
                                    @Override
                                    public Observable<VideoUrlBean> call(TodayBean.DataBean dataBean) {
                                        String content = dataBean.getContent();
                                        TodayContentBean contentBean = VideoPresenter.getTodayContentBean(content);
                                        contentBeans.add(contentBean);
                                        String api = VideoPresenter.getVideoContentApi(contentBean.getVideo_id());
                                        return retrofitHelper.getVideoUrl(api);
                                    }
                                });
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<VideoUrlBean>() {
            @Override
            public void onCompleted() {

                iVideoLoadListener.videoUrlSuccess(videoList,flag, contentBeans);
            }

            @Override
            public void onError(Throwable e) {

                iVideoLoadListener.fail(e.getMessage());
            }

            @Override
            public void onNext(VideoUrlBean videoUrlBean) {


                videoList.add(videoUrlBean);
            }
        });
    }
}
