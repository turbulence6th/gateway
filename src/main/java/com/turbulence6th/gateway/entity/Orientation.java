package com.turbulence6th.gateway.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Orientation {

    @Id
    private Long id;
    private String domain;
    private String url;
}
