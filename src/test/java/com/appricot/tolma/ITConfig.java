package com.appricot.tolma;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@Slf4j
public class ITConfig {

    @Autowired
    private Environment env;

    @Bean
    public WebDriver webDriver() throws MalformedURLException {
        log.info("Loading ITConfig");


        //        FirefoxOptions options = new FirefoxOptions();
//        options.setCapability("marionette", false);
//        WebDriver webDriver = new FirefoxDriver(options);
//
////        newProfile.setCapability("dom.max_chrome_script_run_time", "999");
////        newProfile.setCapability("dom.max_script_run_time", "999");


        String gridNodeUrl = env.getProperty("gridnode.base.url");

        if (gridNodeUrl == null) {
            gridNodeUrl = "http://localhost:9515";
            log.debug("========Testing Locally=========");
            WebDriver driver = new RemoteWebDriver(new URL(gridNodeUrl), new ChromeOptions());
            log.debug("Test locally with hub at: {}", gridNodeUrl);
            return driver;
        } else {
            log.debug("========Testing Remotely=========");
            WebDriver driver = new RemoteWebDriver(new URL(gridNodeUrl), new FirefoxOptions());
            log.debug("Test remote with hub at: {}", gridNodeUrl);
            return driver;
        }
    }

    @Bean
    public String baseURL() {
        String baseUrl = env.getProperty("webdriver.base.url");
        if (StringUtils.isEmpty(baseUrl)) {
            baseUrl = "http://localhost:8080";
        }
        return baseUrl;
    }

    private DesiredCapabilities getDesiredCapabilities() {
        return DesiredCapabilities.firefox();
    }

    private URL getRemoteUrl() throws MalformedURLException {
        return new URL("http://localhost:4444/wd/hub");
    }
}