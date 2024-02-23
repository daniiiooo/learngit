package com.it.stock.mapper;

import com.it.stock.pojo.entity.StockBusiness;

/**
* @author 77519
* @description 针对表【stock_business(主营业务表)】的数据库操作Mapper
* @createDate 2024-02-20 21:29:54
* @Entity com.it.stock.pojo.entity.StockBusiness
*/
public interface StockBusinessMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBusiness record);

    int insertSelective(StockBusiness record);

    StockBusiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBusiness record);

    int updateByPrimaryKey(StockBusiness record);

}
