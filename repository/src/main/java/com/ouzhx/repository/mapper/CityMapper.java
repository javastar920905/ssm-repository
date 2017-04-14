package com.ouzhx.repository.mapper;

import com.ouzhx.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ouzhx on 2017/4/11.
 */
@Mapper
public interface CityMapper {
  @Select("SELECT * FROM CITY WHERE id = #{id}")
  City findByState(@Param("id") String id);
}
