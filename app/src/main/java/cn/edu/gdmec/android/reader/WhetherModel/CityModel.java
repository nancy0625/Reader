package cn.edu.gdmec.android.reader.WhetherModel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.reader.Api;

import cn.edu.gdmec.android.reader.City;
import cn.edu.gdmec.android.reader.Http.RetrofitHelper;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 18/6/12.
 */

public class CityModel implements ICityModel {


    @Override
    public void loadCity(Integer city, final ICityLoadListener iCityLoadListener) {
        final Integer[] citys = {101280101, 101280102, 101280103, 101280104, 101280105, 101280201, 101280202, 101280203, 101280204, 101280205, 101280206, 101280207, 101280208, 101280501};

        final List<City> cityList = new ArrayList<>();

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
