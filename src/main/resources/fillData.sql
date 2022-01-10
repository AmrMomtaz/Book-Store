use book_store;
/* Fill puplishers */
insert into publishers values("Radwan","Agamy","01285518669");
insert into publishers values("Momtaz","Shatby","01285518669");
insert into publishers values("Msbah","Shatby","01285518669");
insert into publishers values("Mostafa","smoha","01285518669");
insert into publishers values("Mohamed","vectoria","01285518669");
insert into publishers values("Hussien","Agamy","01285518669");
insert into publishers values("Yousry","Agamy","01285518669");
insert into publishers values("Ahmed","Agamy","01285518669");
insert into publishers values("Anthipo","Agamy","01285518669");
insert into publishers values("Soly","Agamy","01285518669");

/* books */
insert into books values("111","Title1","Radwan","1980",50,"comedia","60","500");
insert into books values("222","Title2","Radwan","2000",50,"politics","60","600");
insert into books values("333","Title3","Radwan","1985",50,"law","60","100");
insert into books values("444","Title4","Radwan","1977",50,"politics","60","150");
insert into books values("555","Title5","Radwan","2015",50,"comedia","60","150");
insert into books values("666","Title6","Radwan","2014",50,"law","60","200");
insert into books values("777","Title7","Radwan","2017",50,"politics","60","250");
insert into books values("888","Title8","Radwan","2022",50,"comedia","60","150");
insert into books values("999","Title9","Radwan","1960",50,"comedia","60","250");
insert into books values("110","Title10","Radwan","1963",50,"law","60","350");
insert into books values("220","Title11","Radwan","1987",50,"comedia","60","450");
insert into books values("330","Title12","Radwan","1926",50,"comedia","60","350");
insert into books values("440","Title13","Radwan","1974",50,"law","60","650");
insert into books values("550","Title14","Radwan","1963",50,"comedia","60","750");
insert into books values("660","Title15","Radwan","1974",50,"comedia","60","250");
insert into books values("770","Title16","Radwan","1944",50,"law","60","650");
insert into books values("880","Title17","Momtaz","2020",50,"comedia","60","620");
insert into books values("990","Title18","Momtaz","2002",50,"politics","60","120");
insert into books values("100","Title19","Momtaz","2005",50,"law","60","230");
insert into books values("200","Title20","Momtaz","2009",50,"comedia","60","120");
insert into books values("2000","Title21","Momtaz","2007",50,"comedia","60","210");
insert into books values("300","Title22","Momtaz","2005",50,"law","60","310");
insert into books values("400","Title23","Momtaz","2015",50,"comedia","60","610");
insert into books values("500","Title24","Momtaz","2014",50,"comedia","60","360");
insert into books values("600","Title25","Momtaz","2016",50,"comedia","60","320");
insert into books values("700","Title26","Msbah","2017",50,"politics","60","120");
insert into books values("800","Title27","Msbah","2011",50,"comedia","60","310");
insert into books values("900","Title28","Msbah","2013",50,"comedia","60","69");
insert into books values("101","Title29","Msbah","2016",50,"law","60","69");
insert into books values("202","Title30","Msbah","2018",50,"comedia","60","69");
insert into books values("303","Title31","Hussien","2017",50,"comedia","60","78");
insert into books values("404","Title32","Hussien","2016",50,"law","60","100");
insert into books values("505","Title33","Hussien","2013",50,"comedia","60","130");
insert into books values("606","Title34","Yousry","2012",50,"comedia","60","120");
insert into books values("707","Title35","Yousry","2003",50,"law","60","140");
insert into books values("808","Title36","Yousry","2000",50,"comedia","60","170");
insert into books values("909","Title37","Yousry","2000",50,"comedia","60","160");

/*authors*/
insert into authors values("111","author1");
insert into authors values("111","author2");
insert into authors values("111","author3");
insert into authors values("111","author4");
insert into authors values("111","author5");

insert into authors values("222","author5");
insert into authors values("222","author6");
insert into authors values("222","author7");
insert into authors values("222","author8");
insert into authors values("222","author9");


insert into authors values("333","author1");
insert into authors values("333","author2");

insert into authors values("444","author1");
insert into authors values("555","author1");
insert into authors values("666","author1");

insert into authors values("777","author2");
insert into authors values("777","author6");
insert into authors values("777","author4");
insert into authors values("777","author8");
insert into authors values("888","author5");
insert into authors values("888","author2");
insert into authors values("888","author1");

insert into authors values("999","author4");
insert into authors values("999","author1");
insert into authors values("999","author2");
insert into authors values("999","author7");

insert into authors values("110","author9");
insert into authors values("110","author7");
insert into authors values("110","author6");
insert into authors values("110","author1");

insert into authors values("220","author12");
insert into authors values("330","author1");
insert into authors values("220","author1");
insert into authors values("330","author3");
insert into authors values("440","author1");
insert into authors values("550","author1");
insert into authors values("660","author1");
insert into authors values("770","author1");
insert into authors values("880","author1");
insert into authors values("990","author1");
insert into authors values("100","author1");
insert into authors values("200","author1");
insert into authors values("300","author1");
insert into authors values("400","author1");
insert into authors values("500","author1");
insert into authors values("600","author1");
insert into authors values("700","author1");
insert into authors values("800","author1");
insert into authors values("900","author1");
insert into authors values("101","author1");
insert into authors values("202","author1");
insert into authors values("303","author1");
insert into authors values("404","author1");
insert into authors values("505","author1");
insert into authors values("606","author1");
insert into authors values("707","author1");
insert into authors values("808","author1");
insert into authors values("909","author1");


