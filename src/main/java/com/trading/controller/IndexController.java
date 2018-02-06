package com.trading.controller;

import com.trading.entity.Currency;
import com.trading.entity.CurrencyHistory;
import com.trading.model.InsertCurrencyFromCsv;
import com.trading.service.CurrencyHistoryService;
import com.trading.service.CurrencyService;
import com.trading.service.DemoService;
import com.trading.util.ReadFile;
import com.trading.util.Stopper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private DemoService demoService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyHistoryService currencyHistoryService;

    private Log log = LogFactory.getLog(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        ReadFile.readFile();
        List<Currency> currencies = ReadFile.getCurrencies();
        currencyService.saveCurrencies(currencies);
        /*
        List<Deal> deals = demoService.getDeals(new SecondStratagy(), new BigDecimal(100), 2L, 0.6F);

        System.out.println("currencies: "  + currencies.size());
        System.out.println("deals: "  + deals.size());
        System.out.println(deals.get(deals.size()-1));
        model.addAttribute("deals", deals);*/
        //model.addAttribute("currencies", currencies);


        return "site.homepage";
        //return new ModelAndView("site.homepage");
        //return new ModelAndView("index");
    }

    @RequestMapping(value = "/insert-currency-from-csv", method = RequestMethod.GET)
    public String insertCurrencyFromCsv(Model model) {
        InsertCurrencyFromCsv insertCurrencyFromCsv = new InsertCurrencyFromCsv();
        /*insertCurrencyFromCsv.setFileName("");
        insertCurrencyFromCsv.setFinish(LocalDateTime.now());
        insertCurrencyFromCsv.setStart(LocalDateTime.now());*/
        model.addAttribute("insertCurrencyFromCsv", insertCurrencyFromCsv);
        return "site.insertCurrencyFromCsv";
    }

    @ResponseBody
    @RequestMapping(value = "/insert-currency-from-csv", method = RequestMethod.POST)
    public String insertCurrencyFromCsv(InsertCurrencyFromCsv insertCurrencyFromCsv) {
        LocalDateTime start = LocalDateTime.now().minusYears(15);
        boolean change = false;
        /*for (int i = 0; i < 15; i++) {
            Stopper stopper = new Stopper("insertCurrencyFromCsv: " + insertCurrencyFromCsv.getFileName() + " " + i);
            insertCurrencyFromCsv.setStart(start);
            insertCurrencyFromCsv.setFinish(start.plusYears(1));
            List<Currency> currencies = ReadFile.readFile(insertCurrencyFromCsv);
            if (currencies.size() > 0) {
                change = true;
            }
            currencyService.saveCurrencies(currencies);
            start = start.plusYears(1);
            stopper.finish();
        }*/

        //if(change){
            Page<Currency> currencies =
                    currencyService.getByName(insertCurrencyFromCsv.getFileName(), new PageRequest(0, 1));
            CurrencyHistory currencyHistory = new CurrencyHistory(insertCurrencyFromCsv.getFileName(), currencies.getTotalPages());
            currencyHistoryService.save(currencyHistory);
        //}



        return "sussed";
    }

    @RequestMapping(value = "/testSize", method = RequestMethod.GET)
    public String testSize() {
        List<Currency> currencies = currencyService.getAllCurrencies();
        System.out.println("currencies: " + currencies.size());
        return "testSize";
        //return new ModelAndView("site.homepage");
    }

    @RequestMapping(value = "/currency-history", method = RequestMethod.GET)
    public String currencyHistory(Model model) {
        model.addAttribute("currencyHistories", currencyHistoryService.getAll());
        model.addAttribute("insertCurrencyFromCsv", new InsertCurrencyFromCsv());
        return "currencyHistory";
    }

    @RequestMapping(value = "/currency-history", method = RequestMethod.POST)
    public String currencyHistoryPost(Model model, InsertCurrencyFromCsv insertCurrencyFromCsv) {
        insertCurrencyFromCsv.toString();
        ZoneId zoneId = ZoneId.systemDefault();
        model.addAttribute("currencies", currencyService.getByNameAndUnixTime(insertCurrencyFromCsv.getFileName(),
                insertCurrencyFromCsv.getStart().atZone(zoneId).toEpochSecond(),
                insertCurrencyFromCsv.getFinish().atZone(zoneId).toEpochSecond()));
        model.addAttribute("currencyHistories", currencyHistoryService.getAll());
        model.addAttribute("insertCurrencyFromCsv", insertCurrencyFromCsv);
        return "currencyHistory";
    }


}
