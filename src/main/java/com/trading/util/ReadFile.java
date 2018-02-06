package com.trading.util;

import com.trading.entity.Currency;
import com.trading.model.InsertCurrencyFromCsv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    private static List<Currency> currencies;

    public static List<Currency> getCurrencies() {
        return currencies;
    }

    public static void readFile() {
/*
        bitstampUSD.csv.gz                                 04-Feb-2018 11:14           152135698
        btceUSD.csv.gz                                     04-Feb-2018 11:34           202393310
        btcnCNY.csv.gz                                     04-Feb-2018 12:02           503272454
        coinbaseUSD.csv.gz                                 04-Feb-2018 12:12           200355046
        coincheckJPY.csv.gz                                04-Feb-2018 12:39           516488562
        krakenEUR.csv.gz                                   04-Feb-2018 11:25           116204148
        zaifJPY.csv.gz                                     04-Feb-2018 12:58           143061281
*/
        //String csvFile = "D:\\trading-bot\\src\\main\\resources\\.bitfinexUSD.csv";
        String csvFile = "D:\\trading-bot\\src\\main\\resources\\btcnCNY.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        currencies = new ArrayList<>();

        LocalDateTime breakFor = LocalDateTime.now().minusYears(5);//.minusMonths(5);//.minusDays(2).minusHours(22);
        //LocalDateTime breakFor = LocalDateTime.now();//.minusYears(4).minusMonths(5);//.minusDays(2).minusHours(22);
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                LocalDateTime date =
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(data[0])), ZoneId.systemDefault());

                if (breakFor.isBefore(date)) {
                    break;
                }
                BigDecimal bigDecimal = new BigDecimal(data[1]);
                if (bigDecimal.compareTo(new BigDecimal(20)) > 0) {
                    currencies.add(new Currency("btcnCNY", new BigDecimal(data[1]), Long.parseLong(data[0]), date));
                }
            }
            //System.out.println(currencies.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<Currency> readFile(InsertCurrencyFromCsv fromCsv) {
        Stopper stopper = new Stopper("readFile");
        List<Currency> curs = new ArrayList<>();
        String csvFile = "D:\\trading-bot\\src\\main\\resources\\" + fromCsv.getFileName() + ".csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                LocalDateTime date =
                        LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(data[0])), ZoneId.systemDefault());
                if (fromCsv.getStart().isAfter(date)) {
                    continue;
                }
                if(fromCsv.getFinish().isBefore(date)){
                    break;
                }
                BigDecimal bigDecimal = new BigDecimal(data[1]);
                if (bigDecimal.compareTo(new BigDecimal(20)) > 0) {
                    curs.add(new Currency("btcnCNY", new BigDecimal(data[1]), Long.parseLong(data[0]), date));
                }
            }
            //System.out.println(currencies.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        stopper.finish();
        return curs;
    }
}
