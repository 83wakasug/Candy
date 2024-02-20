package org.candy.service;

import lombok.AllArgsConstructor;
import org.candy.entity.Candy;
import org.candy.repository.CandyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CandyService {

    private final CandyRepository candyRepository;

    public Optional<Candy> getACandy(long id) {
        Optional<Candy> candy = candyRepository.findById(id);
        return candy;
    }

    public Optional<List<Candy>> getAllACandies() {
        Optional<List<Candy>> candies = Optional.of(candyRepository.findAll());
        return candies;
    }

    public Candy addCandy(Candy candy) {
        if(candy == null) return null;

        return candyRepository.save(candy) ;
    }

    public Optional<Candy> updateCandy(Candy candy) {
        Optional<Candy> updateCandyOptional = candyRepository.findById(candy.getId());


        updateCandyOptional.ifPresent(updateCandy -> {
            if (candy.getName() != null) {
                updateCandy.setName(candy.getName());
            }
            if (candy.getPrice() != 0) {
                updateCandy.setPrice(candy.getPrice());
            }
            if (candy.getManufacturingCompany() !=null) {
                updateCandy.setManufacturingCompany(candy.getManufacturingCompany());
            }
            candyRepository.save(updateCandy);
        });

        return updateCandyOptional;
    }

    public Optional<Candy> deleteCandy(long id){

        Optional<Candy> candy = candyRepository.findById(id);
        if (candy.isPresent()) {
            candyRepository.deleteById(id);
            return null;
        }
        return candy;
    }

}
