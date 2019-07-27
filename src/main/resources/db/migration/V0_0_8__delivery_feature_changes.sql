INSERT INTO mstr_role VALUES
(3,1,now(),'ROLE_DELIVERY_BOY'),
(0,1,now(),'ROLE_SUPER_ADMIN');

CREATE TABLE `user_settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `record_status` int(11) NOT NULL,
  `created_on` datetime NOT NULL,

  `user_id` bigint(20) NOT NULL,
  `settings` json NOT NULL,

  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_settings_user_id` (`user_id`),
  CONSTRAINT `fk_user_settings_user_id`
    FOREIGN KEY (`user_id`) REFERENCES `mstr_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `app_settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `record_status` int(11) NOT NULL,
  `created_on` datetime NOT NULL,

  `settings` json NOT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `app_settings`
(`id`,
`record_status`,
`created_on`,
`settings`)
VALUES
(1, 1, now(), '{ "localityGroup" : {
	"1" : "Alevoor,Achalady,AdiUdupi,AdarshaNagar,AnanthNagar,VibudapriyaNagar,Ambalpadi,Athradi,Badanidiyoor,Bailur,Bannanje,Budnaru,Brahmagiri,Chitpady,Doddanagudde,CPCLayout,CourtRoad",
	"2" : "DeviNagar,EndPointRoad,Moodabettu,CityBusStand,Gopalpura,Herga,HayagrivaNagar,HudcoColony,Indrali,IndrustrialArea,IshwaraNagar,IndiraNagar,Kadekar,Kadabettu,Kallianpur,Kelarkalabettu",
	"3" : "Katapady,Kaup,Kinnimulki,Kidiyoor,KMMarg,Kodankur,Kolalagiri,Korangrapady,KasturbaNagar,Kukkikatte,Kunjibettu,Kuthpady,Malpe,Manipal,MIT,MGMCollege,Marpalli",
	"4" : "MaruthiVeethika,Mattu,Nittur,Parkala,Padukere,Perampalli,Pithrody,Santhekatte,SampigeNagar,Saralebettu,ShanthiNagar,Shivalli,Tenkanidyoor,Thenkpete,TonseWest,Udyavara,VidyarathnaNagar"
}}');

UPDATE app_settings SET settings = JSON_SET(settings,
'$.localityList', 'Alevoor,Achalady,AdiUdupi,AdarshaNagar,AnanthNagar,VibudapriyaNagar,Ambalpadi,Athradi,
Badanidiyoor,Bailur,Bannanje,Budnaru,Brahmagiri,Chitpady,Doddanagudde,CPCLayout,CourtRoad,DeviNagar,
EndPointRoad,Moodabettu,CityBusStand,Gopalpura,Herga,HayagrivaNagar,HudcoColony,Indrali,IndrustrialArea,
IshwaraNagar,IndiraNagar,Kadekar,Kadabettu,Kallianpur,Kelarkalabettu,Katapady,Kaup,Kinnimulki,Kidiyoor,
KMMarg,Kodankur,Kolalagiri,Korangrapady,KasturbaNagar,Kukkikatte,Kunjibettu,Kuthpady,Malpe,Manipal,
MIT,MGMCollege,Marpalli,MaruthiVeethika,Mattu,Nittur,Parkala,Padukere,Perampalli,Pithrody,Santhekatte,
SampigeNagar,Saralebettu,ShanthiNagar,Shivalli,Tenkanidyoor,Thenkpete,TonseWest,Udyavara,VidyarathnaNagar')
WHERE id = 1;
--UPDATE JSON OBJECT QUERY

--UPDATE app_settings SET settings = JSON_SET(settings,
-- '$.localityGroup', JSON_OBJECT('1', 'abc, def, ghi', '2', 'jkl, mno, pqr'))
--WHERE id = 1;