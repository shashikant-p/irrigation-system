package com.example.irrigationsystem.notifications.impl;

import org.springframework.stereotype.Service;

import com.example.irrigationsystem.notifications.Notification;
import com.example.irrigationsystem.notifications.NotificationProvider;

@Service("consoleNotifier")
public class ConsoleNotificationProvider implements NotificationProvider {

	@Override
	public void sendNotification(Notification notification) {
		System.out.println("=== Notification Received ===");
		System.out.println(notification);
	}

}
