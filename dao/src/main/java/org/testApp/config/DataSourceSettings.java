package org.testApp.config;

import org.springframework.beans.factory.annotation.Value;

public class DataSourceSettings {

    @Value("${url}")
    private String url;
    @Value("${name}")
    private String name;
    @Value("${password}")
    private String password;
    @Value("${driver}")
    private String driver;

    public DataSourceSettings() {
    }


    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }
}
