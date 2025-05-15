package like_lion.pangjam.controller;

import like_lion.pangjam.dto.global.SliceResponseDto;
import like_lion.pangjam.dto.review.ReviewRequestDto;
import like_lion.pangjam.dto.review.ReviewResponseDto;
import like_lion.pangjam.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 생성
    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto reviewRequestDto) {
        return ResponseEntity.ok(reviewService.createReview(reviewRequestDto));
    }

    //리뷰 전체 조회
    @GetMapping
    public ResponseEntity<SliceResponseDto<ReviewResponseDto>> getReviews(
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "15") int size) {

        Pageable pageable = PageRequest.of(page,size);
        Slice<ReviewResponseDto> reviewSlice = reviewService.getReviews(pageable);

        SliceResponseDto<ReviewResponseDto> response = SliceResponseDto.of(
                reviewSlice.getContent(),
                reviewSlice.hasNext(),
                page,
                size
        );

        return ResponseEntity.ok(response);
    }
}