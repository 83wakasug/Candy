package org.candy.service;

import lombok.extern.slf4j.Slf4j;
import org.candy.CandyApplication;
import org.candy.entity.Candy;
import org.candy.repository.CandyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ActiveProfiles("test")
@SpringBootTest(classes = CandyApplication.class)
class CandyServiceTest {

    @Mock
    private CandyRepository candyRepository;

    @InjectMocks
    private CandyService candyService;


    @Test
    void getACandy() {
        when(candyRepository.findById(1L)).thenReturn(Optional.of(candy1()));
        Optional<Candy> candyOptional = candyService.getACandy(1L);

            Candy candy = candyOptional.get();
            assertEquals(candy.getId(), 1L);
            assertEquals(candy.getName(), "hichu");
            assertEquals(candy.getManufacturingCompany(), "Morinaga");
            assertEquals(candy.getPrice(), 100);

    }

    @Test
    void getAllACandies() {
        List<Candy>candyList  = new ArrayList<>();
        candyList.add(candy1());
        candyList.add(candy2());
        when(candyRepository.findAll()).thenReturn(candyList);
        List<Candy>candyList2  = candyRepository.findAll();
        assertEquals(candyList2.get(0),candy1());
        assertEquals(candyList2.get(1),candy2());

    }

    @Test
    void addCandy() {

        // モックの設定
        when(candyRepository.save(any(Candy.class))).thenReturn(candy1());

        // テスト対象のメソッド呼び出し
        Candy candy = candyService.addCandy(candy1());

        // モックの振る舞いを検証
        verify(candyRepository, times(1)).save(any(Candy.class));

        // アサーション
        assertEquals(candy1(), candy);

    }

    @Test
    void updateCandy() {
        when(candyRepository.findById(1L)).thenReturn(Optional.ofNullable(candy1()));
        Optional<Candy> candyOptional = candyService.getACandy(1L);
        Candy candyToUpdate = candyOptional.get();

        // プロパティを更新
        candyToUpdate.setManufacturingCompany("Calbee");
        candyToUpdate.setName("Jagarioko");
        candyToUpdate.setPrice(150);

        when(candyRepository.save(any(Candy.class))).thenReturn(candyToUpdate);
        Optional<Candy> newCandy = candyService.updateCandy(candyToUpdate);
        assertEquals("Calbee", newCandy.get().getManufacturingCompany());
        assertEquals("Jagarioko", newCandy.get().getName());
        assertEquals(150, newCandy.get().getPrice());

    }

    @Test
    void deleteCandy() {
        // モックの設定
        when(candyRepository.findById(1L)).thenReturn(Optional.of(candy1()));
        doNothing().when(candyRepository).deleteById(1L);

        // テスト対象のメソッド呼び出し
        boolean result = candyService.deleteCandy(1L);

        // モックの振る舞いを検証
        verify(candyRepository, times(1)).deleteById(1L);

        // アサーション
        assertTrue(result);

    }


    public Candy candy1(){
        return new Candy(1L, "hichu", "Morinaga", 100);
    }

    public Candy candy2(){
        return new Candy(2L, "Umakabou", "Meiji", 20);
    }
    public Candy candy3(){
        return new Candy(2L, "Jagarioko", "Calbee", 150);
    }




}