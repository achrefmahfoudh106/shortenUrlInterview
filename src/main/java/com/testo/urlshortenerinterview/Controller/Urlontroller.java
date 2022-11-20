package com.testo.urlshortenerinterview.Controller;

import com.testo.urlshortenerinterview.DTO.UrlDto;
import com.testo.urlshortenerinterview.Service.UrlService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/")
public class Urlontroller {

    private UrlService urlService;


    //this method converts long url to short url
    @PostMapping("createUrl")
    public String convertToShortUrl(@RequestBody UrlDto request) {
        return urlService.convertToShortUrl(request);
    }

    //this method finds original url from short url and redirects
    @GetMapping(value = "{getShortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        var url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

}
