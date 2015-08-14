# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table attends (
  user_id                   integer,
  event_id                  integer,
  reserved_at               datetime(6))
;

create table events (
  id                        integer auto_increment not null,
  name                      varchar(255),
  start_date                datetime(6),
  company_id                integer,
  number_of_attendees       integer,
  constraint pk_events primary key (id))
;

create table users (
  id                        integer auto_increment not null,
  name                      varchar(255),
  group_id                  integer,
  constraint pk_users primary key (id))
;

alter table events add constraint fk_events_company_1 foreign key (company_id) references users (id) on delete restrict on update restrict;
create index ix_events_company_1 on events (company_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table attends;

drop table events;

drop table users;

SET FOREIGN_KEY_CHECKS=1;

