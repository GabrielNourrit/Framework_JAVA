insert into Types(idType,libelle) values (1,'administrateur');
insert into Types(idType,libelle) values (types_id.nextval,'utilisateur');
insert into Types(idType,libelle) values (types_id.nextval,'visiteur');

insert into utilisateurs (login, nom, prenom, motDePasse,idType,etat) values ('metzgegu', 'METZGER', 'Guillaume',  '01234',1,'VALID');
insert into utilisateurs (login, nom, prenom, motDePasse,idType,etat) values ('guevarat', 'GUEVARA', 'Thomas', '01234',2,'VALID');
insert into utilisateurs (login, nom, prenom, motDePasse,idType,etat) values ('lyuya', 'LYU', 'Yanan',  '01234',3,'VALID');

insert into groupes (idGr, libelle) values (1, 'Groupe general');
insert into groupes (idGr, libelle) values (groupes_id.nextval, 'Groupe Projet A380');
insert into groupes (idGr, libelle) values (groupes_id.nextval, 'Groupe Projet MORAT');

insert into faitPartieGroupe (login, idGr, dateEntree) values ('metzgegu',1, '12-SEP-2017');
insert into faitPartieGroupe (login, idGr, dateEntree) values ('guevarat',1, '13-SEP-2017');
insert into faitPartieGroupe (login, idGr, dateEntree) values ('metzgegu',2, '14-SEP-2017');
insert into faitPartieGroupe (login, idGr, dateEntree) values ('lyuya',1, '19-SEP-2017');
insert into faitPartieGroupe (login, idGr, dateEntree) values ('metzgegu',3, '11-SEP-2017');
insert into faitPartieGroupe (login, idGr, dateEntree) values ('guevarat',3, '15-SEP-2017');

insert into Posts (idPos, libelle, etat, login) values (1, 'Fete du village le 14 !', 'vis', 'metzgegu');
insert into Posts (idPos, libelle, etat, login) values (posts_id.nextval, 'Licenciment de 50 personnes !', 'vis', 'guevarat');

--insert into Sondage (idSon, libelle, dateDebut, dateFin, resultat, login) values (1, 'Aimez vous votre entreprise ?', '15-SEP-2017', '16-SEP-2017', '','metzgegu',1);

--insert into propositions (idPro, libelle, idSon) values (1, 'oui', 1); 
--insert into propositions (idPro, libelle, idSon) values (propositions_id.nextval, 'non', 1);

--insert into vote (idPro, login, dateVote) values (2,'metzgegu','15-SEP-2017');
--insert into vote (idPro, login, dateVote) values (2,'guevarat','15-SEP-2017');

insert into Droits(idD,libelle) values ('DMU','Droit modifier/supprimer/creer les utilisateurs'); 
insert into Droits(idD,libelle) values ('DVC','Droit de voir le tchat'); 
insert into Droits(idD,libelle) values ('DEC','Droit ecrire dans les tchats'); 
insert into Droits(idD,libelle) values ('DM','Droit de voir et ecrire des mails'); 
insert into Droits(idD,libelle) values ('DPF','Droit de poser des fichiers'); 
insert into Droits(idD,libelle) values ('DTF','Droit de telelcharger des fichiers'); 
insert into Droits(idD,libelle) values ('DMT','Droit de modifier/creer/supprimer les types'); 
insert into Droits(idD,libelle) values ('DCG','Droit de créer des groupes'); 
insert into Droits(idD,libelle) values ('DMG','Droit de modfier/supprimer les groupes');
insert into Droits(idD,libelle) values ('DCS','Droit de creer des sondages');  
insert into Droits(idD,libelle) values ('DVS','Droit de voter dans les sondages'); 

insert into possede(idD,idType) values('DMU',1);
insert into possede(idD,idType) values('DVC',1);
insert into possede(idD,idType) values('DEC',1);
insert into possede(idD,idType) values('DM',1);
insert into possede(idD,idType) values('DPF',1);
insert into possede(idD,idType) values('DTF',1);
insert into possede(idD,idType) values('DMT',1);
insert into possede(idD,idType) values('DCG',1);
insert into possede(idD,idType) values('DMG',1);
insert into possede(idD,idType) values('DCS',1);
insert into possede(idD,idType) values('DVS',1);
insert into possede(idD,idType) values('DVC',2);
insert into possede(idD,idType) values('DEC',2);
insert into possede(idD,idType) values('DM',2);
insert into possede(idD,idType) values('DPF',2);
insert into possede(idD,idType) values('DTF',2);
insert into possede(idD,idType) values('DCG',2);
insert into possede(idD,idType) values('DCS',2);
insert into possede(idD,idType) values('DVS',2);
insert into possede(idD,idType) values('DVC',3);
insert into possede(idD,idType) values('DTF',3);
insert into possede(idD,idType) values('DVS',3);


insert into Fichiers (idFic,nom,dateArrive,url,loginExpediteur,idReceveur) values (1,'fic1','15-SEP-2017','ressources/stockage/fichier','guevarat',1);
insert into Fichiers (idFic,nom,dateArrive,url,loginExpediteur,idReceveur) values (fichiers_id.nextval,'fic2','16-SEP-2017','ressources/stockage/fichier','metzgegu',1);
insert into Fichiers (idFic,nom,dateArrive,url,loginExpediteur,idReceveur) values (fichiers_id.nextval,'fic3','17-SEP-2017','ressources/stockage/fichier','guevarat',2);

insert into Mails(idMai,dateArrive,url,etat,objet,loginExpediteur,loginReceveur) values (1,'05-JAN-2018','ressources/stockage/mail','VAL','t es beau','guevarat','metzgegu'); 
insert into Mails(idMai,dateArrive,url,etat,objet,loginExpediteur,loginReceveur) values (mails_id.nextval,'06-JAN-2018','ressources/stockage/mail','SUPEN','t es moche','lyuya','metzgegu'); 
insert into Mails(idMai,dateArrive,url,etat,objet,loginExpediteur,loginReceveur) values (mails_id.nextval,'07-JAN-2018','ressources/stockage/mail','SUPRE','t es moyen','metzgegu','lyuya');


create or replace trigger plus_un_total
after insert or update on vote
for each row
declare
	totals int;
begin
	select total into totals from sondage where idSon=:new.idSon;
	update sondage set total=totals+1 where idSon=:new.idSon;
end;
/