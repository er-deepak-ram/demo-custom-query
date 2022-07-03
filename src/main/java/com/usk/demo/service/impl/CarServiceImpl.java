package com.usk.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.usk.demo.dto.CarResponseDto;
import com.usk.demo.entity.Car;
import com.usk.demo.repository.CarRepository;
import com.usk.demo.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepository carRepository;

	@Override
	public List<Car> getAllCars(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Direction.ASC, "id"));
		return carRepository.findAll(pageable).getContent();
	}

	@Override
	public Car getCarById(Long id) {
		Optional<Car> result = carRepository.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public void saveCar(Car theCar) {
		carRepository.save(theCar);
	}

	@Override
	public void deleteCarById(Long id) {
		carRepository.deleteById(id);
	}

	@Override
	public List<Car> getCarsByCompanyName(String companyName) {
		return carRepository.findByCompanyNameContainsOrderByCompanyNameAsc(companyName);
	}

	@Override
	public List<Car> getCarsByNames(String companyName, String modelName) {
		return carRepository.findByCompanyNameAndModelName(companyName, modelName);
	}

	@Override
	public List<Car> getCarsByAnyName(String companyName, String modelName) {
		return carRepository.findByCompanyNameContainsOrModelNameContains(companyName, modelName);
	}

	@Override
	public List<Car> getCarsLessThanPrice(double price) {
		return carRepository.findByPriceLessThanOrderByPrice(price);
	}

	@Override
	public List<Car> getCarsGreaterThanPrice(double price) {
		return carRepository.findByPriceGreaterThanOrderByPrice(price);
	}

	@Override
	public List<Car> getCarsBetweenPrice(double lowerCap, double upperCap) {
		return carRepository.findByPriceBetweenOrderByPrice(lowerCap, upperCap);
	}

	@Override
	public List<Car> getCarsFromCompanies(List<String> companyNames) {
		return carRepository.findByCompanyNameInOrderByCompanyNameAsc(companyNames);
	}

	@Override
	public List<CarResponseDto> getCustomCarsByCompanyName(String companyName) {
		return carRepository.getCustomCars(companyName); 
	}

}
