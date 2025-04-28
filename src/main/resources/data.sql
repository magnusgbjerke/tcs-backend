insert into size
values
('S'),
('M'),
('L'),
('36'),
('40'),
('42');

insert into product_category
values
('tops'),
('bottoms'),
('footwear');

insert into type
values
('hoodies', 'tops'),
('t-shirt', 'tops'),
('pants', 'bottoms'),
('shoes', 'footwear');

insert into customer_category
values
('men'),
('women'),
('kids');

insert into brand (name)
values
('Velora'),
('Drift & Dune'),
('Noxen'),
('Urban Loom'),
('LuxeRoots');

insert into product (id, name, brand_name, description, rating, image, type, customer_category, price)
values
(
'velora-houndstooth-blazer-i-regular-fit',
'Houndstooth Blazer i Regular Fit',
'Velora',
'A stylish and sophisticated houndstooth-patterned blazer with a regular fit, perfect for both casual and formal occasions.',
3.7,
'hoodie-axe.jpeg',
'hoodies',
'men',
799
),
(
'drift-&-dune-oversized-hoodie-i-kangaroo-pocket',
'Oversized Hoodie i Kangaroo Pocket',
'Drift & Dune',
'An oversized hoodie made from soft fleece, featuring a cozy kangaroo pocket to keep your hands warm during chilly days.',
4.5,
'hoodie-black-leg.webp',
'hoodies',
'men',
599
),
(
'noxen-cargo-joggers-i-elastic-waist',
'Cargo Joggers i Elastic Waist',
'Noxen',
'Sporty joggers with a comfortable elastic waistband and multiple pockets for a functional and stylish look.',
4.2,
'hoodie-detroit.jpeg',
'hoodies',
'men',
199
),
(
'urban-loom-graphic-tee-i-vintage-wash',
'Graphic Tee i Vintage Wash',
'Urban Loom',
'A vintage-style graphic tee made from 100% organic cotton, featuring a relaxed fit and soft feel.',
4.8,
't-shirt-2020.jpeg',
't-shirt',
'men',
249
),
(
'luxeroots-basic-hoodie-i-warm-fleece',
'Basic Hoodie i Warm Fleece',
'LuxeRoots',
'A cozy basic hoodie made from soft fleece, offering a comfortable fit and warmth for everyday wear.',
4.1,
'hoodie-m.jpeg',
'hoodies',
'men',
900
);

insert into stock (id_id, quantity, size_name)
values
('velora-houndstooth-blazer-i-regular-fit', 5, 'S'),
('velora-houndstooth-blazer-i-regular-fit', 5, 'M'),

('drift-&-dune-oversized-hoodie-i-kangaroo-pocket', 15, 'S'),
('drift-&-dune-oversized-hoodie-i-kangaroo-pocket', 15, 'M'),

('noxen-cargo-joggers-i-elastic-waist', 5, 'S'),
('noxen-cargo-joggers-i-elastic-waist', 14, 'M'),

('urban-loom-graphic-tee-i-vintage-wash', 0, 'S'),
('urban-loom-graphic-tee-i-vintage-wash', 0, 'M'),

('luxeroots-basic-hoodie-i-warm-fleece', 10, 'S'),
('luxeroots-basic-hoodie-i-warm-fleece', 8, 'M');

-- Views

CREATE VIEW Products AS
SELECT product.id, product.brand_name as "brand", product.name, stock.quantity, stock.size_name as "size",
product.customer_category as "customer category", product.type,
type.product_category as "product category"
FROM product
INNER JOIN stock on product.id=stock.id_id
INNER JOIN type on type.name=product.type