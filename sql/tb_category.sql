USE xt8;
INSERT INTO `tb_category` (`id`,`categoryId`,`categoryName`,`parentId`,`description`) VALUES (1, 1, '所有类别', NULL, '类别根目录');
INSERT INTO `tb_category` (`id`,`categoryId`,`categoryName`,`parentId`,`description`) VALUES (2, 100000, '书籍', 1, '书籍类别');
INSERT INTO `tb_category` (`id`,`categoryId`,`categoryName`,`parentId`,`description`) VALUES (3, 100001, '计算机', 2, '计算机书籍');
INSERT INTO `tb_category` (`id`,`categoryId`,`categoryName`,`parentId`,`description`) VALUES (4, 100002, '经济', 2, '经济书籍');
INSERT INTO `tb_category` (`id`,`categoryId`,`categoryName`,`parentId`,`description`) VALUES (5, 200000, '衣帽服饰', 1, '衣服类别');
INSERT INTO `tb_category` (`id`,`categoryId`,`categoryName`,`parentId`,`description`) VALUES (6, 200001, '上衣', 5, '上衣');
INSERT INTO `tb_category` (`id`,`categoryId`,`categoryName`,`parentId`,`description`) VALUES (7, 300000, '生活用品', 1, '生活用品');
INSERT INTO `tb_category` (`id`,`categoryId`,`categoryName`,`parentId`,`description`) VALUES (8, 300001, '桌椅', 7, '桌子');
