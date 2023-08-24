package com.sda.onlineLibrary.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
     private String rating;
    private String bookName;
    private String photoBase64;
    @NotBlank(message = "Review is required")
    private String review;
}
