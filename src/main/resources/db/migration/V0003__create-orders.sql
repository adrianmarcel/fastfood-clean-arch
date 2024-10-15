CREATE TABLE IF NOT EXISTS orders (
    id                               uuid                  primary key             default uuid_generate_v4()        ,
    customer                         uuid                                                                            ,
    products                         text                  not null                                                  ,
    status                           varchar(255)          not null                                                  ,
    mercado_pago_payment_id          uuid                  not null                                                  ,
    created_at                       timestamp             with time zone          default current_timestamp not null,
    updated_at                       timestamp             with time zone          default current_timestamp not null
);