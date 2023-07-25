INSERT INTO Person (PersonID,FirstName,LastName,UserName,Password,Email)
Values (0000000000,'Daniel','West','Blue','Fire1234','DanielWest618@gmail.com');

Insert into list (ListID,ListName,ListDescription)
values (0,'mine','dez');

Insert into TypeOfAccess (AccessType)
values ('own');

Insert into Access (PersonID,ListID,AccessType)
values ((Select PersonID from Person where username='Blue' and password ='Fire1234'),0,'own');


Select * from Person
INNER JOIN Access on Person.PersonID = Access.PersonID
INNER JOIN List on Access.ListID = List.ListID
where username='Blue' and password ='Fire1234';

UPDATE List
SET listtext = 'pie'
where listid in (
    Select list.listid from Person
    INNER JOIN Access on Person.PersonID = Access.PersonID
    INNER JOIN List on Access.ListID = List.ListID
    where username='Blue' and password ='Fire1234' and list.listid = 0
);

select * from list;