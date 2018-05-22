package cn.edu.gdmec.android.reader;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by apple on 18/5/22.
 */

public interface RetrofitService {
    @GET ("nc/article/{type}/{id}/{startPage}-20.html")
    Call<NewsBean> getNews(@Path("type") String type,
                           @Path("id") String id,
                           @Path("startPage") int startPage);
    @GET ("v2/movie/{type}")
    Call<MoviesBean> getMovies(@Path("type") String type);
}
