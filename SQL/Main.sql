--jdbc:postgresql://localhost:5432/postgres

drop table Access;
drop table TypeOfAccess;
drop table List;
drop table Person;

Create Table Person
(
    PersonID Numeric(10,0) not null,
    FirstName varchar(255) not null,
    LastName varchar(255) not null,
    
    UserName varchar(255) not null,
    Password varchar(255) not null,
    Email varchar(255) not null,
    IsVerified Boolean DEFAULT false not null,

    CONSTRAINT PK_Person PRIMARY KEY (PersonID)
);

Create Table List
(
    ListID Numeric(10,0) not null,
    ListText text DEFAULT '' not null,
    lastUpdated timestamp DEFAULT CURRENT_TIMESTAMP not null,
    DateCreated timestamp DEFAULT CURRENT_TIMESTAMP not null,

    CONSTRAINT PK_List PRIMARY KEY (ListID)
);

Create Table TypeOfAccess
(
    AccessType varchar(3) not null,
    CONSTRAINT PK_TypeOfAccess PRIMARY KEY (AccessType)
);

Create Table Access
(
    PersonID Numeric(10,0) not null,
    ListID Numeric(10,0) not null,
    AccessType varchar(3) not null,

    CONSTRAINT FK_PersonOrder FOREIGN KEY (PersonID) REFERENCES Person(PersonID),
    CONSTRAINT FK_LsitOrder FOREIGN KEY (ListID) REFERENCES List(ListID),
    CONSTRAINT FK_Type FOREIGN KEY (AccessType) REFERENCES TypeOfAccess(AccessType)
);

