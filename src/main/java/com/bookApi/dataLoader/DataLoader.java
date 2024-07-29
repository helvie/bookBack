package com.bookApi.dataLoader;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bookApi.repository.BookToLendRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private BookToLendRepository BookToLendRepository;

    @Override
    public void run(String... args) throws Exception {
        // Récupérer les données et les afficher dans la console
    	BookToLendRepository.findAll().forEach(bookToLend -> System.out.println(bookToLend));
    }
}