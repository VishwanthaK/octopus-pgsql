package com.octopus.service.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octopus.service.domain.model.AppSettings;

public interface AppSettingsRepository extends JpaRepository<AppSettings, Long> {

    AppSettings findOneByRecordStatus(final Integer recordStatus);
}
