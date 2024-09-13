
use product;

CREATE TABLE savings_products (
                                  ino BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  fin_co_no BIGINT,
                                  kor_co_nm  varchar(1000),
                                  fin_prdt_cd BIGINT,
                                  fin_prdt_nm varchar(1000),
                                  rsrv_type_nm varchar(1000),
                                  save_trm int,
                                  intr_rate decimal,
                                  intr_rate2 decimal

);

