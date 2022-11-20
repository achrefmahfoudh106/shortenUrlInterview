package com.testo.urlshortenerinterview;

import com.testo.urlshortenerinterview.DTO.UrlDto;
import com.testo.urlshortenerinterview.Models.URL;
import com.testo.urlshortenerinterview.Repository.UrlRepository;
import com.testo.urlshortenerinterview.Service.BaseConversion;
import com.testo.urlshortenerinterview.Service.UrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UrlTest {
    @RunWith(MockitoJUnitRunner.class)
    public class UrlServiceTest {

        @Mock
        UrlRepository mockUrlRepository;

        @Mock
        BaseConversion mockBaseConversion;

        @InjectMocks
        UrlService urlService;

        @Test
        public void convertToShortUrlTest() {
            var url = new URL();
            url.setLongUrl("https://github.com/AnteMarin/UrlShortener-API");
            url.setCreatedDate(new Date());
            url.setId(5);

            when(mockUrlRepository.save(any(URL.class))).thenReturn(url);
            when(mockBaseConversion.encode(url.getId())).thenReturn("f");

            var urlRequest = new UrlDto();
            urlRequest.setLongUrl("https://github.com/achrefmahfoudh106/FrontHuntkingdom_ultimateTeam");

            assertEquals("f", urlService.convertToShortUrl(urlRequest));
        }

        @Test
        public void getOriginalUrlTest() {
            when(mockBaseConversion.decode("h")).thenReturn((long) 7);

            var url = new URL();
            url.setLongUrl("https://github.com/achrefmahfoudh106/FrontHuntkingdom_ultimateTeam");
            url.setCreatedDate(new Date());
            url.setId(7);

            when(mockUrlRepository.findById((long) 7)).thenReturn(java.util.Optional.of(url));
            assertEquals("https://github.com/AnteMarin/UrlShortener-API", urlService.getOriginalUrl("h"));

        }
    }
}
