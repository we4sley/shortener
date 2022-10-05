package com.toyprj.shortener.application;

import com.toyprj.shortener.domain.UrlLine;
import com.toyprj.shortener.domain.dto.UrlRequestDto;
import com.toyprj.shortener.domain.dto.UrlRevertRequestDto;
import com.toyprj.shortener.domain.exception.AlreadyShortenedUrl;
import com.toyprj.shortener.domain.exception.NoSuchUrlExist;
import com.toyprj.shortener.domain.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.validator.routines.UrlValidator;

@RequiredArgsConstructor
@Service
public class UrlService {

    private final UrlRepository urlRepository;

    @Transactional
    public String shortenUrl(UrlRequestDto urlRequestDto) {

        String originUrl = isValidUrl(urlRequestDto.getOriginUrl());

        isShortened(originUrl);

        String shortUrl = Base62.encode(originUrl);

        urlRepository.save(UrlLine.builder()
                .originUrl(originUrl)
                .shortUrl(shortUrl)
                .build());

        return shortUrl;
    }

    public String getOriginUrl(String shortUrl) {
        UrlLine urlLine = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(NoSuchUrlExist::new);
        return urlLine.getOriginUrl();
    }

    public String revertUrl(UrlRevertRequestDto urlRevertRequestDto) {
        String shortUrl = urlRevertRequestDto.getShortUrl().trim().replace("localhost:8080/", "");
        UrlLine urlLine = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(NoSuchUrlExist::new);
        return urlLine.getOriginUrl();
    }

    private void isShortened(String originUrl) {
        if (originUrl.startsWith("localhost:8080/")) {
            throw new AlreadyShortenedUrl();
        }
    }

    private String isValidUrl(String originUrl) {
        UrlValidator urlValidator = new UrlValidator();

        if (urlValidator.isValid(originUrl)) {
            return originUrl;
        } else {
            return "https://" + originUrl;
        }
    }
}
