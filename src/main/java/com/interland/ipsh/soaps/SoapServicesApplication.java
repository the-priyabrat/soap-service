package com.interland.ipsh.soaps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SoapServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapServicesApplication.class, args);
		List<String> stringList = new ArrayList<>();
		stringList.add("Priyabrat");
		stringList.add("Shakti Sanchaya");
		stringList.add("Manmohan");
		stringList.add("Laxmidhar");
		stringList.add("Bhabasagar");
		stringList.add("Priyabrat");
		stringList.add("Sambidhan");
		stringList.add("Manmohan");
		stringList.add("Tapas Kumar");
		stringList.add("Vivek Babu");
		stringList.add("Joseph Thomas");
		stringList.add("Afras");
		stringList.add("Subham Mishra");
		System.out.println("Hello soap");
//		stringList.stream().filter(name -> name.equals("Priyabrat")).forEach(System.out::println);
//		System.out.println("_________________________________________");
//		stringList.stream().map(name -> name.concat(" Swain")).forEach(System.out::println);
//		System.out.println(stringList.stream().anyMatch(name -> name.equals("Priyabrat")));
//		Map<Object, Long> results = stringList.stream()
//				.collect(Collectors.groupingBy(name -> name, Collectors.counting()));
//		results.keySet()
//				.forEach(status -> System.out.println("key :" + status + " -> " + "counting:" + results.get(status)));
	}
}
