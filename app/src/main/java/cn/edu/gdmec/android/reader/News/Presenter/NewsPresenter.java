package cn.edu.gdmec.android.reader.News.Presenter;

import java.util.List;

import cn.edu.gdmec.android.reader.Api;
import cn.edu.gdmec.android.reader.Bean.MoviesBean;
import cn.edu.gdmec.android.reader.Bean.NewsBean;
import cn.edu.gdmec.android.reader.IOnLoadListener;
import cn.edu.gdmec.android.reader.News.FgNewsFragment;
import cn.edu.gdmec.android.reader.News.Model.INewsModel;
import cn.edu.gdmec.android.reader.News.Model.NewsModel;
import cn.edu.gdmec.android.reader.News.View.INewsView;

/**
 * Created by apple on 18/5/22.
 */

public class NewsPresenter implements INewsPresenter,IOnLoadListener {
    private INewsModel iNewsModel;
    private INewsView iNewsView;

    public NewsPresenter (INewsView iNewsView){
        this.iNewsView = iNewsView;
        this.iNewsModel = new NewsModel();
    }
    @Override
    public void loadNews(int type, int startPage) {
    if (startPage == 0){
        iNewsView.showDialog();
    }
      switch (type){
          case FgNewsFragment.NEWS_TYPE_TOP:
              iNewsModel.loadNews("headline",startPage, Api.HEADLINE_ID,this);
              break;
          case FgNewsFragment.NEWS_TYPE_NBA:
              iNewsModel.loadNews("list",startPage,Api.NBA_ID,this);
              break;
          case FgNewsFragment.NEWS_TYPE_JOKES:
              iNewsModel.loadNews("list",startPage,Api.JOKE_ID,this);
              break;
      }
    }

    @Override
    public void success(NewsBean newsBean) {

        iNewsView.hideDialog();
        if (newsBean != null){
            iNewsView.showViews(newsBean);
        }
    }

    @Override
    public void successMov(MoviesBean moviesBean) {

    }

    @Override
    public void loadMoreMovSuccess(List<MoviesBean.SubjectsBean> objects) {

    }

    @Override
    public void fail(String ss) {
        iNewsView.hideDialog();
        iNewsView.showErrorMsg(ss);
    }

    @Override
    public void loadMoreNewsSuccess(NewsBean newsBean) {
        iNewsView.hideDialog();
        iNewsView.showMoreNews(newsBean);
    }


}
