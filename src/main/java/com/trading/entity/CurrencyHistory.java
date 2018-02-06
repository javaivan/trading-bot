package com.trading.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "currency_history")
@CompoundIndexes({
        @CompoundIndex(name = "name", def = "{ 'name': 1}", unique = true)
})
public class CurrencyHistory{

    @Id
    private String id;
    private String name;
    private Integer size;

    public CurrencyHistory(String name, Integer size) {
        this.name = name;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "CurrencyHistory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
