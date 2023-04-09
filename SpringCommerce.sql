create database springcommerce
use springcommerce

Create table Account
(
	id_user char(30) not null,
	name_user varchar(30),
	username varchar(100),
	password varchar(100),
	role int not null,
	primary key(id_user,username)
)
select * from account
drop table account
create table Product(
	id char(10) not null,
	name varchar(30),
	price int,
	description text,
	category int,
	img text,
	color varchar(10),
	brand varchar(30),
	primary key(id)
)

create table Orders(
	id_order char(30) not null,
	name_user varchar(30),
	address varchar(50),
	phone char(12),
	email_user varchar(30),
	total_price int,
	primary key (id_order)
)	
CREATE TABLE orders_products (
    id CHAR(10) NOT NULL,
    id_order CHAR(30) NOT NULL,
	quantity int,
    FOREIGN KEY (id) REFERENCES product(id),
    FOREIGN KEY (id_order) REFERENCES orders(id_order),
    PRIMARY KEY (id,id_order)
)
drop table  orders_products

create table order_account(
	id_user char(30) not null,
    id_order char(30) not null,
    FOREIGN KEY (id_user) REFERENCES Account(id_user),
    FOREIGN KEY (id_order) REFERENCES orders(id_order),
    PRIMARY KEY (id_user,id_order)
)
insert into product values('P01',N'Black Hamburger',19,'Tomato, Chilli, Onion, Beef,...',1,'https://cdn.tgdd.vn/2021/04/CookProductThumb/620-620x620-90.jpg','black','Jolibee'),
('P02',N'French Fries',6,'Patatos, Oliv....',1,'https://cdn-p300.americantowns.com/img/article/fl-fries-1.jpg','yellow','KFC'),
('P03',N'Pizza Night Mare',49,'Tomato, Chilli, Onion, Lamb,...',1,'https://images.unsplash.com/photo-1513104890138-7c749659a591?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHx8&w=1000&q=80','yellow','Company'),
('productproductP04',N'Fried Chicken',24,'Chicken, Chilli,...',1,'https://images.ctfassets.net/crbk84xktnsl/5MPwPLfLOZKedxdkICf40X/1bf85ab6c3af42617e242b0e14b43d0a/3D_Category_App_Chicken_-_Original_Recipe.png','yellow','Jolibee')

insert into product values('P05',N'Sandwich Clamps',25,'Tomato, Salad, Cheese, Beef,...',1,'https://media.istockphoto.com/photos/sandwich-with-ham-cheese-tomatoes-lettuce-and-toasted-bread-above-picture-id914899588?k=6&m=914899588&s=170667a&w=0&h=QIntvjMaxKatdhl_DKQLPj4yaQEo4dIjM-LqqA5ULQI=','white','Breadtalk'),
('P06',N'Pilaf Salad',16,'Tomato, Vegetables, Meat, Fish, Shrimp,...',1,'https://th.bing.com/th/id/R.d110c5f7af0b8d57cf3502900e50d41f?rik=a8PhvRJ%2f%2fN2m0A&pid=ImgRaw&r=0','green','Salad Poke Saigon'),
('P07',N'Italia Spaghetti',27,'Noodle, Tomato, Meat, Egg,...',1,'https://th.bing.com/th/id/R.f3a8726b6790940096d928a8b3df4105?rik=JdM5JIPWlV3kVg&pid=ImgRaw&r=0','red','Spaghetti Box'),
('P08',N'Crab Soup',18,'Rice, Crab, Mushrooms,,...',1,'https://tse1.mm.bing.net/th/id/OIP.ivHr62i3GbUpCisTrNb4hwHaIh?pid=ImgDet&rs=1','orange','King Crab'),
('P09',N'Fried chicken',30,'Chicken, Deep-fried dough,...',1,'https://th.bing.com/th/id/R.634bf14c8b4f95e14a7314b341e8f08a?rik=JaFyIXe8VWAnEg&pid=ImgRaw&r=0','yellow','Texas'),
('P010',N'Cake',39,'Cream, Eggs, Chocolate,...',1,'https://i.pinimg.com/originals/6a/13/29/6a13297785589bafbf5f1a710b1f2a1b.jpg','brown','Tous Les Jours'),
('P011',N'Pizza Detail',29,'Crab sticks, Oysters, Shrimp, Meat, Squid,...',1,'https://th.bing.com/th/id/R.327e60ec04c91f13be3be60f2383a9d5?rik=URcNLSxJ1fBjCQ&riu=http%3a%2f%2fthepizzacompany-myanmar.com%2fproducts%2fy70kaxsmyR5lPrIV9vBNcfOH4ilxlHvuzKCj0yC9.jpeg&ehk=MmZXccPo8Yzlqz7MqUTxRYCGmaqbgF8yKPCwlA94Bos%3d&risl=&pid=ImgRaw&r=0','yellow','Company'),
('P012',N'Salmon Sushi',27,'Fresh Salmon,...',1,'https://th.bing.com/th/id/R.8e60fd56440de254dd7249822e4d8c3c?rik=QjnG%2bgumc%2bLX%2bg&pid=ImgRaw&r=0','red','Sushi Hokkaido Sachi'),
('P013',N'Kim Chi Korean',18,'Kimchi, Chili peppers,...',1,'https://www.bing.com/images/search?view=detailV2&ccid=vu4T7lxo&id=ED17C765F644907EA74666A85274636EB4B6BBC0&thid=OIP.vu4T7lxokPN3clP3J8psTAHaEK&mediaurl=https%3a%2f%2fabby.vn%2fwp-content%2fuploads%2f2016%2f09%2fcac_mon_an_han_11.jpg&cdnurl=https%3a%2f%2fth.bing.com%2fth%2fid%2fR.beee13ee5c6890f3777253f727ca6c4c%3frik%3dwLu2tG5jdFKoZg%26pid%3dImgRaw%26r%3d0%26sres%3d1%26sresct%3d1%26srh%3d730%26srw%3d1300&exph=365&expw=650&q=kim+chi+h%c3%a0n+qu%e1%bb%91c&simid=608048746890535504&FORM=IRPRST&ck=DA91A38E7FAA6BAB23C2199ABE40ABEC&selectedIndex=4&ajaxhist=0&ajaxserp=0','red','Korean Food'),
('P014',N'Kimbap Recipe Chicken',21,'Rice, Carrots, Cucumber, Chicken, Eggs,...',1,'https://i.pinimg.com/originals/50/e6/6a/50e66ad48d687b3dbcb51fa97f3578f0.jpg','white','Kimbap Jung'),
('P015',N'Tom Yum Spicy',26,'Shrimp, Tomatoes, Peppers,...',1,'https://th.bing.com/th/id/R.9413febd1ca54defdf1e760cfe75af76?rik=qyHFOqwwW%2fbuNQ&pid=ImgRaw&r=0','red','Tom Yum Thai')


