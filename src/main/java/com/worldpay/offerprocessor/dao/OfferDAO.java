package com.worldpay.offerprocessor.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.worldpay.offerprocessor.models.Offer;

import org.springframework.stereotype.Component;

/**
 * OfferDAO that persists to memory
 */
@Component
public class OfferDAO implements Dao<Offer> {

    // IDEALLY WOULD BE IN DATABASE JDBC WITH OUR MODEL MAPPED TO DATABASE TABLES
    // HOWEVER THIS IS PERSISTED IN MEMORY FOR SIMPLICITY
    private List<Offer> offerList = new ArrayList<>();

    @Override
    public int create(Offer offer) {
        final int id = offerList.size() + 1;
        offer.setId(id);

        offerList.add(offer);
        return id;
    }

    @Override
    public Optional<Offer> get(int id) {

        try {
            return Optional.ofNullable(this.offerList.get(id - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No offer exists with the id " + (id - 1));
            return Optional.empty();
        }
    }

    @Override
    public void update(Offer offer) {

        this.offerList.set(offer.getId() - 1, offer);

    }

    @Override
    public void delete(Offer offer) {
        this.offerList.set(offer.getId() - 1, null);

    }

}