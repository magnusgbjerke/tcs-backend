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

insert into product (name, type, customer_category)
values
('Houndstooth blazer i regular fit', 'hoodies', 'men'),
('AIR FORCE 1 07', 'shoes', 'women'),
('Slim fit corduroy chinos', 'pants', 'men');

insert into stock (id_id, quantity, size_name)
values
(1, 5, 'medium'),
(1, 10, 'small'),
(2, 2, '36'),
(2, 9, '40'),
(2, 33, '42'),
(3, 13, 'small');

-- Views

CREATE VIEW Products AS
SELECT product.id, product.name, stock.quantity, stock.size_name as "size",
product.customer_category as "customer category", product.type,
type.product_category as "product category"
FROM product
INNER JOIN stock on product.id=stock.id_id
INNER JOIN type on type.name=product.type