package cn.edu.gdmec.android.reader;

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
      iNewsView.showDialog();
      switch (type){
          case FgNewsFragment.NEWS_TYPE_TOP:
              iNewsModel.loadNews("headline",startPage,Api.HEADLINE_ID,this);
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
    public void fail(String throwable) {
        iNewsView.hideDialog();
        iNewsView.showErrorMsg(throwable);
    }
}
