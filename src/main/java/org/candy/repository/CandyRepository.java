package org.candy.repository;

import org.candy.entity.Candy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandyRepository extends JpaRepository<Candy,Long> {

    @Override
    void deleteById(Long aLong);
}
