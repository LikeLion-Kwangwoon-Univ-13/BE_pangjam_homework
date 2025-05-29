package like_lion.pangjam.controller;

import like_lion.pangjam.dto.global.SliceResponseDto;
import like_lion.pangjam.dto.placeReview.PlaceReviewRequestDto;
import like_lion.pangjam.dto.placeReview.PlaceReviewResponseDto;
import like_lion.pangjam.service.PlaceReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/placeReviews")
@RequiredArgsConstructor
public class PlaceReviewController {

    private final PlaceReviewService placeReviewService;

    //리뷰 생성
    @PostMapping
    public ResponseEntity<PlaceReviewResponseDto> createReview(@RequestBody PlaceReviewRequestDto reviewRequestDto) {
        return ResponseEntity.ok(placeReviewService.createReview(reviewRequestDto));
    }

    //리뷰 전체 조회
    @GetMapping("/{placeId}")
    public ResponseEntity<SliceResponseDto<PlaceReviewResponseDto>> getPlaceReviews(@PathVariable int placeId,
                                                                               @RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "15") int size) {
        Pageable pageable = PageRequest.of(page,size);

        Slice<PlaceReviewResponseDto> placeReviewSlice = placeReviewService.getPlaceReviews(placeId, pageable);

        SliceResponseDto<PlaceReviewResponseDto> response = SliceResponseDto.of(
                placeReviewSlice.getContent(),
                placeReviewSlice.hasNext(),
                page,
                size
        );

        return ResponseEntity.ok(response);
    }

}
