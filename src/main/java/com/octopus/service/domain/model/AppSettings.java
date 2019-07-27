package com.octopus.service.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.octopus.service.domain.BaseModel;

@Entity
@Table(name = "app_settings")
public class AppSettings extends BaseModel {

    private String settings;

    @Column(name = "settings")
    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }
}
