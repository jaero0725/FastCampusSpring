package kr.ac.ecommerce.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProductDto {
    @NotNull(message = "name is required")
    @Size(message = "name must be equal to or lower than 300", min = 1, max = 300)
    private String name;

    @NotNull(message = "name is required")
    @Min(0)
    private int price;

    private String discription;
}
