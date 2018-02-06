package com.trading.model;

import java.time.LocalDateTime;

public class InsertCurrencyFromCsv {
    String fileName;

    LocalDateTime start;

    LocalDateTime finish;

    public InsertCurrencyFromCsv() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }
}
