INSERT INTO Person (FirstName,LastName,UserName,Password,Email)
Values ('Daniel','West','Blue','Fire1234','DanielWest618@gmail.com');

Insert into list (ListID,ListName,ListDescription)
values (0,'mine','dez');

Insert into TypeOfAccess (AccessType)
values ('own');

Insert into TypeOfAccess (AccessType)
values ('vie');

Insert into TypeOfAccess (AccessType)
values ('edi');

Insert into Access (username,ListID,AccessType)
values ((Select username from Person where username='Blue' and password ='Fire1234'),0,'own');


Select * from Person
INNER JOIN Access on Person.username = Access.username
INNER JOIN List on Access.ListID = List.ListID
where person.username='Blue' and password ='Fire1234';

UPDATE List
SET listtext = 'pie'
where listid in (
    Select list.listid from Person
    INNER JOIN Access on Person.username = Access.username
    INNER JOIN List on Access.ListID = List.ListID
    where person.username='Blue' and password ='Fire1234' and list.listid = 0
);

select * from list;
select * from access;

Select * from Person ;

select * from access
where listid in (Select list.listID from Person
    INNER JOIN Access on Person.username = Access.username
    INNER JOIN List on Access.ListID = List.ListID
    where person.username='Blue' and password ='Fire1234' and list.listID=0
) and username = 'DaveSmith';

update Access set AccessType='edi'
where listid in (Select list.listID from Person
    INNER JOIN Access on Person.username = Access.username
    INNER JOIN List on Access.ListID = List.ListID
    where person.username='Blue' and password ='Fire1234' and list.listID=0
) and username = 'DaveSmith';

delete from access
where listId in (
    Select list.listId from Person
    INNER JOIN Access on Person.username = Access.username
    INNER JOIN List on Access.ListID = List.ListID
    where person.username='Blue' and password ='Fire1234'and list.listid=2
);


delete from list
where listId in (
    Select list.listId from Person
    INNER JOIN Access on Person.username = Access.username
    INNER JOIN List on Access.ListID = List.ListID
    where person.username='Blue' and password ='Fire1234' and list.listid=4
);

Select * from Person
INNER JOIN Access on Person.username = Access.username
INNER JOIN List on Access.ListID = List.ListID
where person.username='Blue' and password ='Fire1234'