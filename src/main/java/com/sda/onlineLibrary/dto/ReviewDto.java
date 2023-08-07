package com.sda.onlineLibrary.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private String bookId;
    private String rating;
    private String review;
}
