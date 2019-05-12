create table voting_system_meeting_service.meeting
(
  id    bigint   not null
    primary key,
  start datetime not null
)
  engine = MyISAM;

INSERT INTO voting_system_meeting_service.meeting VALUES (1, '2019-01-05T20:00:00');
INSERT INTO voting_system_meeting_service.meeting VALUES (2, '2019-02-05T20:00:00');
INSERT INTO voting_system_meeting_service.meeting VALUES (3, '2019-03-05T20:00:00');
INSERT INTO voting_system_meeting_service.meeting VALUES (4, '2019-04-05T20:00:00');
INSERT INTO voting_system_meeting_service.meeting VALUES (5, '2019-05-05T20:00:00');