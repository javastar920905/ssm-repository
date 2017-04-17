package com.ouzhx.repository.mapper;

import com.ouzhx.entity.City;

public interface CityMapper extends BaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
}