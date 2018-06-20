package cn.edu.gdmec.android.reader;

import java.util.List;

import cn.edu.gdmec.android.reader.Bean.MoviesBean;
import cn.edu.gdmec.android.reader.Bean.NewsBean;

/**
 * Created by apple on 18/5/22.
 */

public interface IOnLoadListener {
    void success(NewsBean newsBean);
    void successMov(MoviesBean moviesBean);
    void loadMoreMovSuccess(List<MoviesBean.SubjectsBean> objects);
    void fail(String string);
    void loadMoreNewsSuccess(NewsBean newsBean);

}
