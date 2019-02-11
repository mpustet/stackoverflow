package de.mle.stackoverflow.jackson;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@JsonIgnoreProperties({
		"duration",
		"errorCode",
		"haveFieldsChanged",
		"serviceRequestToken",
		"storedProcDuration" }) // Abstract Base Class properties
class Bus implements Serializable {

	@JsonIgnore
	private static final long serialVersionId = 1;

	@JsonProperty("name")
	String name;

	@JsonProperty("id")
	int id;

	@JsonProperty("students")
	List<Student> students; // Nested Objects

	@JsonProperty("employer")
	Employer employer; // Nested object

	// Getters and setters - none are annotated

	@JsonRootName(value = "student")
	class Student implements Serializable {
		// student fields
	}

	@JsonRootName(value = "statusType")
	class Employer implements Serializable {

		@JsonProperty("id")
		int id;
	}
}