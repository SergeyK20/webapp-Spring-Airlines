package com.example.airlines.dao;


import com.example.airlines.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface CityDAO extends JpaRepository<City, Integer> {

    @Override
    java.util.Optional<City> findById(Integer Id);

    @Override
    City save(City city);

    @Override
    void deleteById(Integer id);

    @Modifying
    @Query("update City c set c.nameCity = :city where c.idCity = :id ")
    void update(@Param("city") String city, @Param("id") Integer id);

}
