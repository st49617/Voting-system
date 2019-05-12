create table voting_system_user_service.user
(
  id            bigint auto_increment
    primary key,
  email         varchar(255) not null,
  first_name    varchar(255) not null,
  last_name     varchar(255) not null,
  password_hash varchar(255) not null,
  constraint UK_ob8kqyqqgmefl0aco34akdtpe
    unique (email)
)
  engine = MyISAM;


INSERT INTO voting_system_user_service.user VALUES (null, 'address@domain.tld', 'John', 'Doe', '');


