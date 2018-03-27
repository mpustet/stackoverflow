package de.mle.stackoverflow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MyPOJO {

	@JsonProperty("Firstname")
	private String name;

	private String nationality;

	@JsonProperty("nationality")
	public String getNationality() {
		return nationality;
	}

	@JsonProperty("Country")
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	private String state;
}