package com.worldpay.offerprocessor.services;

import com.worldpay.offerprocessor.models.Offer;

/**
 * OfferService
 */
public interface OfferService {

    public Offer getOffer(final int id);

    public void cancelOffer(final int id);

    public int createOffer(final Offer offer);
}