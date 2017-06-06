-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema GitStacked
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `GitStacked` ;

-- -----------------------------------------------------
-- Schema GitStacked
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `GitStacked` DEFAULT CHARACTER SET utf8 ;
USE `GitStacked` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `weight` INT NOT NULL,
  `heightFeet` INT NOT NULL,
  `heightInch` INT NOT NULL,
  `username` VARCHAR(40) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `usertype` ENUM('USER', 'ADMIN') NOT NULL COMMENT 'user type is used as ‘admin’ or ‘normal user’, where admin can edit exercises.\n\n',
  `lName` VARCHAR(100) NOT NULL,
  `fName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `workout`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workout` ;

CREATE TABLE IF NOT EXISTS `workout` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  `userId` INT NOT NULL,
  INDEX `fk_workout_user1_idx` (`userId` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `type` ;

CREATE TABLE IF NOT EXISTS `type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category` ENUM('CARDIO', 'WEIGHTS') NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `muscleGroup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `muscleGroup` ;

CREATE TABLE IF NOT EXISTS `muscleGroup` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category` ENUM('CHEST', 'BACK', 'LEGS', 'ARMS', 'ABS') NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exercise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exercise` ;

CREATE TABLE IF NOT EXISTS `exercise` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `desc` VARCHAR(1000) NULL,
  `imageUrl` VARCHAR(500) NULL,
  `calories` INT NULL,
  `muscleGroup` ENUM('ARMS', 'LEGS', 'ABS', 'CHEST', 'BACK', 'SHOULDERS') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `workoutExercise`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workoutExercise` ;

CREATE TABLE IF NOT EXISTS `workoutExercise` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reps` INT NULL,
  `duration` INT UNSIGNED NULL,
  `date` DATE NULL,
  `weight` INT NULL,
  `typeId` INT NULL,
  `workoutId` INT NOT NULL,
  `exerciseId` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_workout_exercise_type1_idx` (`typeId` ASC),
  INDEX `fk_workout_exercise_workout1_idx` (`workoutId` ASC),
  INDEX `fk_workout_exercise_exercise1_idx` (`exerciseId` ASC))
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO admin@localhost;
 DROP USER admin@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'gitstacked';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'admin'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `GitStacked`;
INSERT INTO `user` (`id`, `weight`, `heightFeet`, `heightInch`, `username`, `password`, `usertype`, `lName`, `fName`) VALUES (1, 230, 6, 6, 'spop', 'password', 'USER', 'Popinski', 'Soda');
INSERT INTO `user` (`id`, `weight`, `heightFeet`, `heightInch`, `username`, `password`, `usertype`, `lName`, `fName`) VALUES (2, 1, 1, 2, 'gjaw', 'password', 'ADMIN', 'Joe', 'Glass');

COMMIT;


