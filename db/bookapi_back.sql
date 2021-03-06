--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2
-- Dumped by pg_dump version 13.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: bookapi; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE bookapi WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';


ALTER DATABASE bookapi OWNER TO postgres;

\connect bookapi

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: authors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authors (
    id bigint NOT NULL,
    birth_date date,
    email_address character varying(255),
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL
);


ALTER TABLE public.authors OWNER TO postgres;

--
-- Name: authors_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.authors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.authors_id_seq OWNER TO postgres;

--
-- Name: authors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.authors_id_seq OWNED BY public.authors.id;


--
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books (
    id bigint NOT NULL,
    creation_date date NOT NULL,
    description character varying(3000) NOT NULL,
    isbn bigint,
    title character varying(500) NOT NULL,
    visibility_status boolean,
    author_id bigint NOT NULL,
    publisher_id bigint,
    CONSTRAINT books_isbn_check CHECK ((isbn >= '9780000000001'::bigint))
);


ALTER TABLE public.books OWNER TO postgres;

--
-- Name: books_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.books_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.books_id_seq OWNER TO postgres;

--
-- Name: books_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.books_id_seq OWNED BY public.books.id;


--
-- Name: publishers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.publishers (
    id bigint NOT NULL,
    address character varying(255),
    name character varying(255),
    telephone character varying(255)
);


ALTER TABLE public.publishers OWNER TO postgres;

--
-- Name: publishers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.publishers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.publishers_id_seq OWNER TO postgres;

--
-- Name: publishers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.publishers_id_seq OWNED BY public.publishers.id;


--
-- Name: authors id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors ALTER COLUMN id SET DEFAULT nextval('public.authors_id_seq'::regclass);


--
-- Name: books id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books ALTER COLUMN id SET DEFAULT nextval('public.books_id_seq'::regclass);


--
-- Name: publishers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishers ALTER COLUMN id SET DEFAULT nextval('public.publishers_id_seq'::regclass);


--
-- Data for Name: authors; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.authors VALUES (1, '1990-10-28', 'cblayd0@washington.edu', 'Cami', 'Blayd');
INSERT INTO public.authors VALUES (2, '1978-09-12', 'ggergus1@joomla.org', 'Gearard', 'Gergus');
INSERT INTO public.authors VALUES (3, '1984-03-16', 'irawnsley2@tumblr.com', 'Izzy', 'Rawnsley');
INSERT INTO public.authors VALUES (4, '1974-01-28', 'rdenzey3@a8.net', 'Ramsay', 'Denzey');
INSERT INTO public.authors VALUES (5, '1962-03-25', 'sjonah4@networkadvertising.org', 'Sal', 'Jonah');
INSERT INTO public.authors VALUES (6, '1955-09-24', 'dwandrack5@mail.ru', 'Darrick', 'Wandrack');
INSERT INTO public.authors VALUES (7, '1966-04-06', 'lrigg6@zdnet.com', 'Larine', 'Rigg');
INSERT INTO public.authors VALUES (8, '1993-03-30', 'glongega7@constantcontact.com', 'Gonzalo', 'Longega');
INSERT INTO public.authors VALUES (9, '1982-04-16', 'cblackden8@godaddy.com', 'Corey', 'Blackden');
INSERT INTO public.authors VALUES (10, '1982-12-13', 'mnannini9@thetimes.co.uk', 'Madonna', 'Nannini');
INSERT INTO public.authors VALUES (11, '1973-02-16', 'crobbingsa@tripadvisor.com', 'Cristionna', 'Robbings');
INSERT INTO public.authors VALUES (12, '1975-06-13', 'rrhoadesb@google.es', 'Ruddie', 'Rhoades');
INSERT INTO public.authors VALUES (13, '1978-12-06', 'cpriddlec@goo.ne.jp', 'Cleveland', 'Priddle');
INSERT INTO public.authors VALUES (14, '1968-01-15', 'vbreedyd@ask.com', 'Vince', 'Breedy');
INSERT INTO public.authors VALUES (15, '1981-02-28', 'pwitarde@nbcnews.com', 'Patton', 'Witard');
INSERT INTO public.authors VALUES (16, '1969-07-17', 'hdonnellf@wix.com', 'Hilton', 'Donnell');
INSERT INTO public.authors VALUES (17, '1954-06-22', 'emcduffieg@spotify.com', 'Elisha', 'McDuffie');
INSERT INTO public.authors VALUES (18, '1999-04-11', 'kgermainh@businessinsider.com', 'Kelcie', 'Germain');
INSERT INTO public.authors VALUES (19, '1994-12-17', 'amaclareni@wikimedia.org', 'Alicea', 'MacLaren');
INSERT INTO public.authors VALUES (20, '1989-03-03', 'ddovydenasj@unicef.org', 'Dino', 'Dovydenas');


