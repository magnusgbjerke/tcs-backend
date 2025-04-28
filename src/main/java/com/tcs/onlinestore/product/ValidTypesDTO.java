package com.tcs.onlinestore.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@Schema(name = "ValidTypes")
public class ValidTypesDTO {
    @Schema(example = "[men, women, kids]")
    private List<String> customerCategory;

    @Schema(example = "[tops, bottoms, footwear]")
    private List<String> productCategory;

    @Schema(example = "[hoodies, pants, shoes]")
    private List<String> type;
}
