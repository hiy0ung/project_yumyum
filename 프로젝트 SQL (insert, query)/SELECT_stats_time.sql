# revene
-- menu_total_price
with menu_total_price as (
	select
		o.id as order_id,
        s.owner_id as user_id,
		sum(m.menu_price) as menu_total_price
	from
		orders o
		left outer join order_details od on od.order_id = o.id
		left outer join menus m on m.id = od.menu_id
        left outer join stores s on s.id = m.store_id
	group by
		o.id, s.owner_id
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
select
	date(o.order_date), 
    hour(o.order_date),
    sum(mtp.menu_total_price + otp.option_total_price) as revenue
from 
	orders o
left outer join
	menu_total_price mtp on mtp.order_id = o.id
left outer join
	option_total_price otp on otp.order_id = o.id
where 
	year(o.order_date) = 2024 # :year
	and month(o.order_date) = 12 # :month
	and day(o.order_date) = 23 # :day
	and o.order_state = '2'
    and mtp.user_id = 1 # :userId
group by
	date(o.order_date),
    hour(o.order_date),
    o.order_state
order by
	date(o.order_date), hour(o.order_date);
    
# quantity
with menu_total_price as (
	select
		o.id as order_id,
        s.owner_id as user_id,
		sum(m.menu_price) as menu_total_price
	from
		orders o
		left outer join order_details od on od.order_id = o.id
		left outer join menus m on m.id = od.menu_id
        left outer join stores s on s.id = m.store_id
	group by
		o.id, s.owner_id
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
select
	date(o.order_date), 
    hour(o.order_date),
    sum(mtp.menu_total_price + otp.option_total_price) as revenue
from 
	orders o
left outer join
	menu_total_price mtp on mtp.order_id = o.id
left outer join
	option_total_price otp on otp.order_id = o.id
where 
	year(o.order_date) = 2024 # :year
	and month(o.order_date) = 12 # :month
	and day(o.order_date) = 23 # :day
	and o.order_state = '2'
    and mtp.user_id = 1 # :userId
group by
	date(o.order_date),
    hour(o.order_date),
    o.order_state
order by
	date(o.order_date), hour(o.order_date);