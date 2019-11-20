package com.moreti.kmoretibrewery.service;

import com.moreti.kmoretibrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);
}
