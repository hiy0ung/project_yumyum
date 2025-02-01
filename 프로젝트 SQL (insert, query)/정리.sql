select * from orders;

select 
	u.id, u.user_id, s.owner_id, s.store_name, s.category
from
	users u 
inner join stores s on s.owner_id = u.id;
# 1 (1번가게) state - 1
-- 1: 후라이드치킨 / 18000 (순살 1000)
-- 2: 양념치킨 / 19000 (뼈 0 / 무추가 500)
-- 14: 후라이드치킨 / 18000 (뼈 0 / 양념치킨소스 추가 500)
-- menu_total_price: 55000
-- option_total_price: 2000
-- sum_total_price: 57000

# 2 (2번가게) state - 2
-- 3: 떡볶이 / 9000 (1단계 0 / 모짜 2000)
-- menu_total_price: 9000
-- option_total_price: 2000
-- sum_total_price: 11000

# 3 (2번가게) - state - 2
-- 4: 모둠튀김 / 12000 (사이즈 L 2000)
-- 5: 떡튀순 / 16000 (2단계 0 / 간+허파 0)
-- menu_total_price: 28000
-- option_total_price: 2000
-- sum_total_price: 30000


# 4 (3번가게) state - 0
-- 6: 토마토스파게티 / 12000 (미트볼 2500 / 기본 0)
-- 7: 크림스파게티 / 12000 (곱빼기 1500)
-- menu_total_price: 24000
-- option_total_price: 4000
-- sum_total_price: 28000


# 5 (3번가게) state - 1
-- 8: 토마토 스파게티 / 12000 (기본 0)
-- 9: 해쉬브라운 / 3000 (2개 추가 1500)
-- 10: 감자고로케 / 2500 (2개 추가 1000)
-- menu_total_price: 17500
-- option_total_price: 2500
-- sum_total_price: 20000


# 6 (1번가게) state - 2
-- 11: 1+1치킨 / 30000 (후라이드 0 / 간장 1000) 
-- menu_total_price: 30000
-- option_total_price: 1000
-- sum_total_price: 31000


# 7 (2번가게) state - 2
-- 12: 떡볶이 / 9000 (3단계 0)
-- 13: 콜라 / 2000 (제로콜라 변경 500)
-- menu_total_price: 11000
-- option_total_price: 500
-- sum_total_price: 11500

# 8 state - 0
-- 15: 후라이드 / 18000 (뼈 0)
-- 16: 후라이드 / 18000 (뼈 0 / 무 추가 500 / 양념치킨소스추가 500)
-- menu_total_price: 36000
-- option_total_price: 1000
-- sum_total_price: 37000

# 9 state - 2
-- 17: 양념치킨 / 19000 (순살 1000)
-- menu_total_price: 19000 
-- option_total_price: 1000
-- sum_total_price: 20000

# 10 state - 2
-- 18: 1+1 치킨 / 30000 (양념 1000 / 간장 1000)
-- menu_total_price: 30000
-- option_total_price: 2000
-- sum_total_price: 32000

# 11 state - 0
-- 19: 후라이드치킨 / 18000 (순살 1000)
-- menu_total_price: 18000
-- option_total_price: 1000
-- sum_total_price: 19000

# 12 state - 1
-- 20: 후라이드치킨 / 18000 (순살 1000 / 양념치킨소스 500)
-- 21: 양념치킨 / 19000 (순살 1000)
-- menu_total_price: 37000
-- option_total_price: 2500
-- sum_total_price: 39500

# 13 state - 2
-- 22: 1+1 치킨 / 30000 (후라이드 0 / 양념 1000)
-- menu_total_price: 30000
-- option_total_price: 1000
-- sum_total_price: 31000

# 14 state - 0
-- 23: 후라이드치킨 / 18000 (뼈 0)
-- 24: 양념치킨 / 19000 (뼈 0)
-- 25: 양념치킨 / 19000 (뼈 0 / 무 추가 500)
-- menu_total_price: 56000
-- option_total_price: 500
-- sum_total_price: 56500

# 15 state - 1
-- 26: 양념치킨 / 19000 (순살 1000)
-- menu_total_price: 19000
-- option_total_price: 1000
-- sum_total_price: 20000

# 16 state - 2
-- 27: 후라이드치킨 / 18000 (순살 1000 / 양념치킨소스추가 500)
-- menu_total_price: 18000
-- option_total_price: 1500
-- sum_total_price: 19500

# 17 state - 0
-- 28: 1+1치킨 / 30000 (양념 1000 / 양념 1000)
-- 29: 1+1치킨 / 30000 (후라이드 0 / 간장 1000)
-- menu_total_price: 60000
-- option_total_price: 3000
-- sum_total_price: 63000

# 18 state - 2
-- 30: 후라이드치킨 / 18000 (순살 1000 / 무 추가 500)
-- 31: 후라이드치킨 / 18000 (순살 1000)
-- menu_total_price: 36000
-- option_total_price: 2500
-- sum_total_price: 38500


