package com.worldpay.offerprocessor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundException
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OfferNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public OfferNotFoundException() {
        super();
    }

    public OfferNotFoundException(int offerID) {
        super("Offer with ID: " + offerID + " does NOT exist.");
    }
    

}