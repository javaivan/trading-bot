package com.trading.service.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.trading.entity.Currency;
import com.trading.repository.CurrencyHistoryRepository;
import com.trading.repository.CurrencyRepository;
import com.trading.service.CurrencyService;

import com.trading.util.Stopper;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private MongoClient mongoClient;

    @Override
    @Transactional
    public String saveOrUpdate(Currency currency) {
        return currencyRepository.save(currency).getId();
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    @Transactional
    public void saveCurrencies(List<Currency> currencies) {
        Stopper stopper = new Stopper("saveCurrencies");
        MongoDatabase database = mongoClient.getDatabase("trading");
        MongoCollection<Document> collection = database.getCollection("currency");

        final int batchSize = 1000;
        int batches = currencies.size() / batchSize;
        if (currencies.size() % batchSize != 0) {
            batches = batches + 1;
        }
        for (int j = 0; j < batches; j++) {
            List<Currency> batchList =
                    currencies.subList(j * batchSize, Math.min((j + 1) * (batchSize), currencies.size()));
            List<Document> docs = new ArrayList<>();
            for (int i = 0; i < batchList.size(); i++){
                Document document = new Document();
                Currency currency = batchList.get(i);
                document.put("name",  currency.getName());
                document.put("price", currency.getPrice());
                document.put("unixTime", currency.getUnixTime());
                docs.add(document);
            }

            if(docs.size()>0){
                collection.insertMany(docs);
            }
        }
        stopper.finish();
    }

    @Override
    public List<Currency> getByName(String name){
        List<Currency> currencies = currencyRepository.findByName(name);
        System.out.println(currencies.size());
        return currencies;
    }

    @Override
    public Currency getByNameAndUnixTime(String name, Long unixTime){
        return currencyRepository.findByNameAndUnixTime(name, unixTime);
    }

    @Override
    public List<Currency> getByNameAndUnixTime(String name,  Long startUnixTime, Long finishUnixTim){
        List<Currency> currencies = new ArrayList<>();
        int i = 0;
        while (true) {
            Pageable pageableRequest = new PageRequest(i, 10000, new Sort(Sort.Direction.ASC, "unixTime"));
            Page<Currency> pages = currencyRepository.findByNameAndUnixTime(name, startUnixTime, finishUnixTim, pageableRequest);
            currencies.addAll(pages.getContent());
            if(pages.getTotalPages() == currencies.size()){
                break;
            }
        }
        return currencies;
    }

    @Override
    public Page<Currency> getByName(String name, Pageable pageableRequest) {
        return currencyRepository.findByName(name, pageableRequest);
    }
}
