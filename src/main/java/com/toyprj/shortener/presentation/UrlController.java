package com.toyprj.shortener.presentation;

import com.toyprj.shortener.application.UrlService;
import com.toyprj.shortener.domain.dto.UrlRequestDto;
import com.toyprj.shortener.domain.dto.UrlRevertRequestDto;
import com.toyprj.shortener.presentation.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:63342"})
@RequiredArgsConstructor
@RestController
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/api/shorten")
    public ApiResponse shortenUrl(@RequestBody UrlRequestDto urlRequestDto) {
        return ApiResponse.success(HttpStatus.OK, "localhost:8080/" + urlService.shortenUrl(urlRequestDto));
    }

    @GetMapping("/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        RedirectView redirectView = new RedirectView(urlService.getOriginUrl(shortUrl));
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }

    @PostMapping("/api/origin")
    public ApiResponse getOriginUrl(@RequestBody UrlRevertRequestDto urlRevertRequestDto) {
        return ApiResponse.success(HttpStatus.OK, urlService.revertUrl(urlRevertRequestDto));
    }
}
