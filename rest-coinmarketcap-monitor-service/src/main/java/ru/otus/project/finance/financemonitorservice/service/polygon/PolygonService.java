package ru.otus.project.finance.financemonitorservice.service.polygon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.finance.financemonitorservice.domain.Stock;
import ru.otus.project.finance.financemonitorservice.dto.StockDto;
import ru.otus.project.finance.financemonitorservice.repository.StockRepository;
import ru.otus.project.finance.financemonitorservice.service.DataRetrieveService;
import ru.otus.project.finance.financemonitorservice.util.Constant;
import ru.otus.project.finance.financemonitorservice.util.DataPartitioner;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PolygonService {

//    The response data from the API includes the following information for the stock with the symbol "TTMI"
//    on January 9, 2023:
//
//    T: Symbol of the stock, in this case, "TTMI".
//    v: Volume of the stock traded on that day, in this case, 394,280 shares were traded.
//    vw: Volume-weighted average price of the stock on that day, which is 16.1078.
//    o: Opening price of the stock on that day, which is 15.96.
//    c: Closing price of the stock on that day, which is 16.08.
//    h: Highest price of the stock on that day, which is 16.335.
//    l: Lowest price of the stock on that day, which is 15.96.
//    t: Unix timestamp representing the date and time of this data point. In this case,
//    it's 1673298000000, which corresponds to January 9, 2023.
//    n: Number of transactions (trades) that occurred for this stock on that day, which is 5416.

    DataRetrieveService dataRetrieveService;
    StockRepository stockRepository;

    public void getHistoryStocks() {
        JSONObject jsonObject = dataRetrieveService.getStock();
        JSONArray results = jsonObject.getJSONArray(Constant.RESULTS);
        List<Stock> stocks = new ArrayList<>();
        for (int i = 0, size = results.length(); i < size; i++) {
            JSONObject objectInArray = results.getJSONObject(i);
            stocks.add(Stock.builder()
                    .symbol(objectInArray.optString("T", "DEFAULT_SYMBOL"))
                    .volume(objectInArray.optLong("v", 0L))
                    .volumeWeightedAverage(objectInArray.optDouble("vw", 0.0))
                    .openingPrice(objectInArray.optDouble("o", 0.0))
                    .closingPrice(objectInArray.optDouble("c", 0.0))
                    .highestPrice(objectInArray.optDouble("h", 0.0))
                    .lowestPrice(objectInArray.optDouble("l", 0.0))
                    .timestamp(objectInArray.optLong("t", 0L))
                    .numberOfTransactions(objectInArray.optInt("n", 0))
                    .build());
        }

        saveStock(DataPartitioner.partitionData(stocks, 500));
    }

    @Transactional
    public void saveStock(List<List<Stock>> chunkedStocks){
        for(List<Stock> stocks : chunkedStocks){
            stockRepository.saveAll(stocks);
        }
    }
}
