# DBTools DBMYSQL - MySQL Database Dump
#

#USE BattleTechDB;

# Dumping Table Structure for bf_dropship

#
CREATE TABLE `bf_dropship` (
  `DropshipID` int(50) NOT NULL auto_increment,
  `UnitID` int(50) default NULL,
  `Mech` int(50) default NULL,
  `Panzer` int(50) default NULL,
  `Infantrie` int(50) default NULL,
  `Ladung` int(50) default NULL,
  `UnittypeID` int(50) default NULL,
  PRIMARY KEY  (`DropshipID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_dropship
#
INSERT INTO bf_dropship (DropshipID, UnitID, Mech, Panzer, Infantrie, Ladung, UnittypeID) VALUES(88, 86, 0, 0, 0, 0, NULL);
INSERT INTO bf_dropship (DropshipID, UnitID, Mech, Panzer, Infantrie, Ladung, UnittypeID) VALUES(2, 12, 2, 0, 1, 240, NULL);
INSERT INTO bf_dropship (DropshipID, UnitID, Mech, Panzer, Infantrie, Ladung, UnittypeID) VALUES(73, 11, 0, 0, 0, 0, NULL);
# Dumping Table Structure for bf_games

#
CREATE TABLE `bf_games` (
  `GameID` int(50) unsigned NOT NULL auto_increment,
  `gameName` varchar(50) NOT NULL default '',
  `RoundDate` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`GameID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_games
#
INSERT INTO bf_games (GameID, gameName, RoundDate) VALUES(3, 'Test Game', '01.01.2750');
INSERT INTO bf_games (GameID, gameName, RoundDate) VALUES(4, 'First Game', '01.01.3052');
# Dumping Table Structure for bf_gameuser

#
CREATE TABLE `bf_gameuser` (
  `GameUserID` int(50) unsigned NOT NULL auto_increment,
  `GameID` int(50) NOT NULL default '0',
  `UserID` int(50) NOT NULL default '0',
  `OwnerID` int(50) default NULL,
  `RoundDate` varchar(50) NOT NULL default '',
  PRIMARY KEY  (`GameUserID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_gameuser
#
INSERT INTO bf_gameuser (GameUserID, GameID, UserID, OwnerID, RoundDate) VALUES(1, 3, 1, 1, '01.01.2750');
INSERT INTO bf_gameuser (GameUserID, GameID, UserID, OwnerID, RoundDate) VALUES(2, 3, 2, 36, '01.01.2750');
INSERT INTO bf_gameuser (GameUserID, GameID, UserID, OwnerID, RoundDate) VALUES(3, 3, 3, 45, '01.01.2750');
INSERT INTO bf_gameuser (GameUserID, GameID, UserID, OwnerID, RoundDate) VALUES(4, 3, 4, 5, '01.01.2750');
INSERT INTO bf_gameuser (GameUserID, GameID, UserID, OwnerID, RoundDate) VALUES(5, 4, 1, 10, '01.01.3052');
INSERT INTO bf_gameuser (GameUserID, GameID, UserID, OwnerID, RoundDate) VALUES(6, 4, 4, 1, '01.01.3052');
# Dumping Table Structure for bf_jumpship

#
CREATE TABLE `bf_jumpship` (
  `JumpshipID` int(10) unsigned NOT NULL auto_increment,
  `ShipPropertyID` int(11) default NULL,
  `JumpCharges` int(11) default NULL,
  `X` float(32,2) default NULL,
  `Y` float(32,2) default NULL,
  `Z` float(32,2) default NULL,
  `UnitID` int(50) default NULL,
  PRIMARY KEY  (`JumpshipID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_jumpship
#
INSERT INTO bf_jumpship (JumpshipID, ShipPropertyID, JumpCharges, X, Y, Z, UnitID) VALUES(2, 4, 1, 0.00, 0.00, 0.00, 2);
INSERT INTO bf_jumpship (JumpshipID, ShipPropertyID, JumpCharges, X, Y, Z, UnitID) VALUES(3, 1, 2, 0.00, 0.00, 0.00, 3);
INSERT INTO bf_jumpship (JumpshipID, ShipPropertyID, JumpCharges, X, Y, Z, UnitID) VALUES(1, 4, 1, 0.00, 0.00, 0.00, 1);
INSERT INTO bf_jumpship (JumpshipID, ShipPropertyID, JumpCharges, X, Y, Z, UnitID) VALUES(100, 0, 1, 0.00, 0.00, 0.00, 98);
INSERT INTO bf_jumpship (JumpshipID, ShipPropertyID, JumpCharges, X, Y, Z, UnitID) VALUES(80, 0, 1, 0.00, 0.00, 0.00, 78);
# Dumping Table Structure for bf_logicalunit

#
CREATE TABLE `bf_logicalunit` (
  `logicalunitid` int(50) NOT NULL auto_increment,
  `logicalunitname` varchar(50) default NULL,
  PRIMARY KEY  (`logicalunitid`)
) TYPE=MyISAM;
#
# Dumping Data for bf_logicalunit
#
INSERT INTO bf_logicalunit (logicalunitid, logicalunitname) VALUES(1, 'Division One');
INSERT INTO bf_logicalunit (logicalunitid, logicalunitname) VALUES(2, 'Divison Two');
INSERT INTO bf_logicalunit (logicalunitid, logicalunitname) VALUES(3, 'Galaxie One');
INSERT INTO bf_logicalunit (logicalunitid, logicalunitname) VALUES(4, 'Zug One');
INSERT INTO bf_logicalunit (logicalunitid, logicalunitname) VALUES(5, 'Zug Two');
INSERT INTO bf_logicalunit (logicalunitid, logicalunitname) VALUES(6, 'Zug Ten');
# Dumping Table Structure for bf_logicalunithierarchy

#
CREATE TABLE `bf_logicalunithierarchy` (
  `logicalunithierarchyID` int(50) NOT NULL auto_increment,
  `parentid` int(50) default NULL,
  `childid` int(50) default NULL,
  PRIMARY KEY  (`logicalunithierarchyID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_logicalunithierarchy
#
INSERT INTO bf_logicalunithierarchy (logicalunithierarchyID, parentid, childid) VALUES(4, 3, 1);
INSERT INTO bf_logicalunithierarchy (logicalunithierarchyID, parentid, childid) VALUES(2, 3, 2);
INSERT INTO bf_logicalunithierarchy (logicalunithierarchyID, parentid, childid) VALUES(3, 1, 4);
INSERT INTO bf_logicalunithierarchy (logicalunithierarchyID, parentid, childid) VALUES(5, 1, 5);
INSERT INTO bf_logicalunithierarchy (logicalunithierarchyID, parentid, childid) VALUES(6, 2, 6);
# Dumping Table Structure for bf_mech

#
CREATE TABLE `bf_mech` (
  `MechID` int(50) NOT NULL auto_increment,
  `UnitID` int(50) default NULL,
  PRIMARY KEY  (`MechID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_mech
#
INSERT INTO bf_mech (MechID, UnitID) VALUES(1, 13);
INSERT INTO bf_mech (MechID, UnitID) VALUES(2, 14);
INSERT INTO bf_mech (MechID, UnitID) VALUES(3, 15);
INSERT INTO bf_mech (MechID, UnitID) VALUES(94, 92);
# Dumping Table Structure for bf_owner

#
CREATE TABLE `bf_owner` (
  `OwnerID` int(10) unsigned NOT NULL auto_increment,
  `Owner` varchar(255) default NULL,
  `Color` varchar(12) default NULL,
  `Logo` varchar(255) default NULL,
  `Ownertype` int(11) default NULL,
  `StartAreaID` int(11) default NULL,
  PRIMARY KEY  (`OwnerID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_owner
#
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(1, 'Neutral', '204,255,204', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(2, 'Arc Royal Defense Cordon', '255,255,255', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(3, 'Capellan Confederation', '255,255,0', 'Cappelan Confederation.jpg', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(4, 'Comstar', '255,204,204', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(5, 'Draconis Combine', '0,0,204', 'cwovsm.gif', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(6, 'Federated Commonwealth', '255,255,0', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(7, 'Free Rasalhague Republic', '0,153,153', 'Free Rasalhague.jpg', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(8, 'Federated Suns', '204,255,204', 'fedcomsm.gif', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(9, 'Free Worlds League', '51,204,255', 'Free Worldsleague.jpg', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(10, 'Lyran Alliance', '255,51,51', 'lyransm.gif', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(11, 'Lyran Commonwealth', '255,204,255', 'Lyran Commonwealth.jpg', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(12, 'St. Ives Compact', '0,102,51', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(13, 'Tikonov Free Republic', '102,0,102', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(14, 'Terran Hegemony', '0,153,153', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(15, 'Word of Blake', '67,22,233', 'Word Of Blake.jpg', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(16, 'Circinus Federation', '255,51,51', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(17, 'Elysian Fields', '102,255,102', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(18, 'Greater Valkrate', '0,153,153', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(19, 'Illyrian Palatinate', '153,0,153', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(20, 'Marian Hegemony', '153,153,153', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(21, 'Magistry of Cannopus', '102,102,102', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(22, 'Outworlds Alliance', '102,255,204', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(23, 'Oberon Confederation', '51,51,51', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(24, 'New Colony Region', '102,51,0', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(25, 'Periphery (Other)', '255,0,204', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(26, 'Rim Collection', '255,102,153', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(27, 'Rim Worlds Republic', '255,102,102', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(28, 'Taurian Concordat', '0,0,102', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(29, 'Tortuga Dominions', '113,255,0', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(30, 'Clan Ghost Bears', '255,204,204', 'cgb.gif', 1, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(31, 'Clan Hell\'s Horses', '153,0,102', '', 1, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(32, 'Clan Jade Falcons', '0,0,153', 'cjf.gif', 1, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(33, 'Clan Nova Cats', '252,123,122', '', 1, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(34, 'Clan Smoke Jaguars', '68,167,75', '', 1, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(35, 'Clan Steel Vipers', '0,102,102', '', 1, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(36, 'Clan Wolf', '255,51,255', 'cw.gif', 1, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(37, 'Chaos March with Capellan Influence', '255,255,0', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(38, 'Chaos March Disputed', '0,255,255', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(39, 'Combine Peacekeepers', '255,0,255', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(40, 'Duchy of Small', '88,89,20', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(41, 'Styk Commonality', '255,171,45', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(42, 'Sarna Supremacy', '102,0,102', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(43, 'Saiph Triumvirate', '252,105,0', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(44, 'Terracap Confederation', '170,167,170', '', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(45, 'Kell Hounds', '255,153,153', 'Kell Hounds.jpg', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(46, 'Clan Wolf In Exile', '153,153,153', 'Clan Wolf In Exile', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(47, 'House Davion', '80,80,80', 'davionsm.gif', 0, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(48, 'test', '255,255,51', 'test.jpg', NULL, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(49, 'test bearbeitet', '204,0,204', 'test2.jpg bearbeitet', NULL, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(50, 'test neu 2', '204,255,204', '', NULL, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(51, 't34_abc', '', '', NULL, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(52, 't35gg', '102,0,51', '', NULL, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(53, 't36zzz', '153,0,153', '', NULL, NULL);
INSERT INTO bf_owner (OwnerID, Owner, Color, Logo, Ownertype, StartAreaID) VALUES(54, 'test neue Fraktion', '102,0,255', '', NULL, NULL);
# Dumping Table Structure for bf_ownertype

#
CREATE TABLE `bf_ownertype` (
  `OwnerTypeID` int(50) NOT NULL auto_increment,
  `OwnerType` varchar(50) default NULL,
  `OwnerTypeKurz` varchar(50) default NULL,
  PRIMARY KEY  (`OwnerTypeID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_ownertype
#
INSERT INTO bf_ownertype (OwnerTypeID, OwnerType, OwnerTypeKurz) VALUES(1, 'Innere Sphere', 'IS');
INSERT INTO bf_ownertype (OwnerTypeID, OwnerType, OwnerTypeKurz) VALUES(2, 'Clan', 'CL');
INSERT INTO bf_ownertype (OwnerTypeID, OwnerType, OwnerTypeKurz) VALUES(3, 'Söldner', 'S');
INSERT INTO bf_ownertype (OwnerTypeID, OwnerType, OwnerTypeKurz) VALUES(4, 'Pirat', 'P');
# Dumping Table Structure for bf_populationstatus

#
CREATE TABLE `bf_populationstatus` (
  `PopulationStatusID` int(10) unsigned NOT NULL auto_increment,
  `PopulationStatus` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`PopulationStatusID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_populationstatus
#
INSERT INTO bf_populationstatus (PopulationStatusID, PopulationStatus) VALUES(1, 'normal');
INSERT INTO bf_populationstatus (PopulationStatusID, PopulationStatus) VALUES(2, 'zufrieden');
INSERT INTO bf_populationstatus (PopulationStatusID, PopulationStatus) VALUES(3, 'unzufrieden');
INSERT INTO bf_populationstatus (PopulationStatusID, PopulationStatus) VALUES(4, 'revolte');
INSERT INTO bf_populationstatus (PopulationStatusID, PopulationStatus) VALUES(5, 'die Bevölkerung preist sie in den Himmel');
# Dumping Table Structure for bf_sequencer

#
CREATE TABLE `bf_sequencer` (
  `Sequencerid` int(50) unsigned NOT NULL auto_increment,
  `Userid` int(50) default NULL,
  PRIMARY KEY  (`Sequencerid`)
) TYPE=MyISAM;
#
# Dumping Data for bf_sequencer
#
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(1, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(2, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(3, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(4, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(5, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(6, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(7, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(8, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(9, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(10, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(11, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(12, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(13, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(14, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(15, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(16, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(17, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(18, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(19, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(20, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(21, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(22, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(23, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(24, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(25, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(26, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(27, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(28, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(29, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(30, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(31, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(32, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(33, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(34, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(35, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(36, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(37, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(38, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(39, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(40, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(41, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(42, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(43, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(44, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(45, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(46, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(47, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(48, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(49, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(50, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(51, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(52, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(53, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(54, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(55, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(56, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(57, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(58, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(59, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(60, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(61, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(62, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(63, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(64, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(65, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(66, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(67, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(68, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(69, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(70, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(71, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(72, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(73, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(74, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(75, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(76, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(77, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(78, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(79, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(80, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(81, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(82, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(83, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(84, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(85, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(86, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(87, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(88, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(89, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(90, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(91, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(92, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(93, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(94, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(95, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(96, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(97, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(98, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(99, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(100, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(101, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(102, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(103, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(104, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(105, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(106, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(10000, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(10001, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(10002, 1);
INSERT INTO bf_sequencer (Sequencerid, Userid) VALUES(10003, 1);
# Dumping Table Structure for bf_session

#
CREATE TABLE `bf_session` (
  `SessionID` int(50) unsigned NOT NULL auto_increment,
  `UserID` int(50) NOT NULL default '0',
  `Login` varchar(19) NOT NULL default '',
  `Logout` varchar(19) default NULL,
  `GameID` int(50) NOT NULL default '0',
  PRIMARY KEY  (`SessionID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_session
#
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(237, 1, '15.11.2004 19:53:29', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(2, 1, '3.10.2004 16:34:21', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(3, 1, '3.10.2004 16:37:42', '3.10.2004 16:45:48', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(4, 1, '3.10.2004 18:18:15', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(5, 1, '3.10.2004 18:21:16', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(6, 1, '3.10.2004 18:23:57', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(7, 1, '3.10.2004 18:25:13', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(8, 1, '3.10.2004 18:26:47', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(9, 1, '3.10.2004 18:30:13', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(10, 1, '5.10.2004 22:1:57', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(11, 1, '5.10.2004 22:2:35', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(12, 1, '5.10.2004 22:4:15', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(13, 1, '5.10.2004 22:5:20', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(14, 1, '5.10.2004 22:9:24', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(15, 1, '5.10.2004 22:11:9', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(16, 1, '5.10.2004 22:12:11', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(17, 1, '5.10.2004 22:15:52', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(18, 1, '5.10.2004 22:17:10', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(19, 1, '5.10.2004 22:18:3', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(20, 1, '5.10.2004 22:20:48', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(21, 1, '5.10.2004 22:32:47', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(22, 1, '5.10.2004 22:37:36', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(23, 1, '5.10.2004 22:45:20', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(24, 1, '5.10.2004 22:47:24', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(25, 1, '5.10.2004 22:59:42', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(26, 1, '5.10.2004 23:1:44', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(27, 1, '5.10.2004 23:5:48', '5.10.2004 23:8:31', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(28, 1, '5.10.2004 23:10:14', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(29, 1, '5.10.2004 23:13:35', '5.10.2004 23:17:41', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(30, 1, '6.10.2004 19:43:35', '6.10.2004 19:47:18', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(31, 1, '6.10.2004 19:47:34', '6.10.2004 19:49:26', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(32, 1, '6.10.2004 19:58:52', '6.10.2004 20:1:30', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(33, 1, '6.10.2004 21:8:9', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(34, 1, '6.10.2004 21:9:47', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(35, 1, '6.10.2004 21:12:14', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(36, 1, '6.10.2004 21:13:42', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(37, 1, '6.10.2004 21:14:50', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(38, 1, '6.10.2004 21:16:15', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(39, 1, '6.10.2004 21:17:53', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(40, 1, '6.10.2004 21:20:30', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(41, 1, '6.10.2004 21:21:36', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(42, 1, '6.10.2004 21:23:21', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(43, 1, '6.10.2004 21:26:34', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(44, 1, '6.10.2004 21:30:45', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(45, 1, '6.10.2004 21:32:43', '6.10.2004 21:33:29', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(46, 1, '6.10.2004 21:34:36', '6.10.2004 21:35:6', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(47, 1, '6.10.2004 21:36:16', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(48, 1, '10.10.2004 19:35:48', '10.10.2004 19:36:36', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(49, 1, '11.10.2004 20:32:41', '11.10.2004 20:33:16', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(50, 1, '11.10.2004 21:1:8', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(51, 1, '11.10.2004 21:3:33', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(52, 1, '11.10.2004 21:57:6', '11.10.2004 22:0:49', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(53, 1, '13.10.2004 21:20:7', '13.10.2004 21:24:18', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(54, 1, '14.10.2004 21:13:52', '14.10.2004 21:15:23', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(55, 1, '14.10.2004 21:19:15', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(56, 1, '14.10.2004 21:21:4', '14.10.2004 21:21:35', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(57, 1, '14.10.2004 21:22:15', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(58, 1, '18.10.2004 19:34:14', '18.10.2004 19:36:56', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(59, 2, '18.10.2004 19:52:30', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(60, 1, '18.10.2004 19:59:50', '18.10.2004 20:2:47', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(61, 1, '18.10.2004 20:2:57', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(62, 1, '18.10.2004 20:6:49', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(63, 1, '18.10.2004 20:15:16', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(64, 1, '18.10.2004 20:16:6', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(65, 1, '18.10.2004 20:19:41', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(66, 1, '18.10.2004 20:20:34', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(67, 1, '18.10.2004 20:26:35', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(68, 1, '18.10.2004 20:32:10', '18.10.2004 20:34:6', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(69, 1, '18.10.2004 20:49:11', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(70, 1, '18.10.2004 20:49:50', '18.10.2004 20:50:10', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(71, 1, '18.10.2004 20:52:25', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(72, 1, '18.10.2004 20:53:37', '18.10.2004 20:58:11', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(73, 1, '18.10.2004 20:58:31', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(74, 1, '24.10.2004 11:33:15', '24.10.2004 11:34:48', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(75, 1, '25.10.2004 18:58:10', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(76, 1, '25.10.2004 18:59:27', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(77, 1, '25.10.2004 19:3:34', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(78, 1, '25.10.2004 19:4:51', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(79, 1, '25.10.2004 19:18:5', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(80, 1, '25.10.2004 19:23:36', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(81, 1, '25.10.2004 19:24:13', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(82, 1, '25.10.2004 19:25:21', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(83, 1, '25.10.2004 19:27:48', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(84, 1, '25.10.2004 19:53:25', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(85, 1, '25.10.2004 19:54:35', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(86, 1, '25.10.2004 20:25:1', '25.10.2004 20:31:31', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(87, 1, '25.10.2004 20:54:8', '25.10.2004 20:55:45', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(88, 1, '25.10.2004 20:57:28', '25.10.2004 20:58:4', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(89, 1, '26.10.2004 21:0:58', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(90, 1, '26.10.2004 21:3:31', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(91, 1, '26.10.2004 21:4:21', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(92, 1, '26.10.2004 21:5:42', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(93, 1, '26.10.2004 21:10:35', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(94, 1, '26.10.2004 21:11:36', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(95, 1, '26.10.2004 21:15:52', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(96, 1, '26.10.2004 21:30:49', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(97, 1, '26.10.2004 21:33:45', '26.10.2004 21:34:10', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(98, 1, '26.10.2004 21:35:59', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(99, 1, '26.10.2004 21:39:13', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(100, 1, '26.10.2004 21:41:12', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(101, 1, '26.10.2004 21:43:34', '26.10.2004 21:44:44', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(102, 1, '26.10.2004 21:52:44', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(103, 1, '27.10.2004 19:30:28', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(104, 1, '27.10.2004 19:32:27', '27.10.2004 19:33:7', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(105, 1, '27.10.2004 19:34:33', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(106, 1, '27.10.2004 19:50:53', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(107, 1, '27.10.2004 19:53:27', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(108, 1, '27.10.2004 19:55:11', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(109, 1, '27.10.2004 19:55:39', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(110, 1, '27.10.2004 20:16:24', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(111, 1, '27.10.2004 20:21:41', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(112, 1, '27.10.2004 20:24:4', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(113, 1, '27.10.2004 20:32:7', '27.10.2004 20:32:41', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(114, 1, '27.10.2004 20:35:2', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(115, 1, '27.10.2004 20:36:21', '27.10.2004 20:41:41', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(116, 1, '27.10.2004 21:1:0', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(117, 1, '27.10.2004 21:3:19', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(118, 1, '27.10.2004 21:5:45', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(119, 1, '27.10.2004 21:6:55', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(120, 1, '27.10.2004 21:7:34', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(121, 1, '27.10.2004 21:9:28', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(122, 1, '27.10.2004 21:11:28', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(123, 1, '27.10.2004 21:17:42', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(124, 1, '27.10.2004 21:19:9', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(125, 1, '27.10.2004 21:21:3', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(126, 1, '27.10.2004 21:23:44', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(127, 1, '27.10.2004 21:26:4', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(128, 1, '27.10.2004 21:34:28', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(129, 1, '27.10.2004 21:38:19', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(130, 1, '27.10.2004 21:39:18', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(131, 1, '27.10.2004 21:41:15', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(132, 1, '27.10.2004 21:42:7', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(133, 1, '27.10.2004 21:43:34', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(134, 1, '27.10.2004 21:45:20', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(135, 1, '27.10.2004 21:45:50', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(136, 1, '27.10.2004 21:50:34', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(137, 1, '27.10.2004 21:51:30', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(138, 1, '27.10.2004 21:52:25', '27.10.2004 21:52:51', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(139, 1, '27.10.2004 21:53:59', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(140, 1, '27.10.2004 21:55:0', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(141, 1, '27.10.2004 21:57:20', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(142, 1, '27.10.2004 21:58:46', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(143, 1, '27.10.2004 21:59:10', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(144, 1, '27.10.2004 22:0:33', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(145, 1, '27.10.2004 22:1:32', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(146, 1, '27.10.2004 22:5:11', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(147, 1, '27.10.2004 23:46:56', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(148, 1, '29.10.2004 15:40:49', '29.10.2004 15:45:6', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(149, 1, '3.11.2004 2:55:44', '3.11.2004 2:57:29', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(150, 1, '3.11.2004 19:5:19', '3.11.2004 19:9:7', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(151, 1, '3.11.2004 19:14:48', '3.11.2004 19:15:6', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(152, 1, '3.11.2004 19:15:14', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(153, 1, '4.11.2004 16:54:37', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(154, 1, '4.11.2004 16:56:23', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(155, 1, '4.11.2004 16:58:17', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(156, 1, '4.11.2004 16:59:1', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(157, 1, '4.11.2004 17:1:31', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(158, 1, '4.11.2004 17:2:59', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(159, 1, '4.11.2004 17:26:20', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(160, 1, '4.11.2004 17:27:31', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(161, 1, '4.11.2004 19:0:30', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(162, 1, '4.11.2004 19:5:23', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(163, 1, '4.11.2004 19:6:19', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(164, 1, '4.11.2004 19:9:14', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(165, 1, '4.11.2004 19:11:10', '4.11.2004 19:13:26', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(166, 1, '4.11.2004 19:13:46', '4.11.2004 19:14:12', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(167, 1, '4.11.2004 19:31:59', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(168, 1, '4.11.2004 19:36:30', '4.11.2004 19:36:47', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(169, 1, '4.11.2004 19:43:9', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(170, 1, '4.11.2004 19:44:22', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(171, 1, '4.11.2004 19:57:22', '4.11.2004 19:57:37', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(172, 1, '4.11.2004 20:30:0', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(173, 1, '6.11.2004 23:20:11', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(174, 1, '8.11.2004 20:22:34', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(175, 1, '8.11.2004 20:23:27', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(176, 1, '8.11.2004 20:24:9', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(177, 1, '8.11.2004 20:25:1', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(178, 1, '8.11.2004 20:27:20', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(179, 1, '8.11.2004 20:35:17', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(180, 1, '8.11.2004 20:36:20', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(181, 1, '8.11.2004 20:39:7', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(182, 1, '8.11.2004 20:40:4', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(183, 1, '8.11.2004 21:22:2', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(184, 1, '8.11.2004 21:23:37', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(185, 1, '8.11.2004 21:43:1', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(186, 1, '8.11.2004 21:43:45', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(187, 1, '13.11.2004 10:32:46', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(188, 1, '13.11.2004 11:52:15', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(189, 1, '13.11.2004 11:55:35', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(190, 1, '13.11.2004 11:57:36', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(191, 1, '13.11.2004 11:59:39', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(192, 1, '13.11.2004 12:10:51', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(193, 1, '13.11.2004 12:11:26', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(194, 1, '13.11.2004 12:22:10', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(195, 1, '13.11.2004 15:52:13', '13.11.2004 15:55:36', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(196, 1, '13.11.2004 15:55:46', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(197, 1, '13.11.2004 15:56:8', '13.11.2004 15:57:40', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(198, 1, '13.11.2004 16:0:24', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(199, 1, '13.11.2004 16:1:32', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(200, 1, '13.11.2004 16:4:18', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(201, 1, '14.11.2004 14:23:20', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(202, 1, '14.11.2004 15:1:59', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(203, 1, '14.11.2004 15:3:39', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(204, 1, '14.11.2004 15:5:51', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(205, 1, '14.11.2004 15:8:34', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(206, 1, '14.11.2004 15:10:39', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(207, 1, '14.11.2004 15:14:58', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(208, 1, '14.11.2004 15:19:40', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(209, 1, '14.11.2004 15:22:39', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(210, 1, '14.11.2004 15:37:10', '14.11.2004 15:42:55', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(211, 1, '14.11.2004 16:8:3', '14.11.2004 16:8:23', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(212, 1, '14.11.2004 16:9:14', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(213, 1, '14.11.2004 16:11:0', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(214, 1, '14.11.2004 16:12:20', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(215, 1, '14.11.2004 16:13:12', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(216, 1, '14.11.2004 16:16:54', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(217, 1, '14.11.2004 16:17:25', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(218, 1, '14.11.2004 18:13:48', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(219, 1, '14.11.2004 18:17:17', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(220, 1, '14.11.2004 18:18:51', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(221, 1, '14.11.2004 19:0:36', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(222, 1, '14.11.2004 19:16:27', '14.11.2004 19:23:46', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(223, 1, '14.11.2004 20:37:38', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(224, 1, '14.11.2004 20:38:34', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(225, 1, '14.11.2004 20:39:33', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(226, 1, '14.11.2004 20:50:35', '14.11.2004 20:51:35', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(227, 1, '14.11.2004 20:55:34', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(228, 1, '14.11.2004 21:18:56', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(229, 1, '14.11.2004 21:24:50', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(230, 1, '14.11.2004 21:29:40', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(231, 1, '15.11.2004 19:21:37', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(232, 1, '15.11.2004 19:24:31', '15.11.2004 19:28:27', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(233, 1, '15.11.2004 19:32:24', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(234, 1, '15.11.2004 19:41:29', '15.11.2004 19:41:54', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(235, 1, '15.11.2004 19:44:29', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(236, 1, '15.11.2004 19:47:6', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(238, 1, '15.11.2004 19:56:2', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(239, 1, '15.11.2004 19:59:59', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(240, 1, '15.11.2004 20:2:31', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(241, 1, '15.11.2004 20:5:28', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(242, 1, '15.11.2004 20:7:54', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(243, 1, '15.11.2004 20:10:28', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(244, 1, '15.11.2004 20:14:52', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(245, 1, '15.11.2004 20:23:12', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(246, 1, '15.11.2004 20:26:44', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(247, 1, '15.11.2004 20:29:28', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(248, 1, '15.11.2004 20:30:59', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(249, 1, '15.11.2004 20:52:44', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(250, 1, '15.11.2004 20:54:6', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(251, 1, '15.11.2004 20:55:6', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(252, 1, '15.11.2004 20:55:45', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(253, 1, '15.11.2004 21:7:42', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(254, 1, '15.11.2004 21:8:41', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(255, 1, '15.11.2004 21:9:48', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(256, 1, '15.11.2004 21:19:25', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(257, 1, '16.11.2004 19:44:15', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(258, 1, '16.11.2004 19:47:9', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(259, 1, '16.11.2004 19:51:29', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(260, 1, '16.11.2004 19:53:1', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(261, 1, '16.11.2004 19:55:28', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(262, 1, '16.11.2004 20:2:52', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(263, 1, '16.11.2004 20:16:9', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(264, 1, '16.11.2004 20:24:37', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(265, 1, '16.11.2004 20:27:48', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(266, 1, '16.11.2004 20:35:42', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(267, 1, '16.11.2004 20:36:31', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(268, 1, '16.11.2004 20:44:7', '16.11.2004 20:46:12', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(269, 1, '16.11.2004 20:47:16', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(270, 1, '16.11.2004 20:48:13', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(271, 1, '16.11.2004 21:39:28', '16.11.2004 21:40:11', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(272, 1, '17.11.2004 21:29:3', '17.11.2004 21:34:53', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(273, 1, '19.11.2004 18:49:13', '19.11.2004 18:50:18', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(274, 1, '19.11.2004 19:7:28', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(275, 1, '19.11.2004 19:9:13', '19.11.2004 19:11:37', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(276, 1, '19.11.2004 19:24:51', '19.11.2004 19:26:17', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(277, 1, '19.11.2004 20:37:45', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(278, 1, '19.11.2004 20:40:34', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(279, 1, '19.11.2004 20:41:34', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(280, 1, '19.11.2004 20:42:23', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(281, 1, '19.11.2004 20:43:52', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(282, 1, '19.11.2004 20:45:21', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(283, 1, '19.11.2004 20:46:52', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(284, 1, '19.11.2004 20:51:10', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(285, 1, '19.11.2004 20:55:31', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(286, 1, '19.11.2004 20:57:47', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(287, 1, '19.11.2004 21:15:35', '19.11.2004 21:17:17', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(288, 1, '19.11.2004 21:29:19', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(289, 1, '20.11.2004 16:58:22', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(290, 1, '20.11.2004 17:1:2', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(291, 1, '20.11.2004 17:51:20', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(292, 1, '20.11.2004 17:57:10', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(293, 1, '20.11.2004 18:0:10', '20.11.2004 18:6:14', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(294, 1, '20.11.2004 18:6:48', '20.11.2004 18:7:42', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(295, 1, '20.11.2004 18:8:1', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(296, 1, '20.11.2004 18:9:40', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(297, 1, '20.11.2004 18:11:38', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(298, 1, '20.11.2004 18:12:27', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(299, 1, '20.11.2004 18:15:10', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(300, 1, '20.11.2004 18:16:19', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(301, 1, '20.11.2004 18:17:27', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(302, 1, '20.11.2004 18:18:48', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(303, 1, '20.11.2004 18:22:54', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(304, 1, '20.11.2004 18:24:15', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(305, 1, '20.11.2004 18:25:40', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(306, 1, '20.11.2004 18:27:18', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(307, 1, '20.11.2004 18:28:41', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(308, 1, '20.11.2004 19:11:4', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(309, 1, '20.11.2004 19:12:5', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(310, 1, '20.11.2004 19:13:32', '20.11.2004 19:13:48', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(311, 1, '20.11.2004 19:20:22', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(312, 1, '20.11.2004 19:33:11', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(313, 1, '20.11.2004 19:34:1', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(314, 1, '20.11.2004 19:37:57', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(315, 1, '20.11.2004 19:40:52', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(316, 1, '21.11.2004 12:45:41', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(317, 1, '21.11.2004 13:49:33', '21.11.2004 13:49:48', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(318, 1, '21.11.2004 13:54:22', '21.11.2004 13:56:31', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(319, 1, '21.11.2004 13:56:50', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(320, 1, '21.11.2004 13:58:57', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(321, 1, '21.11.2004 14:25:51', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(322, 1, '21.11.2004 14:27:53', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(323, 1, '21.11.2004 14:28:27', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(324, 1, '21.11.2004 14:30:37', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(325, 1, '21.11.2004 14:47:58', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(326, 1, '21.11.2004 14:54:24', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(327, 1, '21.11.2004 14:55:47', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(328, 1, '21.11.2004 15:1:42', '21.11.2004 15:10:49', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(329, 1, '21.11.2004 15:51:45', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(330, 1, '21.11.2004 15:52:50', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(331, 1, '21.11.2004 16:10:50', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(332, 1, '21.11.2004 16:12:34', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(333, 1, '21.11.2004 16:23:16', '21.11.2004 16:23:34', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(334, 1, '21.11.2004 16:24:18', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(335, 1, '21.11.2004 16:26:56', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(336, 1, '21.11.2004 16:28:35', '21.11.2004 16:28:50', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(337, 1, '21.11.2004 16:30:17', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(338, 1, '21.11.2004 16:31:1', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(339, 1, '21.11.2004 16:33:18', '21.11.2004 16:33:56', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(340, 1, '22.11.2004 23:14:33', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(341, 1, '22.11.2004 23:16:41', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(342, 1, '22.11.2004 23:19:16', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(343, 1, '22.11.2004 23:20:39', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(344, 1, '22.11.2004 23:23:58', '22.11.2004 23:24:28', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(345, 1, '22.11.2004 23:25:6', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(346, 1, '22.11.2004 23:30:56', '22.11.2004 23:31:11', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(347, 1, '22.11.2004 23:31:23', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(348, 1, '22.11.2004 23:35:20', '22.11.2004 23:35:34', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(349, 1, '22.11.2004 23:35:50', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(350, 1, '25.11.2004 20:5:4', '', 4);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(351, 1, '25.11.2004 20:39:22', '25.11.2004 20:39:43', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(352, 1, '26.11.2004 21:20:35', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(353, 1, '26.11.2004 21:36:19', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(354, 1, '26.11.2004 21:39:15', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(355, 1, '27.11.2004 15:13:16', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(356, 1, '27.11.2004 15:13:57', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(357, 1, '27.11.2004 15:14:29', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(358, 1, '27.11.2004 15:15:58', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(359, 1, '27.11.2004 15:18:28', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(360, 1, '27.11.2004 15:19:29', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(361, 1, '27.11.2004 15:23:11', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(362, 1, '27.11.2004 15:23:52', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(363, 1, '27.11.2004 15:24:27', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(364, 1, '27.11.2004 15:25:45', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(365, 1, '27.11.2004 15:26:38', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(366, 1, '27.11.2004 15:29:9', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(367, 1, '27.11.2004 15:29:51', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(368, 1, '27.11.2004 15:31:33', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(369, 1, '27.11.2004 15:32:13', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(370, 1, '27.11.2004 15:50:21', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(371, 1, '27.11.2004 15:52:18', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(372, 1, '27.11.2004 15:54:26', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(373, 1, '27.11.2004 15:56:3', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(374, 1, '27.11.2004 15:59:32', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(375, 1, '27.11.2004 16:53:46', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(376, 1, '27.11.2004 16:55:21', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(377, 1, '27.11.2004 16:56:7', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(378, 1, '27.11.2004 16:59:19', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(379, 1, '27.11.2004 17:5:12', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(380, 1, '27.11.2004 17:6:27', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(381, 1, '27.11.2004 17:7:18', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(382, 1, '27.11.2004 17:8:7', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(383, 1, '27.11.2004 17:8:50', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(384, 1, '27.11.2004 17:9:26', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(385, 1, '27.11.2004 17:11:14', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(386, 1, '27.11.2004 17:27:26', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(387, 1, '27.11.2004 17:27:59', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(388, 1, '27.11.2004 17:30:30', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(389, 1, '27.11.2004 17:31:49', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(390, 1, '27.11.2004 17:36:4', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(391, 1, '27.11.2004 17:49:18', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(392, 1, '27.11.2004 17:50:36', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(393, 1, '27.11.2004 17:52:56', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(394, 1, '27.11.2004 18:7:21', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(395, 1, '27.11.2004 18:8:11', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(396, 1, '27.11.2004 18:14:16', '', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(397, 1, '27.11.2004 18:40:28', '27.11.2004 18:40:33', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(398, 1, '27.11.2004 18:41:21', '27.11.2004 18:41:26', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(399, 1, '27.11.2004 19:19:6', '27.11.2004 19:19:19', 3);
INSERT INTO bf_session (SessionID, UserID, Login, Logout, GameID) VALUES(400, 1, '27.11.2004 19:20:24', '27.11.2004 19:31:14', 3);
# Dumping Table Structure for bf_shipproperty

#
CREATE TABLE `bf_shipproperty` (
  `ShipPropertyID` int(10) unsigned NOT NULL auto_increment,
  `UnitTypID` int(11) default NULL,
  `MaxJumpCharges` int(11) default '0',
  `DockingStations` int(11) default '0',
  `Tonnage` int(11) default '0',
  `Treibstoff` int(11) default '0',
  `Ladung` int(11) default '0',
  `Crew` int(11) default '0',
  `Passagiere` int(11) default '0',
  `PanzFront` int(11) default '0',
  `PanzHeck` int(11) default '0',
  `PanzFrontL` int(11) default '0',
  `PanzFrontR` int(11) default '0',
  `PanzHeckL` int(11) default '0',
  `PanzHeckR` int(11) default '0',
  PRIMARY KEY  (`ShipPropertyID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_shipproperty
#
INSERT INTO bf_shipproperty (ShipPropertyID, UnitTypID, MaxJumpCharges, DockingStations, Tonnage, Treibstoff, Ladung, Crew, Passagiere, PanzFront, PanzHeck, PanzFrontL, PanzFrontR, PanzHeckL, PanzHeckR) VALUES(1, 7, 2, 6, 274000, 100, 60, 30, 50, 6, 4, 6, 6, 5, 5);
INSERT INTO bf_shipproperty (ShipPropertyID, UnitTypID, MaxJumpCharges, DockingStations, Tonnage, Treibstoff, Ladung, Crew, Passagiere, PanzFront, PanzHeck, PanzFrontL, PanzFrontR, PanzHeckL, PanzHeckR) VALUES(5, 1, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO bf_shipproperty (ShipPropertyID, UnitTypID, MaxJumpCharges, DockingStations, Tonnage, Treibstoff, Ladung, Crew, Passagiere, PanzFront, PanzHeck, PanzFrontL, PanzFrontR, PanzHeckL, PanzHeckR) VALUES(4, 2, 1, 3, 152000, 50, 522, 24, 0, 7, 5, 7, 7, 6, 6);
INSERT INTO bf_shipproperty (ShipPropertyID, UnitTypID, MaxJumpCharges, DockingStations, Tonnage, Treibstoff, Ladung, Crew, Passagiere, PanzFront, PanzHeck, PanzFrontL, PanzFrontR, PanzHeckL, PanzHeckR) VALUES(6, 3, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO bf_shipproperty (ShipPropertyID, UnitTypID, MaxJumpCharges, DockingStations, Tonnage, Treibstoff, Ladung, Crew, Passagiere, PanzFront, PanzHeck, PanzFrontL, PanzFrontR, PanzHeckL, PanzHeckR) VALUES(7, 4, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO bf_shipproperty (ShipPropertyID, UnitTypID, MaxJumpCharges, DockingStations, Tonnage, Treibstoff, Ladung, Crew, Passagiere, PanzFront, PanzHeck, PanzFrontL, PanzFrontR, PanzHeckL, PanzHeckR) VALUES(8, 5, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO bf_shipproperty (ShipPropertyID, UnitTypID, MaxJumpCharges, DockingStations, Tonnage, Treibstoff, Ladung, Crew, Passagiere, PanzFront, PanzHeck, PanzFrontL, PanzFrontR, PanzHeckL, PanzHeckR) VALUES(9, 6, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
# Dumping Table Structure for bf_startarea

#
CREATE TABLE `bf_startarea` (
  `StartAreaID` int(10) unsigned NOT NULL auto_increment,
  `StartArea` varchar(50) default NULL,
  PRIMARY KEY  (`StartAreaID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_startarea
#
INSERT INTO bf_startarea (StartAreaID, StartArea) VALUES(1, '2750');
INSERT INTO bf_startarea (StartAreaID, StartArea) VALUES(2, '3025');
INSERT INTO bf_startarea (StartAreaID, StartArea) VALUES(3, '3030');
INSERT INTO bf_startarea (StartAreaID, StartArea) VALUES(4, '3040');
INSERT INTO bf_startarea (StartAreaID, StartArea) VALUES(5, '3052');
INSERT INTO bf_startarea (StartAreaID, StartArea) VALUES(6, '3057');
INSERT INTO bf_startarea (StartAreaID, StartArea) VALUES(7, '3062');
# Dumping Table Structure for bf_startsystemowner

#
CREATE TABLE `bf_startsystemowner` (
  `StartSystemOwnerID` int(10) unsigned NOT NULL auto_increment,
  `SystemID` int(11) default NULL,
  `OwnerID` int(11) default NULL,
  `StartAreaID` int(11) default NULL,
  PRIMARY KEY  (`StartSystemOwnerID`)
) TYPE=MyISAM;
#
# Dumping Data for bf_startsystemowner
#
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(1, 1, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(2, 2, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(3, 3, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(4, 4, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(5, 5, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(6, 6, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(7, 7, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(8, 8, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(9, 9, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(10, 10, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(11, 11, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(12, 12, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(13, 13, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(14, 14, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(15, 15, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(16, 16, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(17, 17, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(18, 18, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(19, 19, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(20, 20, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(21, 21, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(22, 22, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(23, 23, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(24, 24, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(25, 25, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(26, 26, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(27, 27, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(28, 28, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(29, 29, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(30, 30, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(31, 31, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(32, 32, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(33, 33, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(34, 34, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(35, 35, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(36, 36, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(37, 37, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(38, 38, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(39, 39, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(40, 40, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(41, 41, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(42, 42, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(43, 43, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(44, 44, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(45, 45, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(46, 46, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(47, 47, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(48, 48, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(49, 49, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(50, 50, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(51, 51, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(52, 52, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(53, 53, 25, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(54, 54, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(55, 55, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(56, 56, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(57, 57, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(58, 58, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(59, 59, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(60, 60, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(61, 61, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(62, 62, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(63, 63, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(64, 64, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(65, 65, 27, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(66, 66, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(67, 67, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(68, 68, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(69, 69, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(70, 70, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(71, 71, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(72, 72, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(73, 73, 25, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(74, 74, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(75, 75, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(76, 76, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(77, 77, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(78, 78, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(79, 79, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(80, 80, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(81, 81, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(82, 82, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(83, 83, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(84, 84, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(85, 85, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(86, 86, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(87, 87, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(88, 88, 26, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(89, 89, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(90, 90, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(91, 91, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(92, 92, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(93, 93, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(94, 94, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(95, 95, 26, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(96, 96, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(97, 97, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(98, 98, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(99, 99, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(100, 100, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(101, 101, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(102, 102, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(103, 103, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(104, 104, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(105, 105, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(106, 106, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(107, 107, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(108, 108, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(109, 109, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(110, 110, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(111, 111, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(112, 112, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(113, 113, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(114, 114, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(115, 115, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(116, 116, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(117, 117, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(118, 118, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(119, 119, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(120, 120, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(121, 121, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(122, 122, 27, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(123, 123, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(124, 124, 27, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(125, 125, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(126, 126, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(127, 127, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(128, 128, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(129, 129, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(130, 130, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(131, 131, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(132, 132, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(133, 133, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(134, 134, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(135, 135, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(136, 136, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(137, 137, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(138, 138, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(139, 139, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(140, 140, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(141, 141, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(142, 142, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(143, 143, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(144, 144, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(145, 145, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(146, 146, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(147, 147, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(148, 148, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(149, 149, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(150, 150, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(151, 151, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(152, 152, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(153, 153, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(154, 154, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(155, 155, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(156, 156, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(157, 157, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(158, 158, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(159, 159, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(160, 160, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(161, 161, 26, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(162, 162, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(163, 163, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(164, 164, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(165, 165, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(166, 166, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(167, 167, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(168, 168, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(169, 169, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(170, 170, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(171, 171, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(172, 172, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(173, 173, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(174, 174, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(175, 175, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(176, 176, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(177, 177, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(178, 178, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(179, 179, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(180, 180, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(181, 181, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(182, 182, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(183, 183, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(184, 184, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(185, 185, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(186, 186, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(187, 187, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(188, 188, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(189, 189, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(190, 190, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(191, 191, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(192, 192, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(193, 193, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(194, 194, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(195, 195, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(196, 196, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(197, 197, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(198, 198, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(199, 199, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(200, 200, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(201, 201, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(202, 202, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(203, 203, 27, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(204, 204, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(205, 205, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(206, 206, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(207, 207, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(208, 208, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(209, 209, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(210, 210, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(211, 211, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(212, 212, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(213, 213, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(214, 214, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(215, 215, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(216, 216, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(217, 217, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(218, 218, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(219, 219, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(220, 220, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(221, 221, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(222, 222, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(223, 223, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(224, 224, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(225, 225, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(226, 226, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(227, 227, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(228, 228, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(229, 229, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(230, 230, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(231, 231, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(232, 232, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(233, 233, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(234, 234, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(235, 235, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(236, 236, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(237, 237, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(238, 238, 27, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(239, 239, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(240, 240, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(241, 241, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(242, 242, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(243, 243, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(244, 244, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(245, 245, 27, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(246, 246, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(247, 247, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(248, 248, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(249, 249, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(250, 250, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(251, 251, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(252, 252, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(253, 253, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(254, 254, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(255, 255, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(256, 256, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(257, 257, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(258, 258, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(259, 259, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(260, 260, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(261, 261, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(262, 262, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(263, 263, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(264, 264, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(265, 265, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(266, 266, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(267, 267, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(268, 268, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(269, 269, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(270, 270, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(271, 271, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(272, 272, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(273, 273, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(274, 274, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(275, 275, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(276, 276, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(277, 277, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(278, 278, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(279, 279, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(280, 280, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(281, 281, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(282, 282, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(283, 283, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(284, 284, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(285, 285, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(286, 286, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(287, 287, 27, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(288, 288, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(289, 289, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(290, 290, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(291, 291, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(292, 292, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(293, 293, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(294, 294, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(295, 295, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(296, 296, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(297, 297, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(298, 298, 26, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(299, 299, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(300, 300, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(301, 301, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(302, 302, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(303, 303, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(304, 304, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(305, 305, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(306, 306, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(307, 307, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(308, 308, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(309, 309, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(310, 310, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(311, 311, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(312, 312, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(313, 313, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(314, 314, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(315, 315, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(316, 316, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(317, 317, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(318, 318, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(319, 319, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(320, 320, 26, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(321, 321, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(322, 322, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(323, 323, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(324, 324, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(325, 325, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(326, 326, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(327, 327, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(328, 328, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(329, 329, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(330, 330, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(331, 331, 27, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(332, 332, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(333, 333, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(334, 334, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(335, 335, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(336, 336, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(337, 337, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(338, 338, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(339, 339, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(340, 340, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(341, 341, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(342, 342, 26, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(343, 343, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(344, 344, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(345, 345, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(346, 346, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(347, 347, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(348, 348, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(349, 349, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(350, 350, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(351, 351, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(352, 352, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(353, 353, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(354, 354, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(355, 355, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(356, 356, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(357, 357, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(358, 358, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(359, 359, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(360, 360, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(361, 361, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(362, 362, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(363, 363, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(364, 364, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(365, 365, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(366, 366, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(367, 367, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(368, 368, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(369, 369, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(370, 370, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(371, 371, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(372, 372, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(373, 373, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(374, 374, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(375, 375, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(376, 376, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(377, 377, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(378, 378, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(379, 379, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(380, 380, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(381, 381, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(382, 382, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(383, 383, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(384, 384, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(385, 385, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(386, 386, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(387, 387, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(388, 388, 26, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(389, 389, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(390, 390, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(391, 391, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(392, 392, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(393, 393, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(394, 394, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(395, 395, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(396, 396, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(397, 397, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(398, 398, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(399, 399, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(400, 400, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(401, 401, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(402, 402, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(403, 403, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(404, 404, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(405, 405, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(406, 406, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(407, 407, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(408, 408, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(409, 409, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(410, 410, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(411, 411, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(412, 412, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(413, 413, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(414, 414, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(415, 415, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(416, 416, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(417, 417, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(418, 418, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(419, 419, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(420, 420, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(421, 421, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(422, 422, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(423, 423, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(424, 424, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(425, 425, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(426, 426, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(427, 427, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(428, 428, 16, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(429, 429, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(430, 430, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(431, 431, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(432, 432, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(433, 433, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(434, 434, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(435, 435, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(436, 436, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(437, 437, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(438, 438, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(439, 439, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(440, 440, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(441, 441, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(442, 442, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(443, 443, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(444, 444, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(445, 445, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(446, 446, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(447, 447, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(448, 448, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(449, 449, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(450, 450, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(451, 451, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(452, 452, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(453, 453, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(454, 454, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(455, 455, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(456, 456, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(457, 457, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(458, 458, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(459, 459, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(460, 460, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(461, 461, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(462, 462, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(463, 463, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(464, 464, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(465, 465, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(466, 466, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(467, 467, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(468, 468, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(469, 469, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(470, 470, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(471, 471, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(472, 472, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(473, 473, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(474, 474, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(475, 475, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(476, 476, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(477, 477, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(478, 478, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(479, 479, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(480, 480, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(481, 481, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(482, 482, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(483, 483, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(484, 484, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(485, 485, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(486, 486, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(487, 487, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(488, 488, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(489, 489, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(490, 490, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(491, 491, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(492, 492, 26, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(493, 493, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(494, 494, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(495, 495, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(496, 496, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(497, 497, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(498, 498, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(499, 499, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(500, 500, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(501, 501, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(502, 502, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(503, 503, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(504, 504, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(505, 505, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(506, 506, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(507, 507, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(508, 508, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(509, 509, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(510, 510, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(511, 511, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(512, 512, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(513, 513, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(514, 514, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(515, 515, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(516, 516, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(517, 517, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(518, 518, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(519, 519, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(520, 520, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(521, 521, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(522, 522, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(523, 523, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(524, 524, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(525, 525, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(526, 526, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(527, 527, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(528, 528, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(529, 529, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(530, 530, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(531, 531, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(532, 532, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(533, 533, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(534, 534, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(535, 535, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(536, 536, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(537, 537, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(538, 538, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(539, 539, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(540, 540, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(541, 541, 25, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(542, 542, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(543, 543, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(544, 544, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(545, 545, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(546, 546, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(547, 547, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(548, 548, 26, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(549, 549, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(550, 550, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(551, 551, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(552, 552, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(553, 553, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(554, 554, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(555, 555, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(556, 556, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(557, 557, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(558, 558, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(559, 559, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(560, 560, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(561, 561, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(562, 562, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(563, 563, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(564, 564, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(565, 565, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(566, 566, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(567, 567, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(568, 568, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(569, 569, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(570, 570, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(571, 571, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(572, 572, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(573, 573, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(574, 574, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(575, 575, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(576, 576, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(577, 577, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(578, 578, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(579, 579, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(580, 580, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(581, 581, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(582, 582, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(583, 583, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(584, 584, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(585, 585, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(586, 586, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(587, 587, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(588, 588, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(589, 589, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(590, 590, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(591, 591, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(592, 592, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(593, 593, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(594, 594, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(595, 595, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(596, 596, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(597, 597, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(598, 598, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(599, 599, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(600, 600, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(601, 601, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(602, 602, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(603, 603, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(604, 604, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(605, 605, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(606, 606, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(607, 607, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(608, 608, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(609, 609, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(610, 610, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(611, 611, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(612, 612, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(613, 613, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(614, 614, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(615, 615, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(616, 616, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(617, 617, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(618, 618, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(619, 619, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(620, 620, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(621, 621, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(622, 622, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(623, 623, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(624, 624, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(625, 625, 27, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(626, 626, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(627, 627, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(628, 628, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(629, 629, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(630, 630, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(631, 631, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(632, 632, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(633, 633, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(634, 634, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(635, 635, 26, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(636, 636, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(637, 637, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(638, 638, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(639, 639, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(640, 640, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(641, 641, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(642, 642, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(643, 643, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(644, 644, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(645, 645, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(646, 646, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(647, 647, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(648, 648, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(649, 649, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(650, 650, 21, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(651, 651, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(652, 652, 1, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(653, 653, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(654, 654, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(655, 655, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(656, 656, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(657, 657, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(658, 658, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(659, 659, 22, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(660, 660, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(661, 661, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(662, 662, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(663, 663, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(664, 664, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(665, 665, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(666, 666, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(667, 667, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(668, 668, 26, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(669, 669, 26, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(670, 670, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(671, 671, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(672, 672, 29, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(673, 673, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(674, 674, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(675, 675, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(676, 676, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(677, 677, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(678, 678, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(679, 679, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(680, 680, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(681, 681, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(682, 682, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(683, 683, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(684, 684, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(685, 685, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(686, 686, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(687, 687, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(688, 688, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(689, 689, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(690, 690, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(691, 691, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(692, 692, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(693, 693, 3, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(694, 694, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(695, 695, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(696, 696, 11, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(697, 697, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(698, 698, 14, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(699, 699, 8, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(700, 700, 5, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(701, 701, 10, 1);
INSERT INTO bf_startsystemowner (StartSystemOwnerID, SystemID, OwnerID, StartAreaID) VALUES(702, 702, 10, 1);
