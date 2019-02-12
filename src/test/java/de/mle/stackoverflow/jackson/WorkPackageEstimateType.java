package de.mle.stackoverflow.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = WorkPackageEstimateTypeDeserializer.class)
public enum WorkPackageEstimateType {
	HOUR("Hours"),
	DAY("Days"),
	WEEK("Weeks"),
	STORY_POINT("Story Points"),
	ITERATION("Iterations");

	private String displayName;

	WorkPackageEstimateType(String displayName) {
		this.displayName = displayName;
	}

	public Integer getId() {
		return ordinal();
	}

	public String getName() {
		return toString();
	}

	public String getDisplayName() {
		return displayName;
	}
}