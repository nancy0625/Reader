package cn.edu.gdmec.android.reader;

/**
 * Created by apple on 18/5/22.
 */

public interface IOnLoadListener {
    void success(NewsBean newsBean);
    void successMov(MoviesBean moviesBean);
    void fail(String throwable);
}
