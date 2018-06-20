package cn.edu.gdmec.android.reader.Movie.Model;

import android.widget.Toast;

import cn.edu.gdmec.android.reader.Api;
import cn.edu.gdmec.android.reader.Bean.MoviesBean;
import cn.edu.gdmec.android.reader.Http.RetrofitHelper;
import cn.edu.gdmec.android.reader.IOnLoadListener;
import cn.edu.gdmec.android.reader.Movie.Model.IMovieModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 18/5/22.
 */

public class MovieModel implements IMovieModel {
    @Override
    public void loadMovies(final String hostType, final String type, final int start, final IOnLoadListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.MOVIES_HOST);
        retrofitHelper.getMovies(type,start).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MoviesBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        iOnLoadListener.fail("cuowu");
                    }

                    @Override
                    public void onNext(MoviesBean moviesBean) {
                        if (moviesBean.getStart() != 0){

                            iOnLoadListener.loadMoreMovSuccess(moviesBean.getSubjects());
                        }else if(moviesBean.getStart()>30){
                            iOnLoadListener.fail("没有更多了");

                        }else {
                            iOnLoadListener.successMov(moviesBean);
                        }



                    }
                });
    }


}
