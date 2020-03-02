package com.worldpay.offerprocessor.services;

import java.util.Optional;

import com.worldpay.offerprocessor.dao.Dao;
import com.worldpay.offerprocessor.exceptions.OfferNotFoundException;
import com.worldpay.offerprocessor.models.Offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OfferServiceImpl
 */
@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private Dao<Offer> offerDao;

    @Override
    public Offer getOffer(int id) {

        Optional<Offer> offer = offerDao.get(id);
        if(offer.isEmpty()){
            throw new OfferNotFoundException(id);
        }

        return offer.get();
    }

    @Override
    public void cancelOffer(int id) {

        final Optional<Offer> offer = offerDao.get(id);

        if(offer.isEmpty()){
            throw new OfferNotFoundException(id);
        }

        offer.get().setCancelled(true);
        offerDao.update(offer.get());

    }

    @Override
    public int createOffer(Offer offer) {
        return offerDao.create(offer);
    }
}