package com.it.stock.service;

import com.it.stock.pojo.domain.InnerMarketDomain;
import com.it.stock.pojo.domain.StockBlockDomain;
import com.it.stock.vo.resp.R;

import java.util.List;

public interface StockService {
    R<List<InnerMarketDomain>> getInnerMarketInfo();

    R<List<StockBlockDomain>> sectorAllLimit();
}
