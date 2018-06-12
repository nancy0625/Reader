package cn.edu.gdmec.android.reader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.reader.Http.RetrofitHelper;
import cn.edu.gdmec.android.reader.WhetherModel.CityModel;
import cn.edu.gdmec.android.reader.WhetherModel.ICityLoadListener;
import cn.edu.gdmec.android.reader.WhetherModel.ICityModel;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        final Integer[] citys = {101280101, 101280102, 101280103, 101280104, 101280105, 101280201, 101280202, 101280203, 101280204, 101280205, 101280206, 101280207, 101280208, 101280501};

        final RetrofitHelper retrofitHelper = new RetrofitHelper(Api.CITY_HOST);
        Observable.from(citys)
                .flatMap(new Func1<Integer, Observable<City>>() {
                    @Override
                    public Observable<City> call(Integer integer) {
                        return retrofitHelper.getCityWeather(integer);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<City>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(City city) {

                        Log.i("onNext",city.getData().getCity()+":"+city.getData().getGanmao()+":"+city.getData().getWendu());
                    }
                });

    }


}
