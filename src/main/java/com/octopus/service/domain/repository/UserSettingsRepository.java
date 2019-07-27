package com.octopus.service.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.octopus.service.domain.model.UserSettings;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {

    @Query(" SELECT us from UserSettings us "
         + " JOIN us.user u "
         + " WHERE us.recordStatus = 1 AND u.id = ?1 ")
    UserSettings getActiveUserSettings(final Long userId);
}
