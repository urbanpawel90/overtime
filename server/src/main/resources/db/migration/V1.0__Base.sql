create table overtime (
    id int not null auto_increment,
    `date` date not null,
    hours decimal(19,2) not null,
    primary key (id),
    unique key (`date`)
);
create table overtime_summary_changes (
    overtime_summary_id int not null,
    amount decimal(19,2),
    `when` datetime not null
);