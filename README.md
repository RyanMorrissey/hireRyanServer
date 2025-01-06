# HireRyanServer

Written as a companion to HireRyanClient.
Built with Spring and PostgreSQL, this was an excuse to learn how to deploy a 
server on EC2.

Overall not too much here, I mostly focused on setting up a service, controller, 
dto and model so that all my main bases were covered.  

Deployed (once) with EC2 with a linux server and load balancer.  Taken down because
it was just too expensive to maintain for such a small server.  I will still update 
it for exercises but won't be deployed online again, sorry!


## Required PostgreSQL tables

```SQL
CREATE SEQUENCE IF NOT EXISTS public.database_test_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
    
CREATE TABLE IF NOT EXISTS public.database_test
(
    id bigint NOT NULL DEFAULT nextval('database_test_id_seq'::regclass),
    note character varying COLLATE pg_catalog."default",
    date_created timestamp with time zone NOT NULL,
    date_updated timestamp with time zone NOT NULL,
    is_deleted boolean,
    browser_cookie character varying COLLATE pg_catalog."default" NOT NULL,
    personal_id integer
)
```
