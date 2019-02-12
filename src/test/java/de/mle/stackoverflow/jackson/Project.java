package de.mle.stackoverflow.jackson;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

	@NotNull
	private WorkPackageEstimateType estimateType = WorkPackageEstimateType.DAY;

	public WorkPackageEstimateType getEstimateType() {
		return estimateType;
	}

	public void setEstimateType(WorkPackageEstimateType estimateType) {
		this.estimateType = estimateType;
	}
}