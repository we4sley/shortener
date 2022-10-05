package com.toyprj.shortener.domain.repository;

import com.toyprj.shortener.domain.UrlLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UrlRepositoryTest {

    @Autowired
    private UrlRepository urlRepository;

    private UrlLine urlLine;

    @BeforeEach
    void setUp() {
        urlLine = UrlLine.builder()
                .id(1L)
                .shortUrl("shortUrl")
                .originUrl("originUrl")
                .build();

        urlRepository.save(urlLine);
    }

    @Test
    void 조회_성공() {
        UrlLine foundUrl = urlRepository.findById(1L).orElseThrow(RuntimeException::new);

        assertThat(foundUrl.getOriginUrl()).isEqualTo(urlLine.getOriginUrl());
    }
}