package com.trading.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "currency")
@CompoundIndexes({
        @CompoundIndex(name = "name_unix_time", def = "{ 'name': 1, 'unixTime': 1}", unique = true)
})
public class Currency {

    @Id
    private String id;
    private String name;
    private BigDecimal price = new BigDecimal(0);
    private Long unixTime;
    private LocalDateTime date;

    public Currency() {

    }
    public Currency( BigDecimal price) {
        this.price = price;
    }
    public Currency(String name, BigDecimal price, Long unixTime, LocalDateTime date) {
        this.name = name;
        this.price = price;
        this.unixTime = unixTime;
        this.date = date;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(Long unixTime) {
        this.unixTime = unixTime;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", unixTime=" + unixTime +
                ", date=" + date +
                '}';
    }
}
