package de.mle.stackoverflow.jackson;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
class Detail {
    private List<Integer> singleItemList;
}