# 19 state - 2
-- 32: 후라이드치킨 / 18000 (순살 1000 / 무 추가 500)
-- 33: 양념치킨 / 19000 (순살 1000)
-- menu_total_price: 37000
-- option_total_price: 2500
-- sum_total_price: 39500

# 20 state - 2
-- 34: 양념치킨 / 19000 (순살 1000 / 무 추가 500)
-- 35: 1+1치킨 / 30000 (간장 1000 / 후라이드 0)
-- menu_total_price: 49000
-- option_total_price: 2500
-- sum_total_price: 51500

# 21 state - 1
-- 36: 양념치킨 / 19000 (뼈 0)
-- menu_total_price: 19000
-- option_total_price: 0
-- sum_total_price: 19000

# 22 state - 2
-- 37: 후라이드치킨 / 18000 (순살 1000)
-- 38: 후라이드치킨 / 18000 (순살 1000 / 무 추가 500 / 양념치킨소스추가 500)
-- menu_total_price: 36000
-- option_total_price: 3000
-- sum_total_price: 39000

# 23 state - 0
-- 39: 후라이드 / 18000 (뼈 0)
-- 40: 후라이드 / 18000 (뼈 0 / 무 추가 500 / 양념치킨소스추가 500)
-- menu_total_price: 36000
-- option_total_price: 1000
-- sum_total_price: 37000

# 24 state - 1
-- 41: 양념치킨 / 19000 (순살 1000)
-- menu_total_price: 19000 
-- option_total_price: 1000
-- sum_total_price: 20000

# 25 state - 2
-- 42: 1+1 치킨 / 30000 (양념 1000 / 간장 1000)
-- menu_total_price: 30000
-- option_total_price: 2000
-- sum_total_price: 32000

# 26 state - 0
-- 43: 후라이드치킨 / 18000 (순살 1000)
-- menu_total_price: 18000
-- option_total_price: 1000
-- sum_total_price: 19000

# 27 state - 1
-- 44: 후라이드치킨 / 18000 (순살 1000 / 양념치킨소스 500)
-- 45: 양념치킨 / 19000 (순살 1000)
-- menu_total_price: 37000
-- option_total_price: 2500
-- sum_total_price: 39500

# 28 state - 2
-- 46: 1+1 치킨 / 30000 (후라이드 0 / 양념 1000)
-- menu_total_price: 30000
-- option_total_price: 1000
-- sum_total_price: 31000

# 29 state - 0
-- 47: 후라이드치킨 / 18000 (뼈 0)
-- 48: 양념치킨 / 19000 (뼈 0)
-- 49: 양념치킨 / 19000 (뼈 0 / 무 추가 500)
-- menu_total_price: 56000
-- option_total_price: 500
-- sum_total_price: 56500

# 30 state - 1
-- 50: 양념치킨 / 19000 (순살 1000)
-- menu_total_price: 19000
-- option_total_price: 1000
-- sum_total_price: 20000

# 31 state - 2
-- 51: 후라이드치킨 / 18000 (순살 1000 / 양념치킨소스추가 500)
-- menu_total_price: 18000
-- option_total_price: 1500
-- sum_total_price: 19500

# 32 state - 0
-- 52: 1+1치킨 / 30000 (양념 1000 / 양념 1000)
-- 53: 1+1치킨 / 30000 (후라이드 0 / 간장 1000)
-- menu_total_price: 60000
-- option_total_price: 3000
-- sum_total_price: 63000

# 33 state - 1
-- 54: 후라이드치킨 / 18000 (순살 1000 / 무 추가 500)
-- 55: 후라이드치킨 / 18000 (순살 1000)
-- menu_total_price: 36000
-- option_total_price: 2500
-- sum_total_price: 38500


# 34 state - 2
-- 56: 후라이드치킨 / 18000 (순살 1000 / 무 추가 500)
-- 57: 양념치킨 / 19000 (순살 1000)
-- menu_total_price: 37000
-- option_total_price: 2500
-- sum_total_price: 39500

# 35 state - 0
-- 58: 양념치킨 / 19000 (순살 1000 / 무 추가 500)
-- 59: 1+1치킨 / 30000 (간장 1000 / 후라이드 0)
-- menu_total_price: 49000
-- option_total_price: 2500
-- sum_total_price: 51500

# 36 state - 1
-- 60: 양념치킨 / 19000 (뼈 0)
-- menu_total_price: 19000
-- option_total_price: 0
-- sum_total_price: 19000

# 37 state - 2
-- 61: 후라이드치킨 / 18000 (순살 1000)
-- 62: 후라이드치킨 / 18000 (순살 1000 / 무 추가 500 / 양념치킨소스추가 500)
-- menu_total_price: 36000
-- option_total_price: 3000
-- sum_total_price: 39000

select * from orders;