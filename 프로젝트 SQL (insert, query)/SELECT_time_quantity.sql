select * from orders;

select
    date(o.order_date) as order_date,
    hour(o.order_date) as order_time,
    count(*) as quantity
from
	orders o
inner join stores s on s.id = o.store_id
where
    year(o.order_date) = 2024
    and month(o.order_date) = 12
    and day(o.order_date) = 15
    and o.order_state = '2'
    and s.owner_id = 1
group by
    date(o.order_date),
    hour(o.order_date)
order by
	date(o.order_date),
    hour(o.order_date)