package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class SaleService {


	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleSellerDTO> findAll(Pageable pageable, String minDate, String maxDate, String name) {
		LocalDate startDate = (minDate == null || minDate.isEmpty()) ? LocalDate.
				ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1) : LocalDate.parse(minDate);
		LocalDate endDate = (maxDate == null || maxDate.isEmpty()) ? LocalDate.ofInstant(
				Instant.now(), ZoneId.systemDefault()
		) : LocalDate.parse(maxDate);

		Page<SaleSellerDTO> page = repository.searchAll(pageable, startDate, endDate, name);

		return page;
	}

	public Page<SellerMinDTO> findAllSellerSale(Pageable pageable, String minDate, String maxDate) {
		LocalDate startDate = (minDate == null || minDate.isEmpty()) ? LocalDate.
				ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1) : LocalDate.parse(minDate);
		LocalDate endDate = (maxDate == null || maxDate.isEmpty()) ? LocalDate.ofInstant(
				Instant.now(), ZoneId.systemDefault()
		) : LocalDate.parse(maxDate);

		Page<SellerMinDTO> page = repository.searchAllSeller(pageable, startDate, endDate);

		return page;
	}



}
