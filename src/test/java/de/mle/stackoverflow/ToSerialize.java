package de.mle.stackoverflow;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

@JsonSerialize(using = ToSerializeSeserializer.class)
public class ToSerialize {
	@Getter
	private List<NestedObject> objs;

	public ToSerialize(List<NestedObject> objs) {
		this.objs = objs;
	}
}