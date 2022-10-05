package com.toyprj.shortener.domain.repository;

import com.toyprj.shortener.domain.UrlLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlLine, Long> {

    Optional<UrlLine> findByShortUrl(String shortUrl);
}
