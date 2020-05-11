package com.example.airlines.dao;

import com.example.airlines.model.Access;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AccessDAO extends JpaRepository<Access, Integer> {

    @Override
    List<Access> findAll();

    @Override
    Optional<Access> findById(Integer integer);

    @Override
    Access save(Access access);

    @Override
    void deleteById(Integer id);
}
