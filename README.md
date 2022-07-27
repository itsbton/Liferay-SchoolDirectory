##SQL

CREATE TABLE `schooldirectory`.`educationalservicedistricts` (
  `idEducationalServiceDistricts` INT NOT NULL,
  `addressLine1` VARCHAR(200) NULL,
  `addressLine2` VARCHAR(200) NULL,
  `code` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zipCode` VARCHAR(45) NULL,
  `administratorName` VARCHAR(45) NULL,
  PRIMARY KEY (`idEducationalServiceDistricts`));
  
  CREATE TABLE `schooldirectory`.`districts` (
  `iddistricts` INT NOT NULL AUTO_INCREMENT,
  `addressLine1` VARCHAR(200) NULL,
  `addressLine2` VARCHAR(200) NULL,
  `code` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `zipCode` VARCHAR(45) NULL,
  `administratorName` VARCHAR(45) NULL,
  `esdCode` VARCHAR(45) NULL,
  `esdName` VARCHAR(45) NULL,
  PRIMARY KEY (`iddistricts`));
  
  CREATE TABLE `schooldirectory`.`schools` (
  `idschools` INT NOT NULL AUTO_INCREMENT,
  `addressLine1` VARCHAR(200) NULL,
  `addressLine2` VARCHAR(200) NULL,
  `code` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `zipCode` VARCHAR(45) NULL,
  `aypCode` VARCHAR(45) NULL,
  `esdCode` VARCHAR(45) NULL,
  `esdName` VARCHAR(45) NULL,
  `gradeCategory` VARCHAR(45) NULL,
  `highestGrade` VARCHAR(45) NULL,
  `leaCode` VARCHAR(45) NULL,
  `leaName` VARCHAR(45) NULL,
  `lowestGrade` VARCHAR(45) NULL,
  `orgCategoryList` VARCHAR(45) NULL,
  `principalName` VARCHAR(45) NULL,
  PRIMARY KEY (`idschools`));