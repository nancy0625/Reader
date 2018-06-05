package cn.edu.gdmec.android.reader.News.View;

import cn.edu.gdmec.android.reader.Bean.NewsBean;

/**
 * Created by apple on 18/5/22.
 */

public interface INewsView {
    void showViews(NewsBean newsBean);
    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable error);
}
