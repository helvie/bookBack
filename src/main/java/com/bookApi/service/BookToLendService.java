package com.bookApi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookApi.entity.BookToLend;
import com.bookApi.repository.BookToLendRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bookApi.config.ExternalApiConfig;

@Service
public class BookToLendService {
	
    private final ExternalApiConfig externalApiConfig;
	
    @Autowired
    private BookToLendRepository bookToLendRepository;
    
    @Autowired
    public BookToLendService(ExternalApiConfig externalApiConfig) {
        this.externalApiConfig = externalApiConfig;
    }
    
//    **********************************************************************************************************
    
    public BookToLend getBookToLendByIsbn(String isbn) {
    	
        String apiKey = externalApiConfig.getKey();
        String url = String.format(apiKey, isbn);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, HashMap.class);

        if (response != null && response.containsKey("items")) {
            List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");
            if (items != null && !items.isEmpty()) {
                Map<String, Object> 
                volumeInfo = (Map<String, Object>) items.get(0).get("volumeInfo"),
                searchInfo= (Map<String, Object>) items.get(0).get("searchInfo");
                
                BookToLend bookToLend = new BookToLend();
                bookToLend.setIsbn(isbn);

                if (volumeInfo != null) {
                    bookToLend.setTitle((String) volumeInfo.get("title"));
                    bookToLend.setAuthors((List<String>) volumeInfo.get("authors"));
                    bookToLend.setEditor((String) volumeInfo.get("publisher"));
                    bookToLend.setNumberOfPages((Integer)volumeInfo.get("pageCount"));
                    bookToLend.setFormat((String)volumeInfo.get("printType"));
                    
                    String publicationDateString = (String) volumeInfo.get("publishedDate");
                    if (publicationDateString != null) {
                        try {
                            Date publicationDate = new SimpleDateFormat("yyyy-MM-dd").parse(publicationDateString);
                            bookToLend.setPublicationDate(publicationDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                }   
                if (searchInfo != null) {
                    bookToLend.setDescription((String) searchInfo.get("textSnippet"));                
                    }   
                return bookToLend;

            }
        }
        return null;
    }
    
//  **********************************************************************************************************

    public BookToLend saveBookToLend(BookToLend bookToLend) {
        return bookToLendRepository.save(bookToLend);
    }
    
}

