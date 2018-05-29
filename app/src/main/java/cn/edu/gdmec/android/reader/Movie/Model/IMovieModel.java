package cn.edu.gdmec.android.reader.Movie.Model;

import cn.edu.gdmec.android.reader.IOnLoadListener;

/**
 * Created by apple on 18/5/22.
 */

public interface IMovieModel {
    void loadMovies(String hostType,String type, IOnLoadListener iOnLoadListener);
}
