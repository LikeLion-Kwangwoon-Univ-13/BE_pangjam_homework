package like_lion.pangjam.controller;

import like_lion.pangjam.dto.global.SliceResponseDto;
import like_lion.pangjam.dto.placeReview.PlaceReviewRequestDto;
import like_lion.pangjam.dto.placeReview.PlaceReviewResponseDto;
import like_lion.pangjam.service.PlaceReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/placeReviews")
@RequiredArgsConstructor
public class PlaceReviewController {

    private final PlaceReviewService placeReviewService;

    //리뷰 생성
    @PostMapping("/{placeId}")
    public ResponseEntity<PlaceReviewResponseDto> createReview(
            @PathVariable long placeId,
            @RequestBody PlaceReviewRequestDto reviewRequestDto) {
        reviewRequestDto.setPlaceId(placeId);
        return ResponseEntity.ok(placeReviewService.createReview(reviewRequestDto));
    }

    //리뷰 전체 조회
    @GetMapping("/page/{page}")
    public ResponseEntity<SliceResponseDto<PlaceReviewResponseDto>> getPlaceReviews(
            @PathVariable int page,
            @RequestParam long placeId,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "15") int size
    ) {

        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC, sortBy));

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
