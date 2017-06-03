-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `weight` INT NOT NULL,
  `heightFeet` INT NOT NULL,
  `heightInch` INT NOT NULL,
  `username` VARCHAR(40) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `usertype` ENUM('USER', 'ADMIN') NOT NULL COMMENT 'user type is used as ‘admin’ or ‘normal user’, where admin can edit exercises.\n\n',
  `lName` VARCHAR(100) NOT NULL,
  `fName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`workout`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`workout` ;

CREATE TABLE IF NOT EXISTS `mydb`.`workout` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE INDEX `fk_workout_user1_idx` ON `mydb`.`workout` (`userId` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`type` ;

CREATE TABLE IF NOT EXISTS `mydb`.`type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category` ENUM('CARDIO', 'WEIGHTS') NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`muscleGroup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`muscleGroup` ;

CREATE TABLE IF NOT EXISTS `mydb`.`muscleGroup` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category` ENUM('CHEST', 'BACK', 'LEGS', 'ARMS', 'ABS') NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`exercise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`exercise` ;

CREATE TABLE IF NOT EXISTS `mydb`.`exercise` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `desc` VARCHAR(1000) NULL,
  `imageUrl` VARCHAR(500) NULL,
  `calories` INT NULL,
  `muscleGroup` ENUM('ARMS', 'LEGS', 'ABS', 'CHEST', 'BACK', 'SHOULDERS', 'CALVES') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE INDEX `fk_exercise_muscle_group1_idx` ON `mydb`.`exercise` (`muscleGroup` ASC);


-- -----------------------------------------------------
-- Table `mydb`.`workoutExercise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`workoutExercise` ;

CREATE TABLE IF NOT EXISTS `mydb`.`workoutExercise` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reps` INT NULL,
  `duration` INT UNSIGNED NULL,
  `date` DATE NOT NULL,
  `weight` INT NULL,
  `typeId` INT NOT NULL,
  `workoutId` INT NOT NULL,
  `exerciseId` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE INDEX `fk_workout_exercise_type1_idx` ON `mydb`.`workoutExercise` (`typeId` ASC);

CREATE INDEX `fk_workout_exercise_workout1_idx` ON `mydb`.`workoutExercise` (`workoutId` ASC);

CREATE INDEX `fk_workout_exercise_exercise1_idx` ON `mydb`.`workoutExercise` (`exerciseId` ASC);

SET SQL_MODE = '';
GRANT USAGE ON *.* TO admin@localhost;
 DROP USER admin@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'gitstacked';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `mydb`.* TO 'admin'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
