package com.openbox.querychecker.controller;

import com.openbox.querychecker.model.dto.CustomerStatusDTO;
import com.openbox.querychecker.model.dto.QueryResultDTO;
import com.openbox.querychecker.model.dto.StockSoldDTO;
import com.openbox.querychecker.model.dto.TopCustomerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/queries")
@Tag(name = "SQL problem endpoints", description = """
        Endpoints Provided contain queries that resolve problems for Open Box Assessment.
        """)
public class QueryController {

    @Autowired
    private EntityManager entityManager;

    @GetMapping("/customers/status")
    @Operation(summary = """
            return customers with a status ID of 1, ordered by Surname then Firstname
            (Fields: Surname, Firstname, CustomerStatusId, CreateDateTime)
            """)
    public QueryResultDTO<List<CustomerStatusDTO>> getActiveCustomers(){
        long startTime = System.currentTimeMillis();
        String sql = """
                SELECT
                    Surname,
                    Firstname,
                    CustomerStatusId,
                    CreateDateTime
                FROM Customer
                WHERE CustomerStatusId = 1
                ORDER BY Surname, Firstname
                """;
        Query query = entityManager.createNativeQuery(sql);
        List<CustomerStatusDTO> results = query.getResultList();
        return new QueryResultDTO<>(
                results,
                System.currentTimeMillis() - startTime,
                sql
        );
    }

    @GetMapping("/stock/sold/january-2018")
    @Operation(summary = """
            Returns a list of stock items sold in January 2018.
            (Fields: Description)
            """)
    public QueryResultDTO<List<StockSoldDTO>> stockSoldJan2018(){
        long startTime = System.currentTimeMillis();
        String sql = """
                SELECT DISTINCT
                    s.Description
                FROM Stock s
                INNER JOIN SaleItem si ON s.StockId = si.StockId
                INNER JOIN Sale sa ON si.SaleId = sa.SaleId
                WHERE YEAR(sa.CreateDateTime) = 2018
                AND MONTH(sa.CreateDateTime) = 1
                """;
        Query query = entityManager.createNativeQuery(sql);
        List<StockSoldDTO> results = query.getResultList();
        return new QueryResultDTO<>(
                results,
                System.currentTimeMillis() - startTime,
                sql
        );
    }

    @GetMapping("/stock/not-sold/january-2018")
    @Operation(summary = """
            Returns a list of items not sold in January 2018
            (Fields: Description)
            """)
    public QueryResultDTO<List<String>> stockNotSoldJan2018(){
        long startTime = System.currentTimeMillis();
        String sql = """
                SELECT Description
                FROM Stock
                WHERE StockId NOT IN (
                    SELECT DISTINCT
                        s.stockId
                    FROM Stock s
                    INNER JOIN SaleItem si ON s.StockId = si.StockId
                    INNER JOIN Sale sa ON si.SaleId = sa.SaleId
                    WHERE YEAR(sa.CreateDateTime) = 2018
                    AND MONTH(sa.CreateDateTime) = 1
                );
                """;
        Query query = entityManager.createNativeQuery(sql);
        List<String> results = query.getResultList();
        return new QueryResultDTO<>(
                results,
                System.currentTimeMillis() - startTime,
                sql
        );
    }

    @GetMapping("/stock/top-selling")
    @Operation(summary = """
            Returns a list of the top 10 highest selling stock items for January 2018.
            (Fields: Description, Quantity Sold)
            """)
    public QueryResultDTO<List<StockSoldDTO>> getTopSellers(){
        long startTime = System.currentTimeMillis();
        String sql = """
                SELECT
                    s.Description,
                    SUM(si.Quantity) as QuantitySold
                FROM Stock s
                INNER JOIN SaleItem si ON s.StockId = si.StockId
                INNER JOIN Sale sa ON si.SaleId = sa.SaleId
                WHERE YEAR(sa.CreateDateTime) = 2018
                AND MONTH(sa.CreateDateTime) = 1
                GROUP BY
                    s.StockId,
                    s.Description
                ORDER BY QuantitySold DESC
                LIMIT 10;
                """;
        Query query = entityManager.createNativeQuery(sql);
        List<StockSoldDTO> results = query.getResultList();
        return new QueryResultDTO<>(
                results,
                System.currentTimeMillis() - startTime,
                sql
        );
    }

    @GetMapping("/customers/top-sales")
    @Operation(summary = """
            Get top 10 customers by sales value
            (Fields: Firstname, Surname, Number of Sales, sum(Value of Sales))
            """)
    public QueryResultDTO<List<TopCustomerDTO>> getTopCustomersBySales() {
        long startTime = System.currentTimeMillis();
        String sql = """
               SELECT
                   c.FirstName,
                   c.Surname,
                   COUNT(DISTINCT sa.SaleId) as NumberOfSales,
                   SUM(si.Quantity * si.Price) as TotalSaleValue
               FROM Customer c
               INNER JOIN Sale sa ON c.CustomerId = sa.CustomerId
               INNER JOIN SaleItem si ON sa.SaleId = si.SaleId
               GROUP BY c.CustomerId, c.FirstName, c.Surname
               ORDER BY TotalSaleValue DESC
               LIMIT 10
               """;
        Query query = entityManager.createNativeQuery(sql);
        List<TopCustomerDTO> results = query.getResultList();

        return new QueryResultDTO<>(
                results,
                System.currentTimeMillis() - startTime,
                sql
        );
    }
}
