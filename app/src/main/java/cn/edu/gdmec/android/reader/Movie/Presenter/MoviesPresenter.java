package cn.edu.gdmec.android.reader.Movie.Presenter;

import java.util.List;

import cn.edu.gdmec.android.reader.Api;
import cn.edu.gdmec.android.reader.Bean.MoviesBean;
import cn.edu.gdmec.android.reader.Bean.NewsBean;
import cn.edu.gdmec.android.reader.IOnLoadListener;
import cn.edu.gdmec.android.reader.Movie.Model.IMovieModel;
import cn.edu.gdmec.android.reader.Movie.Model.MovieModel;
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
    public void loadMovies(String type,int start) {
        if (start == 0){
            iMovieView.showDialog();
        }

        iMovieModel.loadMovies("headline",type,start,this);


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
    public void loadMoreMovSuccess(List<MoviesBean.SubjectsBean> objects) {

        iMovieView.hideDialog();
        iMovieView.showMoreMovies(objects);
    }


    @Override
    public void fail(String ss) {

        iMovieView.hideDialog();
        iMovieView.showErrorMsg("ddd");
    }

    @Override
    public void loadMoreNewsSuccess(NewsBean newsBean) {

    }
}
