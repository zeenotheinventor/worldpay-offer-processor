package com.worldpay.offerprocessor.controllers;

import javax.validation.Valid;

import com.worldpay.offerprocessor.models.Offer;
import com.worldpay.offerprocessor.services.OfferService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * OfferController
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    /**
     * Resource entry point for offer creation
     * 
     * @return the Offer ID generated from the Data Layer
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public int createOffer(@Valid @RequestBody Offer offer) {

        final int id = offerService.createOffer(offer);
        return id;
    }

    /**
     * Resource entry point for getting Offer details
     * 
     * @param id the ID of the offer in our database
     * @return the Offer details
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Offer> getOffer(@PathVariable(name = "id") int id) {

        Offer offer = offerService.getOffer(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    /**
     * Resource entry point for cancelling an Offer
     * 
     * @param id the ID of the offer in our database
     */
    @PatchMapping(value = "/{id}/cancel")
    public void cancelOffer(@PathVariable(name = "id") int id) {
        offerService.cancelOffer(id);

    }

}