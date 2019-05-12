create table voting_system_voting_service.voting
(
  id            bigint not null
    primary key,
  suggestion_id bigint not null,
  user_id       bigint not null,
  vote          int    null
)
  engine = MyISAM;


INSERT INTO voting_system_voting_service.voting VALUES (1, 1, 1, 1);
INSERT INTO voting_system_voting_service.voting VALUES (2, 2, 1, 0);
INSERT INTO voting_system_voting_service.voting VALUES (3, 3, 1, 1);
INSERT INTO voting_system_voting_service.voting VALUES (4, 4, 1, 0);
INSERT INTO voting_system_voting_service.voting VALUES (5, 5, 1, 1);


