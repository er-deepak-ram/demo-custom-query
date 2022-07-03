package com.usk.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usk.demo.dto.CarResponseDto;
import com.usk.demo.entity.Car;
import com.usk.demo.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarRestController {

	@Autowired
	private CarService carService;
	
	@GetMapping
	public List<Car> getAllCars(@RequestParam int pageNumber, int pageSize) {
		return carService.getAllCars(pageNumber, pageSize);
	}
	
	@GetMapping("/{id}")
	public Car getCar(@PathVariable int id) {
		Car theCar = carService.getCarById(Long.valueOf(id));
		if(theCar == null)
			throw new RuntimeException("Car ID not found: "+id);
		return theCar;
	}
	 
	@PostMapping
	public Car saveCar(@RequestBody Car car) {
		carService.saveCar(car);
		return car;
	}
	
	@PutMapping
	public Car updateCar(@RequestBody Car car) {
		carService.saveCar(car);
		return car;
	}
	
	@DeleteMapping("/{id}")
	public String deleteCar(@PathVariable int id) {
		Car theCar = carService.getCarById(Long.valueOf(id));
		if(theCar == null)
			throw new RuntimeException("Car ID not found - "+id);
		carService.deleteCarById(Long.valueOf(id));
		return "Deleted Car ID - "+id;
	}
	
	@GetMapping("/companySearch")
	public List<Car> getCarsByCompanyName(@RequestParam String companyName) {
		return carService.getCarsByCompanyName(companyName);
	}
	
	@GetMapping("/carSearch")
	public List<Car> getCarsByNames(@RequestParam String companyName, @RequestParam String modelName) {
		return carService.getCarsByNames(companyName, modelName);
	}
	
	@GetMapping("/carOrModelSearch")
	public List<Car> getCarsByAnyName(@RequestParam String companyName, @RequestParam String modelName) {
		return carService.getCarsByAnyName(companyName, modelName);
	}
	
	@GetMapping("/carsLessThanPrice")
	public List<Car> getCarsLessThanPrice(@RequestParam double price) {
		return carService.getCarsLessThanPrice(price);
	}
	
	@GetMapping("/carsGreaterThanPrice")
	public List<Car> getCarsGreaterThanPrice(@RequestParam double price) {
		return carService.getCarsGreaterThanPrice(price);
	}
	
	@GetMapping("/carsBetweenPrice")
	public List<Car> getCarsBetweenPrice(@RequestParam double lowerCap, @RequestParam double upperCap) {
		return carService.getCarsBetweenPrice(lowerCap, upperCap);
	}
	
	@GetMapping("/carCompaniesSearch")
	public List<Car> getCarsFromCompanies(@RequestParam List<String> companyNames) {
		return carService.getCarsFromCompanies(companyNames); 
	}
	
	@GetMapping("/customCarSearch")
	public List<CarResponseDto> getCustomCarsByCompanyName(@RequestParam String companyName) {
		return carService.getCustomCarsByCompanyName(companyName);
	}
}
