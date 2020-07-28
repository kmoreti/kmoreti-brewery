package com.moreti.kmoretibrewery.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moreti.kmoretibrewery.services.BeerService;
import com.moreti.kmoretibrewery.web.model.BeerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BeerControllerTest {

    private static final String API_V1_BEER_URL = "/api/v1/beer";

    @Mock
    private BeerService beerService;

    @InjectMocks
    private BeerController beerController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private BeerDto validBeer;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(beerController)
                .build();

        validBeer = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Beer 1")
                .beerStyle("PALE_ALE")
                .upc(1234L)
                .build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void getBeer() throws Exception {
        given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

        mockMvc.perform(get(API_V1_BEER_URL + "/" + validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("Beer 1")).exists())
                .andExpect(jsonPath("$.beerName", is("PALE_ALE")).exists());
    }

    @Test
    void handlePost() throws Exception {
        //given
        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        BeerDto savedBeer = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer").build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any(BeerDto.class))).willReturn(savedBeer);

        mockMvc.perform(post(API_V1_BEER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void handleUpdate() throws Exception {
        //given
        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put(API_V1_BEER_URL + "/" + UUID.randomUUID() )
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerService).should().updateBeer(any(), any());
        verify(beerService).updateBeer(any(),any());
    }
}