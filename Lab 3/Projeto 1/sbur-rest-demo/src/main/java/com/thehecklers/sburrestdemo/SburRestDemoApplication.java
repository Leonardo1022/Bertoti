package com.thehecklers.sburrestdemo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//Sqlite
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class SburRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SburRestDemoApplication.class, args);
		//CoffeeDAO.conectar();
		//Coffee c = new Coffee("branco");
		//CoffeeDAO.inserirCafe(c);
		//CoffeeDAO.mostrarCafes();
		//CoffeeDAO.removerCafe(c);
		//CoffeeDAO.mostrarCafes();
	}

}

@RestController
@RequestMapping("/coffees")
class RestApiDemoController {
	private final List<Coffee> coffees;

	public RestApiDemoController() {
		coffees = CoffeeDAO.mostrarCafes();
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

class CoffeeDAO {
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

	public static void removerCafe(Coffee cafe) {
		String sql = "DELETE FROM Cafe WHERE nome = ?";
		try (Connection conn = DriverManager.getConnection(bd_url);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, cafe.getName());
			pstmt.executeUpdate();
			System.out.println("Café removido com sucesso!");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Coffee> mostrarCafes() {
		ArrayList<Coffee> cafes = new ArrayList<>();
		String sql = "SELECT * FROM Cafe";
		try (Connection conn = DriverManager.getConnection(bd_url);
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery()) {
			while(rs.next()) {
				System.out.println("Id: " + rs.getString(1) + "; " + "Nome: "+ rs.getString(2));
				Coffee c = new Coffee(rs.getString(1), rs.getString(2));
				cafes.add(c);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return cafes;
	}
}