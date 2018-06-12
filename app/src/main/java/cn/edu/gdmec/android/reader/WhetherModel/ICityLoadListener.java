package cn.edu.gdmec.android.reader.WhetherModel;

import java.util.List;

import cn.edu.gdmec.android.reader.Bean.TodayContentBean;
import cn.edu.gdmec.android.reader.Bean.VideoUrlBean;
import cn.edu.gdmec.android.reader.City;

/**
 * Created by apple on 18/6/12.
 */

public interface ICityLoadListener {
    void Success(String ss);

    void fail(Throwable throwable);
}
