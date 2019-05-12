create table hibernate_sequence
(
  next_val bigint null
)
  engine = MyISAM;

create table suggestion
(
  id         bigint       not null
    primary key,
  accepted   int          null,
  content    varchar(255) null,
  meeting_id bigint       null
)
  engine = MyISAM;

create table tag
(
  id    bigint       not null
    primary key,
  color varchar(255) not null,
  name  varchar(255) not null
)
  engine = MyISAM;

create table tag_suggestion
(
  tag_id        bigint not null,
  suggestion_id bigint not null
)
  engine = MyISAM;

create index FKrhehk68rqow3qfb763qoosusf
  on tag_suggestion (suggestion_id);

create index FKsf41us9sw9yqobas9wyp2ph6y
  on tag_suggestion (tag_id);

INSERT INTO voting_system_suggestion_service.tag VALUES (1, 'green', 'zeleny');
INSERT INTO voting_system_suggestion_service.tag VALUES (2, 'red', 'cerveny');

INSERT INTO voting_system_suggestion_service.suggestion VALUES (1, 0, 'suggestion 1', 1);
INSERT INTO voting_system_suggestion_service.suggestion VALUES (2, 0, 'suggestion 2', 2);
INSERT INTO voting_system_suggestion_service.suggestion VALUES (3, 0, 'suggestion 3', 3);
INSERT INTO voting_system_suggestion_service.suggestion VALUES (4, 0, 'suggestion 4', 4);
INSERT INTO voting_system_suggestion_service.suggestion VALUES (5, 0, 'suggestion 5', 5);
INSERT INTO voting_system_suggestion_service.suggestion VALUES (6, 0, 'suggestion 6', 1);
INSERT INTO voting_system_suggestion_service.suggestion VALUES (7, 0, 'suggestion 7', 2);
INSERT INTO voting_system_suggestion_service.suggestion VALUES (8, 0, 'suggestion 8', 3);
INSERT INTO voting_system_suggestion_service.suggestion VALUES (9, 0, 'suggestion 9', 4);
INSERT INTO voting_system_suggestion_service.suggestion VALUES (10, 0, 'suggestion 10', 5);

INSERT INTO voting_system_suggestion_service.tag_suggestion VALUES (1, 1);
INSERT INTO voting_system_suggestion_service.tag_suggestion VALUES (1, 2);
INSERT INTO voting_system_suggestion_service.tag_suggestion VALUES (1, 3);
INSERT INTO voting_system_suggestion_service.tag_suggestion VALUES (1, 4);
INSERT INTO voting_system_suggestion_service.tag_suggestion VALUES (1, 5);
INSERT INTO voting_system_suggestion_service.tag_suggestion VALUES (2, 1);
INSERT INTO voting_system_suggestion_service.tag_suggestion VALUES (2, 2);
INSERT INTO voting_system_suggestion_service.tag_suggestion VALUES (2, 3);
INSERT INTO voting_system_suggestion_service.tag_suggestion VALUES (2, 4);
INSERT INTO voting_system_suggestion_service.tag_suggestion VALUES (2, 5);