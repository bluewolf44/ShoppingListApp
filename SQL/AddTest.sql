--INSERT INTO Person (PersonID,FirstName,LastName,UserName,Password,Email)
--Values (0000000000,'Daniel','West','Blue','Fire1234','DanielWest618@gmail.com');

--Insert into list (ListID,ListName,ListDescription)
--values (0,'mine','dez');

--Insert into TypeOfAccess (AccessType)
--values ('bas');

--Insert into Access (PersonID,ListID,AccessType)
--values (0000000000,0000000000,'bas');


Select * from Person;
--INNER JOIN Access on Person.PersonID = Access.PersonID
--where username='Blue' and password ='Fire1234';