package com.moreti.kmoretibrewery.service;

import com.moreti.kmoretibrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);

    void deleteBeer(UUID beerId);
}
