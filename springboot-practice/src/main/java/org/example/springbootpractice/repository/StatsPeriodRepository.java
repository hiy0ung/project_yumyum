package org.example.springbootpractice.repository;

import org.example.springbootpractice.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsPeriodRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = """
SELECT 
    DATE(orderDate) AS orderDay, 
    SUM(totalPrice) AS dailyPrice 
FROM Order 
WHERE YEAR(orderDate) = :orderDateYear AND MONTH(orderDate) = :orderDateMonth 
GROUP BY DATE(orderDate), orderDate 
ORDER BY orderDate;
""" , nativeQuery = true)
    List<Object[]> findDailySales(@Param("orderDateYear") int year,
                                  @Param("orderDateMonth") int month
    );

    @Query(value = """
SELECT 
    YEAR (orderDate) AS orderYear, 
    MONTH (orderDate) AS orderMonth, 
    SUM(totalPrice) AS monthPrice 
FROM Order 
WHERE YEAR (orderDate) = :orderDateYear AND MONTH (orderDate) = :orderDateMonth 
GROUP BY YEAR (orderDate), MONTH (orderDate);
"""
    , nativeQuery = true)
    List<Object[]> findMonthSales(@Param("orderDateYear") int year,
                                  @Param("orderDateMonth") int month
    );

    @Query(value = """
SELECT 
    YEAR (orderDate) AS orderYear, 
    SUM (totalPrice) AS yearPrice 
FROM Order 
WHERE YEAR (orderDate) = :orderDateYear 
GROUP BY YEAR (orderDate) 
ORDER BY YEAR (orderDate);
"""
    , nativeQuery = true)
    List<Object[]> findYearSales(@Param("orderDateYear") int year);
}