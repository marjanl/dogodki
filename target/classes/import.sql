create table varnostnik (id integer, ime text);

insert into varnostnik (id, ime) values (1,'Igor');
insert into varnostnik (id, ime) values (2,'Tanja');
insert into varnostnik (id, ime) values (3,'Mirko');
insert into varnostnik (id, ime) values (4,'Polde');

create table izmenovodja(id INTEGER PRIMARY KEY AUTOINCREMENT,ime TEXT);
insert into izmenovodja (ime) values ('Dejan Potočnik');
insert into izmenovodja (ime) values ('Romana Ramšak');
insert into izmenovodja (ime) values ('Marjan Hren');
insert into izmenovodja (ime) values ('Mario Miatovič');
insert into izmenovodja (ime) values ('Tatjana Štekarnik');
insert into izmenovodja (ime) values ('Igor Centrih');
insert into izmenovodja (ime) values ('Dejan Potočnik');

create table ostalo(id INTEGER PRIMARY KEY AUTOINCREMENT,opis TEXT, vrsta text);
insert into ostalo(opis, vrsta) values('1 - od 06:00 do 14:00 ure', 'izmena');
insert into ostalo(opis, vrsta) values('2 - od 14:00 do 22:00 ure', 'izmena');
insert into ostalo(opis, vrsta) values('3 - od 22:00 do 06:00 ure', 'izmena');

insert into ostalo(opis, vrsta) values('VM1', 'varnostno mesto');
insert into ostalo(opis, vrsta) values('VM2', 'varnostno mesto');

insert into ostalo(opis, vrsta) values('Varnostni obhod', 'aktivnost');
insert into ostalo(opis, vrsta) values('Kontrola oseb', 'aktivnost');
insert into ostalo(opis, vrsta) values('Kontrola vozil', 'aktivnost');

insert into ostalo(opis, vrsta) values('Varnostni obhod', 'aktivnost');
insert into ostalo(opis, vrsta) values('Kontrola oseb', 'aktivnost');
insert into ostalo(opis, vrsta) values('Kontrola vozil', 'aktivnost');

insert into ostalo(opis, vrsta) values('Tovorni vhod 1 (VM1)','mesto dogodka');
insert into ostalo(opis, vrsta) values('Upravna stavba','mesto dogodka');
insert into ostalo(opis, vrsta) values('Upravna stavba – Garaža','mesto dogodka');
insert into ostalo(opis, vrsta) values('Jedilnica – parkirišče','mesto dogodka');
insert into ostalo(opis, vrsta) values('Tovorni vhod 3 (VM3)','mesto dogodka');
insert into ostalo(opis, vrsta) values('Plinska postaja','mesto dogodka');
insert into ostalo(opis, vrsta) values('Objekt VKN6','mesto dogodka');
insert into ostalo(opis, vrsta) values('Hladilni stolp B6','mesto dogodka');
insert into ostalo(opis, vrsta) values('Objekt Blok 1-3','mesto dogodka');
insert into ostalo(opis, vrsta) values('Objekt VKN2','mesto dogodka');
insert into ostalo(opis, vrsta) values('Stikališče','mesto dogodka');
insert into ostalo(opis, vrsta) values('Centralni silos apnenca','mesto dogodka');
insert into ostalo(opis, vrsta) values('Objekt VKN3','mesto dogodka');
insert into ostalo(opis, vrsta) values('Objekt VKN4','mesto dogodka');
insert into ostalo(opis, vrsta) values('Objekt VKN5','mesto dogodka');
insert into ostalo(opis, vrsta) values('Hladilni stolp B5','mesto dogodka');
insert into ostalo(opis, vrsta) values('RDP B5','mesto dogodka');
insert into ostalo(opis, vrsta) values('Vodikarna','mesto dogodka');
insert into ostalo(opis, vrsta) values('Stavba priučevanja','mesto dogodka');
insert into ostalo(opis, vrsta) values('Tovorni vhod 2 (VM2)','mesto dogodka');
insert into ostalo(opis, vrsta) values('Mlinska delavnica','mesto dogodka');
insert into ostalo(opis, vrsta) values('Centralno skladišče','mesto dogodka');
insert into ostalo(opis, vrsta) values('Delavnice','mesto dogodka');
insert into ostalo(opis, vrsta) values('Priprava vode 1','mesto dogodka');
insert into ostalo(opis, vrsta) values('Priprava vode 2','mesto dogodka');
insert into ostalo(opis, vrsta) values('Priprava vode 3','mesto dogodka');

create table porocilo(id INTEGER PRIMARY KEY AUTOINCREMENT,
	datum datetime,
	izmena text,
	varnostno_mesto text,
	varnostnik text,
	izmenovodja text,
	save_time datetime
	);


create table aktivnost(id INTEGER PRIMARY KEY AUTOINCREMENT,
	id_porocilo integer, 
	aktivnost TEXT, 
	od_ur integer, 
	od_min integer, 
	do_ur integer, 
	do_min integer,
	mesto_dogodka text,
	zaznamek text
	);