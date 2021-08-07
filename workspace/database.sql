/*
 Navicat Premium Data Transfer

 Source Server         : 华为云
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : 124.71.7.170:8082
 Source Schema         : c4_project_check

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 02/08/2021 16:50:16
*/
use mysql;
grant all privileges on *.* to 'root'@'%' identified by 'dzhelp_2020';
flush privileges;

create database c4_project_check;

use c4_project_check;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(64) NOT NULL COMMENT '地点名称',
  `region` varchar(255) NOT NULL COMMENT '省市区',
  `address` varchar(255) NOT NULL COMMENT '详情地址',
  `create_time` int(11) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='打卡地点表';

-- ----------------------------
-- Records of address
-- ----------------------------
BEGIN;
INSERT INTO `address` VALUES (1, '长沙CSDN', '湖南省长沙市岳麓区', '麓谷街道芯城科技园二期', 1623921145);
INSERT INTO `address` VALUES (2, '北京CSDN', '北京省北京市朝阳区', '恒通商务园B8栋2层', 1623921145);
COMMIT;

-- ----------------------------
-- Table structure for admin_login
-- ----------------------------
DROP TABLE IF EXISTS `admin_login`;
CREATE TABLE `admin_login` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `login_name` varchar(32) NOT NULL COMMENT '登录名',
  `password` varchar(64) NOT NULL COMMENT '密码hash',
  `salt` char(32) NOT NULL COMMENT '密码盐',
  `create_time` int(11) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户登录表';

-- ----------------------------
-- Records of admin_login
-- ----------------------------
BEGIN;
INSERT INTO `admin_login` VALUES (1, 1, 'admin', '77cf918a45cb845fe0954196acef1fc8ef1e4b9c419f18892aef41ae25e041e3', '21232f297a57a5a743894a0e4a801fc3', 0);
INSERT INTO `admin_login` VALUES (3, 13, 'test', '123456', '28729cfcd25c45fbb62144be7b8f9f64', 1626419937);
COMMIT;

