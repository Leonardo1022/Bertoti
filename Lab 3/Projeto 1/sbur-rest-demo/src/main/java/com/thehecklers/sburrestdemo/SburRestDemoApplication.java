package com.thehecklers.sburrestdemo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//Sqlite
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class SburRestDemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SburRestDemoApplication.class, args);
		CoffeDAO.conectar();
	}

}

@RestController
@RequestMapping("/coffees")
class RestApiDemoController {
	private final List<Coffee> coffees = new ArrayList<>();

	public RestApiDemoController() {
		coffees.addAll(List.of(
				new Coffee("Café Cereza"),
				new Coffee("Café Ganador"),
				new Coffee("Café Lareño"),
				new Coffee("Café Três Pontas")
		));
	}

	@GetMapping
	Iterable<Coffee> getCoffees() {
		return coffees;
	}

	@GetMapping("/{id}")
	Optional<Coffee> getCoffeeById(@PathVariable String id) {
		for (Coffee c: coffees) {
			if (c.getId().equals(id)) {
				return Optional.of(c);
			}
		}

		return Optional.empty();
	}

	@PostMapping
	Coffee postCoffee(@RequestBody Coffee coffee) {
		coffees.add(coffee);
		return coffee;
	}

	@PutMapping("/{id}")
	ResponseEntity<Coffee> putCoffee(@PathVariable String id,
									 @RequestBody Coffee coffee) {
		int coffeeIndex = -1;

		for (Coffee c: coffees) {
			if (c.getId().equals(id)) {
				coffeeIndex = coffees.indexOf(c);
				coffees.set(coffeeIndex, coffee);
			}
		}

		return (coffeeIndex == -1) ?
				new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
				new ResponseEntity<>(coffee, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	void deleteCoffee(@PathVariable String id) {
		coffees.removeIf(c -> c.getId().equals(id));
	}
}

class Coffee {
	private final String id;
	private String name;

	@JsonCreator
	public Coffee(@JsonProperty("id") String id, @JsonProperty("name") String name) {
		this.id = id;
		this.name = name;
	}

	public Coffee(String name) {
		this(UUID.randomUUID().toString(), name);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class CoffeDAO {
	private static final String bd_url = "jdbc:sqlite:main.bd";
	private static String sql;

    public static void conectar() {
		try {
			sql = Files.readString(Paths.get("src/main/resources/SQL/script.sql"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try (Connection conn = DriverManager.getConnection(bd_url)) {

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void inserirCafe(Coffee cafe) {
		String sql = "INSERT INTO Cafe (nome) VALUES (?)";
		try (Connection conn = DriverManager.getConnection(bd_url);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, cafe.getName());
			pstmt.executeUpdate();
			System.out.println("Café inserido com sucesso!");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}