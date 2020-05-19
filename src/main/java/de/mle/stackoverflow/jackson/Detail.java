package de.mle.stackoverflow.jackson;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class Detail {
    private List<Integer> singleItemList;
}