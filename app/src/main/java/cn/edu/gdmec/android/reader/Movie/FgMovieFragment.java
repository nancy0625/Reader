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
        srl_top = view.findViewById(R.id.srl_top);
        rv_movie_on = view.findViewById(R.id.rv_movie_on);
        rv_movie_top = view.findViewById(R.id.rv_movie_top);
        movieOnAdapter = new ItemMovieOnAdapter(getActivity());
        movieTopAdapter = new ItemMovieTopAdapter(getActivity());
        srl_movie.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        srl_top.setColorSchemeColors(Color.parseColor("#ffce3d3a"));

        moviesPresenter.loadMovies(Api.MOVIE_TOP);
        moviesPresenter.loadMovies(Api.MOVIE_ID);

        srl_top.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesPresenter.loadMovies(Api.MOVIE_TOP);

            }
        });
        srl_movie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesPresenter.loadMovies(Api.MOVIE_ID);
            }
        });
    }



    @Override
    public void showViews(MoviesBean moviesBean) {
        if (moviesBean.getTotal()==250){
            movieTopAdapter.setData(moviesBean.getSubjects());
            rv_movie_top.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
            rv_movie_top.setAdapter(movieTopAdapter);
        }else {

            movieOnAdapter.setData(moviesBean.getSubjects());
            rv_movie_on.setLayoutManager(new LinearLayoutManager(getActivity()));
            rv_movie_on.setAdapter(movieOnAdapter);
        }


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
    public void showErrorMsg(Throwable throwable) {

        Toast.makeText(getContext(), "加载出错:"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }


}

