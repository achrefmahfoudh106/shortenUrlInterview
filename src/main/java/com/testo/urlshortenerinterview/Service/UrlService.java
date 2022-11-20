package com.testo.urlshortenerinterview.Service;


import com.testo.urlshortenerinterview.DTO.UrlDto;
import com.testo.urlshortenerinterview.Models.URL;
import com.testo.urlshortenerinterview.Repository.UrlRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class UrlService {

    private  UrlRepository urlRepository;
    private  BaseConversion conversion;

    public String convertToShortUrl(UrlDto request) {
        var url = new URL();
        url.setLongUrl(request.getLongUrl());
        url.setExpiresDate(request.getExpiresDate());
        //url.setCreatedDate(new Date());
        var entity = urlRepository.save(url);

        return conversion.encode(entity.getId());
    }

    public String getOriginalUrl(String shortUrl) {
        var id = conversion.decode(shortUrl);
        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        if (entity.getExpiresDate() != null && entity.getExpiresDate().before(new Date())){
            urlRepository.delete(entity);
            throw new EntityNotFoundException("Link expired!");
        }

        return entity.getLongUrl();
    }
}