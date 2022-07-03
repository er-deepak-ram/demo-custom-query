package com.usk.demo.service;

import java.util.List;

import com.usk.demo.dto.CarResponseDto;
import com.usk.demo.entity.Car;

public interface CarService {

	public List<Car> getAllCars(int pageNumber, int pageSize);
	public Car getCarById(Long id);
	public void saveCar(Car theCar);
	public void deleteCarById(Long id);
	public List<Car> getCarsByCompanyName(String companyName);
	public List<Car> getCarsByNames(String companyName, String modelName);
	public List<Car> getCarsByAnyName(String companyName, String modelName);
	public List<Car> getCarsLessThanPrice(double price);
	public List<Car> getCarsGreaterThanPrice(double price);
	public List<Car> getCarsBetweenPrice(double lowerCap, double upperCap);
	public List<Car> getCarsFromCompanies(List<String> companyNames);
	public List<CarResponseDto> getCustomCarsByCompanyName(String companyName);
}