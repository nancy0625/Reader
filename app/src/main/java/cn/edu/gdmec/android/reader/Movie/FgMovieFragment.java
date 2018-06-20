package cn.edu.gdmec.android.reader.Movie;

import android.graphics.Color;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Toast;


import java.util.List;

import cn.edu.gdmec.android.reader.Api;
import cn.edu.gdmec.android.reader.Bean.MoviesBean;
import cn.edu.gdmec.android.reader.Movie.Presenter.MoviesPresenter;
import cn.edu.gdmec.android.reader.Movie.View.IMovieView;
import cn.edu.gdmec.android.reader.R;


public class FgMovieFragment extends Fragment implements IMovieView {

    private MoviesPresenter moviesPresenter;
    private RecyclerView rv_movie_on,rv_movie_top;
    private SwipeRefreshLayout srl_movie,srl_top;
    private ItemMovieOnAdapter movieOnAdapter;
    private ItemMovieTopAdapter movieTopAdapter;
    private LinearLayoutManager layoutManager;
    private int start=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_movie, null);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesPresenter = new MoviesPresenter(this);
        srl_movie = view.findViewById(R.id.srl_movie);

        rv_movie_on = view.findViewById(R.id.rv_movie_on);
        rv_movie_top = view.findViewById(R.id.rv_movie_top);
        movieOnAdapter = new ItemMovieOnAdapter(getActivity());
        movieTopAdapter = new ItemMovieTopAdapter(getActivity());
        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));

        moviesPresenter.loadMovies(Api.MOVIE_TOP,start);
        moviesPresenter.loadMovies(Api.MOVIE_ID,start);

        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesPresenter.loadMovies(Api.MOVIE_ID,start);
            }
        });
        rv_movie_on.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        (layoutManager.findLastVisibleItemPosition() + 1) == layoutManager.getItemCount()){
                    loadMore();
                }
            }
        });
    }
    private void loadMore(){
        start += 10;
        moviesPresenter.loadMovies(Api.MOVIE_ID,start);
    }



    @Override
    public void showViews(MoviesBean moviesBean) {
        if (moviesBean.getTotal()==250){
            movieTopAdapter.setData(moviesBean.getSubjects());
            rv_movie_top.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
            rv_movie_top.setAdapter(movieTopAdapter);
        }else {
            movieOnAdapter.setData(moviesBean.getSubjects());
            layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            rv_movie_on.setLayoutManager(layoutManager);
            rv_movie_on.setAdapter(movieOnAdapter);
        }


    }

    @Override
    public void showMoreMovies(List<MoviesBean.SubjectsBean> objects) {
        movieOnAdapter.addData(objects);
        movieOnAdapter.notifyDataSetChanged();

    }


    @Override
    public void hideDialog() {
        srl_movie.setRefreshing(false);

    }

    @Override
    public void showDialog() {
        srl_movie.setRefreshing(true);

    }

    @Override
    public void showErrorMsg(String ss) {

        Toast.makeText(getContext(), "加载出错:"+ss, Toast.LENGTH_SHORT).show();
    }


}

