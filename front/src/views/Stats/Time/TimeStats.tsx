/** @jsxImportSource @emotion/react */
import React, { useEffect, useState } from 'react';
import * as css from "./Style"
import { ResponseStatsTime, StatsTime } from '../../../types/TimeStats';
import axios from 'axios';
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";
import { useCookies } from 'react-cookie';

const TimeStats = () => {
    const convertDate = new Date().toISOString().slice(0, 10);

    const [orderDate, setOrderDate] = useState<string>(convertDate);
    const [stats, setStats] = useState<StatsTime[]>([]);
    const [ cookies ] = useCookies(['token']);
  
    const token = cookies.token;
    const fetch = async () => {
      try {
        
        const response = await axios.get(
        `http://localhost:4041/api/v1/stats/time/${orderDate}T00:00:00`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );
        const statsTimes: ResponseStatsTime[] = response.data.data;
        setStats(statsTimes.map(statsTime => ({
          name: statsTime.hour,
          revenue: statsTime.revenue
        })));
      } catch (e) {
        console.error(e);
      }
    }

    const changeDate = (days: number) => {
      const currentDate = new Date(orderDate);
      currentDate.setDate(currentDate.getDate() + days);
      setOrderDate(currentDate.toISOString().slice(0, 10));
      console.log(currentDate.toISOString().slice(0, 10));
    }
    
    useEffect(() => {
      fetch();
    }, [orderDate]);
    return (
    <>
      <div css={css.inputContainerStyle}>
        <button 
          onClick={() => changeDate(-1)} 
          css={css.buttonStyle}
        >◀</button>
        <input 
        type="date" 
        onChange={e => setOrderDate(e.target.value)} 
        value={orderDate}
        css={css.inputStyle}
        />
        <button 
          onClick={() => changeDate(+1)}
          css={css.buttonStyle}
        >▶</button>
      </div>
        <div>
          <ResponsiveContainer width={"100%"} height={500}>
            <LineChart data={stats}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="name" padding={{ left: 30, right: 30 }} />
              <YAxis />
              <Tooltip />
              <Legend />
              <Line
                type="monotone"
                dataKey="revenue"
                name="매출"
                stroke="#8884d8"
                activeDot={{ r: 8 }}
              />
              {/* <Line type="monotone" dataKey="uv" stroke="#82ca9d" /> */}
            </LineChart>
          </ResponsiveContainer>
        </div>
        </>
    )
};

export default TimeStats;