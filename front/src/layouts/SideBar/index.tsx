/** @jsxImportSource @emotion/react */
import * as React from "react";
import {useEffect, useState} from "react";
import {Link, useLocation} from "react-router-dom";
import StorefrontIcon from "@mui/icons-material/Storefront";
import MenuBookIcon from "@mui/icons-material/MenuBook";
import QueryStatsIcon from "@mui/icons-material/QueryStats";
import RateReviewIcon from "@mui/icons-material/RateReview";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import DonutSmallIcon from "@mui/icons-material/DonutSmall";
import InsightsIcon from "@mui/icons-material/Insights";
import HomeIcon from "@mui/icons-material/Home";
import * as css from "./Style";
import {
    CONTACT_PATH,
    CREATE_STORE_PATH,
    MAIN_PATH,
    MENU_PATH, MY_PAGE,
    REVIEW_PATH,
    STATS_MENUS_PATH,
    STATS_PATH,
    STATS_PERIOD_PATH,
    STATS_TIME_PATH,
    STORE_PATH,
} from "../../constants";
import defaultProfileImg from "../../img/default_Profile_Img.png";
import {useCookies} from "react-cookie";
import axios from "axios";
import YumYumLogoImg from "../../img/yumyumLogo.png";

export default function SideBar() {
    const [pathValue, setPathValue] = useState("");
    const [cookies, setCookies] = useCookies(["token"]);
    const token = cookies.token;
    const location = useLocation();
    const pathHandle = (path: any) => {
        switch (true) {
            case path === MAIN_PATH:
                setPathValue(MAIN_PATH);
                break;
            case path === STORE_PATH:
                setPathValue(STORE_PATH);
                break;
            case path === MENU_PATH:
                setPathValue(MENU_PATH);
                break;
            case path.startsWith(STATS_PERIOD_PATH):
                setPathValue(STATS_PERIOD_PATH);
                break;
            case path.startsWith(STATS_MENUS_PATH):
                setPathValue(STATS_MENUS_PATH);
                break;
            case path.startsWith(STATS_TIME_PATH):
                setPathValue(STATS_TIME_PATH);
                break;
            case path.startsWith(STATS_PATH):
                setPathValue(STATS_PATH);
                break;
            case path === REVIEW_PATH:
                setPathValue(REVIEW_PATH);
                break;
            default:
                setPathValue("");
        }
    };
    useEffect(() => {
        pathHandle(location.pathname);
    }, [location.pathname]);

    const checkStore = async (e: React.MouseEvent<HTMLAnchorElement>) => {
        e.preventDefault();
        try {
            const response = await axios.get("http://localhost:4041/api/v1/stores", {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            if (response.data) {
                window.location.href = STORE_PATH;
            } else {
                window.location.href = CREATE_STORE_PATH;
            }
        } catch (e) {
            console.error(e);
        }
    };

    return (
        <aside css={css.categoryContainer}>
            <nav>
                <h1 css={css.categoryLogoImgContainer}>
                    <img css={css.categoryLogoImg} src={YumYumLogoImg} alt="로고 사진"/>
                </h1>
                <ul>
                    <li css={pathValue === MAIN_PATH && css.categoriesStyle}>
                        <Link
                            onClick={() => {
                                pathHandle(MAIN_PATH);
                            }}
                            to={MAIN_PATH}
                        >
                            <HomeIcon/>
                            <span>홈</span>
                        </Link>
                    </li>
                    <li css={pathValue === STORE_PATH && css.categoriesStyle}>
                        <Link
                            onClick={(e) => {
                                checkStore(e);
                            }}
                            to={STORE_PATH}
                        >
                            <StorefrontIcon/>
                            <span>가게</span>
                        </Link>
                    </li>
                    <li css={pathValue === MENU_PATH && css.categoriesStyle}>
                        <Link
                            onClick={() => {
                                pathHandle(MENU_PATH);
                            }}
                            to={MENU_PATH}
                        >
                            <MenuBookIcon/>
                            <span>메뉴</span>
                        </Link>
                    </li>
                    <li css={pathValue.startsWith(STATS_PATH) && css.categoriesStyle}>
                        <Link
                            onClick={() => {
                                pathHandle(STATS_PERIOD_PATH);
                            }}
                            to={STATS_PERIOD_PATH}
                        >
                            <QueryStatsIcon/>
                            <span>통계</span>
                        </Link>
                        <ul
                            css={
                                pathValue.startsWith(STATS_PATH)
                                    ? css.categoriesChildGroupOnStyle
                                    : css.categoriesChildGroupOffStyle
                            }
                        >
                            <li
                                css={
                                    pathValue === STATS_PERIOD_PATH && css.categoriesChildStyle
                                }
                            >
                                <Link
                                    onClick={() => {
                                        pathHandle(STATS_PERIOD_PATH);
                                    }}
                                    to={STATS_PERIOD_PATH}
                                >
                                    <CalendarMonthIcon/>
                                    <span>기간별 통계</span>
                                </Link>
                            </li>
                            <li
                                css={pathValue === STATS_MENUS_PATH && css.categoriesChildStyle}
                            >
                                <Link
                                    onClick={() => {
                                        pathHandle(STATS_MENUS_PATH);
                                    }}
                                    to={STATS_MENUS_PATH}
                                >
                                    <DonutSmallIcon/>
                                    <span>메뉴별 통계</span>
                                </Link>
                            </li>
                            <li
                                css={pathValue === STATS_TIME_PATH && css.categoriesChildStyle}
                            >
                                <Link
                                    onClick={() => {
                                        pathHandle(STATS_TIME_PATH);
                                    }}
                                    to={STATS_TIME_PATH}
                                >
                                    <InsightsIcon/>
                                    <span>시간별 통계</span>
                                </Link>
                            </li>
                        </ul>
                    </li>
                    <li css={pathValue === REVIEW_PATH && css.categoriesStyle}>
                        <Link
                            onClick={() => {
                                pathHandle(REVIEW_PATH);
                            }}
                            to={REVIEW_PATH}
                        >
                            <RateReviewIcon/>
                            <span>리뷰</span>
                        </Link>
                    </li>
                </ul>
            </nav>
            <div>
                <Link css={css.profile} to={MY_PAGE}>
                    <img src={defaultProfileImg} alt="프로필 사진입니다"/>
                    <span>마이페이지</span>
                </Link>

                <div css={css.userActionsContainer}>
                    <Link to={CONTACT_PATH}>문의하기</Link>
                    <button>로그아웃</button>
                </div>
            </div>
        </aside>
    );
}
