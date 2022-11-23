package com.example.irrigationsystem.notifications.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.irrigationsystem.notifications.Notification;
import com.example.irrigationsystem.notifications.NotificationProvider;

/**
 * 
 * This is a placeholder/stub class for email notifications.
 *
 */
@Service("emailNotifier")
public class EmailNotificationProvider implements NotificationProvider {

	private static final Logger logger = LoggerFactory.getLogger(EmailNotificationProvider.class);

	@Override
	public void sendNotification(Notification notification) {
		logger.info("=== Notification Received ===");
		logger.info(notification.toString());

		// Logic for sending email to be implemented here
	}

}
