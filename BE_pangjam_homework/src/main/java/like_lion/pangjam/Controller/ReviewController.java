package like_lion.pangjam.Controller;

import like_lion.pangjam.Dto.ReviewDto;
import like_lion.pangjam.Service.ReviewService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    // 리뷰 생성
    @PostMapping
    public ResponseEntity<ReviewDto.Response.Create> createReview(@RequestBody ReviewDto.Request.Create reviewDto) {
        ReviewDto.Response.Create createdReview = reviewService.createReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    //리뷰 조회
    @GetMapping("{locationId}")
    public ResponseEntity<ReviewDto.Response> getAllReview(@PathVariable int locationId,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam (defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        ReviewDto.Response reviews = reviewService.getAllReview(locationId, pageable);
        if (reviews == null) {
            return ResponseEntity.notFound().build(); // HTTP 404 반환
        }
        return ResponseEntity.ok(reviews); // HTTP 200 반환
    }

    /*
    //리뷰 수정
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable int reviewId, @RequestBody ReviewDto reviewDto) {
        //ReviewDto updatedReview = reviewService.updateReview(reviewDto);
        return ResponseEntity.ok(updatedReview);
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable int reviewId) {
        //reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
    */
}