insert into product values('P016',N'Zero CocaCola',10,'Carbonated beverage',2,'hinh-anh-coc-coca-cola-nuoc-ban-tung-toe-png-354.webp (600×931) (thuviendohoa.vn)','black','Coca'),
('P017',N'Starbucks Coffee',20,'Milk coffee with chocolate ice cream',2,'9b60e87fc9c1d60fbb4c2205ccdbd5ef.jpg (1281×1280) (zmtcdn.com)','brown','Starbucks'),
('P018',N'Berry Berry',19,'Yogurt and strawberries',2,'https://phuclong.com.vn/uploads/dish/d1c388a9975c2c-hoatuyetberry60000838.png','pink','Phuc Long'),
('P019',N'Pure Coffee',12,'Black and bitter coffee',2,'https://salt.tikicdn.com/cache/w1200/ts/product/ba/ab/3d/54f94c322f3a88d83a113b5b8767cc57.jpg','black','Trung Nguyen'),
('P020',N'Tra sua tran chau hoang kim',20,'Tea mixed with milk',2,'https://img.cdnki.com/tran-chau-hoang-kim-cua-koi---de0868a70b6a6cba174474905808224c.wepb','white','Koi Thé'),
('P021',N'Fruit tea',15,'Variety of tropical fruits',2,'https://png.pngtree.com/png-vector/20220120/ourmid/pngtree-fruit-tea-png-image_4355589.png','yellow','Small garden'),
('P022',N'Mint Choco',18,'Mint chocolate and milk',2,'https://gongcha.com.vn/wp-content/uploads/2022/11/Mint-Chocolate-Milk-Tea-w-Pearl-Iced.png','blue','Gong Cha'),
('P023',N'Golden Matcha',18,'Matcha with cantaloupe',2,'https://d1-concepts.com/wp-content/uploads/2023/02/30.-Matcha-Dua-Luoi.jpg','yellow','Katinat'),
('P024',N'Water',8,'Pure filtered water with added minerals',2,'https://sudospaces.com/karofi-com/2020/10/nuoc-uong-tinh-khiet-va-nuoc-khoang.jpg','white','Aquafina'),
('P025',N'Orange juice',14,'Fresh oranges and sugar',2,'https://suckhoedoisong.qltns.mediacdn.vn/324455921873985536/2022/2/19/cach-lam-nuoc-cam-ep-ngon-va-thom-ket-hop-voi-le-va-gung-5-1645248090817401855254.jpg','orange','Vfresh')


select * from account
select * from orders