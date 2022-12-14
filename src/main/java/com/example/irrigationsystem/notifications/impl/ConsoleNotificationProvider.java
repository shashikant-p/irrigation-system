package com.example.irrigationsystem.notifications.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.irrigationsystem.notifications.Notification;
import com.example.irrigationsystem.notifications.NotificationProvider;

/**
 * 
 * This is class which logs notifications and alerts to log file.
 *
 */
@Service("consoleNotifier")
public class ConsoleNotificationProvider implements NotificationProvider {

	private static final Logger logger = LoggerFactory.getLogger(ConsoleNotificationProvider.class);

	@Override
	public void sendNotification(Notification notification) {
		logger.info("=== Notification Received ===");
		logger.info(notification.toString());
	}

}
