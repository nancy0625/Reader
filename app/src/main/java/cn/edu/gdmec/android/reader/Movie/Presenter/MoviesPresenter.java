package cn.edu.gdmec.android.reader.Movie.Presenter;

import cn.edu.gdmec.android.reader.Api;
import cn.edu.gdmec.android.reader.Bean.MoviesBean;
import cn.edu.gdmec.android.reader.Bean.NewsBean;
import cn.edu.gdmec.android.reader.IOnLoadListener;
import cn.edu.gdmec.android.reader.Movie.Model.IMovieModel;
import cn.edu.gdmec.android.reader.Movie.Model.MovieModel;
import cn.edu.gdmec.android.reader.Movie.Presenter.IMoviePresenter;
import cn.edu.gdmec.android.reader.Movie.View.IMovieView;

/**
 * Created by apple on 18/5/22.
 */

public class MoviesPresenter implements IMoviePresenter,IOnLoadListener {
    private IMovieModel iMovieModel;
    private IMovieView iMovieView;

    public MoviesPresenter(IMovieView iMovieView){
        this.iMovieView = iMovieView;
        this.iMovieModel = new MovieModel();
    }


    @Override
    public void loadMovies() {
        iMovieView.showDialog();
        iMovieModel.loadMovies("headline", Api.MOVIE_ID,this);
    }

    @Override
    public void success(NewsBean newsBean) {


    }

    @Override
    public void successMov(MoviesBean moviesBean) {

        iMovieView.hideDialog();
        if (moviesBean != null){
            iMovieView.showViews(moviesBean);
        }
    }

    @Override
    public void fail(String throwable) {

        iMovieView.hideDialog();
        iMovieView.showErrorMsg(throwable);
    }
}
