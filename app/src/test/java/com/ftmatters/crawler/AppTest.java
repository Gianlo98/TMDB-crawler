package com.ftmatters.crawler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {
    @Test void appHasAGreeting() {
        Crawler classUnderTest = new Crawler();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
