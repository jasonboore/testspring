package com.firstspring.testspring.services;

import com.firstspring.testspring.dao.TvSeriesDao;
import com.firstspring.testspring.data.TvSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TvSeriesSevice {
    @Autowired TvSeriesDao tvSeriesDao;
    public TvSeriesSevice(TvSeriesDao tvSeriesDao) {
        this.tvSeriesDao = tvSeriesDao;
    }

    public List<TvSeries> getAllTvSeries() {
        return tvSeriesDao.getAll();
    }
    public int deleteOne(int id) {
        return tvSeriesDao.delete(id);
    }
    public int updateOne(int id) {
        return tvSeriesDao.update(id);
    }
    public int insertOne(TvSeries tvSeries) {
        return tvSeriesDao.insert(tvSeries);
    }

}
