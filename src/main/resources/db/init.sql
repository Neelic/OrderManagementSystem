create table products
(
    id    uuid         not null
        constraint products_pk
            primary key,
    name  varchar(150) not null,
    price numeric      not null
);

alter table products
    owner to "user";

create table orders
(
    id            uuid        not null
        constraint orders_pk
            primary key,
    customer_name varchar(70) not null,
    total_price   numeric     not null
);

alter table orders
    owner to "user";

create table order_products
(
    id_product uuid not null
        constraint order_products_products_id_fk
            references products,
    id_order   uuid not null
        constraint order_products_orders_id_fk
            references orders,
    constraint order_products_pk
        primary key (id_order, id_product)
);

alter table order_products
    owner to "user";

-- test data
INSERT INTO public.orders (id, customer_name, total_price)
VALUES ('fd46ba38-ba62-43c3-b594-6daba5ad2d8e', 'Test client', 45);
INSERT INTO public.orders (id, customer_name, total_price)
VALUES ('341552c6-5661-4589-a0db-08d4834cab28', 'Test client', 72.45);
INSERT INTO public.products (id, name, price)
VALUES ('d62158ec-b3b9-4271-9d7d-642bbef5373f', 'Test product', 27.45);
INSERT INTO public.products (id, name, price)
VALUES ('ab25375d-3ff4-4962-a2da-a090defe4a3f', 'Other test product', 45);
INSERT INTO public.order_products (id_product, id_order)
VALUES ('ab25375d-3ff4-4962-a2da-a090defe4a3f', 'fd46ba38-ba62-43c3-b594-6daba5ad2d8e');
INSERT INTO public.order_products (id_product, id_order)
VALUES ('d62158ec-b3b9-4271-9d7d-642bbef5373f', '341552c6-5661-4589-a0db-08d4834cab28');
INSERT INTO public.order_products (id_product, id_order)
VALUES ('ab25375d-3ff4-4962-a2da-a090defe4a3f', '341552c6-5661-4589-a0db-08d4834cab28');


