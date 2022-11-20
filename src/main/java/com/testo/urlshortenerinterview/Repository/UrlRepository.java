package com.testo.urlshortenerinterview.Repository;


import com.testo.urlshortenerinterview.Models.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<URL, Long> {
}