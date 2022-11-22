package com.example.irrigationsystem.common;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@CreatedDate
	private Date createdDate;

	@Getter
	@Setter
	@LastModifiedDate
	private Date modifiedDate;

}
