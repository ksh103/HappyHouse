CREATE TABLE `user` (
  `user_seq` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_id` varchar(100) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_level` int DEFAULT '2',
  PRIMARY KEY (`user_seq`,`user_id`),
  UNIQUE KEY `userEmail_UNIQUE` (`user_email`),
  UNIQUE KEY `userId_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;