package like_lion.pangjam.controller;

import like_lion.pangjam.dto.global.SliceResponseDto;
import like_lion.pangjam.dto.locationReview.LocationReviewRequestDto;
import like_lion.pangjam.dto.locationReview.LocationReviewResponseDto;
import like_lion.pangjam.service.LocationReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location_reviews")
@RequiredArgsConstructor
public class LocationReviewController {

    private final LocationReviewService locationReviewService;

    //리뷰 생성
    @PostMapping
    public ResponseEntity<LocationReviewResponseDto> createReview(@RequestBody LocationReviewRequestDto reviewRequestDto) {
        return ResponseEntity.ok(locationReviewService.createReview(reviewRequestDto));
    }

    //리뷰 전체 조회
    @GetMapping("/{locationId}")
    public ResponseEntity<SliceResponseDto<LocationReviewResponseDto>> getLocationReviews(@PathVariable int locationId,
                                                                               @RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "15") int size) {
        Pageable pageable = PageRequest.of(page,size);

        Slice<LocationReviewResponseDto> locationReviewSlice = locationReviewService.getLocationReviews(locationId, pageable);

        SliceResponseDto<LocationReviewResponseDto> response = SliceResponseDto.of(
                locationReviewSlice.getContent(),
                locationReviewSlice.hasNext(),
                page,
                size
        );

        return ResponseEntity.ok(response);
    }

}
