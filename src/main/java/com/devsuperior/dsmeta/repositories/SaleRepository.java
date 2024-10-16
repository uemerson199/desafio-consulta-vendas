package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleSellerDTO(obj.id, obj.date, obj.amount, obj.seller.name) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :firstDate AND :lastDate " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :beginName, '%'))",
            countQuery = "SELECT count(obj) FROM Sale obj WHERE obj.date BETWEEN :firstDate AND :lastDate " +
                    "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :beginName, '%'))")
    Page<SaleSellerDTO> searchAll(Pageable pageable, LocalDate firstDate, LocalDate lastDate, String beginName);



    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SellerMinDTO(seller.name, SUM(sale.amount)) " +
            "FROM Sale sale " +
            "JOIN sale.seller seller " +
            "WHERE sale.date BETWEEN :firstDate AND :lastDate " +
            "GROUP BY seller.name",
            countQuery = "SELECT COUNT(DISTINCT seller.id) " +
                    "FROM Sale sale " +
                    "JOIN sale.seller seller " +
                    "WHERE sale.date BETWEEN :firstDate AND :lastDate")
    Page<SellerMinDTO> searchAllSeller(Pageable pageable, LocalDate firstDate, LocalDate lastDate);




}