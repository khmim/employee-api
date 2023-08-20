package com.employee.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(of = "uuid")
public class Employee {
	@Id
	@GeneratedValue
	private UUID uuid;

	@Email
	@NotBlank
	@Column(unique = true)
	private String email;

	@NotBlank
	private String fullName;

	@NotNull
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private LocalDate birthday;

	@ElementCollection
	private List<String> hobbies;
}

