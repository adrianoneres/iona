package com.iona.core.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggerProducer {
   
	@Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        Logger logger = Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
        setLoggerLevel(logger);
        return logger;
    }
	
	private void setLoggerLevel(Logger logger) {
		logger.setLevel(Level.toLevel("INFO")); //TODO Adriano 03/01/2017 - Recover from a environment or wildfly defined variable
		if (logger.getLevel() == null) {
			logger.setLevel(Level.toLevel("INFO"));
		}
	}
}