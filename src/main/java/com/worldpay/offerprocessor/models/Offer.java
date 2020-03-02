package com.worldpay.offerprocessor.models;

import java.time.LocalDateTime;

import javax.money.MonetaryAmount;
import javax.validation.constraints.NotBlank;

import org.javamoney.moneta.Money;

/**
 * Offer
 */
public class Offer {

    private int id;
    private MonetaryAmount price;
    private boolean cancelled;

    @NotBlank(message = "Offer must have a description")
    private String description;

    private LocalDateTime expiry_date;

    public Offer(int id, String price, String description, String expiry_date) {

        this.id = id;
        this.price = Money.parse(price);
        this.cancelled = false;
        this.description = description;
        this.expiry_date = LocalDateTime.parse(expiry_date);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return this.price.getCurrency() + " " + this.price.getNumber();
    }

    public void setCancelled(final boolean cancelled) {

        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getExpiry_date() {
        return this.expiry_date;
    }

}