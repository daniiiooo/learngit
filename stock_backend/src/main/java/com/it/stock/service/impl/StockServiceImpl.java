package com.it.stock.service.impl;

import com.it.stock.mapper.StockBlockRtInfoMapper;
import com.it.stock.mapper.StockMarketIndexInfoMapper;
import com.it.stock.pojo.domain.InnerMarketDomain;
import com.it.stock.pojo.domain.StockBlockDomain;
import com.it.stock.pojo.vo.StockInfoConfig;
import com.it.stock.service.StockService;
import com.it.stock.utils.DateTimeUtil;
import com.it.stock.vo.resp.R;
import com.it.stock.vo.resp.ResponseCode;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
//股票服务实现
@Service("stockService")
public class StockServiceImpl implements StockService {
    @Autowired
    private StockInfoConfig stockInfoConfig;
    @Autowired
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;

    @Override
    public R<List<InnerMarketDomain>> getInnerMarketInfo() {
        Date curDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        curDate = DateTime.parse("2022-01-02 09:32:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        List<String> mCodes = stockInfoConfig.getInner();
        List<InnerMarketDomain> date = stockMarketIndexInfoMapper.getMarketInfo(curDate,mCodes);
        return R.ok(date);


    }
    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;


    @Override
    public R<List<StockBlockDomain>> sectorAllLimit() {
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        lastDate = DateTime.parse("2021-12-21 14:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        List<StockBlockDomain> infos = stockBlockRtInfoMapper.sectorAllLimit(lastDate);
        if(CollectionUtils.isEmpty(infos)){
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        return R.ok(infos);

    }
}
