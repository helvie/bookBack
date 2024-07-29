package com.ecolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolivre.entity.BookToLend;

@Repository
public interface BookToLendRepository extends JpaRepository<BookToLend, Long> {
}