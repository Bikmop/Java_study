package com.bikmop.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class SettingsTest {
    final Settings settings = Settings.getInstance();

    @Test
    public void testGetValue() throws Exception {
        assertNotNull(settings.getValue("jdbc.url"));
        assertNotNull(settings.getValue("jdbc.driver_class"));
        assertNotNull(settings.getValue("jdbc.username"));
        assertNotNull(settings.getValue("jdbc.password"));
        assertNull(settings.getValue("jdbc.something"));
    }
}