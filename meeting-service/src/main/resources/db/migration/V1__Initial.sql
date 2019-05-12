create table meeting
(
  id    bigint auto_increment
    primary key,
  start datetime not null
)
  engine = MyISAM;

INSERT INTO voting_system_meeting_service.meeting VALUES (null, '2019-01-05T20:00:00');
INSERT INTO voting_system_meeting_service.meeting VALUES (null, '2019-02-05T20:00:00');
INSERT INTO voting_system_meeting_service.meeting VALUES (null, '2019-03-05T20:00:00');
INSERT INTO voting_system_meeting_service.meeting VALUES (null, '2019-04-05T20:00:00');
INSERT INTO voting_system_meeting_service.meeting VALUES (null, '2019-05-05T20:00:00');