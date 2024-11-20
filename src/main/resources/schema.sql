CREATE TABLE IF NOT EXISTS `student` (
  `id` int PRIMARY KEY,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `level` varchar(100) NOT NULL,
  `gpa` double NOT NULL,
  `major` varchar(100) NOT NULL,
  `created_at` date DEFAULT NULL,
  `created_by` varchar(20) DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL
);