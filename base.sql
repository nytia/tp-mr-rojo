create database flotte;
\c flotte 

create table utilisateur(
    id serial primary key not null,
    nom varchar(40) not null,
    mdp varchar(40) not null
);

create table avion(
    id serial primary key not null,
    idmarque int not null,
    modele varchar(40) not null,
    matricule varchar(15) not null
);

create table marque(
    id serial primary key not null,
    nommarque varchar(20) not null
);

create table assurance(
    id serial primary key not null,
    idavion int not null,
    montant float not null,
    datepayement date not null,
    dateexpiration date not null
);

-- create table assurancepaye(
--     id serial primary key not null,
--     idassurance int not null,
--     datepayement date not null
-- );

create table typeentretien(
    id serial primary key not null,
    nomtype varchar(15) not null
);

create table entretien(
    id serial primary key not null,
    idavion int not null,
    idtypeentretien int not null,
    dateentretien date not null
);

create table kilometrage(
    id serial primary key not null,
    idavion int not null,
    debutkm int,
    finkm int not null,
    datekm date not null
);

create table token(
    id serial primary key not null,
    valeur varchar(300) not null,
    expiration timestamp not null,
    idutilisateur int not null
);

alter table avion add foreign key(idmarque) references marque(id);
alter table assurance add foreign key(idavion) references avion(id);
-- alter table assurancepaye add foreign key(idassurance) references assurance(id);
alter table entretien add foreign key(idavion) references avion(id);
alter table entretien add foreign key(idtypeentretien) references typeentretien(id);
alter table kilometrage add foreign key(idavion) references avion(id);
alter table token add foreign key(idutilisateur) references utilisateur(id);

insert into utilisateur(nom,mdp) values('User1','mdp1');
insert into utilisateur(nom,mdp) values('User2','mdp2');

insert into marque(nommarque) values('Peugeot');
insert into marque(nommarque) values('Audi');
insert into marque(nommarque) values('Toyota');

insert into avion(idmarque,modele,matricule) values(1,'306','2288TBG');
insert into avion(idmarque,modele,matricule) values(2,'Q5','4095TBA');
insert into avion(idmarque,modele,matricule) values(1,'207','1130TBS');


insert into assurance(idavion,montant,datepayement,dateexpiration) values
    (1,45000,'2022-10-21','2022-12-21'),
    (3,45000,'2022-11-13','2022-12-31'),
    (3,45000,'2022-10-18','2022-01-18'),
    (4,45000,'2022-12-11','2023-02-26'),
    (3,45000,'2022-12-15','2023-03-15');

create or replace view v_avion as
    select v.id,m.nommarque as marque,v.modele,v.matricule,v.idmarque from avion v join marque m on v.idmarque=m.id order by v.id;

create or replace view v_assurance1mois as 
    select a.*,m.nommarque,v.modele,v.matricule from assurance a join avion v on a.idavion=v.id join marque m on m.id=v.idmarque where dateexpiration < (now()::date)+32   ;

create or replace view v_assurance3mois as 
    select a.*,m.nommarque,v.modele,v.matricule from assurance a join avion v on a.idavion=v.id join marque m on m.id=v.idmarque where dateexpiration < (now()::date)+95 /* and dateexpiration >(now()::date)+85 and a.dateexpiration not in (select dateexpiration from v_assurance1mois) */ ;

