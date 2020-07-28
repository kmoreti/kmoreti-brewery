package com.moreti.kmoretibrewery.web.mappers;

import com.moreti.kmoretibrewery.domain.Customer;
import com.moreti.kmoretibrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDto customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDto customerDto);
}
