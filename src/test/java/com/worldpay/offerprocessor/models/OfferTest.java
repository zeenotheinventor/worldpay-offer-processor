package com.worldpay.offerprocessor.models;

import org.junit.Test;
import org.junit.Assert;

/**
 * OfferTest
 */
public class OfferTest {

    @Test
    public void testGetPrice() {

        Offer offer = new Offer(-1, "USD 75.54", "Offer for 10 cakes", "2018-05-05T11:50:55");

        Assert.assertEquals("USD 75.54", offer.getPrice());
        
    }
}