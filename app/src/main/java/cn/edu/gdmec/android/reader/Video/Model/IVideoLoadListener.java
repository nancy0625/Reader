package cn.edu.gdmec.android.reader.Video.Model;

import java.util.List;

import cn.edu.gdmec.android.reader.Bean.TodayContentBean;
import cn.edu.gdmec.android.reader.Bean.VideoUrlBean;

/**
 * Created by apple on 18/6/12.
 */

public interface IVideoLoadListener {
    void videoUrlSuccess(List<VideoUrlBean> videoUrlBeans, boolean flag,List<TodayContentBean> contentBeans);
    void fail(String  string);
}
