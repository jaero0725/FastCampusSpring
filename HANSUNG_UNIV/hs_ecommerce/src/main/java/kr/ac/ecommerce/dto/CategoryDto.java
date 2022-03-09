package kr.ac.ecommerce.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryDto {
	@NotNull(message = "name is required")
    @Size(message = "name must be equal to or lower than 100", min = 1, max = 100)
    private String name;
}
