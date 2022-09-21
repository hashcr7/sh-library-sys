--图书表
--author:hqc
--2022/09/19
INSERT INTO book (name, author,borrowing_num) VALUES
  ('语文', '张三',23),
  ('数学', '李三',34),
  ('地理', '张四',1),
  ('英语', '李四',2),
  ('化学', '张五',34),
  ('入门到放弃', '李五',45),
  ('C++', '张六',22),
  ('体育', '李六',0),
  ('足球', '张七',2),
  ('篮球', '李七',5);

--用户表
--author:hqc
--2022/09/21
INSERT INTO account(username,password,perms,role) VALUES
('a','a','','reader'),
('b','b','','admin');


commit;

