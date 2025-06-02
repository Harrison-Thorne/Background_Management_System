/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : manager_system

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 01/06/2025 16:24:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Username',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Password',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Name',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Role',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Avatar',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_admin_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin', 'admin1', 'ADMIN', 'http://localhost:9090/files/download/1743858494772_下载.jpg');
INSERT INTO `admin` VALUES (3, 'admin2', 'admin', 'Admin2', 'ADMIN', 'http://localhost:9090/files/download/1744254000085_1743857119503_下载.jpg');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '简介',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容\r\n',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_article_title`(`title` ASC) USING BTREE,
  INDEX `idx_article_time`(`time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, 'US sends nuclear deal proposal to Iran', 'http://localhost:9090/files/download/1748760398764_微信图片_20250601144626.png', 'BBC News', '<p style=\"text-align: start;\"><br></p><p><strong>The US has sent Iran a proposal for a nuclear deal between Tehran and Washington, the White House confirmed on Saturday.</strong></p><p>Iranian Foreign Minister Abbas Araghchi said he had been presented with \"elements of a US deal\" by his Omani counterpart Badr Albusaidi during a short visit to the Iranian capital.</p><p>It comes after <a href=\"https://www.bbc.co.uk/news/articles/c1mg7kx2d45o\" target=\"_self\">a report by the UN nuclear watchdog said</a> Iran had further stepped up its production of enriched uranium, a key component in the making of nuclear weapons.</p><p>White House press secretary Karoline Leavitt said on Saturday it was in Tehran\'s \"best interest to accept\" the deal, adding: \"President Trump has made it clear that Iran can never obtain a nuclear bomb\".</p><p><br></p><p>Leavitt said a \"detailed and acceptable\" proposal had been sent to Iran by US President Donald Trump\'s special envoy Steve Witkoff.</p><p>The US proposal \"will be appropriately responded to in line with the principles, national interests and rights of the people of Iran\", Araghchi wrote on X.</p><p>The precise details of the deal are not yet clear.</p><p>The proposal follows a report by the International Atomic Energy Agency (IAEA) - seen by the BBC - which found Iran now possesses over 400kg of uranium enriched to 60% purity - close to the 90% purity required for weapons-grade uranium.</p><p>This is well above the level of purity sufficient for civilian nuclear power and research purposes.</p><p>It is enough for about 10 nuclear weapons if further refined, making Iran the only non nuclear-armed state producing uranium at this level.</p><p>The report paves the way for the US, Britain, France and Germany to push for the IAEA\'s board of governors to find Iran in violation of its non-proliferation obligations.</p><p>Iran insists its programme is peaceful. On Saturday, Iranian state media described the IAEA report as \"politically motivated\" containing \"baseless accusations\".</p><p>Iran has said it will \"implement appropriate measures\" in response to any effort to take action against Tehran at the IAEA governors\' meeting.</p>', '2025-06-01 11:05:29');
INSERT INTO `article` VALUES (2, 'These women helped bring down a president - now they say they feel invisible', 'http://localhost:9090/files/download/1748760583518_微信图片_20250601144936.png', 'BBC News', '<p style=\"text-align: start;\"><br></p><p>An Byunghui was in the middle of a video game on the night of 3 December when she learned that the South Korean president had declared martial law.</p><p>She couldn\'t quite believe it - until the internet blew up with the evidence. The shock announcement from then-president Yoon Suk Yeol, the now-famous shots of soldiers breaking down the windows of the National Assembly and MPs scaling the walls to force their way into the building so they could vote the motion down.</p><p>Within hours, thousands had spurred into protest, especially young women. And Byunghui joined them, travelling hundreds of miles from Daegu in the south-east to the capital Seoul.</p><p>They turned up not just because Yoon\'s decision had alarmed and angered them, but to protest against a president who insisted South Korea was free of sexism - despite the deep discrimination and flashes of violence that said otherwise.</p><p>They returned week after week as the investigation into Yoon\'s abuse of power went on - and they rejoiced when he was impeached after four dramatic months.</p><p>And yet, with the country set to elect a new president on 3 June, those very women say they feel invisible again.</p><p>The two main candidates have been largely silent about equality for women. A polarising subject, it had helped Yoon into power in 2022 as he vowed to defend men who felt sidelined in a world that they saw as too feminist. And a third candidate, who is popular among young men for his anti-feminist stance, has been making headlines.</p><p>For many young South Korean women, this new name on the ballot symbolises a new fight.</p><p>\"So many of us felt like we were trying to make the world a better place by attending the [anti-Yoon] rallies,\" the 24-year-old college student says.</p><p>\"But now, I wonder if anything has really improved… I can\'t shake the feeling that they\'re trying to erase women\'s voices.\"</p><p><br></p><h2>The women who turned up against Yoon</h2><p><br></p><p>When Byunghui arrived at the protests, she was struck by the atmosphere.</p><p>The bitter December cold didn\'t stop tens of thousands of women from gathering. Huddling inside hooded jackets or under umbrellas, waving lightsticks and banners, singing hopeful K-pop numbers, they demanded Yoon\'s ouster.</p><p>\"Most of those around me were young women, we were singing \'Into the World\' by Girls\' Generation,\" Byunghui says.</p>', '2025-05-31 11:05:29');
INSERT INTO `article` VALUES (3, 'Silent acts of resistance and fear under Russian occupation in Ukraine', 'http://localhost:9090/files/download/1748760740773_1.png', 'BBC News', '<p style=\"text-align: start;\">A fifth of Ukrainian territory is now under Russian control, and for Ukrainians living under occupation there seems little chance that any future deal to end the war will change that.</p><p style=\"text-align: start;\">Three Ukrainians in different Russian-controlled cities have told the BBC of the pressures they face, from being forced to accept a Russian passport to the risks of carrying out small acts of resistance. We are not using their real names for their own safety, and will call them Mavka, Pavlo and Iryna.</p><p style=\"text-align: start;\">The potential dangers are the same, whether in Mariupol or Melitopol, seized by Russia in the full-scale invasion in 2022, or in Crimea which was annexed eight years before.</p><p style=\"text-align: start;\">Mavka chose to stay in Melitopol when the Russians invaded her city on 25 February 2022, \"because it is unfair that someone can just come to my home and take it out\".</p><p style=\"text-align: start;\">She has lived there since birth, midway between the Crimean peninsula and the regional capital Zaporizhzhia.</p><p style=\"text-align: start;\">In recent months she has noticed a ramping up of not only a strict policy of \"Russification\" in the city, but of an increased militarisation of all spheres of life, including in schools.</p><p style=\"text-align: start;\">She has shared pictures of a billboard promoting conscription to young locals, a school notebook with Putin\'s portrait on it, and photos and a video of pupils wearing Russian military uniforms instead of the school outfits - boys and girls - and performing military education tasks.</p>', '2025-06-01 11:05:29');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '部门名字',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_department_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (3, '	Human Resources');
INSERT INTO `department` VALUES (1, '	Product');
INSERT INTO `department` VALUES (5, '	Sales');
INSERT INTO `department` VALUES (2, 'Administration');
INSERT INTO `department` VALUES (4, 'Finance');
INSERT INTO `department` VALUES (6, 'Technical');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Username',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Password',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'EMP' COMMENT 'Role',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Name',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Gender',
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Work Number',
  `age` int NULL DEFAULT NULL COMMENT 'Age',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Self description',
  `department_id` int NOT NULL COMMENT 'who\'s department id',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Avatar',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_employee_username`(`username` ASC) USING BTREE,
  INDEX `idx_employee_no`(`no` ASC) USING BTREE,
  INDEX `idx_employee_department_id`(`department_id` ASC) USING BTREE,
  INDEX `idx_employee_name`(`name` ASC) USING BTREE,
  CONSTRAINT `fk_employee_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (47, 'william841', '123', 'EMP', 'William', 'Male', 'EMP10000', 24, 'Problem solver.', 4, NULL);
INSERT INTO `employee` VALUES (48, 'robert470', '123', 'EMP', 'Robert', 'Male', 'EMP10001', 25, 'Positive attitude.', 6, NULL);
INSERT INTO `employee` VALUES (49, 'john691', '123', 'EMP', 'John', 'Male', 'EMP10002', 28, 'Excellent communicator.', 4, NULL);
INSERT INTO `employee` VALUES (50, 'james759', '123', 'EMP', 'James', 'Male', 'EMP10003', 43, 'Positive attitude.', 5, NULL);
INSERT INTO `employee` VALUES (51, 'emma294', '123', 'EMP', 'Emma', 'Female', 'EMP10004', 35, 'Tech enthusiast.', 6, NULL);
INSERT INTO `employee` VALUES (52, 'olivia888', '123', 'EMP', 'Olivia', 'Female', 'EMP10005', 36, 'Results-driven.', 2, NULL);
INSERT INTO `employee` VALUES (53, 'tom304', '123', 'EMP', 'Tom', 'Male', 'EMP10006', 30, 'A hardworking employee.', 2, NULL);
INSERT INTO `employee` VALUES (54, 'michael491', '123', 'EMP', 'Michael', 'Male', 'EMP10007', 22, 'Tech enthusiast.', 5, NULL);
INSERT INTO `employee` VALUES (55, 'sophia985', '123', 'EMP', 'Sophia', 'Female', 'EMP10008', 40, 'Detail-oriented.', 5, NULL);
INSERT INTO `employee` VALUES (56, 'emma807', '123', 'EMP', 'Emma', 'Female', 'EMP10009', 22, 'Positive attitude.', 6, NULL);
INSERT INTO `employee` VALUES (57, 'jack136', '123', 'EMP', 'Jack', 'Male', 'EMP10010', 43, 'Excellent communicator.', 1, NULL);
INSERT INTO `employee` VALUES (58, 'daniel802', '123', 'EMP', 'Daniel', 'Male', 'EMP10011', 30, 'A hardworking employee.', 3, NULL);
INSERT INTO `employee` VALUES (59, 'olivia318', '123', 'EMP', 'Olivia', 'Female', 'EMP10012', 25, 'Tech enthusiast.', 6, NULL);
INSERT INTO `employee` VALUES (60, 'olivia113', '123', 'EMP', 'Olivia', 'Female', 'EMP10013', 31, 'Results-driven.', 6, NULL);
INSERT INTO `employee` VALUES (61, 'david409', '123', 'EMP', 'David', 'Male', 'EMP10014', 41, 'Problem solver.', 2, NULL);
INSERT INTO `employee` VALUES (62, 'olivia766', '123', 'EMP', 'Olivia', 'Female', 'EMP10015', 25, 'A hardworking employee.', 1, NULL);
INSERT INTO `employee` VALUES (63, 'tom414', '123', 'EMP', 'Tom', 'Male', 'EMP10016', 22, 'Excellent communicator.', 1, NULL);
INSERT INTO `employee` VALUES (64, 'lily136', '123', 'EMP', 'Lily', 'Female', 'EMP10017', 28, 'Results-driven.', 6, NULL);
INSERT INTO `employee` VALUES (65, 'lily529', '123', 'EMP', 'Lily', 'Female', 'EMP10018', 22, 'Tech enthusiast.', 4, NULL);
INSERT INTO `employee` VALUES (66, 'hannah825', '123', 'EMP', 'Hannah', 'Female', 'EMP10019', 41, 'Excellent communicator.', 3, NULL);
INSERT INTO `employee` VALUES (67, 'ava594', '123', 'EMP', 'Ava', 'Female', 'EMP10020', 42, 'Tech enthusiast.', 4, NULL);
INSERT INTO `employee` VALUES (68, 'emma137', '123', 'EMP', 'Emma', 'Female', 'EMP10021', 37, 'Loves challenges.', 5, NULL);
INSERT INTO `employee` VALUES (69, 'sophia866', '123', 'EMP', 'Sophia', 'Female', 'EMP10022', 45, 'Excellent communicator.', 4, NULL);
INSERT INTO `employee` VALUES (70, 'david633', '123', 'EMP', 'David', 'Male', 'EMP10023', 22, 'Positive attitude.', 3, NULL);
INSERT INTO `employee` VALUES (71, 'daniel351', '123', 'EMP', 'Daniel', 'Male', 'EMP10024', 35, 'Excellent communicator.', 5, NULL);
INSERT INTO `employee` VALUES (72, 'olivia669', '123', 'EMP', 'Olivia', 'Female', 'EMP10025', 33, 'Team player.', 5, NULL);
INSERT INTO `employee` VALUES (73, 'william495', '123', 'EMP', 'William', 'Male', 'EMP10026', 29, 'Tech enthusiast.', 1, NULL);
INSERT INTO `employee` VALUES (74, 'william476', '123', 'EMP', 'William', 'Male', 'EMP10027', 30, 'Problem solver.', 2, NULL);
INSERT INTO `employee` VALUES (75, 'lucy826', '123', 'EMP', 'Lucy', 'Female', 'EMP10028', 32, 'Creative thinker.', 1, NULL);
INSERT INTO `employee` VALUES (76, 'jack219', '123', 'EMP', 'Jack', 'Male', 'EMP10029', 34, 'Loves challenges.', 1, NULL);

-- ----------------------------
-- View structure for v_article_daily_count
-- ----------------------------
DROP VIEW IF EXISTS `v_article_daily_count`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_article_daily_count` AS select date_format(str_to_date(`article`.`time`,'%Y-%m-%d'),'%Y-%m-%d') AS `publish_date`,count(0) AS `count` from `article` group by `publish_date`;

-- ----------------------------
-- View structure for v_employee_detail
-- ----------------------------
DROP VIEW IF EXISTS `v_employee_detail`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_employee_detail` AS select `e`.`id` AS `id`,`e`.`username` AS `username`,`e`.`password` AS `password`,`e`.`role` AS `role`,`e`.`name` AS `name`,`e`.`sex` AS `sex`,`e`.`no` AS `no`,`e`.`age` AS `age`,`e`.`description` AS `description`,`e`.`department_id` AS `department_id`,`e`.`avatar` AS `avatar`,`d`.`name` AS `department_name` from (`employee` `e` left join `department` `d` on((`e`.`department_id` = `d`.`id`)));

SET FOREIGN_KEY_CHECKS = 1;
