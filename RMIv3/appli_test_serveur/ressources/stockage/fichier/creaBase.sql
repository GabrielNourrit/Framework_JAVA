drop table possede;
drop table Droits;
drop table Mails;
drop table vote;
drop table propositions;
drop table Sondage;
drop table Posts;
drop table Fichiers;
drop table faitPartieGroupe;
drop table groupes;
drop table utilisateurs;
drop table Types;

drop sequence types_id;
drop sequence groupes_id;
drop sequence fichiers_id;
drop sequence posts_id;
drop sequence sondage_id;
drop sequence propositions_id;
drop sequence mails_id;
drop sequence droits_id;

create sequence types_id start with 2;
create sequence groupes_id start with 2;
create sequence fichiers_id start with 2;
create sequence posts_id start with 2;
CREATE SEQUENCE sondage_id 
  START WITH 0 
  INCREMENT BY 1 
  MINVALUE 0 
  NOCACHE 
  NOCYCLE;
create sequence propositions_id start with 2;
create sequence mails_id start with 2;
create sequence droits_id start with 2;


create table Types(idType integer primary key, libelle varchar(50));

create table utilisateurs ( login varchar(50) primary key,nom varchar(50),prenom varchar(50),motDePasse varchar(100) not null,idType integer,etat varchar(5),dateNaissance date,description varchar(500),foreign key(idType) references Types(idType));

create table groupes ( idGr integer primary key, libelle varchar(100));

create table faitPartieGroupe(login varchar(50), idGr integer , dateEntree date , primary key (login,idGr),foreign key(login) references utilisateurs(login),foreign key(idGr) references groupes(idGr));

create table Fichiers (idFic integer primary key, dateArrive date,nom varchar(50),url varchar(250), loginExpediteur varchar(50), idReceveur integer, foreign key(loginExpediteur) references utilisateurs(login), foreign key (idReceveur) references groupes(idGr));

create table Posts (idPos integer primary key,libelle varchar(100),etat varchar(5),login varchar(50), foreign key(login) references utilisateurs(login));

create table Sondage(
	idSon integer primary key,
	libelle varchar(100),
	dateDebut date,
	dateFin date,
	resultat varchar(250),
	login varchar(50),
	multiple int,
	total int,
	foreign key (login) references utilisateurs(login)
);

create table propositions(idPro integer primary key ,libelle varchar(100), idSon integer, foreign key (idSon) references Sondage(idSon));


create table vote(
	idSon integer,
	login varchar(50),
	dateVote date,
	primary key(idSon,login),
	foreign key(login) references utilisateurs(login),
	foreign key(idSon) references sondage(idSon)
);

create table Mails(idMai integer primary key, dateArrive date,url varchar(250),etat varchar(5),objet varchar(50),loginExpediteur varchar(50),loginReceveur varchar(50), foreign key (loginExpediteur) references utilisateurs(login),foreign key (loginReceveur) references utilisateurs(login));

create table Droits(idD varchar(10) primary key,libelle varchar(50));

create table possede (idD varchar(10),idType integer ,primary key(idD,idType),foreign key(idD) references Droits(idD),foreign key(idType) references Types(idType));