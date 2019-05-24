use spring;

create table if NOT EXISTS user (
  id int PRIMARY KEY  NOT NULL auto_increment,
  name VARCHAR(30),
  password VARCHAR(10),
  email VARCHAR(30)
);

-- INSERT INTO user(name, password, email) values("test", "001", "test@163.com");

INSERT INTO user(name, password, email)
SELECT * FROM (SELECT "test", "001", "test@163.com") AS tmp
WHERE NOT EXISTS (
            SELECT name FROM user WHERE name='test' AND email='test@163.com'
  ) limit 1;