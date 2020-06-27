package com.BlogBoot.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "captcha_codes")
public class CaptchaCodes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private Byte code;

    @Column(name = "secret_code", nullable = false)
    private Byte secretCode;
}
