package cn.edu.gdmec.android.reader.Movie.Model;

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
    public void loadMovies(final String hostType,final String type, final IOnLoadListener iOnLoadListener) {
        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.MOVIES_HOST);
        retrofitHelper.getMovies(type).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<MoviesBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        iOnLoadListener.fail(e);
                    }

                    @Override
                    public void onNext(MoviesBean moviesBean) {

                        iOnLoadListener.successMov(moviesBean);
                    }
                });
    }


}
