create table voting
(
  id            bigint auto_increment
    primary key,
  suggestion_id bigint not null,
  user_id       bigint not null,
  vote          int    null
)
  engine = MyISAM;

INSERT INTO voting_system_voting_service.voting VALUES (null, 1, 1, 1);
INSERT INTO voting_system_voting_service.voting VALUES (null, 2, 1, 0);
INSERT INTO voting_system_voting_service.voting VALUES (null, 3, 1, 1);
INSERT INTO voting_system_voting_service.voting VALUES (null, 4, 1, 0);
INSERT INTO voting_system_voting_service.voting VALUES (null, 5, 1, 1);


