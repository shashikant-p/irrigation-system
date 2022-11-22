DROP TABLE IF EXISTS `irrigation_schedule`;

DROP TABLE IF EXISTS `plot_config`;

DROP TABLE IF EXISTS `plot`;

CREATE TABLE `plot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `area` decimal(10,2) NOT NULL,
  `city` varchar(255) NOT NULL,
  `plot_id` varchar(36) NOT NULL,
  `province` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pid_1` (`plot_id`)
)
ENGINE=InnoDB;

CREATE TABLE `plot_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `crop` varchar(50) DEFAULT NULL,
  `frequency` varchar(255) DEFAULT NULL,
  `plot_config_id` varchar(36) NOT NULL,
  `start_time` time DEFAULT NULL,
  `duration` bigint(20) NOT NULL,
  `water_quantity` bigint(20) NOT NULL,
  `plot_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pcid_2` (`plot_config_id`),
  KEY `FK_pid_2` (`plot_id`),
  CONSTRAINT `FK_pid_2` FOREIGN KEY (`plot_id`) REFERENCES `plot` (`id`)	
) 
ENGINE=InnoDB;

CREATE TABLE `irrigation_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `end_time` time NOT NULL,
  `irrigation_status` varchar(255) NOT NULL,
  `start_time` time NOT NULL,
  `water_quantity` int(11) NOT NULL,
  `plot_id` bigint(20) DEFAULT NULL,
  `plot_config_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pid_3` (`plot_id`),
  KEY `FK_pcid_3` (`plot_config_id`),
  CONSTRAINT `FK_pid_3` FOREIGN KEY (`plot_id`) REFERENCES `plot` (`id`),
  CONSTRAINT `FK_pcid_3` FOREIGN KEY (`plot_config_id`) REFERENCES `plot_config` (`id`)
) 
ENGINE=InnoDB;



