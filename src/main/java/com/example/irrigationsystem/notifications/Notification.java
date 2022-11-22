package com.example.irrigationsystem.notifications;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Notification {

	String title;
	String subject;
	String message;
}
