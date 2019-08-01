
CREATE DATABASE /*!32312 IF NOT EXISTS*/`BOOK_API` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `BOOK_API`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(100) DEFAULT NULL,
  `book_author` varchar(100) DEFAULT NULL,
  `book_price` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `book_name` (`book_name`)
) ;

/*Testing Data for the table `book` */

insert  into `book`(`book_id`,`book_name`,`book_author`,`book_price`) values (1,'BrooksJava','David','1500.08'),(2,'JavaEE','Johnson','1250.10'),(3,'JavaSpring','Rajat','100.00');