-- -----------------------------------------------------
-- Data for table `exercise`
-- -----------------------------------------------------
START TRANSACTION;
USE `GitStacked`;
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (1, 'Bench Press Narrow Grip', 'Lay down on a bench, ensuring the bar is directly over your eyes.  Your knees should form a slight angle while your feet remain firmly on the ground. Hold the bar with a narrow grip (around 20cm.). Lead the weight slowly down till the arms are parallel to the floor (the elbow is at a right angle), then press then the bar up. When bringing the bar down| don\'t let it down on your nipples as with the regular bench pressing| but somewhat lower.', 'https://wger.de/media/exercise-images/88/Narrow-grip-bench-press-1.png.200x200_q85.png', 20, 'ARMS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (2, 'Biceps Curl With Cable', 'Stand around 30 - 40cm away from the cable, with your feet firmly on the floor. Take the bar and lift the weight with a fast movement. Lower the weight as slowly and then slowy slowly the dumbbell back up', 'https://wger.de/media/exercise-images/129/Standing-biceps-curl-2.png.200x200_q85.png', 15, 'ARMS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (3, 'Biceps Curls With Barbell', 'Hold the Barbell shoulder-wide while keeping your back straight, with shoulders slightly back, and your arms are streched. Bend your arms, bringing the weight up with a fast movement. Without pausing, let down the bar with a slow and controlled movement.', 'https://wger.de/media/exercise-images/74/Bicep-curls-2.png.200x200_q85.png', 15, 'ARMS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (4, 'Biceps Curls With Dumbbell', 'Hold two barbells, with arms streched, your hands are on your side, and palms facing inwards. Bend the arms and bring the weight up with a fast movement. At the same time, rotate your arms by 90 degrees at the very beginning of the movement. At the highest point, rotate a the weights slightly outwards. Without a pause, bring them down slowly.', 'https://wger.de/media/exercise-images/81/Biceps-curl-2.png.200x200_q85.png', 15, 'ARMS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (5, 'Dips', 'Hold onto the bars at a narrow place (if they are not parallel) and press yourself up, but don\'t stretch your arms completely (don\'t lock your arms out).  The muscles should stay taunt whole exercise. Now bend the arms and go down as much as you can, keeping the elbows pointing back. You can pause at the top or bottom but do not lock the elbows out.', 'https://wger.de/media/exercise-images/82/Tricep-dips-2-2.png.200x200_q85.png', 15, 'ARMS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (6, 'Dips Between Two Benches', 'Put two benches so far apart, that you can hold onto one with your hands and are just able to reach the other with your feet. The legs should reamin fully extended throughout the exercise. With your elbows facing back, bend them as much as you can. Push yourself up, but don\'t lock out the arms.', 'https://wger.de/media/exercise-images/83/Bench-dips-2.png.200x200_q85.png', 15, 'ARMS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (7, 'French Press (skullcrusher) SZ-bar', 'Hold the SZ-bar and lay down on a flat bench in such a way that around 1/4 of your head is over the edge. Stretch your arms with the bar and bend them so that the bar is lowered. Just before it touches your forehead, push it up.', 'https://wger.de/media/exercise-images/84/Lying-close-grip-triceps-press-to-chin-1.png.200x200_q85.png', 15, 'ARMS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (8, 'Hammercurls', 'Hold two dumbbells and sit on a bench with a straight back, the shoulders are slightly rolled backwards. Bend the arms and bring the weight up with a fast movement. Without any pause bring the dumbbell down with a slow, controlled movement', 'https://wger.de/media/exercise-images/86/Bicep-hammer-curl-2.png.200x200_q85.png', 15, 'ARMS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (9, 'Hammercurls on Cable', 'Take a cable in your hands (palms parallel and point to each other) and the body straight. Bend the arms and bring the weight up with a fast movement. Without any pause bring it back down with a slow, controlled movement|, but don\'t stretch completely your arms.', 'https://wger.de/media/exercise-images/138/Hammer-curls-with-rope-2.png.200x200_q85.png', 15, 'ARMS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (10, 'Preacher Curls', 'Place the EZ curl bar on the rest handles in front of the preacher bench.  Lean over the bench and grab the EZ curl bar with palms up.  Sit down on the preacher bench seat so your upper arms rest on top of the pad and your chest is pressed against the pad. Lower the weight until your elbows are extended and arms are straight. Bring the weights back up to the starting point by contracting biceps. Repeat', 'https://wger.de/media/exercise-images/193/Preacher-curl-3-2.png.200x200_q85.png', 15, 'ARMS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (11, 'Push Ups', 'Start with your body streched and your hands shoulder-wide appart on the ground. Push yourself off the ground till you strech your arms. The back and neck should always be straight. Lower yourself to the initial position and repeat.', 'https://wger.de/media/exercise-images/195/Push-ups-1.png.200x200_q85.png', 15, 'ARMS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (12, 'Dumbbell Lunges Walking', 'Take two dumbbells in your hands, standing straight, with feet about shoulder width apart. Take one long step so that the front knee is approximately forming a right angle. The back leg is streched, the knee is low but doesn\'t touch the ground. \"Complete\" the step by standing up and repeat the movement with the other leg.', 'https://wger.de/media/exercise-images/113/Walking-lunges-4.png.200x200_q85.jpg', 15, 'LEGS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (13, 'Front Squats', 'From rack with barbell upper chest height, position bar in front of shoulders. Cross arms and place hands on top of barbell with upper arms parallel to floor. Dismount bar from rack. Squat down by bending hips back while allowing knees to bend forward, keeping your back straight and knees pointed the same direction as your feet.', 'https://wger.de/media/exercise-images/191/Front-squat-2-857x1024.png.200x200_q85.png', 15, 'LEGS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (14, 'Good Morning', 'Keep your back tight, shoulder blades pinched together, and your knees slightly bent. Begin by bending at the hips, moving them back as you bend over to near parallel. Keep your back arched and your cervical spine in proper alignment. Reverse the motion by extending through the hips with your glutes and hamstrings.', 'https://wger.de/media/exercise-images/116/Good-mornings-1.png.200x200_q85.png', 15, 'LEGS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (15, 'Leg Curls (Laying)', 'Lay on a bench and put your calves behind the leg holder (better if they are hold on around the lower calves). Hold a grip on the bars to make sure the body is firmly in place. Bend your legs bringing the weight up, go slowly back. During the exercise the body should not move| all work is done by the legs.', 'https://wger.de/media/exercise-images/154/lying-leg-curl-machine-large-2.png.200x200_q85.jpg', 15, 'LEGS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (16, 'Leg Curls (Sitting)', 'Sit on bench and put your calves in front of the leg holder (better if they are hold on around the lower calves). Hold a grip on the bars to make sure the body is firmly in place. Bend your legs bringing the weight up, go slowly back. During the exercise the body should not move| all work is done by the legs.', 'https://wger.de/media/exercise-images/117/seated-leg-curl-large-2.png.200x200_q85.jpg', 15, 'LEGS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (17, 'Leg Extension', 'Leg extensions is a resistance weight training exercise that targets the quadriceps muscle in the legs. The exercise is done using a machine called the Leg ExtensionMachine. The exercise consists of bending the leg at the knee and extending the legs then lowering them back to the original position.', 'https://wger.de/media/exercise-images/177/Seated-leg-curl-2.png.200x200_q85.jpg', 15, 'LEGS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (18, 'Leg Press on Hackenschmidt Machine', 'Place feet on bottom of platform and stand.  Repeat till huge.', 'https://wger.de/media/exercise-images/130/Narrow-stance-hack-squats-2-1024x721.png.200x200_q85.png', 15, 'LEGS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (19, 'Squats', 'Make sure you have put the barbell at a height where you can comfortably take it out and put it back in. The bar should be somewhat lower than your shoulders, with your feet are shoulder width apart and pointing out. ', 'https://wger.de/media/exercise-images/111/Wide-stance-squat-1.gif.200x200_q85.jpg', 15, 'LEGS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (20, 'Crunches', 'Lay down on your back on a soft surface, with your feet on the floor.  From this position move your upper body up till your head or elbows touch your knees. Do this movement by rolling up your back.', 'https://wger.de/media/exercise-images/91/Crunches-2.png.200x200_q85.png', 15, 'ABS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (21, 'Lying Leg Raises', 'Lay down on a bench and hold onto the chair with your hands to keep you stable. Hold your legs straight and lift them till they make an angle of about 45. Make a short pause of 1 sec. and go slowly down to the initial position. To increase the intensity you can make a longer pause of 7 sec. every 5th time.', 'https://wger.de/media/exercise-images/125/Leg-raises-1.png.200x200_q85.png', 15, 'ABS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (22, 'Negative Crunches', 'Sit yourself on the decline bench and fix your legs. Cross your arms over the chest and bring with a rolling movement your upper body up, go now without a pause and with a slow movement down again. Don\'t let your head move during the exercise.', 'https://wger.de/media/exercise-images/93/Decline-crunch-2.png.200x200_q85.png', 15, 'ABS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (23, 'Side Crunch', 'Hold weight in one hand. Bend side ways to the knee. Pull up to upright position using your obliquus.', 'https://wger.de/media/exercise-images/176/Cross-body-crunch-2.png.200x200_q85.png', 15, 'ABS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (24, 'Bench Press', 'Lay down on a bench. The bar should be directly above your eyes,  your knees should be somewhat angled with your feet firmly on the floor. Concentrate, breath deeply and grab the bar more than shoulder wide. Bring it slowly down till it briefly touches your chest at the height of your nipples. Push the bar up. If you train with a high weight it is advisable to have a spotter that can help you up if you can\'t lift the weight on your own.  With the width of the grip you can also control which part of the chest is trained more by using a wide grip: outer chest muscles or narrow grip: inner chest muscles and triceps|', 'https://wger.de/media/exercise-images/192/Bench-press-2.png.200x200_q85.png', 15, 'CHEST');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (25, 'Benchpress Dumbbells', 'The movement is very similar to benchpressing with a barbell, however, the weight is brought down to the chest at a lower point.  Hold two dumbbells and lay down on a bench. Hold the weights next to the chest, at the height of your nipples and press them up till the arms are stretched. Let the weight slowly and controlled down.', 'https://wger.de/media/exercise-images/97/Dumbbell-bench-press-2.png.200x200_q85.jpg', 15, 'CHEST');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (26, 'Butterfly', 'Sit on the butterfly machine, with your feet firmly on the floor, and the upper arms parallel to the floor. Press your arms together till the handles are practically together (but aren\'t!). Go slowly back. The weights should stay all the time in the air.', 'https://wger.de/media/exercise-images/98/Butterfly-machine-1.png.200x200_q85.jpg', 15, 'CHEST');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (27, 'Decline Bench Press Barbell', 'Lay down on a decline bench, the bar should be directly above your eyes, with your knees somewhat angled and your feet firmly on the floor. Concentrate, breath deeply and grab the bar more than shoulder wide. Bring it slowly down till it briefly touches your chest at the height of your nipples. Push the bar up.', 'https://wger.de/media/exercise-images/100/Decline-bench-press-2.png.200x200_q85.jpg', 15, 'CHEST');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (28, 'Fly With Cable', 'With a slight bend on your elbows in order to prevent stress at the biceps tendon, extend your arms to the side (straight out at both sides) in a wide arc until you feel a stretch on your chest. Breathe in as you perform this portion of the movement. Tip: Keep in mind that throughout the movement| the arms and torso should remain stationary; the movement should only occur at the shoulder joint.', 'https://wger.de/media/exercise-images/122/Incline-cable-flyes-2.png.200x200_q85.jpg', 15, 'CHEST');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (29, 'Incline Bench Press', 'The incline barbell bench press is an upper body strength exercise that targets the chest, shoulder, and triceps. Performing this move on an incline allows for targeted emphasis on the upper portion of the chest. The incline also makes it safer and more joint-friendly for your shoulders.', 'https://wger.de/media/exercise-images/163/Incline-bench-press-2.png.200x200_q85.jpg', 15, 'CHEST');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (30, 'Bent Over Rowing', 'Grab the barbell with a wide grip (slightly more than shoulder wide) and lean forward. Your upper body is not quite parallel to the floor| but forms a slight angle. The chest\'s out during the whole exercise.  Pull now the barbell with a fast movement towards your belly button| not further up. Go slowly down to the initial position. Don\'t swing with your body and keep your arms next to your body.', 'https://wger.de/media/exercise-images/109/Barbell-rear-delt-row-2.png.200x200_q85.jpg', 15, 'BACK');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (31, 'Bent Over Row Reverse', 'The same as regular rowing, but holding a reversed grip (your palms pointing forwards).  Grab the barbell with a wide grIp (slightly more than shoulder wide) and lean forward. Your upper body is not quite parallel to the floor, but forms a slight angle. The chest\'s out during the whole exercise.  Pull now the barbell with a fast movement towards your belly button, not further up. Go slowly down to the initial position. Don\'t swing with your body and keep your arms next to your body.', 'https://wger.de/media/exercise-images/110/Reverse-grip-bent-over-rows-2.png.200x200_q85.jpg', 15, 'BACK');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (32, 'Chin-ups', 'Like pull-ups but with a reverse grip', 'https://wger.de/media/exercise-images/181/Chin-ups-1.png.200x200_q85.png', 15, 'BACK');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (33, 'Hyperextensions', 'Lay on the hyperextension pad with the belly button just at the leading edge, the upper body can hang freely. Tense your whole back\'s muscles and bring your upper body up till it is horizontal, but not more. Go slowly down and don\'t relax your muscles.', 'https://wger.de/media/exercise-images/128/Hyperextensions-2.png.200x200_q85.png', 15, 'BACK');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (34, 'Long-Pulley (low Row)', 'Sit down, put your feet on the supporting points and grab the bar with a wide grip. Pull the weight with a rapid movement towards your belly button, not upper. Keep your arms and elbows during the movement close to your body. Your shoulders are pulled together. Let the weight slowly down till your arms are completely stretched.', 'https://wger.de/media/exercise-images/143/Cable-seated-rows-1.png.200x200_q85.jpg', 15, 'BACK');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (35, 'Rack Deadlift', 'Deadlift to be done using a Smith machine or a free rack. Bar or barbell hould be just right under the knee cap level. Lift using the glutes and through the heels, then come back to starting postion with a control movement of 2 seconds.  This exercise targets mainly the lower back and glutes.', 'https://wger.de/media/exercise-images/161/Dead-lifts-1.png.200x200_q85.jpg', 15, 'BACK');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (36, 'Rowing| T-bar', 'The execution of this exercise is very similar to the regular bent over rowing, only that the bar is fixed here.  Grab the barbell with a wide grip (slightly more than shoulder wide) and lean forward. Your upper body is not quite parallel to the floor| but forms a slight angle. The chest\'s out during the whole exercise. Pull now the barbell with a fast movement towards your belly button| not further up. Go slowly down to the initial position. Don\'t swing with your body and keep your arms next to your body.', 'https://wger.de/media/exercise-images/106/T-bar-row-2.png.200x200_q85.png', 15, 'BACK');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (37, 'Lateral Raises', 'Start in a standing position - with your feet firmly placed for balance and grasp a dumbbell in each hand. Keep the dumbbells at hanging straight down at your sides. Simultaneously, raise the dumbbells directly to your sides. Be sure to turn your palm facing down, and have your elbows slightly bent.', 'https://wger.de/media/exercise-images/148/lateral-dumbbell-raises-large-1.png.200x200_q85.jpg', 15, 'SHOULDERS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (38, 'Shoulder Press| Barbell', 'Sit on a bench, your back rest should be almost vertical. Take a barbell with a shoulder wide grip and bring it up to chest height. Press the weight up, but don\'t stretch the arms completely. Go slowly down and repeat.', 'https://wger.de/media/exercise-images/119/seated-barbell-shoulder-press-large-2.png.200x200_q85.jpg', 15, 'SHOULDERS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (39, 'Shoulder Press| Dumbbells', 'Sit on a bench, your back rest should be almost vertical. Take two dumbbells and bring them up to shoulder height, the palms and the elbows point during the whole exercise to the front. Press the weights up, at the highest point they come very near but don\'t touch. Go slowly down and repeat.', 'https://wger.de/media/exercise-images/123/dumbbell-shoulder-press-large-2.png.200x200_q85.jpg', 15, 'SHOULDERS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (40, 'Shoulder Press| on Machine', 'Sit down on the Shoulder Press Machine and select the weight. Grab the handles to your sides as you keep the elbows bent and in line with your torso.', 'https://wger.de/media/exercise-images/152/seated-shoulder-press-machine-large-2.png.200x200_q85.jpg', 15, 'SHOULDERS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (41, 'Shrugs| Barbells', 'Take a barbell and stand with a straight body, the arms are hanging freely in front of you. Lift from this position the shoulders as high as you can, but don\'t bend the arms during the movement. On the highest point, make a short pause of 1 or 2 seconds before returning slowly to the initial position.', 'https://wger.de/media/exercise-images/150/Barbell-shrugs-2.png.200x200_q85.png', 15, 'SHOULDERS');
INSERT INTO `exercise` (`id`, `name`, `desc`, `imageUrl`, `calories`, `muscleGroup`) VALUES (42, 'Shrugs| Dumbbells', 'Stand with youe body straight, the hands hanging freely on the side and hold each a dumbbell. Lift from this position the shoulders as high as you can| but don\'t bend the arms during the movement. On the highest point, make a short pause of 1 or 2 seconds before returning slowly to the initial position.', 'https://wger.de/media/exercise-images/151/Dumbbell-shrugs-1.png.200x200_q85.jpg', 15, 'SHOULDERS');

COMMIT;