--
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.books VALUES (1, '1953-01-18', 'a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum ante ipsum', 9784446365403, 'etiam pretium iaculis justo in', true, 18, 10);
INSERT INTO public.books VALUES (2, '2014-09-25', 'hac habitasse platea dictumst maecenas ut massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh in hac habitasse platea dictumst aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed', 9789536716911, 'dolor morbi vel', false, 18, NULL);
INSERT INTO public.books VALUES (3, '1987-12-06', 'est quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae mauris viverra diam vitae quam suspendisse potenti nullam porttitor', 9784423259821, 'mattis pulvinar nulla pede ullamcorper', false, 1, NULL);
INSERT INTO public.books VALUES (4, '1974-01-22', 'dolor morbi vel lectus in quam fringilla rhoncus mauris enim leo rhoncus sed vestibulum sit amet cursus id turpis integer aliquet massa id lobortis convallis tortor risus dapibus augue vel accumsan tellus nisi eu orci mauris lacinia sapien quis', 9783950741606, 'nullam', false, 7, NULL);
INSERT INTO public.books VALUES (5, '1983-07-28', 'non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum ante ipsum primis in faucibus orci luctus et ultrices', 9780951682526, 'ac tellus semper', false, 4, NULL);
INSERT INTO public.books VALUES (6, '1950-08-13', 'porttitor id consequat in consequat ut nulla sed accumsan felis ut at dolor quis odio consequat varius integer ac leo pellentesque ultrices mattis odio donec vitae nisi', 9787467444297, 'in tempor turpis nec', false, 9, NULL);
INSERT INTO public.books VALUES (7, '1970-02-06', 'ut mauris eget massa tempor convallis nulla neque libero convallis eget eleifend luctus ultricies eu nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante', 9783186273257, 'non mauris morbi non', false, 2, NULL);
INSERT INTO public.books VALUES (8, '1957-01-26', 'vulputate ut ultrices vel augue vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin mi sit amet lobortis sapien sapien non mi integer', 9786938608786, 'vulputate nonummy maecenas tincidunt lacus', true, 20, 3);
INSERT INTO public.books VALUES (9, '2010-12-11', 'dapibus dolor vel est donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia nisi venenatis tristique fusce congue diam id ornare imperdiet sapien urna', 9787600351901, 'suspendisse', true, 13, 10);
INSERT INTO public.books VALUES (10, '1959-10-03', 'ut volutpat sapien arcu sed augue aliquam erat volutpat in congue etiam justo etiam pretium iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy', 9787796213913, 'suspendisse potenti in eleifend', true, 9, 7);
INSERT INTO public.books VALUES (11, '2017-06-15', 'lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo pellentesque viverra pede ac diam', 9782900931502, 'rhoncus aliquam lacus morbi', true, 5, 10);
INSERT INTO public.books VALUES (12, '2006-08-25', 'a ipsum integer a nibh in quis justo maecenas rhoncus aliquam lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo pellentesque viverra pede ac diam cras pellentesque volutpat dui maecenas tristique est et tempus semper est quam pharetra magna ac consequat', 9785769547207, 'neque aenean', true, 18, 6);
INSERT INTO public.books VALUES (13, '1952-11-29', 'eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor duis mattis egestas metus aenean fermentum donec ut mauris eget massa tempor convallis nulla neque libero convallis eget eleifend luctus ultricies eu nibh quisque id justo sit amet sapien', 9788510084906, 'justo in blandit ultrices enim', true, 8, 2);
INSERT INTO public.books VALUES (14, '2018-11-20', 'sed lacus morbi sem mauris laoreet ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in lectus', 9786729428835, 'mauris lacinia sapien', true, 20, 10);
INSERT INTO public.books VALUES (15, '2015-01-11', 'lorem vitae mattis nibh ligula nec sem duis aliquam convallis nunc proin at turpis a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec pharetra magna', 9784994549762, 'ac nibh fusce', true, 20, 5);
INSERT INTO public.books VALUES (16, '2008-01-05', 'convallis eget eleifend luctus ultricies eu nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis in faucibus orci', 9786804615023, 'dui proin leo', true, 20, 6);
INSERT INTO public.books VALUES (17, '1984-09-14', 'aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero', 9789965663005, 'mattis pulvinar nulla', true, 20, 5);
INSERT INTO public.books VALUES (18, '1981-09-08', 'nisi venenatis tristique fusce congue diam id ornare imperdiet sapien urna pretium nisl ut volutpat sapien arcu sed augue aliquam erat volutpat in congue etiam justo etiam pretium iaculis justo in hac habitasse', 9784776487479, 'tellus semper interdum', true, 7, 8);
INSERT INTO public.books VALUES (19, '1970-10-16', 'quam turpis adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam convallis nunc proin at turpis a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum ante ipsum primis in faucibus orci luctus', 9787504354721, 'donec', true, 14, 9);
INSERT INTO public.books VALUES (20, '1981-02-06', 'rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras', 9780217210197, 'pretium iaculis', true, 12, 3);
INSERT INTO public.books VALUES (21, '1963-03-01', 'penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio cras mi', 9782382208670, 'elementum ligula vehicula consequat', true, 4, 2);
INSERT INTO public.books VALUES (22, '1989-11-07', 'quis justo maecenas rhoncus aliquam lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo', 9784263313640, 'ligula suspendisse', true, 8, 8);
INSERT INTO public.books VALUES (23, '1981-07-29', 'accumsan odio curabitur convallis duis consequat dui nec nisi volutpat eleifend donec ut dolor morbi vel lectus in quam fringilla rhoncus mauris enim leo rhoncus sed vestibulum sit amet', 9785119612160, 'pretium iaculis justo in hac', true, 8, 2);
INSERT INTO public.books VALUES (24, '1981-06-20', 'aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate', 9783914688999, 'in faucibus orci', true, 20, 2);
INSERT INTO public.books VALUES (25, '2017-03-27', 'integer pede justo lacinia eget tincidunt eget tempus vel pede morbi porttitor lorem id ligula suspendisse ornare consequat lectus in est risus', 9780135427426, 'potenti', true, 16, 7);
INSERT INTO public.books VALUES (26, '2002-01-23', 'at ipsum ac tellus semper interdum mauris ullamcorper purus sit amet nulla quisque arcu libero rutrum ac lobortis vel dapibus at diam', 9788630580087, 'phasellus id sapien in', true, 6, 7);
INSERT INTO public.books VALUES (27, '1990-06-29', 'mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget', 9789093807843, 'diam vitae quam suspendisse', true, 12, 4);
INSERT INTO public.books VALUES (28, '1970-05-29', 'justo in blandit ultrices enim lorem ipsum dolor sit amet consectetuer adipiscing elit proin interdum mauris non ligula pellentesque ultrices phasellus id', 9782643197456, 'vestibulum ante ipsum primis in', true, 3, 3);
INSERT INTO public.books VALUES (29, '1966-04-22', 'est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim in tempor', 9786709112721, 'dis parturient montes nascetur ridiculus', true, 6, 5);
INSERT INTO public.books VALUES (30, '1981-06-03', 'quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti in eleifend quam a odio', 9785083778107, 'vestibulum rutrum rutrum', true, 9, 8);
INSERT INTO public.books VALUES (31, '1995-07-09', 'pellentesque ultrices mattis odio donec vitae nisi nam ultrices libero non mattis pulvinar nulla pede ullamcorper augue a suscipit nulla elit ac nulla sed vel enim sit amet nunc viverra dapibus nulla suscipit ligula in lacus', 9788727782553, 'scelerisque mauris', true, 20, 5);
INSERT INTO public.books VALUES (32, '1950-05-17', 'nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at velit vivamus vel nulla eget eros elementum pellentesque quisque porta volutpat erat quisque erat eros viverra eget congue eget semper rutrum nulla', 9787465085423, 'nulla sed accumsan', true, 14, 7);
INSERT INTO public.books VALUES (33, '2010-05-05', 'in hac habitasse platea dictumst aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed magna at nunc commodo placerat praesent blandit nam nulla integer pede justo lacinia', 9789977732044, 'accumsan', true, 20, 8);
INSERT INTO public.books VALUES (34, '1985-09-12', 'justo eu massa donec dapibus duis at velit eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum justo nec condimentum neque sapien placerat ante', 9786544215543, 'fusce lacus purus aliquet at', true, 8, 4);
INSERT INTO public.books VALUES (35, '1980-01-27', 'ultrices enim lorem ipsum dolor sit amet consectetuer adipiscing elit proin interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vehicula condimentum curabitur in libero', 9782690172078, 'mauris lacinia', true, 14, 7);
INSERT INTO public.books VALUES (36, '1979-12-05', 'pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum sagittis sapien cum sociis', 9786159663814, 'non velit', true, 6, 2);
INSERT INTO public.books VALUES (37, '1960-08-30', 'aliquet ultrices erat tortor sollicitudin mi sit amet lobortis sapien sapien non mi integer ac neque duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti', 9789264935795, 'nibh in lectus pellentesque', true, 17, 4);
INSERT INTO public.books VALUES (38, '2008-11-29', 'nulla eget eros elementum pellentesque quisque porta volutpat erat quisque erat eros viverra eget congue eget semper rutrum nulla nunc purus phasellus', 9789803459856, 'maecenas', true, 8, 2);
INSERT INTO public.books VALUES (39, '2000-01-12', 'lorem ipsum dolor sit amet consectetuer adipiscing elit proin interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque eget nunc donec quis orci eget orci vehicula condimentum curabitur', 9781168888604, 'duis bibendum morbi non quam', true, 17, 1);
INSERT INTO public.books VALUES (40, '1986-06-15', 'ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae nulla dapibus dolor vel est donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia nisi venenatis tristique fusce congue diam id ornare', 9787151568876, 'sed lacus morbi sem mauris', true, 11, 7);
INSERT INTO public.books VALUES (41, '1965-12-22', 'adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam convallis nunc proin at turpis a pede posuere nonummy integer non velit donec diam neque vestibulum eget vulputate ut ultrices vel augue vestibulum ante ipsum primis in faucibus orci luctus', 9787427481297, 'praesent lectus', true, 7, 2);
INSERT INTO public.books VALUES (42, '1983-05-26', 'suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus vestibulum sagittis sapien cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel', 9789363118473, 'ut dolor', true, 14, 5);
INSERT INTO public.books VALUES (43, '1976-08-17', 'elit sodales scelerisque mauris sit amet eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor duis mattis egestas metus aenean fermentum donec ut mauris eget massa tempor convallis', 9786961059789, 'justo sit amet sapien', true, 5, 6);
INSERT INTO public.books VALUES (44, '2011-04-15', 'fringilla rhoncus mauris enim leo rhoncus sed vestibulum sit amet cursus id turpis integer aliquet massa id lobortis convallis tortor risus dapibus augue vel accumsan tellus nisi eu orci mauris lacinia sapien', 9788520979807, 'rutrum', true, 3, 8);
INSERT INTO public.books VALUES (45, '1970-01-15', 'aliquam erat volutpat in congue etiam justo etiam pretium iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non', 9789498601080, 'montes nascetur', true, 13, 3);
INSERT INTO public.books VALUES (46, '1986-11-01', 'ut rhoncus aliquet pulvinar sed nisl nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna', 9785060602769, 'consequat in consequat ut nulla', true, 18, 9);
INSERT INTO public.books VALUES (47, '1969-10-02', 'ac consequat metus sapien ut nunc vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae mauris viverra diam vitae quam suspendisse potenti nullam porttitor lacus at turpis donec posuere metus vitae ipsum aliquam', 9788513488601, 'platea dictumst aliquam', true, 5, 10);
INSERT INTO public.books VALUES (48, '1987-03-28', 'pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque', 9787438200735, 'platea dictumst aliquam augue', true, 14, 8);
INSERT INTO public.books VALUES (49, '1985-03-14', 'luctus et ultrices posuere cubilia curae nulla dapibus dolor vel est donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia nisi venenatis tristique fusce congue diam id ornare imperdiet sapien urna pretium nisl ut volutpat sapien arcu sed augue aliquam', 9786769200871, 'nulla suscipit', true, 2, 5);
INSERT INTO public.books VALUES (50, '1965-05-29', 'duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis lectus suspendisse potenti in eleifend quam a odio in hac habitasse platea dictumst maecenas ut massa quis', 9783319930968, 'purus aliquet at feugiat non', true, 10, 2);


