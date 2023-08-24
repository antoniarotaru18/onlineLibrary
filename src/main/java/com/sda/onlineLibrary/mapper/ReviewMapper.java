package com.sda.onlineLibrary.mapper;

import com.sda.onlineLibrary.dto.ReviewDto;
import com.sda.onlineLibrary.entity.Review;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.sda.onlineLibrary.constant.Util.BASE64_PREFIX;

@Component
public class ReviewMapper {

    public ReviewDto map (Review review){
        return ReviewDto.builder()
                .bookName(review.getBook().getName())
                .review(review.getReview())
                .rating(String.valueOf(review.getRating()))
                .photoBase64(BASE64_PREFIX + Base64.encodeBase64String(review.getBook().getPhoto()))
                .build();
    }



}
