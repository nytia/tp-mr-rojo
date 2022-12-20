create database flotte2;
\c flotte2 

create table utilisateur(
    id serial primary key not null,
    nom varchar(40) not null,
    mdp varchar(40) not null
);

create table avion(
    id serial primary key not null,
    idmarque int not null,
    modele varchar(40) not null,
    matricule varchar(15) not null,
    img text ,
    nombrePlace int not null
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

insert into marque(nommarque) values('Dassault ');
insert into marque(nommarque) values('Beechcraft ');
insert into marque(nommarque) values('Cessna ');

insert into avion(idmarque,modele,matricule,nombrePlace,img) values(1,'737','188ISZNSZ',90,'avion.jpg');
insert into avion(idmarque,modele,matricule,nombrePlace,img) values(2,'747','1TYZN178',110,'avion.jpg');
insert into avion(idmarque,modele,matricule,nombrePlace,img) values(1,'787','YAUHNZ98189',130,'avion.jpg');

insert into kilometrage(idavion,debutkm,finkm,datekm) values
    (1,5000,24000,'2022-12-04'),
    (2,5000,8000,'2022-12-05'),
    (3,5000,12000,'2022-12-07'),
    (1,24000,31000,'2022-12-13');

insert into assurance(idavion,montant,datepayement,dateexpiration) values
    (1,45000,'2022-10-21','2022-12-21'),
    (2,45000,'2022-11-13','2022-12-31'),
    (2,45000,'2022-10-18','2022-01-18'),
    (3,45000,'2022-12-11','2023-02-26'),
    (1,45000,'2022-12-15','2023-03-15');

create or replace view v_avion as 
    select v.id,m.nommarque as marque,v.modele,v.matricule,v.idmarque,v.img,v.nombrePlace from avion v join marque m on v.idmarque=m.id order by v.id;

create or replace view v_assurance1mois as 
    select a.*,m.nommarque,v.modele,v.matricule from assurance a join avion v on a.idavion=v.id join marque m on m.id=v.idmarque where dateexpiration < (now()::date)+32   ;

create or replace view v_assurance3mois as 
    select a.*,m.nommarque,v.modele,v.matricule from assurance a join avion v on a.idavion=v.id join marque m on m.id=v.idmarque where dateexpiration < (now()::date)+95 /* and dateexpiration >(now()::date)+85 and a.dateexpiration not in (select dateexpiration from v_assurance1mois) */ ;

create or replace view v_kilometrage as
    select k.idavion,sum(k.finkm-k.debutkm) as totalkm from kilometrage k group by k.idavion ;

create or replace view v_detail as 
    select k.*,a.matricule,a.nombrePlace from v_kilometrage k join avion a on a.id=k.idavion;

-- create or replace view v_token as
--     select t.*,u.nom


-- create table test(
--     image bytea 
-- );
-- insert into test values('');