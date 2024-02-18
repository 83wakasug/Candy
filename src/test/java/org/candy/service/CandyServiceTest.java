package org.candy.service;

import lombok.extern.slf4j.Slf4j;
import org.candy.entity.Candy;
import org.candy.repository.CandyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;


@ActiveProfiles("test")
@Slf4j
@SpringBootTest
class CandyServiceTest {

    @Mock
    private CandyRepository candyRepository;

    @InjectMocks
    private CandyService candyService;

    @BeforeEach // テストメソッド（@Testをつけたメソッド）実行前に都度実施
    public void initmocks() {

        when(candyRepository.findById(1L)).thenReturn(Optional.of(new Candy(1L, "hichu", "Morinaga", 100)));
    }

    @Test
    void getACandy() {

        Optional<Candy> candyOptional = candyService.getACandy(1L);

            Candy candy = candyOptional.get();
            assertEquals(candy.getId(), 1L);
            assertEquals(candy.getName(), "hichu");
            assertEquals(candy.getManufacturingCompany(), "Morinaga");
            assertEquals(candy.getPrice(), 100);

    }

    @Test
    void getAllACandies() {


    }

    @Test
    void addCandy() {
    }

    @Test
    void updateCandy() {
    }

    @Test
    void deleteCandy() {
    }
}