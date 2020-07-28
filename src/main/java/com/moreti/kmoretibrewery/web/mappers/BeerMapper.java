package com.moreti.kmoretibrewery.web.mappers;

import com.moreti.kmoretibrewery.domain.Beer;
import com.moreti.kmoretibrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
