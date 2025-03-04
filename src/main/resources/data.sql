insert into size
values
('small'),
('medium'),
('large'),
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

insert into product (id, brand_name, name, type, customer_category)
values
('velora-houndstooth-blazer-i-regular-fit', 'Velora', 'Houndstooth blazer i regular fit', 'hoodies', 'men'),
('velora-air-force-1-07','Velora', 'AIR FORCE 1 07', 'shoes', 'women'),
('noxen-slim-fit-corduroy-chinos','Noxen', 'Slim fit corduroy chinos', 'pants', 'men');

insert into stock (id_id, quantity, size_name)
values
('velora-houndstooth-blazer-i-regular-fit', 5, 'medium'),
('velora-houndstooth-blazer-i-regular-fit', 10, 'small'),
('velora-air-force-1-07', 2, '36'),
('velora-air-force-1-07', 9, '40'),
('velora-air-force-1-07', 33, '42'),
('noxen-slim-fit-corduroy-chinos', 13, 'small');

-- Views

CREATE VIEW Products AS
SELECT product.id, product.brand_name as "brand", product.name, stock.quantity, stock.size_name as "size",
product.customer_category as "customer category", product.type,
type.product_category as "product category"
FROM product
INNER JOIN stock on product.id=stock.id_id
INNER JOIN type on type.name=product.type