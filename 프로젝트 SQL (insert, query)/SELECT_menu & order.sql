# SELECT - menu, order, order_detail
select * from menus;
select * from menu_options;
select * from menu_option_details;
select * from menu_option_group;
select * from orders;
select * from order_details;
select * from order_menu_option;

-- menu
SELECT 
    m.id AS menu_id,
    s.id AS store_id,
    s.owner_id,
    m.menu_name,
    m.menu_price,
    m.image_url,
    m.menu_description,
    m.is_available,
    mc.menu_category,
    mo.id AS menu_option_id, 
    mo.option_name,
    md.id AS detail_id,
    md.option_detail_name,
    md.additional_fee
FROM 
    menus m
left outer join 
	stores s on s.id = m.store_id
LEFT OUTER JOIN
	menu_categories mc ON m.category_id = mc.id
LEFT OUTER JOIN 
    menu_option_group mog ON mog.menu_id = m.id
LEFT OUTER JOIN
    menu_options mo ON mog.menu_option_id = mo.id
LEFT OUTER JOIN
    menu_option_details md ON md.menu_option_id = mo.id
ORDER BY
	s.owner_id, m.id, mo.id, md.id;
    
    

-- menu_total_price
with menu_total_price as (
	select
		o.id as order_id,
		sum(m.menu_price) as menu_total_price
	from
		orders o
		left outer join order_details od on od.order_id = o.id
		left outer join menus m on m.id = od.menu_id
	group by
		o.id
),

-- option_total_price
option_total_price as (
	select 
		o.id as order_id,
		sum(md.additional_fee) as option_total_price
	from
		orders o
	left outer join
		order_details od on od.order_id = o.id
	left outer join
		menus m on m.id = od.menu_id 
	left outer join
		order_menu_option omo on omo.order_detail_id = od.id
	left outer join
		menu_option_details md on md.id = omo.menu_option_detail_id
	group by
		o.id
)
-- order
select
	o.id as order_id,
    o.store_id,
    o.delivery_address,
    o.order_date,
    g.guest_nickname,
    mtp.menu_total_price + otp.option_total_price as sum_total_price,
    o.order_state
from
	orders o
left outer join
	guests g on g.order_id = o.id
left outer join
	menu_total_price mtp on mtp.order_id = o.id
left outer join
	option_total_price otp on otp.order_id = o.id
left outer join 
	order_details od on od.order_id = o.id
left outer join
	menus m on m.id = od.menu_id
left outer join
	order_menu_option omo on omo.order_detail_id = od.id
left outer join
	menu_option_details md on md.id = omo.menu_option_detail_id
where
	o.store_id = 1
group by
	o.id,
    o.store_id,
    o.delivery_address,
    g.guest_nickname

order by
	o.id;    
    
    
-- order_details
with menu_quantity as (
	select
		o.id as order_id,
		m.id as menu_id,
		m.menu_name,
		m.menu_price,
		count(od.id) as quantity
	from
		orders o
	left outer join order_details od on od.order_id = o.id
	left outer join menus m on m.id = od.menu_id
	group by
		o.id,
		m.id,
		m.menu_name,
		m.menu_price
)
select
	o.id as order_id,
	od.id as order_detail_id,
	o.delivery_address,
	o.order_date,
	mq.menu_name,
	mq.menu_price,
	mq.quantity,
	mo.option_name as menu_option_name,
	md.option_detail_name as menu_option_detail_name, 
	md.additional_fee
from
	orders o
left outer join
	menu_quantity mq on mq.order_id = o.id
left outer join
	order_details od on od.order_id = o.id and od.menu_id = mq.menu_id
left outer join
	order_menu_option omo on omo.order_detail_id = od.id
left outer join
	menu_option_details md on md.id = omo.menu_option_detail_id
left outer join
	menu_options mo on mo.id = md.menu_option_id
group by
	o.id,
	od.id,
	o.delivery_address,
	o.order_date,
	mq.menu_name,
	mq.menu_price,
	mq.quantity,
	mo.option_name,
	md.option_detail_name,
	md.additional_fee
order by
	o.id, od.id;

select
	o.id as order_id,
	m.id as menu_id, 
	m.menu_name, 
	m.menu_price,
    count(m.id) as quantity
from 
	orders o
left outer join order_details od on od.order_id = o.id
left outer join menus m on m.id = od.menu_id
group by 
	o.id, 
	m.id, 
	m.menu_name, 
	m.menu_price
order by
	o.id,
    m.id;
    
    
-- 통계 쿼리
with menu_total_price as (
	select
		o.id as order_id,
		sum(m.menu_price) as menu_total_price
	from
		orders o
		left outer join order_details od on od.order_id = o.id
		left outer join menus m on m.id = od.menu_id
	group by
		o.id
),
option_total_price as (
	select 
		o.id as order_id,
		sum(md.additional_fee) as option_total_price
	from
		orders o
	left outer join
		order_details od on od.order_id = o.id
	left outer join
		menus m on m.id = od.menu_id 
	left outer join
		order_menu_option omo on omo.order_detail_id = od.id
	left outer join
		menu_option_details md on md.id = omo.menu_option_detail_id
	group by
		o.id
)
select
	date(o.order_date), 
    hour(o.order_date),
    o.order_state,
    sum(mtp.menu_total_price + otp.option_total_price) as revenue
from 
	orders o
left outer join
	menu_total_price mtp on mtp.order_id = o.id
left outer join
	option_total_price otp on otp.order_id = o.id
where 
	year(o.order_date) = 2024
	and month(o.order_date) = 12
	and day(o.order_date) = 20
	and o.order_state = '2'
group by
	date(o.order_date),
    hour(o.order_date),
    o.order_state
order by
	date(o.order_date), hour(o.order_date);
    
select * from orders;
select * from  menu_options;
select * from menu_option_details;

