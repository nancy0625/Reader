package cn.edu.gdmec.android.reader.Video.View;

import java.util.List;

import cn.edu.gdmec.android.reader.Bean.TodayContentBean;
import cn.edu.gdmec.android.reader.Bean.VideoUrlBean;

/**
 * Created by apple on 18/6/12.
 */

public interface IVideoView {
    void showVideo(List<TodayContentBean> todayContentBeans,List<String> videoList);
    void hideDialog();
    void showDialog();
    void showErrorMsg(String ss);
    void showMoreVideos(List<TodayContentBean> todayContentBeans,List<String> videoList);
}
