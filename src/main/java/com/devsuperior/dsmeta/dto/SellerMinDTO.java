package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SellerMinDTO {

    private String sellerName;
    private Double total;


    public SellerMinDTO() {

    }

    public SellerMinDTO(String name, Double total) {
        this.sellerName = name;
        this.total = total;
    }

    public SellerMinDTO(Seller entity) {
        sellerName = entity.getName();
        for (Sale sale: entity.getSales()){
            total += sale.getAmount();
        }
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
