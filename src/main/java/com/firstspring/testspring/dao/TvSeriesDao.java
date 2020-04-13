package com.firstspring.testspring.dao;

import com.firstspring.testspring.data.TvSeries;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface TvSeriesDao {
    @Select("select * from tvseries")
    public List<TvSeries> getAll();

    @Insert("insert into tvSeries values (#{id}, #{name},#{seasonCount}, #{originRelease}) ")
    public int insert(TvSeries tvSeries);

    @Delete("delete from tvseries where id=#{id}")
    public int delete(int id);

    @Update("update tvseries set name=\"速度与激情8\" where id=#{id}")
    public int update(int id);
}
