# WorldPay Offer Processor

## Table of Contents
- [WorldPay Offer Processor](#worldpay-offer-processor)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Notable Classes](#notable-classes)
  - [Assumptions](#assumptions)

## Introduction
This is an offer processor running using Spring Boot on port 8090. To use it please access [http://localhost:8090/offer](http://localhost:8090/offer)

## Notable Classes
1. OfferProcessorApplication.java (Entry point if you are launching from VSCode).
2. AllTests.java (Entry Point for tests if you are Launching from VSCode).

## Assumptions

1. Merchants cannot change the offer description or price once an offer has been created.
2. It is assumed an Offer cannot exist without its description, price and expiry date.
3. There is no need for authentication or authorization, i.e;
    * Anyone can view merchants' offers regardless of permissions
    * Every client machine consuming this API would not reject a response served over HTTP (i.e it doesn't require HTTPS). 
4. Whether or not the offer has been claimed does not need to be tracked by the system.
5. Once an offer has been cancelled, it cannot be undone (i.e, a new offer will have to be made).
6. Merchants would like to consume the API that gives only JSON responses.
7. It is assumed data layer is a NoSQL database like MongoDB.
8. It is assumed the user would have adequate documentation on how to use the API.