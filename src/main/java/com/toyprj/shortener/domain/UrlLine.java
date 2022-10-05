package com.toyprj.shortener.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class UrlLine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originUrl;

    private String shortUrl;
}
