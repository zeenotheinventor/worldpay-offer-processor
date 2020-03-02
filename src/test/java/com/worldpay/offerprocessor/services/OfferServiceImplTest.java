package com.worldpay.offerprocessor.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.worldpay.offerprocessor.dao.Dao;
import com.worldpay.offerprocessor.exceptions.OfferNotFoundException;
import com.worldpay.offerprocessor.models.Offer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * OfferServiceTest
 */
@RunWith(MockitoJUnitRunner.class)
public class OfferServiceImplTest {

    @Mock
    private Dao<Offer> offerDaoMock;

    @InjectMocks
    private OfferServiceImpl offerService;

    @Test
    public void testGetOffer_WhenExists() {

        int id = 1;
        Offer offer = new Offer(id, "USD 42.51", "Offer for 43 yams", "2018-05-05T11:50:55");
        when(offerDaoMock.get(id)).thenReturn(Optional.ofNullable(offer));
        assertEquals(offer, offerService.getOffer(id));
    }

    @Test(expected = OfferNotFoundException.class)
    public void testGetOffer_ThrowsException_WhenNotExists() {

        offerService.getOffer(1);
    }

    @Test
    public void testCancelOffer_WhenExists() {
        int id = 1;
        Offer offer = new Offer(id, "USD 42.51", "Offer for 43 yams", "2018-05-05T11:50:55");
        when(offerDaoMock.get(id)).thenReturn(Optional.ofNullable(offer));

        offerService.cancelOffer(id);

    }

    @Test(expected = OfferNotFoundException.class)
    public void testCancelOffer_WhenNotExists() {
        int id = 1;
        offerService.cancelOffer(id);

    }

    @Test
    public void testCreateOffer() {

        Offer offer = new Offer(-1, "USD 42.51", "Offer for 43 yams", "2018-05-05T11:50:55");

        when(offerDaoMock.create(offer)).thenReturn(1);

        assertEquals("Offers are not the same", 1, offerService.createOffer(offer));

    }

}