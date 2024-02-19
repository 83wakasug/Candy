package org.candy.controller;

import lombok.RequiredArgsConstructor;
import org.candy.entity.Candy;
import org.candy.service.CandyService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candy")
@RequiredArgsConstructor
public class CandyController {




private final CandyService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCandyInfo(@RequestParam long id){
        try{
            if (id < 0) {
                return ResponseEntity.badRequest().build();
            }

             var candy = service.getACandy(id);
            System.out.println(candy.get());

            if(candy.isEmpty()){
            return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(candy.get());
        }catch (Exception e){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/")
    public ResponseEntity<?> addCandy(@RequestBody Candy candy) {
        try {
            Candy addedCandy = service.addCandy(candy);
            return ResponseEntity.ok(addedCandy);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/all")
    public ResponseEntity<?> GetCandyList() {
        try {
            Optional<List<Candy>> candyList = service.getAllACandies();
            if(candyList.isEmpty()){

                return ResponseEntity.notFound().build();

            }
            return ResponseEntity.ok(candyList.get());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

    @PutMapping("/")
    public ResponseEntity<?> updateCandy(@RequestBody Candy candy) {
        try {
            Optional<Candy> updatedCandy = service.updateCandy(candy);
            if (updatedCandy.isPresent()) {
                return ResponseEntity.ok(updatedCandy.get());
            } else {
                return ResponseEntity.notFound().build(); // or handle as needed
            }
        } catch (DataIntegrityViolationException e) {
            // Log the exception details for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            // Log the exception details for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("")
    public ResponseEntity<?> deleteCandy(Long id) {
        try {
            boolean deleted = service.deleteCandy(id);
            if(!deleted){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(deleted);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

}