/*Users*/
insert into users values(1,"moradwan","123456","mohamed","radwan","mr.radwan2000@gmail.com","0125518669","Alexandria","admin");
insert into users values(2,"momtaz","123456","Amr","momtaz","amrmomtaz@gmail.com","0125518669","Alexandria","admin");
insert into users values(3,"mostafa","123456","mostafa","msbah","mostafa@gmail.com","0125518669","Alexandria","admin");
insert into users values(4,"yousry","123456","yousry","kamel","yousry@gmail.com","0125518669","Alexandria","customer");
insert into users values(5,"nour","123456","nour","rady","nuor@gmail.com","0125518669","Alexandria","customer");
insert into users values(6,"dummy1","123456","dummy","man","dummy1@gmail.com","0125518669","Alexandria","customer");
insert into users values(7,"dummy2","123456","dummy","man","dummy2@gmail.com","0125518669","Alexandria","customer");
insert into users values(8,"dummy3","123456","dummy","man","dummy3@gmail.com","0125518669","Alexandria","customer");
insert into users values(9,"dummy4","123456","dummy","man","dummy4@gmail.com","0125518669","Alexandria","customer");
insert into users values(10,"dummy5","123456","dummy","man","dummy5@gmail.com","0125518669","Alexandria","customer");
insert into users values(11,"dummy6","123456","dummy","man","dummy6@gmail.com","0125518669","Alexandria","customer");
insert into users values(12,"dummy7","123456","dummy","man","dummy7@gmail.com","0125518669","Alexandria","customer");
insert into users values(13,"dummy8","123456","dummy","man","dummy8@gmail.com","0125518669","Alexandria","customer");
insert into users values(14,"dummy9","123456","dummy","man","dummy9@gmail.com","0125518669","Alexandria","customer");
insert into users values(15,"dummy10","123456","dummy","man","dummy10@gmail.com","0125518669","Alexandria","customer");
insert into users values(16,"dummy11","123456","dummy","man","dummy11@gmail.com","0125518669","Alexandria","customer");
insert into users values(17,"dummy12","123456","dummy","man","dummy12@gmail.com","0125518669","Alexandria","customer");



/*orders*/
insert into orders values(1,"111","2","50","2022-01-07");
insert into orders values(1,"300","4","9","2021-12-22");
insert into orders values(2,"222","7","150","2022-01-07");
insert into orders values(2,"111","2","7","2021-12-24");
insert into orders values(3,"333","4","17","2021-12-25");
insert into orders values(3,"101","2","3","2021-12-24");
insert into orders values(3,"400","2","13","2021-12-24");
insert into orders values(4,"111","2","15","2021-12-24");
insert into orders values(4,"440","2","13","2021-12-24");
insert into orders values(5,"111","2","12","2021-12-23");
insert into orders values(6,"200","7","5","2021-12-21");
insert into orders values(6,"404","14","12","2021-12-24");
insert into orders values(7,"800","2","4","2021-12-24");
insert into orders values(8,"900","15","13","2021-12-24");
insert into orders values(11,"220","7","13","2021-11-24");
insert into orders values(12,"606","2","13","2021-11-24");
insert into orders values(15,"707","2","13","2021-11-24");
insert into orders values(17,"808","2","13","2021-11-24");
insert into orders values(14,"909","2","13","2021-11-24");
insert into orders values(16,"707","2","13","2021-11-24");
insert into orders values(12,"101","2","13","2021-10-24");
insert into orders values(16,"700","2","13","2021-10-24");
insert into orders values(14,"300","2","13","2021-11-24");
insert into orders values(13,"202","2","13","2021-10-24");
insert into orders values(15,"100","2","13","2021-12-24");
insert into orders values(14,"101","2","13","2021-9-24");
insert into orders values(13,"707","2","13","2021-8-24");
insert into orders values(15,"505","2","13","2021-7-24");
insert into orders values(15,"550","2","13","2021-7-24");
insert into orders values(15,"660","2","13","2021-7-24");

/*credit card*/
insert into credit_card values("5555555555554444","2023-12-24");
insert into credit_card values("5555555555599444","2023-12-24");
insert into credit_card values("5555555577554444","2023-12-24");
insert into credit_card values("5555554455554444","2023-12-24");
insert into credit_card values("5555555555124444","2024-12-24");
insert into credit_card values("5555578555554444","2025-12-24");
insert into credit_card values("5555596555554444","2026-12-24");



/* shopping_cart*/

insert into shopping_cart values(4,"100",10,70);
insert into shopping_cart values(4,"101",10,40);
insert into shopping_cart values(4,"110",10,10);
insert into shopping_cart values(4,"111",10,30);

insert into shopping_cart values(5,"101",10,70);
insert into shopping_cart values(5,"200",10,70);
insert into shopping_cart values(5,"300",10,70);

insert into shopping_cart values(6,"100",10,70);
insert into shopping_cart values(6,"300",10,60);
insert into shopping_cart values(6,"700",10,40);

insert into shopping_cart values(7,"300",10,70);
insert into shopping_cart values(7,"303",10,50);
insert into shopping_cart values(7,"404",10,10);