--
-- Data for Name: publishers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.publishers VALUES (1, '8723 Weeping Birch Lane', 'Ullrich, Sporer and Rempel', '572-388-7700');
INSERT INTO public.publishers VALUES (2, '41631 Ilene Point', 'Welch-Mann', '702-140-9494');
INSERT INTO public.publishers VALUES (3, '90006 Westend Plaza', 'Heller-Nicolas', '928-776-1693');
INSERT INTO public.publishers VALUES (4, '9 Fairfield Park', 'Lesch-Pagac', '882-194-0350');
INSERT INTO public.publishers VALUES (5, '063 Vahlen Center', 'Boyle, Greenholt and Mann', '775-738-1004');
INSERT INTO public.publishers VALUES (6, '17647 Moland Way', 'Schowalter LLC', '980-785-8692');
INSERT INTO public.publishers VALUES (7, '2075 Grim Street', 'Daugherty-Trantow', '201-288-4037');
INSERT INTO public.publishers VALUES (8, '43404 Bobwhite Center', 'VonRueden and Sons', '701-363-5810');
INSERT INTO public.publishers VALUES (9, '0 Green Ridge Crossing', 'Brakus and Sons', '123-885-6085');
INSERT INTO public.publishers VALUES (10, '153 Bluejay Parkway', 'Brown, Witting and Wunsch', '333-413-7642');


--
-- Name: authors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.authors_id_seq', 20, true);


--
-- Name: books_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.books_id_seq', 50, true);


--
-- Name: publishers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.publishers_id_seq', 10, true);


--
-- Name: authors authors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (id);


--
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);


--
-- Name: publishers publishers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT publishers_pkey PRIMARY KEY (id);


--
-- Name: books ukkibbepcitr0a3cpk3rfr7nihn; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT ukkibbepcitr0a3cpk3rfr7nihn UNIQUE (isbn);


--
-- Name: books fkayy5edfrqnegqj3882nce6qo8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT fkayy5edfrqnegqj3882nce6qo8 FOREIGN KEY (publisher_id) REFERENCES public.publishers(id);


--
-- Name: books fkfjixh2vym2cvfj3ufxj91jem7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT fkfjixh2vym2cvfj3ufxj91jem7 FOREIGN KEY (author_id) REFERENCES public.authors(id);


--
-- PostgreSQL database dump complete
--

