package cn.edu.gdmec.android.reader;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

import java.util.TimerTask;

public class FgNewsListFragment extends Fragment  implements INewsView{

    private TextView tvNews;
    private int type;
    private NewsPresenter presenter;
    private SwipeRefreshLayout srl_news;

    public static FgNewsListFragment newInstance(int type){
        Bundle args = new Bundle();
        FgNewsListFragment fragment = new FgNewsListFragment();
        args.putInt("type",type);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fg_news_list,null);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = getArguments().getInt("type");
        tvNews = (TextView) view.findViewById(R.id.tv_news);
        srl_news = view.findViewById(R.id.srl_news);
        srl_news.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        presenter = new NewsPresenter(this);
        srl_news.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadNews(type,0);
            }
        });


    }


    @Override
    public void showViews(final NewsBean newsBean) {
        getActivity().runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                switch (type){
                    case FgNewsFragment.NEWS_TYPE_TOP:
                        tvNews.setText(newsBean.getTop().get(0).getTitle()+" "+ newsBean.getTop().get(0).getMtime());
                        break;
                    case FgNewsFragment.NEWS_TYPE_NBA:
                        tvNews.setText(newsBean.getNBA().get(0).getTitle()+" "+ newsBean.getNBA().get(0).getMtime());
                        break;
                    case FgNewsFragment.NEWS_TYPE_JOKES:
                        tvNews.setText(newsBean.getJoke().get(0).getTitle()+" "+ newsBean.getJoke().get(0).getMtime());
                        break;
                }
            }
        });
    }

    @Override
    public void hideDialog() {

        srl_news.setRefreshing(false);
    }

    @Override
    public void showDialog() {

        srl_news.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(String error) {

        tvNews.setText("加载失败："+error);
    }
}
