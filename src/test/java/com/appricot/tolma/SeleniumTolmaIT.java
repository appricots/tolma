package com.appricot.tolma;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ITConfig.class)
@SpringBootTest
@Slf4j
public class SeleniumTolmaIT {

        @Autowired
        private WebDriver webDriver;

        @Autowired
        private String baseUrl;

        @Test
        public void visitIndexPage() throws Exception {
            log.debug("Doing test visitIndexPage with baseURL {}", baseUrl);
            webDriver.get(baseUrl);
            WebElement working = webDriver.findElement(By.id("working"));

            Assert.assertThat(working.getText(), is(equalTo("Barev ape. THis will fail")));
        }
    }