CREATE TABLE `hcdb`.`doctordetail` (
  `drID` INT(10) NOT NULL,
  `drName` VARCHAR(45) NOT NULL,
  `drPwd` VARCHAR(45) NOT NULL,
  `drGender` VARCHAR(45) NOT NULL,
  `drEmail` VARCHAR(45) NOT NULL,
  `drAddress` VARCHAR(45) NOT NULL,
  `drPhone` INT(15) NOT NULL,
  PRIMARY KEY (`drID`));





ALTER TABLE `hcdb`.`doctordetail` 
CHANGE COLUMN `drID` `drID` INT(10) NOT NULL AUTO_INCREMENT ;