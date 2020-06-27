package com.BlogBoot.repository;

import com.BlogBoot.model.CaptchaCodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaptchaCodesRepository extends JpaRepository<CaptchaCodes, Integer> {
}
