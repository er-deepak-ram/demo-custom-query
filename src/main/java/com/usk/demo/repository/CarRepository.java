package com.usk.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.usk.demo.dto.CarResponseDto;
import com.usk.demo.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

	List<Car> findByCompanyNameContainsOrderByCompanyNameAsc(String companyName);
	List<Car> findByCompanyNameAndModelName(String companyName, String modelName);
	List<Car> findByCompanyNameContainsOrModelNameContains(String companyName, String modelName);
	List<Car> findByPriceLessThanOrderByPrice(double price);
	List<Car> findByPriceGreaterThanOrderByPrice(double price);
	List<Car> findByPriceBetweenOrderByPrice(double lowerCap, double upperCap);
	List<Car> findByCompanyNameInOrderByCompanyNameAsc(List<String> companyNames);
	
	@Query("select new com.usk.demo.dto.CarResponseDto(companyName, count(*)) from Car where companyName=:companyName")
	List<CarResponseDto> getCustomCars(@Param("companyName") String companyName);
}