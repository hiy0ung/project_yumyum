-- (추가) 4. 리뷰 정보 예시 데이터 INSERT
-- orders.id = 1 ~ 10 번 주문에 대한 리뷰 10개
--------------------------------------------------------------------------------

-- reviews
INSERT INTO `reviews` (
    `id`, 
    `order_id`, 
    `rating`, 
    `review_date`, 
    `review_text`, 
    `is_reported`
) VALUES
(1,  1,  4.5, NOW(), '후라이드치킨 맛있어요!',          FALSE),
(2,  2,  3.0, NOW(), '떡볶이 평범했어요.',             FALSE),
(3,  3,  4.0, NOW(), '모둠튀김 바삭바삭!',              FALSE),
(4,  4,  4.8, NOW(), '토마토 스파게티 존맛!',           FALSE),
(5,  5,  2.5, NOW(), '재료가 조금 덜 신선했어요.',       FALSE),
(6,  6,  4.2, NOW(), '1+1 치킨 가성비 좋네요.',          FALSE),
(7,  7,  5.0, NOW(), '떡볶이 국물 최고!',               FALSE),
(8,  8,  3.8, NOW(), '치킨은 맛있지만 배달이 살짝 늦음.', FALSE),
(9,  9,  4.9, NOW(), '양식 잘하는 집이네요. 강추!',       FALSE),
(10, 10, 1.5, NOW(), '맛이 좀 별로였네요...',           FALSE),
(11, 12, 4.2, NOW(), '이 집 파스타는 기본 이상! 배달도 빨랐어요.', FALSE),
(12, 15, 3.5, NOW(), '면발이 조금 불었지만 소스 맛이 괜찮았어요.', FALSE),
(13, 18, 4.8, NOW(), '역시 믿고 먹는 양식 전문점! 완전 추천합니다.', FALSE),
(14, 21, 2.9, NOW(), '생각보다 평범했습니다. 다음엔 다른 메뉴 시도 예정.', FALSE),
(15, 24, 5.0, NOW(), '크림 스파게티 최고! 별 5개도 부족하네요.', FALSE),
(16, 27, 3.0, NOW(), '가격 대비 양은 적당, 조금 싱거웠어요.', FALSE),
(17, 30, 4.4, NOW(), '빠른 배달, 친절한 기사님, 전체적으로 만족!', FALSE),
(18, 33, 4.1, NOW(), '다른 곳보다 확실히 파스타 면이 탱탱해요. 굿~', FALSE),
(19, 36, 3.9, NOW(), '나쁘지 않았어요. 그래도 다음엔 다른 메뉴 먹어볼 듯.', FALSE);

--------------------------------------------------------------------------------
-- 리뷰 사진(review_photos) : review_id를 참조
--------------------------------------------------------------------------------

INSERT INTO `review_photos` (
    `id`,
    `review_id`,
    `photo_url`
) VALUES
-- review_id=1 (주문1 리뷰 사진 2장)
(1, 1, 'https://png.pngtree.com/thumb_back/fh260/background/20230609/pngtree-three-puppies-with-their-mouths-open-are-posing-for-a-photo-image_2902292.jpg'),
(2, 1, 'https://helpx.adobe.com/content/dam/help/en/photoshop/using/quick-actions/remove-background-before-qa1.png'),

-- review_id=2 (주문2 리뷰 사진 1장)
(3, 2, 'https://example.com/review2_photo1.jpg'),

-- review_id=4 (주문4 리뷰 사진 1장)
(4, 4, 'https://example.com/review4_photo1.jpg'),

-- review_id=9 (주문9 리뷰 사진 2장)
(5, 9, 'https://example.com/review9_photo1.jpg'),
(6, 9, 'https://example.com/review9_photo2.jpg');

--------------------------------------------------------------------------------
-- 리뷰 댓글(review_comments) : review_id를 참조
--------------------------------------------------------------------------------

INSERT INTO `review_comments` (
    `id`,
    `review_id`,
    `comment_text`,
    `comment_date`
) VALUES
-- review_id=1 에 대한 점주 답글
(1, 1, '맛있게 드셨다니 감사합니다~ 또 이용해주세요!', NOW()),

-- review_id=2 에 대한 점주 답글
(2, 2, '소중한 의견 감사합니다. 더 노력하겠습니다!', NOW()),

-- review_id=4 에 대한 점주 답글
(3, 4, '스파게티 맛있게 드셨다니 정말 기쁘네요!', NOW()),

-- review_id=7 에 대한 점주 답글
(4, 7, '떡볶이 칭찬 감사합니다! 좋은 재료로 보답드릴게요!', NOW());