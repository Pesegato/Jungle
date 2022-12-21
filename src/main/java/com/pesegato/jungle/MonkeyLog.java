package com.pesegato.jungle;

public class MonkeyLog extends ch.qos.logback.core.spi.ContextAwareBase implements ch.qos.logback.core.spi.PropertyDefiner {

    public String getPropertyValue(){
        return Environment.getSystemGameFolder();
    }
}