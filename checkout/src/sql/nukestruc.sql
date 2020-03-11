# MySQL dump 8.14
#
# Host: localhost    Database: nextnuke
#--------------------------------------------------------
# Server version	0.00.00-1 Testbuild 1

#
# Table structure for table 'nuke_admin'
#

CREATE TABLE nuke_admin (
  aid varchar(25) NOT NULL default '',
  name varchar(50) default NULL,
  url varchar(255) NOT NULL default '',
  email varchar(255) NOT NULL default '',
  pwd varchar(40) default NULL,
  counter int(11) NOT NULL default '0',
  admlanguage varchar(30) NOT NULL default '',
  PRIMARY KEY  (aid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_admin_module'
#

CREATE TABLE nuke_admin_module (
  id int(11) NOT NULL default '0',
  name varchar(50) NOT NULL default ''
) TYPE=MyISAM;

#
# Table structure for table 'nuke_admin_perm'
#

CREATE TABLE nuke_admin_perm (
  aid varchar(25) NOT NULL default '0',
  module varchar(20) NOT NULL default '',
  permitted tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (aid,module,permitted)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_arsc_bannlist'
#

CREATE TABLE nuke_arsc_bannlist (
  id int(11) NOT NULL auto_increment,
  ip varchar(15) NOT NULL default '',
  until int(11) NOT NULL default '0',
  PRIMARY KEY  (id),
  KEY ip (ip)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_arsc_config'
#

CREATE TABLE nuke_arsc_config (
  socketserver_use tinyint(1) NOT NULL default '0',
  socketserver_adress varchar(255) NOT NULL default 'localhost',
  socketserver_port int(6) NOT NULL default '8080',
  socketserver_maximumusers int(11) NOT NULL default '200',
  socketserver_refresh double NOT NULL default '400000',
  selfop_password varchar(60) NOT NULL default '',
  activate_counter_pic tinyint(1) NOT NULL default '0',
  drawboard tinyint(1) NOT NULL default '0',
  drawboard_width int(6) NOT NULL default '400',
  drawboard_height int(6) NOT NULL default '350',
  drawboard_port int(6) NOT NULL default '8081',
  allow_textformatting tinyint(1) NOT NULL default '1',
  logo_path varchar(255) NOT NULL default 'logo.png',
  smilies tinyint(1) NOT NULL default '1',
  flood_max int(11) NOT NULL default '4',
  first_user_gets_op tinyint(1) NOT NULL default '0',
  keep_sended_message tinyint(1) NOT NULL default '1',
  input_maxsize int(11) NOT NULL default '400',
  ping int(11) NOT NULL default '10',
  ping_text int(11) NOT NULL default '600',
  userlist_refresh int(11) NOT NULL default '8',
  roomlist_refresh int(11) NOT NULL default '240',
  rowlimit int(11) NOT NULL default '100'
) TYPE=MyISAM;

#
# Table structure for table 'nuke_arsc_messages'
#

CREATE TABLE nuke_arsc_messages (
  id int(11) NOT NULL auto_increment,
  rid int(11) NOT NULL default '0',
  message text NOT NULL,
  uid int(11) NOT NULL default '0',
  flag_ripped tinyint(4) NOT NULL default '0',
  sendtime time NOT NULL default '00:00:00',
  timeid bigint(20) NOT NULL default '0',
  PRIMARY KEY  (id),
  KEY timeid (timeid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_arsc_rooms'
#

CREATE TABLE nuke_arsc_rooms (
  id int(11) NOT NULL auto_increment,
  name varchar(64) NOT NULL default '',
  createdby int(11) NOT NULL default '-1',
  createtime timestamp(14) NOT NULL,
  PRIMARY KEY  (id)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_arsc_user_config'
#

CREATE TABLE nuke_arsc_user_config (
  uid int(11) NOT NULL default '0',
  version varchar(50) NOT NULL default 'header_js',
  room varchar(50) NOT NULL default 'lounge',
  level int(11) NOT NULL default '0',
  PRIMARY KEY  (uid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_arsc_users'
#

CREATE TABLE nuke_arsc_users (
  id int(11) NOT NULL auto_increment,
  user varchar(60) NOT NULL default '',
  lastping int(11) NOT NULL default '0',
  ip varchar(15) NOT NULL default '',
  room varchar(64) NOT NULL default '',
  language varchar(32) NOT NULL default '',
  version varchar(16) NOT NULL default '',
  level int(11) NOT NULL default '0',
  flag_ripped tinyint(4) NOT NULL default '0',
  sid varchar(32) NOT NULL default '',
  lastmessageping bigint(20) NOT NULL default '0',
  flood_count tinyint(4) NOT NULL default '0',
  flood_lastmessage text NOT NULL,
  PRIMARY KEY  (id),
  KEY lastping (lastping),
  KEY user (user)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_autonews'
#

CREATE TABLE nuke_autonews (
  anid int(11) NOT NULL auto_increment,
  catid int(11) NOT NULL default '0',
  aid varchar(30) NOT NULL default '',
  title varchar(80) NOT NULL default '',
  time varchar(19) NOT NULL default '',
  hometext text NOT NULL,
  bodytext text NOT NULL,
  topic int(3) NOT NULL default '1',
  informant varchar(20) NOT NULL default '',
  notes text NOT NULL,
  ihome int(1) NOT NULL default '0',
  alanguage varchar(30) NOT NULL default '',
  acomm int(1) NOT NULL default '0',
  PRIMARY KEY  (anid)
) TYPE=MyISAM;


#
# Table structure for table 'nuke_blocks'
#

CREATE TABLE nuke_blocks (
  bid int(10) NOT NULL auto_increment,
  bkey varchar(15) NOT NULL default '',
  title varchar(60) NOT NULL default '',
  content text NOT NULL,
  url varchar(200) NOT NULL default '',
  position char(1) NOT NULL default '',
  weight int(10) NOT NULL default '1',
  active int(1) NOT NULL default '1',
  refresh int(10) NOT NULL default '0',
  time varchar(14) NOT NULL default '0',
  blanguage varchar(30) NOT NULL default '',
  blockfile varchar(255) NOT NULL default '',
  view int(1) NOT NULL default '0',
  gid int(11) NOT NULL default '0',
  PRIMARY KEY  (bid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_blocks_users'
#

CREATE TABLE nuke_blocks_users (
  userid int(11) NOT NULL default '0',
  bid int(11) NOT NULL default '0',
  active tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (userid,bid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_censorlist'
#

CREATE TABLE nuke_censorlist (
  word varchar(50) NOT NULL default '',
  PRIMARY KEY  (word)
) TYPE=MyISAM;


#
# Table structure for table 'nuke_config_site'
#

CREATE TABLE nuke_config_site (
  id int(11) NOT NULL auto_increment,
  sitename varchar(255) NOT NULL default 'nextNuke Powered Site',
  nukeurl varchar(255) NOT NULL default 'http://www.tnt-computer.de/nextnuke/',
  site_logo varchar(50) NOT NULL default 'logo.gif',
  slogan varchar(255) NOT NULL default '',
  startdate varchar(25) NOT NULL default '',
  adminmail varchar(255) NOT NULL default '',
  anonymous varchar(100) NOT NULL default 'Anonymous',
  anonpost int(1) NOT NULL default '0',
  deftheme varchar(255) NOT NULL default 'NukeNews',
  footer text NOT NULL,
  minpass int(2) NOT NULL default '5',
  commentlimit int(10) NOT NULL default '4096',
  pollcomm int(1) NOT NULL default '1',
  articlecomm int(1) NOT NULL default '1',
  top int(5) NOT NULL default '10',
  storyhome int(5) NOT NULL default '10',
  oldnum int(5) NOT NULL default '30',
  ultramode int(1) NOT NULL default '0',
  banners int(1) NOT NULL default '0',
  backend_title varchar(255) NOT NULL default 'nextNuke Powered Site',
  backend_language varchar(20) NOT NULL default 'de-DE',
  language varchar(20) NOT NULL default 'german',
  locale varchar(20) NOT NULL default 'de_DE',
  multilingual int(1) NOT NULL default '0',
  useflags int(1) NOT NULL default '0',
  notify int(1) NOT NULL default '0',
  notify_email varchar(255) NOT NULL default 'me@yoursite.com',
  notify_subject varchar(255) NOT NULL default 'NEWS for my site',
  notify_message varchar(255) NOT NULL default 'Hey! You got a new submission for your site.',
  notify_from varchar(255) NOT NULL default 'webmaster',
  moderate int(1) NOT NULL default '0',
  reasons varchar(255) NOT NULL default 'array("As Is","Offtopic","Flamebait","Troll","Redundant","Insighful","Interesting","Informative","Funny","Overrated","Underrated")',
  badreasons int(3) NOT NULL default '4',
  admingraphic int(1) NOT NULL default '0',
  httpref int(1) NOT NULL default '1',
  httprefmax int(10) NOT NULL default '0',
  CensorMode int(1) NOT NULL default '1',
  CensorReplace varchar(255) NOT NULL default '*****',
  mod_title_graphic tinyint(1) NOT NULL default '1',
  externallinks varchar(50) NOT NULL default '',
  sql_stats tinyint(4) NOT NULL default '0',
  use_avatars tinyint(1) NOT NULL default '1',
  subst_urls tinyint(1) NOT NULL default '0',
  use_tooltips tinyint(1) NOT NULL default '1',
  preload tinyint(1) NOT NULL default '1',
  mailnotify_on_article tinyint(1) NOT NULL default '0',
  afterlogin varchar(255) NOT NULL default '',
  PRIMARY KEY  (id)
) TYPE=MyISAM;


#
# Table structure for table 'nuke_countrycode'
#

CREATE TABLE nuke_countrycode (
  code varchar(5) NOT NULL default '',
  country varchar(100) NOT NULL default '',
  PRIMARY KEY  (code)
) TYPE=MyISAM;


#
# Table structure for table 'nuke_groups'
#

CREATE TABLE nuke_groups (
  id int(11) NOT NULL auto_increment,
  name varchar(255) NOT NULL default '',
  PRIMARY KEY  (id),
  UNIQUE KEY name (name)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_groups_users'
#

CREATE TABLE nuke_groups_users (
  gid int(11) NOT NULL default '0',
  uid int(11) NOT NULL default '0',
  PRIMARY KEY  (gid,uid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_impressum'
#

CREATE TABLE nuke_impressum (
  name varchar(255) NOT NULL default '',
  vertreter varchar(255) NOT NULL default '',
  strasse varchar(100) NOT NULL default '',
  plz varchar(5) NOT NULL default '',
  ort varchar(100) NOT NULL default '',
  email varchar(255) NOT NULL default '',
  telefon varchar(20) NOT NULL default '',
  register text NOT NULL,
  aufsichtsbehoerde text NOT NULL,
  kammer varchar(100) NOT NULL default '',
  beruf varchar(100) NOT NULL default '',
  beruf_staat varchar(50) NOT NULL default '',
  beruf_regeln text NOT NULL,
  umsatzsteuerid varchar(50) NOT NULL default '',
  disclaimer tinyint(1) NOT NULL default '1'
) TYPE=MyISAM;

#
# Table structure for table 'nuke_locked'
#

CREATE TABLE nuke_locked (
  id int(11) NOT NULL default '0',
  location varchar(100) NOT NULL default '',
  PRIMARY KEY  (id,location)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_main'
#

CREATE TABLE nuke_main (
  main_module varchar(255) NOT NULL default ''
) TYPE=MyISAM;

#
# Table structure for table 'nuke_module_cat'
#

CREATE TABLE nuke_module_cat (
  id int(11) NOT NULL auto_increment,
  name varchar(50) NOT NULL default '',
  PRIMARY KEY  (id)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_modules'
#

CREATE TABLE nuke_modules (
  mid int(10) NOT NULL auto_increment,
  title varchar(255) NOT NULL default '',
  custom_title varchar(255) NOT NULL default '',
  active int(1) NOT NULL default '0',
  view int(1) NOT NULL default '0',
  in_block tinyint(1) NOT NULL default '1',
  position int(11) default NULL,
  catid int(11) NOT NULL default '0',
  KEY mid (mid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_ntlogin'
#

CREATE TABLE nuke_ntlogin (
  ident varchar(100) NOT NULL default '',
  enckey varchar(100) NOT NULL default '',
  time timestamp(14) NOT NULL
) TYPE=MyISAM;

#
# Table structure for table 'nuke_optimize_gain'
#

CREATE TABLE nuke_optimize_gain (
  gain decimal(10,3) default NULL
) TYPE=MyISAM;

#
# Table structure for table 'nuke_pages'
#

CREATE TABLE nuke_pages (
  pid int(10) NOT NULL auto_increment,
  sort int(11) NOT NULL default '0',
  cid int(10) NOT NULL default '0',
  title varchar(255) NOT NULL default '',
  subtitle varchar(255) NOT NULL default '',
  active int(1) NOT NULL default '0',
  page_header text NOT NULL,
  text text NOT NULL,
  page_footer text NOT NULL,
  signature text NOT NULL,
  date datetime NOT NULL default '0000-00-00 00:00:00',
  counter int(10) NOT NULL default '0',
  clanguage varchar(30) NOT NULL default '',
  PRIMARY KEY  (pid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_pages_categories'
#

CREATE TABLE nuke_pages_categories (
  cid int(10) NOT NULL auto_increment,
  sort int(11) NOT NULL default '0',
  title varchar(255) NOT NULL default '',
  description text NOT NULL,
  PRIMARY KEY  (cid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_priv_msgs'
#

CREATE TABLE nuke_priv_msgs (
  msg_id int(10) NOT NULL auto_increment,
  msg_image varchar(100) default NULL,
  subject varchar(100) default NULL,
  from_userid int(10) NOT NULL default '0',
  to_userid int(10) NOT NULL default '0',
  msg_time varchar(20) default NULL,
  msg_text text,
  read_msg tinyint(10) NOT NULL default '0',
  notified tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (msg_id),
  KEY msg_id (msg_id),
  KEY to_userid (to_userid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_ranking'
#

CREATE TABLE nuke_ranking (
  id int(11) NOT NULL auto_increment,
  rtid int(11) NOT NULL default '0',
  title varchar(200) NOT NULL default '',
  text text NOT NULL,
  url varchar(255) NOT NULL default '',
  votecount int(11) NOT NULL default '0',
  vote float NOT NULL default '0',
  submitter varchar(60) NOT NULL default '0',
  topic_id int(11) NOT NULL default '0',
  PRIMARY KEY  (id)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_ranking_topic'
#

CREATE TABLE nuke_ranking_topic (
  id int(11) NOT NULL auto_increment,
  title varchar(100) NOT NULL default '',
  description text NOT NULL,
  syslist varchar(10) default NULL,
  PRIMARY KEY  (id)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_referer'
#

CREATE TABLE nuke_referer (
  rid int(11) NOT NULL auto_increment,
  url varchar(100) NOT NULL default '',
  count tinyint(11) NOT NULL default '0',
  PRIMARY KEY  (rid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_registration_conf'
#

CREATE TABLE nuke_registration_conf (
  whos_reg_for_users tinyint(1) NOT NULL default '0'
) TYPE=MyISAM;

#
# Table structure for table 'nuke_registration_events'
#

CREATE TABLE nuke_registration_events (
  id int(11) NOT NULL auto_increment,
  subject varchar(255) NOT NULL default '',
  time timestamp(14) NOT NULL,
  text text NOT NULL,
  PRIMARY KEY  (id)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_registration_regs'
#

CREATE TABLE nuke_registration_regs (
  eid int(11) NOT NULL default '0',
  userid int(11) NOT NULL default '0',
  note varchar(255) NOT NULL default '',
  PRIMARY KEY  (eid,userid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_remotes'
#

CREATE TABLE nuke_remotes (
  rid int(11) NOT NULL auto_increment,
  userid int(11) NOT NULL default '0',
  site varchar(255) NOT NULL default '',
  username varchar(25) NOT NULL default '',
  pass varchar(40) NOT NULL default '',
  PRIMARY KEY  (rid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_sections'
#

CREATE TABLE nuke_sections (
  secid int(11) NOT NULL auto_increment,
  secname varchar(40) NOT NULL default '',
  image varchar(50) NOT NULL default '',
  PRIMARY KEY  (secid)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_security'
#

CREATE TABLE nuke_security (
  ntlogin_allowed tinyint(1) NOT NULL default '0',
  inet_fetch_allowed tinyint(1) NOT NULL default '1',
  remote_services tinyint(1) NOT NULL default '0'
) TYPE=MyISAM;

#
# Table structure for table 'nuke_session'
#

CREATE TABLE nuke_session (
  username varchar(25) NOT NULL default '',
  time varchar(14) NOT NULL default '',
  host_addr varchar(48) NOT NULL default '',
  guest int(1) NOT NULL default '0'
) TYPE=MyISAM;

#
# Table structure for table 'nuke_shout'
#

CREATE TABLE nuke_shout (
  ip varchar(15) NOT NULL default '',
  userid int(11) NOT NULL default '0',
  text varchar(30) NOT NULL default '',
  edate timestamp(14) NOT NULL
) TYPE=MyISAM;

#
# Table structure for table 'nuke_sql_stats'
#

CREATE TABLE nuke_sql_stats (
  query_select bigint(20) unsigned NOT NULL default '0',
  query_insert bigint(20) unsigned NOT NULL default '0',
  query_update bigint(20) unsigned NOT NULL default '0',
  num_rows bigint(20) unsigned NOT NULL default '0',
  fetch_row bigint(20) unsigned NOT NULL default '0',
  fetch_array bigint(20) unsigned NOT NULL default '0',
  fetch_object bigint(20) unsigned NOT NULL default '0',
  impressions bigint(20) unsigned NOT NULL default '0'
) TYPE=MyISAM;

#
# Table structure for table 'nuke_todo'
#

CREATE TABLE nuke_todo (
  id int(11) NOT NULL auto_increment,
  userid int(11) NOT NULL default '0',
  pos int(4) NOT NULL default '0',
  title varchar(255) NOT NULL default '',
  text text NOT NULL,
  time timestamp(14) NOT NULL,
  done tinyint(1) NOT NULL default '0',
  from_user int(11) NOT NULL default '0',
  PRIMARY KEY  (id)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_update_conf'
#

CREATE TABLE nuke_update_conf (
  server varchar(255) NOT NULL default ''
) TYPE=MyISAM;

#
# Table structure for table 'nuke_users'
#

CREATE TABLE nuke_users (
  uid int(11) NOT NULL auto_increment,
  name varchar(60) NOT NULL default '',
  uname varchar(25) NOT NULL default '',
  email varchar(255) NOT NULL default '',
  femail varchar(255) NOT NULL default '',
  url varchar(255) NOT NULL default '',
  user_avatar varchar(255) default 'blank.gif',
  user_regdate varchar(20) NOT NULL default '',
  user_icq varchar(15) default NULL,
  user_occ varchar(100) default NULL,
  user_from varchar(100) default NULL,
  user_intrest varchar(150) default NULL,
  user_sig varchar(255) default NULL,
  user_viewemail tinyint(2) default NULL,
  user_theme int(3) default NULL,
  user_aim varchar(18) default NULL,
  user_yim varchar(25) default NULL,
  user_msnm varchar(25) default NULL,
  pass varchar(40) NOT NULL default '',
  storynum tinyint(4) NOT NULL default '10',
  umode varchar(10) NOT NULL default '',
  uorder tinyint(1) NOT NULL default '0',
  thold tinyint(1) NOT NULL default '0',
  noscore tinyint(1) NOT NULL default '0',
  bio tinytext NOT NULL,
  ublockon tinyint(1) NOT NULL default '0',
  ublock tinytext NOT NULL,
  theme varchar(255) NOT NULL default '',
  commentmax int(11) NOT NULL default '4096',
  counter int(11) NOT NULL default '0',
  newsletter int(1) NOT NULL default '0',
  user_posts int(10) NOT NULL default '0',
  user_attachsig int(2) NOT NULL default '0',
  user_rank int(10) NOT NULL default '0',
  user_level int(10) NOT NULL default '1',
  notify_priv_message tinyint(1) NOT NULL default '1',
  lastlogin varchar(14) NOT NULL default '',
  lastvisit varchar(14) default NULL,
  locked tinyint(1) NOT NULL default '0',
  dob datetime NOT NULL default '0000-00-00 00:00:00',
  country varchar(10) NOT NULL default '',
  zip varchar(10) NOT NULL default '',
  comment varchar(255) NOT NULL default '',
  address varchar(60) NOT NULL default '',
  phone1 varchar(25) NOT NULL default '',
  phone2 varchar(25) NOT NULL default '',
  use_tooltips tinyint(1) NOT NULL default '1',
  use_preload tinyint(1) NOT NULL default '1',
  mailnotify_on_article tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (uid),
  UNIQUE KEY uname (uname),
  FULLTEXT KEY name (name)
) TYPE=MyISAM;

#
# Table structure for table 'nuke_version'
#

CREATE TABLE nuke_version (
  version varchar(20) NOT NULL default ''
) TYPE=MyISAM;

#
# Table structure for table 'nuke_webmail_config'
#

CREATE TABLE nuke_webmail_config (
  id int(11) NOT NULL auto_increment,
  userid int(11) NOT NULL default '0',
  server varchar(255) NOT NULL default '',
  port int(6) NOT NULL default '110',
  uname varchar(255) NOT NULL default '',
  pass varchar(50) NOT NULL default '',
  PRIMARY KEY  (id)
) TYPE=MyISAM COMMENT='Die Spalte UID kann geloescht werden wenn alle 0';

