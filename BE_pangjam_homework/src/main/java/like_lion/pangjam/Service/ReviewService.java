package like_lion.pangjam.Service;

import like_lion.pangjam.Dto.ReviewDto;
import like_lion.pangjam.Model.Location;
import like_lion.pangjam.Model.Review;
import like_lion.pangjam.Model.User;
import like_lion.pangjam.Repository.LocationRepository;
import like_lion.pangjam.Repository.ReviewRepository;
import like_lion.pangjam.Repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, LocationRepository locationRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    //식당별 리뷰 전체 조회
    public ReviewDto.Response getAllReview(int locationId, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findByLocation_LocationId(locationId, pageable);
        List<Review> reviews = reviewRepository.findByLocationId(locationId);
        return convertToResponse(reviews);
    }

    //식당별 리뷰 작성
    public ReviewDto.Response.Create createReview(ReviewDto.Request.Create reviewDto) {

        User user = userRepository.findById(reviewDto.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다"));


        Location location = locationRepository.findByLocationId(reviewDto.getLocationId())
                .orElseThrow(() -> new RuntimeException("식당을 찾을 수 없습니다"));

        Review review = Review.builder()
                .user(user)
                .location(location)
                .content(reviewDto.getContent())
                .rating(reviewDto.getRating())
                .build();

        Review savedReview = reviewRepository.save(review);
        updateAverageRating(location);

        return ReviewDto.Response.Create.builder()
                .nickname(user.getNickname())
                .content(savedReview.getContent())
                .rating(savedReview.getRating())
                .build();
    }

    // public ReviewDto updateReview(ReviewDto reviewDto) {}
    //public void deleteReview(int reviewId) {}

    public ReviewDto.Response convertToResponse(List<Review> reviews) {
        List<ReviewDto.Response.ReviewInfo> reviewInfos = reviews.stream()
                .map(ReviewDto.Response.ReviewInfo::from)
                .toList();

        return ReviewDto.Response.builder()
                .reviews(reviewInfos)
                .build();
    }

    //식당 평점 계산
    public double calculateAverageRating(List<Review> reviews) {
        return Math.round(
                reviews.stream()
                        .mapToDouble(Review::getRating)
                        .average()
                        .orElse(0.0) * 100.0) / 100.0;
    }

    //식당 평점 업데이트
    public void updateAverageRating(Location location) {
        List<Review> reviews = reviewRepository.findByLocationId(location.getLocationId());
        double avgRating = calculateAverageRating(reviews);
        location.setAverageRating(avgRating);
        locationRepository.save(location);
    }
}
