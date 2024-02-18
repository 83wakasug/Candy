package org.candy.controller;

import lombok.RequiredArgsConstructor;
import org.candy.entity.Candy;
import org.candy.service.CandyService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candy")
@RequiredArgsConstructor
public class CandyController {




private final CandyService service;

    @GetMapping("/")
    public ResponseEntity<?> getCandyInfo(@RequestParam long id){
        try{
            if (id < 0 || id > Long.MAX_VALUE) {
                return ResponseEntity.badRequest().build();
            }

             var candy = service.getACandy(id);

            if(candy.isEmpty()){
            return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(candy.get());
        }catch (Exception e){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/")
    public ResponseEntity<?> addCandy(Candy candy) {
        try {
            Candy addedCandy = service.addCandy(candy);
            return ResponseEntity.ok(addedCandy);
        } catch (DataIntegrityViolationException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }



}
