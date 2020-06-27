package com.BlogBoot.repository;

import com.BlogBoot.model.GlobalSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalSettingRepository extends JpaRepository<GlobalSettings, Integer> {
}
