package cn.edu.gdmec.android.reader;

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
        iMovieModel.loadMovies("headline",Api.MOVIE_ID,this);
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
