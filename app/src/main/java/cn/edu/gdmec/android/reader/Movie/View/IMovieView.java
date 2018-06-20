package cn.edu.gdmec.android.reader.Movie.View;

import java.util.List;

import cn.edu.gdmec.android.reader.Bean.MoviesBean;

/**
 * Created by apple on 18/5/22.
 */

public interface IMovieView {
    void showViews(MoviesBean moviesBean);
    void showMoreMovies(List<MoviesBean.SubjectsBean> objects);

    void hideDialog();
    void showDialog();
    void showErrorMsg(String ss);
}
