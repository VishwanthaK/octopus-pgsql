package com.octopus.service.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.octopus.service.domain.BaseModel;

@Entity
@Table(name = "octopus_settings")
@JsonFilter("OCTOPUS_SETTINGS_FILTER")
public class OctopusSettings extends BaseModel {

    private static final long serialVersionUID = -5346233042355041878L;

    private String settingKey;
    private String settingValue;

    @Column(name = "setting_key")
    public String getSettingKey() {
        return settingKey;
    }

    public void setSettingKey(String settingKey) {
        this.settingKey = settingKey;
    }

    @Column(name = "setting_value")
    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
}
