package cn.edu.gdmec.android.reader;

/**
 * Created by apple on 18/5/22.
 */

public interface INewsView {
    void showViews(NewsBean newsBean);
    void hideDialog();
    void showDialog();
    void showErrorMsg(String error);
}
