package com.worldpay.offerprocessor;

import com.worldpay.offerprocessor.controllers.IOfferControllerTests;
import com.worldpay.offerprocessor.models.OfferTest;
import com.worldpay.offerprocessor.services.OfferServiceImplTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * AllTests
 */
@RunWith(Suite.class)
@SuiteClasses({OfferServiceImplTest.class, OfferTest.class, IOfferControllerTests.class})
public class AllTests {

    
}