package de.mle.stackoverflow;

import com.google.common.base.Optional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuavaBean {
	private Optional<Long> abc;
}