-- ----------------------------
-- Table structure for check_record
-- ----------------------------
DROP TABLE IF EXISTS `check_record`;
CREATE TABLE `check_record` (
  `id` int(11) NOT NULL COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `check_time` int(11) NOT NULL COMMENT '打卡时间',
  `address_id` int(11) NOT NULL DEFAULT '0' COMMENT '打卡地点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='打卡记录表';

-- ----------------------------
-- Records of check_record
-- ----------------------------
BEGIN;
INSERT INTO `check_record` VALUES (1, 10, 1620260574, 1);
INSERT INTO `check_record` VALUES (2, 8, 1620261751, 1);
INSERT INTO `check_record` VALUES (3, 9, 1620262572, 1);
INSERT INTO `check_record` VALUES (4, 2, 1620262588, 1);
INSERT INTO `check_record` VALUES (5, 4, 1620263401, 1);
INSERT INTO `check_record` VALUES (6, 3, 1620263801, 1);
INSERT INTO `check_record` VALUES (7, 7, 1620263950, 1);
INSERT INTO `check_record` VALUES (8, 11, 1620264162, 1);
INSERT INTO `check_record` VALUES (9, 1, 1620264171, 1);
INSERT INTO `check_record` VALUES (10, 6, 1620264729, 1);
INSERT INTO `check_record` VALUES (11, 5, 1620266537, 1);
INSERT INTO `check_record` VALUES (12, 8, 1620298264, 1);
INSERT INTO `check_record` VALUES (13, 9, 1620301662, 1);
INSERT INTO `check_record` VALUES (14, 4, 1620301704, 1);
INSERT INTO `check_record` VALUES (15, 1, 1620304196, 1);
INSERT INTO `check_record` VALUES (16, 6, 1620304262, 1);
INSERT INTO `check_record` VALUES (17, 7, 1620306101, 1);
INSERT INTO `check_record` VALUES (18, 2, 1620307183, 1);
INSERT INTO `check_record` VALUES (19, 3, 1620307391, 1);
INSERT INTO `check_record` VALUES (20, 10, 1620310288, 1);
INSERT INTO `check_record` VALUES (21, 5, 1620310472, 1);
INSERT INTO `check_record` VALUES (22, 11, 1620313424, 1);
INSERT INTO `check_record` VALUES (23, 3, 1620347563, 1);
INSERT INTO `check_record` VALUES (24, 4, 1620348426, 1);
INSERT INTO `check_record` VALUES (25, 8, 1620348593, 1);
INSERT INTO `check_record` VALUES (26, 7, 1620349041, 1);
INSERT INTO `check_record` VALUES (27, 1, 1620349316, 1);
INSERT INTO `check_record` VALUES (28, 10, 1620349418, 1);
INSERT INTO `check_record` VALUES (29, 5, 1620349651, 1);
INSERT INTO `check_record` VALUES (30, 11, 1620349785, 1);
INSERT INTO `check_record` VALUES (31, 2, 1620350651, 1);
INSERT INTO `check_record` VALUES (32, 6, 1620351479, 1);
INSERT INTO `check_record` VALUES (33, 9, 1620351973, 1);
INSERT INTO `check_record` VALUES (34, 11, 1620384711, 1);
INSERT INTO `check_record` VALUES (35, 6, 1620387220, 1);
INSERT INTO `check_record` VALUES (36, 1, 1620388757, 1);
INSERT INTO `check_record` VALUES (37, 2, 1620389866, 1);
INSERT INTO `check_record` VALUES (38, 5, 1620390370, 1);
INSERT INTO `check_record` VALUES (39, 3, 1620392771, 1);
INSERT INTO `check_record` VALUES (40, 9, 1620393541, 1);
INSERT INTO `check_record` VALUES (41, 4, 1620394276, 1);
INSERT INTO `check_record` VALUES (42, 8, 1620396392, 1);
INSERT INTO `check_record` VALUES (43, 7, 1620396950, 1);
INSERT INTO `check_record` VALUES (44, 10, 1620399774, 1);
INSERT INTO `check_record` VALUES (45, 3, 1620433253, 1);
INSERT INTO `check_record` VALUES (46, 1, 1620433315, 1);
INSERT INTO `check_record` VALUES (47, 11, 1620434271, 1);
INSERT INTO `check_record` VALUES (48, 10, 1620434482, 1);
INSERT INTO `check_record` VALUES (49, 2, 1620434875, 1);
INSERT INTO `check_record` VALUES (50, 5, 1620435693, 1);
INSERT INTO `check_record` VALUES (51, 7, 1620435129, 1);
INSERT INTO `check_record` VALUES (52, 4, 1620436810, 1);
INSERT INTO `check_record` VALUES (53, 8, 1620437191, 1);
INSERT INTO `check_record` VALUES (54, 6, 1620438200, 1);
INSERT INTO `check_record` VALUES (55, 9, 1620439917, 1);
INSERT INTO `check_record` VALUES (56, 4, 1620471155, 1);
INSERT INTO `check_record` VALUES (57, 3, 1620471482, 1);
INSERT INTO `check_record` VALUES (58, 5, 1620472329, 1);
INSERT INTO `check_record` VALUES (59, 6, 1620473943, 1);
INSERT INTO `check_record` VALUES (60, 7, 1620474781, 1);
INSERT INTO `check_record` VALUES (61, 2, 1620475871, 1);
INSERT INTO `check_record` VALUES (62, 9, 1620476308, 1);
INSERT INTO `check_record` VALUES (63, 1, 1620478033, 1);
INSERT INTO `check_record` VALUES (64, 8, 1620479131, 1);
INSERT INTO `check_record` VALUES (65, 11, 1620482924, 1);
INSERT INTO `check_record` VALUES (66, 10, 1620484989, 1);
INSERT INTO `check_record` VALUES (67, 1, 1620606038, 1);
INSERT INTO `check_record` VALUES (68, 4, 1620607127, 1);
INSERT INTO `check_record` VALUES (69, 2, 1620607389, 1);
INSERT INTO `check_record` VALUES (70, 3, 1620607521, 1);
INSERT INTO `check_record` VALUES (71, 5, 1620608236, 1);
INSERT INTO `check_record` VALUES (72, 11, 1620608745, 1);
INSERT INTO `check_record` VALUES (73, 6, 1620608906, 1);
INSERT INTO `check_record` VALUES (74, 7, 1620609068, 1);
INSERT INTO `check_record` VALUES (75, 9, 1620610121, 1);
INSERT INTO `check_record` VALUES (76, 10, 1620610449, 1);
INSERT INTO `check_record` VALUES (77, 8, 1620611343, 1);
INSERT INTO `check_record` VALUES (78, 4, 1620644243, 1);
INSERT INTO `check_record` VALUES (79, 5, 1620645126, 1);
INSERT INTO `check_record` VALUES (80, 1, 1620645648, 1);
INSERT INTO `check_record` VALUES (81, 6, 1620646282, 1);
INSERT INTO `check_record` VALUES (82, 11, 1620646970, 1);
INSERT INTO `check_record` VALUES (83, 3, 1620648109, 1);
INSERT INTO `check_record` VALUES (84, 2, 1620652496, 1);
INSERT INTO `check_record` VALUES (85, 8, 1620650419, 1);
INSERT INTO `check_record` VALUES (86, 10, 1620653566, 1);
INSERT INTO `check_record` VALUES (87, 7, 1620654970, 1);
INSERT INTO `check_record` VALUES (88, 9, 1620655653, 1);
INSERT INTO `check_record` VALUES (89, 1, 1620693205, 1);
INSERT INTO `check_record` VALUES (90, 2, 1620694070, 1);
INSERT INTO `check_record` VALUES (91, 3, 1620694388, 1);
INSERT INTO `check_record` VALUES (92, 4, 1620694707, 1);
INSERT INTO `check_record` VALUES (93, 6, 1620695901, 1);
INSERT INTO `check_record` VALUES (94, 9, 1620695966, 1);
INSERT INTO `check_record` VALUES (95, 5, 1620696090, 1);
INSERT INTO `check_record` VALUES (96, 10, 1620696188, 1);
INSERT INTO `check_record` VALUES (97, 11, 1620696672, 1);
INSERT INTO `check_record` VALUES (98, 7, 1620697432, 1);
INSERT INTO `check_record` VALUES (99, 8, 1620698674, 1);
INSERT INTO `check_record` VALUES (100, 3, 1620732848, 1);
INSERT INTO `check_record` VALUES (101, 7, 1620733062, 1);
INSERT INTO `check_record` VALUES (102, 11, 1620733950, 1);
INSERT INTO `check_record` VALUES (103, 4, 1620733971, 1);
INSERT INTO `check_record` VALUES (104, 2, 1620735636, 1);
INSERT INTO `check_record` VALUES (105, 10, 1620737241, 1);
INSERT INTO `check_record` VALUES (106, 1, 1620738681, 1);
INSERT INTO `check_record` VALUES (107, 6, 1620739450, 1);
INSERT INTO `check_record` VALUES (108, 5, 1620739762, 1);
INSERT INTO `check_record` VALUES (109, 9, 1620742503, 1);
INSERT INTO `check_record` VALUES (111, 8, 1620742794, 1);
INSERT INTO `check_record` VALUES (112, 1, 1620778847, 1);
INSERT INTO `check_record` VALUES (113, 3, 1620779879, 1);
INSERT INTO `check_record` VALUES (114, 4, 1620780247, 1);
INSERT INTO `check_record` VALUES (115, 2, 1620780261, 1);
INSERT INTO `check_record` VALUES (116, 11, 1620780842, 1);
INSERT INTO `check_record` VALUES (117, 9, 1620781374, 1);
INSERT INTO `check_record` VALUES (118, 8, 1620781400, 1);
INSERT INTO `check_record` VALUES (119, 7, 1620781929, 1);
INSERT INTO `check_record` VALUES (120, 6, 1620782494, 1);
INSERT INTO `check_record` VALUES (121, 10, 1620782521, 1);
INSERT INTO `check_record` VALUES (122, 8, 1620819749, 1);
INSERT INTO `check_record` VALUES (123, 1, 1620821002, 1);
INSERT INTO `check_record` VALUES (124, 7, 1620821484, 1);
INSERT INTO `check_record` VALUES (125, 11, 1620821940, 1);
INSERT INTO `check_record` VALUES (126, 6, 1620823448, 1);
INSERT INTO `check_record` VALUES (127, 5, 1620824341, 1);
INSERT INTO `check_record` VALUES (128, 10, 1620824372, 1);
INSERT INTO `check_record` VALUES (129, 4, 1620825152, 1);
INSERT INTO `check_record` VALUES (130, 3, 1620826989, 1);
INSERT INTO `check_record` VALUES (131, 9, 1620829161, 1);
INSERT INTO `check_record` VALUES (132, 2, 1620830291, 1);
INSERT INTO `check_record` VALUES (133, 1, 1620865229, 1);
INSERT INTO `check_record` VALUES (134, 2, 1620866393, 1);
INSERT INTO `check_record` VALUES (135, 4, 1620866799, 1);
INSERT INTO `check_record` VALUES (136, 3, 1620867065, 1);
INSERT INTO `check_record` VALUES (137, 8, 1620867142, 1);
INSERT INTO `check_record` VALUES (138, 9, 1620867398, 1);
INSERT INTO `check_record` VALUES (139, 6, 1620867761, 1);
INSERT INTO `check_record` VALUES (140, 11, 1620867786, 1);
INSERT INTO `check_record` VALUES (141, 5, 1620868269, 1);
INSERT INTO `check_record` VALUES (142, 7, 1620868867, 1);
INSERT INTO `check_record` VALUES (143, 10, 1620868904, 1);
INSERT INTO `check_record` VALUES (144, 9, 1620904303, 1);
INSERT INTO `check_record` VALUES (145, 10, 1620907093, 1);
INSERT INTO `check_record` VALUES (146, 5, 1620907930, 1);
INSERT INTO `check_record` VALUES (147, 11, 1620898450, 1);
INSERT INTO `check_record` VALUES (148, 6, 1620910145, 1);
INSERT INTO `check_record` VALUES (149, 1, 1620910912, 1);
INSERT INTO `check_record` VALUES (150, 4, 1620912104, 1);
INSERT INTO `check_record` VALUES (151, 7, 1620912961, 1);
INSERT INTO `check_record` VALUES (152, 8, 1620914818, 1);
INSERT INTO `check_record` VALUES (153, 3, 1620917218, 1);
INSERT INTO `check_record` VALUES (154, 2, 1620917242, 1);
INSERT INTO `check_record` VALUES (155, 1, 1620951617, 1);
INSERT INTO `check_record` VALUES (156, 2, 1620952401, 1);
INSERT INTO `check_record` VALUES (157, 6, 1620953611, 1);
INSERT INTO `check_record` VALUES (158, 7, 1620953902, 1);
INSERT INTO `check_record` VALUES (159, 10, 1620954068, 1);
INSERT INTO `check_record` VALUES (160, 3, 1620954115, 1);
INSERT INTO `check_record` VALUES (161, 4, 1620954428, 1);
INSERT INTO `check_record` VALUES (162, 9, 1620954573, 1);
INSERT INTO `check_record` VALUES (163, 5, 1620954660, 1);
INSERT INTO `check_record` VALUES (164, 8, 1620955249, 1);
INSERT INTO `check_record` VALUES (165, 11, 1620955921, 1);
INSERT INTO `check_record` VALUES (166, 3, 1620994265, 1);
INSERT INTO `check_record` VALUES (167, 2, 1620994401, 1);
INSERT INTO `check_record` VALUES (168, 10, 1620994436, 1);
INSERT INTO `check_record` VALUES (169, 9, 1620996129, 1);
INSERT INTO `check_record` VALUES (170, 8, 1620996562, 1);
INSERT INTO `check_record` VALUES (171, 7, 1620998469, 1);
INSERT INTO `check_record` VALUES (172, 6, 1621000311, 1);
INSERT INTO `check_record` VALUES (173, 1, 1621000673, 1);
INSERT INTO `check_record` VALUES (174, 11, 1621001032, 1);
INSERT INTO `check_record` VALUES (175, 4, 1621001264, 1);
INSERT INTO `check_record` VALUES (176, 5, 1621001419, 1);
INSERT INTO `check_record` VALUES (177, 1, 1621210848, 1);
INSERT INTO `check_record` VALUES (178, 4, 1621211913, 1);
INSERT INTO `check_record` VALUES (179, 11, 1621212089, 1);
INSERT INTO `check_record` VALUES (180, 2, 1621212432, 1);
INSERT INTO `check_record` VALUES (181, 3, 1621213062, 1);
INSERT INTO `check_record` VALUES (182, 7, 1621213342, 1);
INSERT INTO `check_record` VALUES (183, 8, 1621213690, 1);
INSERT INTO `check_record` VALUES (184, 5, 1621213692, 1);
INSERT INTO `check_record` VALUES (185, 6, 1621213868, 1);
INSERT INTO `check_record` VALUES (186, 9, 1621215902, 1);
INSERT INTO `check_record` VALUES (187, 10, 1621217138, 1);
INSERT INTO `check_record` VALUES (188, 5, 1621251852, 1);
INSERT INTO `check_record` VALUES (189, 1, 1621253001, 1);
INSERT INTO `check_record` VALUES (190, 3, 1621253469, 1);
INSERT INTO `check_record` VALUES (191, 2, 1621254724, 1);
INSERT INTO `check_record` VALUES (192, 4, 1621256132, 1);
INSERT INTO `check_record` VALUES (193, 6, 1621258278, 1);
INSERT INTO `check_record` VALUES (194, 7, 1621259532, 1);
INSERT INTO `check_record` VALUES (195, 8, 1621260662, 1);
INSERT INTO `check_record` VALUES (196, 9, 1621261677, 1);
INSERT INTO `check_record` VALUES (197, 10, 1621261909, 1);
INSERT INTO `check_record` VALUES (198, 11, 1621263491, 1);
INSERT INTO `check_record` VALUES (199, 1, 1621297211, 1);
INSERT INTO `check_record` VALUES (200, 2, 1621298247, 1);
INSERT INTO `check_record` VALUES (201, 3, 1621298504, 1);
INSERT INTO `check_record` VALUES (202, 4, 1621299349, 1);
INSERT INTO `check_record` VALUES (203, 11, 1621299592, 1);
INSERT INTO `check_record` VALUES (204, 9, 1621299813, 1);
INSERT INTO `check_record` VALUES (205, 6, 1621299862, 1);
INSERT INTO `check_record` VALUES (206, 7, 1621300914, 1);
INSERT INTO `check_record` VALUES (207, 8, 1621301631, 1);
INSERT INTO `check_record` VALUES (208, 5, 1621301697, 1);
INSERT INTO `check_record` VALUES (211, 10, 1621302708, 1);
INSERT INTO `check_record` VALUES (212, 4, 1621338262, 1);
INSERT INTO `check_record` VALUES (213, 5, 1621339068, 1);
INSERT INTO `check_record` VALUES (214, 8, 1621339375, 1);
INSERT INTO `check_record` VALUES (215, 3, 1621339903, 1);
INSERT INTO `check_record` VALUES (216, 1, 1621341551, 1);
INSERT INTO `check_record` VALUES (217, 2, 1621341562, 1);
INSERT INTO `check_record` VALUES (218, 11, 1621342293, 1);
INSERT INTO `check_record` VALUES (219, 9, 1621347072, 1);
INSERT INTO `check_record` VALUES (220, 10, 1621349529, 1);
INSERT INTO `check_record` VALUES (221, 1, 1621383671, 1);
INSERT INTO `check_record` VALUES (222, 6, 1621384629, 1);
INSERT INTO `check_record` VALUES (223, 2, 1621385751, 1);
INSERT INTO `check_record` VALUES (224, 7, 1621385854, 1);
INSERT INTO `check_record` VALUES (225, 3, 1621386108, 1);
INSERT INTO `check_record` VALUES (226, 8, 1621386470, 1);
INSERT INTO `check_record` VALUES (227, 5, 1621386476, 1);
INSERT INTO `check_record` VALUES (228, 4, 1621387052, 1);
INSERT INTO `check_record` VALUES (229, 9, 1621387229, 1);
INSERT INTO `check_record` VALUES (230, 11, 1621387751, 1);
INSERT INTO `check_record` VALUES (233, 10, 1621388911, 1);
INSERT INTO `check_record` VALUES (234, 10, 1621424663, 1);
INSERT INTO `check_record` VALUES (235, 11, 1621406710, 1);
INSERT INTO `check_record` VALUES (236, 4, 1621426291, 1);
INSERT INTO `check_record` VALUES (237, 5, 1621427941, 1);
INSERT INTO `check_record` VALUES (238, 7, 1621429743, 1);
INSERT INTO `check_record` VALUES (239, 1, 1621432777, 1);
INSERT INTO `check_record` VALUES (240, 3, 1621432968, 1);
INSERT INTO `check_record` VALUES (241, 2, 1621432981, 1);
INSERT INTO `check_record` VALUES (242, 9, 1621434782, 1);
INSERT INTO `check_record` VALUES (243, 1, 1621470061, 1);
INSERT INTO `check_record` VALUES (244, 2, 1621471341, 1);
INSERT INTO `check_record` VALUES (245, 3, 1621471861, 1);
INSERT INTO `check_record` VALUES (246, 10, 1621471910, 1);
INSERT INTO `check_record` VALUES (247, 4, 1621472211, 1);
INSERT INTO `check_record` VALUES (248, 9, 1621472277, 1);
INSERT INTO `check_record` VALUES (249, 5, 1621472591, 1);
INSERT INTO `check_record` VALUES (250, 8, 1621472837, 1);
INSERT INTO `check_record` VALUES (251, 6, 1621473099, 1);
INSERT INTO `check_record` VALUES (252, 11, 1621473761, 1);
INSERT INTO `check_record` VALUES (253, 7, 1621474924, 1);
INSERT INTO `check_record` VALUES (254, 6, 1621509394, 1);
INSERT INTO `check_record` VALUES (255, 5, 1621510966, 1);
INSERT INTO `check_record` VALUES (256, 4, 1621512663, 1);
INSERT INTO `check_record` VALUES (257, 1, 1621512771, 1);
INSERT INTO `check_record` VALUES (258, 2, 1621512908, 1);
INSERT INTO `check_record` VALUES (259, 3, 1621514973, 1);
INSERT INTO `check_record` VALUES (260, 8, 1621516131, 1);
INSERT INTO `check_record` VALUES (261, 7, 1621516681, 1);
INSERT INTO `check_record` VALUES (262, 10, 1621518771, 1);
INSERT INTO `check_record` VALUES (263, 9, 1621518809, 1);
INSERT INTO `check_record` VALUES (264, 11, 1621524436, 1);
INSERT INTO `check_record` VALUES (265, 1, 1621556419, 1);
INSERT INTO `check_record` VALUES (266, 3, 1621557316, 1);
INSERT INTO `check_record` VALUES (267, 2, 1621557581, 1);
INSERT INTO `check_record` VALUES (268, 10, 1621558353, 1);
INSERT INTO `check_record` VALUES (269, 11, 1621558783, 1);
INSERT INTO `check_record` VALUES (270, 5, 1621559341, 1);
INSERT INTO `check_record` VALUES (271, 6, 1621559478, 1);
INSERT INTO `check_record` VALUES (272, 7, 1621560403, 1);
INSERT INTO `check_record` VALUES (273, 8, 1621561635, 1);
INSERT INTO `check_record` VALUES (274, 4, 1621561711, 1);
INSERT INTO `check_record` VALUES (275, 9, 1621561906, 1);
INSERT INTO `check_record` VALUES (276, 11, 1621597337, 1);
INSERT INTO `check_record` VALUES (277, 3, 1621599094, 1);
INSERT INTO `check_record` VALUES (278, 4, 1621600789, 1);
INSERT INTO `check_record` VALUES (279, 1, 1621602316, 1);
INSERT INTO `check_record` VALUES (280, 2, 1621602362, 1);
INSERT INTO `check_record` VALUES (281, 5, 1621603203, 1);
INSERT INTO `check_record` VALUES (282, 6, 1621604630, 1);
INSERT INTO `check_record` VALUES (283, 7, 1621605486, 1);
INSERT INTO `check_record` VALUES (284, 10, 1621605862, 1);
INSERT INTO `check_record` VALUES (285, 8, 1621609631, 1);
INSERT INTO `check_record` VALUES (286, 9, 1621610049, 1);
INSERT INTO `check_record` VALUES (287, 1, 1621815662, 1);
INSERT INTO `check_record` VALUES (288, 2, 1621816196, 1);
INSERT INTO `check_record` VALUES (289, 3, 1621816991, 1);
INSERT INTO `check_record` VALUES (290, 7, 1621817371, 1);
INSERT INTO `check_record` VALUES (291, 8, 1621817513, 1);
INSERT INTO `check_record` VALUES (292, 4, 1621818107, 1);
INSERT INTO `check_record` VALUES (293, 9, 1621818436, 1);
INSERT INTO `check_record` VALUES (294, 5, 1621818756, 1);
INSERT INTO `check_record` VALUES (295, 10, 1621818765, 1);
INSERT INTO `check_record` VALUES (296, 11, 1621818931, 1);
INSERT INTO `check_record` VALUES (297, 6, 1621819082, 1);
INSERT INTO `check_record` VALUES (298, 7, 1621858260, 1);
INSERT INTO `check_record` VALUES (299, 1, 1621858308, 1);
INSERT INTO `check_record` VALUES (300, 8, 1621858855, 1);
INSERT INTO `check_record` VALUES (301, 2, 1621859892, 1);
INSERT INTO `check_record` VALUES (302, 3, 1621860711, 1);
INSERT INTO `check_record` VALUES (303, 4, 1621862581, 1);
INSERT INTO `check_record` VALUES (304, 5, 1621863832, 1);
INSERT INTO `check_record` VALUES (305, 6, 1621864593, 1);
INSERT INTO `check_record` VALUES (306, 9, 1621864849, 1);
INSERT INTO `check_record` VALUES (307, 10, 1621864921, 1);
INSERT INTO `check_record` VALUES (308, 11, 1621870806, 1);
INSERT INTO `check_record` VALUES (309, 1, 1621872179, 1);
INSERT INTO `check_record` VALUES (310, 1, 1621902050, 1);
INSERT INTO `check_record` VALUES (311, 2, 1621902517, 1);
INSERT INTO `check_record` VALUES (312, 7, 1621902896, 1);
INSERT INTO `check_record` VALUES (313, 3, 1621903269, 1);
INSERT INTO `check_record` VALUES (314, 5, 1621903774, 1);
INSERT INTO `check_record` VALUES (315, 4, 1621904170, 1);
INSERT INTO `check_record` VALUES (316, 6, 1621904292, 1);
INSERT INTO `check_record` VALUES (317, 8, 1621904625, 1);
INSERT INTO `check_record` VALUES (318, 10, 1621905329, 1);
INSERT INTO `check_record` VALUES (319, 9, 1621905718, 1);
INSERT INTO `check_record` VALUES (320, 11, 1621906053, 1);
INSERT INTO `check_record` VALUES (321, 7, 1621943257, 1);
INSERT INTO `check_record` VALUES (322, 8, 1621943987, 1);
INSERT INTO `check_record` VALUES (323, 1, 1621945581, 1);
INSERT INTO `check_record` VALUES (324, 6, 1621945995, 1);
INSERT INTO `check_record` VALUES (325, 2, 1621947834, 1);
INSERT INTO `check_record` VALUES (326, 4, 1621948738, 1);
INSERT INTO `check_record` VALUES (327, 3, 1621950062, 1);
INSERT INTO `check_record` VALUES (328, 5, 1621950116, 1);
INSERT INTO `check_record` VALUES (329, 9, 1621952384, 1);
INSERT INTO `check_record` VALUES (330, 11, 1621953872, 1);
INSERT INTO `check_record` VALUES (331, 10, 1621953911, 1);
INSERT INTO `check_record` VALUES (332, 1, 1621988421, 1);
INSERT INTO `check_record` VALUES (333, 2, 1621989936, 1);
INSERT INTO `check_record` VALUES (334, 4, 1621990269, 1);
INSERT INTO `check_record` VALUES (335, 3, 1621990661, 1);
INSERT INTO `check_record` VALUES (336, 5, 1621990997, 1);
INSERT INTO `check_record` VALUES (337, 6, 1621991334, 1);
INSERT INTO `check_record` VALUES (338, 7, 1621991449, 1);
INSERT INTO `check_record` VALUES (339, 9, 1621991648, 1);
INSERT INTO `check_record` VALUES (340, 10, 1621991913, 1);
INSERT INTO `check_record` VALUES (341, 8, 1621992082, 1);
INSERT INTO `check_record` VALUES (342, 11, 1621993458, 1);
INSERT INTO `check_record` VALUES (343, 1, 1622030568, 1);
INSERT INTO `check_record` VALUES (344, 2, 1622032553, 1);
INSERT INTO `check_record` VALUES (345, 3, 1622033232, 1);
INSERT INTO `check_record` VALUES (346, 4, 1622033571, 1);
INSERT INTO `check_record` VALUES (347, 6, 1622034575, 1);
INSERT INTO `check_record` VALUES (348, 7, 1622034667, 1);
INSERT INTO `check_record` VALUES (349, 8, 1622036668, 1);
INSERT INTO `check_record` VALUES (350, 5, 1622037316, 1);
INSERT INTO `check_record` VALUES (351, 9, 1622037717, 1);
INSERT INTO `check_record` VALUES (352, 10, 1622038683, 1);
INSERT INTO `check_record` VALUES (353, 11, 1622038899, 1);
INSERT INTO `check_record` VALUES (354, 1, 1622074832, 1);
INSERT INTO `check_record` VALUES (355, 2, 1622075617, 1);
INSERT INTO `check_record` VALUES (356, 3, 1622075712, 1);
INSERT INTO `check_record` VALUES (357, 10, 1622076160, 1);
INSERT INTO `check_record` VALUES (358, 4, 1622076464, 1);
INSERT INTO `check_record` VALUES (359, 9, 1622076756, 1);
INSERT INTO `check_record` VALUES (360, 5, 1622076775, 1);
INSERT INTO `check_record` VALUES (361, 6, 1622077158, 1);
INSERT INTO `check_record` VALUES (362, 8, 1622078046, 1);
INSERT INTO `check_record` VALUES (363, 11, 1622078856, 1);
INSERT INTO `check_record` VALUES (364, 7, 1622079097, 1);
INSERT INTO `check_record` VALUES (365, 4, 1622117363, 1);
INSERT INTO `check_record` VALUES (366, 6, 1622119032, 1);
INSERT INTO `check_record` VALUES (367, 5, 1622119469, 1);
INSERT INTO `check_record` VALUES (368, 1, 1622120055, 1);
INSERT INTO `check_record` VALUES (369, 3, 1622120272, 1);
INSERT INTO `check_record` VALUES (370, 2, 1622120288, 1);
INSERT INTO `check_record` VALUES (371, 8, 1622121108, 1);
INSERT INTO `check_record` VALUES (372, 7, 1622123193, 1);
INSERT INTO `check_record` VALUES (373, 9, 1622123599, 1);
INSERT INTO `check_record` VALUES (374, 10, 1622124232, 1);
INSERT INTO `check_record` VALUES (375, 11, 1622124430, 1);
INSERT INTO `check_record` VALUES (376, 4, 1622128353, 1);
INSERT INTO `check_record` VALUES (377, 1, 1622130687, 1);
INSERT INTO `check_record` VALUES (378, 1, 1622161495, 1);
INSERT INTO `check_record` VALUES (379, 2, 1622161884, 1);
INSERT INTO `check_record` VALUES (380, 3, 1622162005, 1);
INSERT INTO `check_record` VALUES (381, 4, 1622162957, 1);
INSERT INTO `check_record` VALUES (382, 11, 1622162982, 1);
INSERT INTO `check_record` VALUES (383, 10, 1622163458, 1);
INSERT INTO `check_record` VALUES (384, 6, 1622163863, 1);
INSERT INTO `check_record` VALUES (385, 5, 1622164366, 1);
INSERT INTO `check_record` VALUES (386, 7, 1622164878, 1);
INSERT INTO `check_record` VALUES (387, 8, 1622165785, 1);
INSERT INTO `check_record` VALUES (388, 9, 1622167311, 1);
INSERT INTO `check_record` VALUES (389, 1, 1622204373, 1);
INSERT INTO `check_record` VALUES (390, 2, 1622204751, 1);
INSERT INTO `check_record` VALUES (391, 3, 1622204769, 1);
INSERT INTO `check_record` VALUES (392, 7, 1622206151, 1);
INSERT INTO `check_record` VALUES (393, 4, 1622206768, 1);
INSERT INTO `check_record` VALUES (394, 5, 1622208258, 1);
INSERT INTO `check_record` VALUES (395, 6, 1622208745, 1);
INSERT INTO `check_record` VALUES (396, 8, 1622209189, 1);
INSERT INTO `check_record` VALUES (397, 9, 1622210901, 1);
INSERT INTO `check_record` VALUES (398, 11, 1622211202, 1);
INSERT INTO `check_record` VALUES (399, 10, 1622211337, 1);
INSERT INTO `check_record` VALUES (400, 3, 1622216802, 1);
INSERT INTO `check_record` VALUES (402, 1, 1622218388, 1);
INSERT INTO `check_record` VALUES (403, 1, 1622420717, 1);
INSERT INTO `check_record` VALUES (404, 2, 1622421079, 1);
INSERT INTO `check_record` VALUES (405, 4, 1622421759, 1);
INSERT INTO `check_record` VALUES (406, 3, 1622422194, 1);
INSERT INTO `check_record` VALUES (407, 5, 1622422570, 1);
INSERT INTO `check_record` VALUES (408, 6, 1622422764, 1);
INSERT INTO `check_record` VALUES (409, 11, 1622422916, 1);
INSERT INTO `check_record` VALUES (410, 9, 1622423561, 1);
INSERT INTO `check_record` VALUES (411, 10, 1622425752, 1);
INSERT INTO `check_record` VALUES (412, 7, 1622426358, 1);
INSERT INTO `check_record` VALUES (413, 8, 1622426532, 1);
INSERT INTO `check_record` VALUES (414, 6, 1622459842, 1);
INSERT INTO `check_record` VALUES (415, 7, 1622461895, 1);
INSERT INTO `check_record` VALUES (416, 1, 1622465719, 1);
INSERT INTO `check_record` VALUES (417, 2, 1622465901, 1);
INSERT INTO `check_record` VALUES (418, 3, 1622465920, 1);
INSERT INTO `check_record` VALUES (419, 5, 1622466158, 1);
INSERT INTO `check_record` VALUES (420, 4, 1622467034, 1);
INSERT INTO `check_record` VALUES (421, 8, 1622470604, 1);
INSERT INTO `check_record` VALUES (422, 9, 1622470680, 1);
INSERT INTO `check_record` VALUES (423, 11, 1622470786, 1);
INSERT INTO `check_record` VALUES (424, 10, 1622473339, 1);
INSERT INTO `check_record` VALUES (425, 7, 1622473395, 1);
INSERT INTO `check_record` VALUES (426, 8, 1622473551, 1);
INSERT INTO `check_record` VALUES (427, 4, 1622478749, 1);
INSERT INTO `check_record` VALUES (428, 1, 1622480116, 1);
COMMIT;

-- ----------------------------
-- Table structure for dateday
-- ----------------------------
DROP TABLE IF EXISTS `dateday`;
CREATE TABLE `dateday` (
  `id` char(32) NOT NULL COMMENT '主键',
  `dateday` date NOT NULL DEFAULT '2000-01-01' COMMENT '具体日期',
  `duration` tinyint(1) NOT NULL DEFAULT '8' COMMENT '每天的工作时长，默认小时',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1：固定时间上下班；2：自由上下班；3：不上班',
  `start_time` time NOT NULL DEFAULT '00:00:00' COMMENT '开始时间',
  `end_time` time NOT NULL DEFAULT '00:00:00' COMMENT '结束时间',
  `create_time` int(11) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作日期表';

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '部门名称',
  `create_time` int(11) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
INSERT INTO `department` VALUES (1, '综合部', 1623920625);
INSERT INTO `department` VALUES (2, '运营部', 1623920625);
INSERT INTO `department` VALUES (3, '研发部', 1623920625);
INSERT INTO `department` VALUES (4, '市场部', 1623920625);
COMMIT;

-- ----------------------------
-- Table structure for options
-- ----------------------------
DROP TABLE IF EXISTS `options`;
CREATE TABLE `options` (
  `id` char(32) NOT NULL COMMENT '主键',
  `title` varchar(64) NOT NULL COMMENT '规则选项标题',
  `description` varchar(255) NOT NULL COMMENT '规则选项描述',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类型，1：星期，2：日期',
  `create_time` int(11) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规则选项表';

-- ----------------------------
-- Records of options
-- ----------------------------
BEGIN;
INSERT INTO `options` VALUES ('2f6e5deadd7711ebbd36fa163e8c653e', '2021年节假日（公休日）', '2021年国务院颁布的全国节假公休日，各企业放假', 2, 1625478952);
INSERT INTO `options` VALUES ('b8da28d0dd3511ebba78fa163e8c653e', '1024程序员节', '1024程序员节，所有开发加班', 1, 1625450836);
INSERT INTO `options` VALUES ('c5557e1cdd3411eba965fa163e8c653e', '五天班（朝九晚六）', '每周上班五天周一到周五，早上9:00到晚上18:00。', 1, 1625450427);
INSERT INTO `options` VALUES ('cfbfe4f8dd7711eb86c0fa163e8c653e', '2021年节假日调班（上班日）', '2021年节假日调休带来的调班，需要上班。', 2, 1625479221);
COMMIT;

-- ----------------------------
-- Table structure for rel_department_user
-- ----------------------------
DROP TABLE IF EXISTS `rel_department_user`;
CREATE TABLE `rel_department_user` (
  `id` int(11) NOT NULL COMMENT '主键',
  `dept_id` int(11) NOT NULL COMMENT '部门id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门用户表';

-- ----------------------------
-- Records of rel_department_user
-- ----------------------------
BEGIN;
INSERT INTO `rel_department_user` VALUES (1, 1, 11);
INSERT INTO `rel_department_user` VALUES (2, 2, 5);
INSERT INTO `rel_department_user` VALUES (3, 2, 6);
INSERT INTO `rel_department_user` VALUES (4, 3, 2);
INSERT INTO `rel_department_user` VALUES (5, 3, 3);
INSERT INTO `rel_department_user` VALUES (6, 3, 4);
INSERT INTO `rel_department_user` VALUES (7, 3, 7);
INSERT INTO `rel_department_user` VALUES (8, 3, 8);
INSERT INTO `rel_department_user` VALUES (9, 3, 9);
INSERT INTO `rel_department_user` VALUES (10, 4, 10);
INSERT INTO `rel_department_user` VALUES (11, 4, 1);
COMMIT;

-- ----------------------------
-- Table structure for rel_rule_address
-- ----------------------------
DROP TABLE IF EXISTS `rel_rule_address`;
CREATE TABLE `rel_rule_address` (
  `rule_id` char(32) NOT NULL DEFAULT '0' COMMENT '规则id',
  `address_id` int(11) NOT NULL DEFAULT '0' COMMENT '地址id',
  PRIMARY KEY (`rule_id`,`address_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规则打卡地点表';

-- ----------------------------
-- Records of rel_rule_address
-- ----------------------------
BEGIN;
INSERT INTO `rel_rule_address` VALUES ('19daa0b2dd7f11eb9ef4fa163e8c653e', 1);
INSERT INTO `rel_rule_address` VALUES ('74ad80c2dd5c11eba950fa163e8c653e', 1);
INSERT INTO `rel_rule_address` VALUES ('74ad80c2dd5c11eba950fa163e8c653e', 2);
COMMIT;

-- ----------------------------
-- Table structure for rel_rule_option
-- ----------------------------
DROP TABLE IF EXISTS `rel_rule_option`;
CREATE TABLE `rel_rule_option` (
  `rule_id` char(32) NOT NULL,
  `option_id` char(32) NOT NULL,
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '规则选项排序值',
  PRIMARY KEY (`rule_id`,`option_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规则选项表';

-- ----------------------------
-- Records of rel_rule_option
-- ----------------------------
BEGIN;
INSERT INTO `rel_rule_option` VALUES ('19daa0b2dd7f11eb9ef4fa163e8c653e', '2f6e5deadd7711ebbd36fa163e8c653e', 1);
INSERT INTO `rel_rule_option` VALUES ('19daa0b2dd7f11eb9ef4fa163e8c653e', 'b8da28d0dd3511ebba78fa163e8c653e', 1);
INSERT INTO `rel_rule_option` VALUES ('19daa0b2dd7f11eb9ef4fa163e8c653e', 'c5557e1cdd3411eba965fa163e8c653e', 1);
INSERT INTO `rel_rule_option` VALUES ('19daa0b2dd7f11eb9ef4fa163e8c653e', 'cfbfe4f8dd7711eb86c0fa163e8c653e', 1);
INSERT INTO `rel_rule_option` VALUES ('74ad80c2dd5c11eba950fa163e8c653e', '2f6e5deadd7711ebbd36fa163e8c653e', 1);
INSERT INTO `rel_rule_option` VALUES ('74ad80c2dd5c11eba950fa163e8c653e', 'b8da28d0dd3511ebba78fa163e8c653e', 1);
INSERT INTO `rel_rule_option` VALUES ('74ad80c2dd5c11eba950fa163e8c653e', 'c5557e1cdd3411eba965fa163e8c653e', 1);
INSERT INTO `rel_rule_option` VALUES ('74ad80c2dd5c11eba950fa163e8c653e', 'cfbfe4f8dd7711eb86c0fa163e8c653e', 1);
COMMIT;

-- ----------------------------
-- Table structure for rel_user_rule
-- ----------------------------
DROP TABLE IF EXISTS `rel_user_rule`;
CREATE TABLE `rel_user_rule` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_id` char(32) NOT NULL COMMENT '规则id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8mb4 COMMENT='用户规则表';

-- ----------------------------
-- Records of rel_user_rule
-- ----------------------------
BEGIN;
INSERT INTO `rel_user_rule` VALUES (157, '19daa0b2dd7f11eb9ef4fa163e8c653e', 11);
INSERT INTO `rel_user_rule` VALUES (158, '19daa0b2dd7f11eb9ef4fa163e8c653e', 5);
INSERT INTO `rel_user_rule` VALUES (159, '19daa0b2dd7f11eb9ef4fa163e8c653e', 6);
INSERT INTO `rel_user_rule` VALUES (160, '19daa0b2dd7f11eb9ef4fa163e8c653e', 10);
INSERT INTO `rel_user_rule` VALUES (161, '19daa0b2dd7f11eb9ef4fa163e8c653e', 1);
INSERT INTO `rel_user_rule` VALUES (162, '74ad80c2dd5c11eba950fa163e8c653e', 2);
INSERT INTO `rel_user_rule` VALUES (163, '74ad80c2dd5c11eba950fa163e8c653e', 3);
INSERT INTO `rel_user_rule` VALUES (164, '74ad80c2dd5c11eba950fa163e8c653e', 4);
INSERT INTO `rel_user_rule` VALUES (165, '74ad80c2dd5c11eba950fa163e8c653e', 7);
INSERT INTO `rel_user_rule` VALUES (166, '74ad80c2dd5c11eba950fa163e8c653e', 8);
INSERT INTO `rel_user_rule` VALUES (167, '74ad80c2dd5c11eba950fa163e8c653e', 9);
COMMIT;

-- ----------------------------
-- Table structure for rel_workday
-- ----------------------------
DROP TABLE IF EXISTS `rel_workday`;
CREATE TABLE `rel_workday` (
  `option_id` char(32) NOT NULL COMMENT '规则选项id',
  `work_id` char(32) NOT NULL COMMENT '星期或者日期id',
  PRIMARY KEY (`option_id`,`work_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作日表';

-- ----------------------------
-- Records of rel_workday
-- ----------------------------
BEGIN;
INSERT INTO `rel_workday` VALUES ('2f6e5deadd7711ebbd36fa163e8c653e', '2f6e6db2dd7711eba8c7fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('2f6e5deadd7711ebbd36fa163e8c653e', '2f6e70bedd7711eb95d2fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('2f6e5deadd7711ebbd36fa163e8c653e', '2f6e72bcdd7711eb9282fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('2f6e5deadd7711ebbd36fa163e8c653e', '2f6e74a6dd7711ebb4f2fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('2f6e5deadd7711ebbd36fa163e8c653e', '2f6e7672dd7711eb909dfa163e8c653e');
INSERT INTO `rel_workday` VALUES ('2f6e5deadd7711ebbd36fa163e8c653e', '2f6e782add7711eb8d17fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('2f6e5deadd7711ebbd36fa163e8c653e', '2f6e7a64dd7711eba028fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('2f6e5deadd7711ebbd36fa163e8c653e', '2f6e7c30dd7711eb9c13fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('5b3ca4b424a1418b9d02d901b65fce41', '24d7642d53d14304a626d19de4867c99');
INSERT INTO `rel_workday` VALUES ('5b3ca4b424a1418b9d02d901b65fce41', '6277adf1d8d646c9906926a60d3884fd');
INSERT INTO `rel_workday` VALUES ('5b3ca4b424a1418b9d02d901b65fce41', 'd825b427044d44f2b90a2da1cf820e8f');
INSERT INTO `rel_workday` VALUES ('90cf5ac3654e46a1b63471c393c46c9e', '0a82eb2b55ee4bb298dd669c83a87a7b');
INSERT INTO `rel_workday` VALUES ('90cf5ac3654e46a1b63471c393c46c9e', '3571b3a4890b4badaee82f26500c3a88');
INSERT INTO `rel_workday` VALUES ('90cf5ac3654e46a1b63471c393c46c9e', '5032c4d0db1f430da6b3055d473ae1da');
INSERT INTO `rel_workday` VALUES ('90cf5ac3654e46a1b63471c393c46c9e', '778b020f2af54965a6584d6a25c1bbfd');
INSERT INTO `rel_workday` VALUES ('90cf5ac3654e46a1b63471c393c46c9e', '928e437cf5214818b2973968c8305734');
INSERT INTO `rel_workday` VALUES ('90cf5ac3654e46a1b63471c393c46c9e', 'a26548422e1f45639164bee77775101b');
INSERT INTO `rel_workday` VALUES ('90cf5ac3654e46a1b63471c393c46c9e', 'bda938eb7b484e789b62d5fefdca9228');
INSERT INTO `rel_workday` VALUES ('90cf5ac3654e46a1b63471c393c46c9e', 'd12056b6c30547d3a5284f46540621a7');
INSERT INTO `rel_workday` VALUES ('90cf5ac3654e46a1b63471c393c46c9e', 'ea11deb8fa834765b941fe954d188948');
INSERT INTO `rel_workday` VALUES ('96c0f859ca06499ea1270b929dd8b9d6', '1d03ced83f864d17872abed8a4731965');
INSERT INTO `rel_workday` VALUES ('96c0f859ca06499ea1270b929dd8b9d6', '9b0b64ede6974189a37a81e926b61fad');
INSERT INTO `rel_workday` VALUES ('96c0f859ca06499ea1270b929dd8b9d6', 'a7429adc15d244e1abcb053d5b67346a');
INSERT INTO `rel_workday` VALUES ('b8da28d0dd3511ebba78fa163e8c653e', '0f6e3776ebb511ebaccffa163e8c653e');
INSERT INTO `rel_workday` VALUES ('b8da28d0dd3511ebba78fa163e8c653e', '0f6e3f96ebb511ebb167fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('b8da28d0dd3511ebba78fa163e8c653e', '0f6e41b2ebb511eb864cfa163e8c653e');
INSERT INTO `rel_workday` VALUES ('b8da28d0dd3511ebba78fa163e8c653e', '0f6e4306ebb511eba0dffa163e8c653e');
INSERT INTO `rel_workday` VALUES ('b8da28d0dd3511ebba78fa163e8c653e', '0f6e4428ebb511ebbd30fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('bd60b7d3f73c408c8c08c05ca3ac1a79', '0fff3e8b1fd345f295ba2152010dfd69');
INSERT INTO `rel_workday` VALUES ('bd60b7d3f73c408c8c08c05ca3ac1a79', '1f98ad56c6bc4b02bc53a22d2d161741');
INSERT INTO `rel_workday` VALUES ('bd60b7d3f73c408c8c08c05ca3ac1a79', '74c5f2a1ce764584872f912abc9280dc');
INSERT INTO `rel_workday` VALUES ('bd60b7d3f73c408c8c08c05ca3ac1a79', 'ab41ff48814f406985917fcc4948a266');
INSERT INTO `rel_workday` VALUES ('bd60b7d3f73c408c8c08c05ca3ac1a79', 'c2918a38687f470eb596c7e89dc5844e');
INSERT INTO `rel_workday` VALUES ('bd60b7d3f73c408c8c08c05ca3ac1a79', 'd593c72dc0614be1913f4705765b4a73');
INSERT INTO `rel_workday` VALUES ('c5557e1cdd3411eba965fa163e8c653e', 'c5558d62dd3411eba094fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('c5557e1cdd3411eba965fa163e8c653e', 'c5559046dd3411ebbd5ffa163e8c653e');
INSERT INTO `rel_workday` VALUES ('c5557e1cdd3411eba965fa163e8c653e', 'c5559280dd3411ebaca6fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('c5557e1cdd3411eba965fa163e8c653e', 'c5559474dd3411eb9541fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('c5557e1cdd3411eba965fa163e8c653e', 'c5559640dd3411eba4d8fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('c8e43c56d98511eb939e0242ac150004', 'c8e4faf6d98511eb86fe0242ac150004');
INSERT INTO `rel_workday` VALUES ('c909e75aec5211eba54ffa163e8c653e', '26d8cc48ec5311ebb3defa163e8c653e');
INSERT INTO `rel_workday` VALUES ('c909e75aec5211eba54ffa163e8c653e', '26d8d45eec5311eb87f8fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('c909e75aec5211eba54ffa163e8c653e', '26d8d602ec5311eb8d11fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('c909e75aec5211eba54ffa163e8c653e', '26d8d74cec5311eba3befa163e8c653e');
INSERT INTO `rel_workday` VALUES ('c909e75aec5211eba54ffa163e8c653e', '26d8d878ec5311ebad78fa163e8c653e');
INSERT INTO `rel_workday` VALUES ('cfbfe4f8dd7711eb86c0fa163e8c653e', 'cfbfef34dd7711eb8e2cfa163e8c653e');
INSERT INTO `rel_workday` VALUES ('d50046c4d98511eb988f0242ac150004', 'd501533ed98511eb89a80242ac150004');
COMMIT;

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `id` char(32) NOT NULL COMMENT '主键',
  `title` varchar(64) NOT NULL COMMENT '规则名称',
  `description` varchar(255) NOT NULL COMMENT '规则描述',
  `influenced` tinyint(1) NOT NULL DEFAULT '0' COMMENT '适用人数',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0：未使用；1：已使用；2：已禁用',
  `create_time` int(11) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规则表';

-- ----------------------------
-- Records of rule
-- ----------------------------
BEGIN;
INSERT INTO `rule` VALUES ('19daa0b2dd7f11eb9ef4fa163e8c653e', '长沙CSDN', '长沙CSDN全员打卡规则', 5, 1, 1625482352);
INSERT INTO `rule` VALUES ('74ad80c2dd5c11eba950fa163e8c653e', '研发部打卡规则', '会尽快尽快', 6, 1, 1625467472);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `real_name` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '用户真实姓名',
  `gender` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0女性，1男性',
  `birthday` date NOT NULL COMMENT '出生年月日',
  `create_time` int(11) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '白月光', 1, '1986-10-04', 1623919108);
INSERT INTO `user` VALUES (2, '丢哥', 1, '1987-02-14', 1623919108);
INSERT INTO `user` VALUES (3, '湘王', 1, '1980-10-01', 1623919108);
INSERT INTO `user` VALUES (4, '画家', 1, '1987-12-01', 1623919108);
INSERT INTO `user` VALUES (5, '发财', 0, '1987-03-21', 1623919108);
INSERT INTO `user` VALUES (6, '戴戴米', 0, '1985-12-01', 1623919108);
INSERT INTO `user` VALUES (7, '郝三石', 1, '1990-08-01', 1623919108);
INSERT INTO `user` VALUES (8, '环环', 1, '1993-09-01', 1623919108);
INSERT INTO `user` VALUES (9, '邵鹏', 1, '1993-06-01', 1623919108);
INSERT INTO `user` VALUES (10, '小海', 1, '1999-02-01', 1623919108);
INSERT INTO `user` VALUES (11, '团子', 0, '1998-01-01', 1623919108);
COMMIT;

-- ----------------------------
-- Table structure for weekday
-- ----------------------------
DROP TABLE IF EXISTS `weekday`;
CREATE TABLE `weekday` (
  `id` char(32) NOT NULL COMMENT '主键',
  `weekday` tinyint(1) NOT NULL DEFAULT '0' COMMENT '周几，默认从0开始，0：周日；1：周一；依此类推',
  `duration` tinyint(1) NOT NULL DEFAULT '8' COMMENT '每天的工作时长，默认小时',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1：固定时间上下班；2：自由上下班；3：不上班',
  `start_time` time NOT NULL DEFAULT '00:00:00' COMMENT '开始时间',
  `end_time` time NOT NULL DEFAULT '00:00:00' COMMENT '结束时间',
  `create_time` int(11) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工作星期表';

SET FOREIGN_KEY_CHECKS = 1;
