package like_lion.pangjam.dto.location;
import java.time.LocalDateTime;
import java.util.List;

import like_lion.pangjam.domain.Location;
import like_lion.pangjam.domain.LocationReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponseDto {

    private List<LocationInfo> locations;

    @Getter @Builder
    public static class LocationInfo
    {
        private int locationId;
        private String name;
        private String category;
        private double averageRating;
        private String phone;

        public static LocationInfo from(Location location) {
            return LocationInfo.builder()
                    .locationId(location.getLocationId())
                    .name(location.getName())
                    .category(location.getCategory())
                    .averageRating(location.getAverageRating())
                    .phone(location.getPhone())
                    .build();
        }
    }

    //상세정보 : 이름, 주소, 거리, 평균 평점, 카테고리, 번호
    @Getter @Builder
    public static class Detail
    {
        private int locationId;
        private String name;
        private String address;
        private String distance;
        private double averageRating;
        private String category;
        private String phone;

        private List<Reviews> review;

        @Getter @Builder
        public static class Reviews{
            private String nickname;
            private String content;
            private int rating;
            private LocalDateTime createdAt;
        }

        // Location 객체를 변환하는 메서드
        public static Detail from(Location location, List<LocationReview> locationReviews) {
            List<Detail.Reviews> reviews = location.getLocationReviews().stream()
                    .map(review -> Reviews.builder()
                            .nickname(review.getNickname())
                            .content(review.getContent())
                            .rating(review.getRating())
                            .createdAt(review.getCreatedAt())
                            .build())
                    .toList();

            return Detail.builder()
                    .locationId(location.getLocationId())
                    .name(location.getName())
                    .address(location.getAddress())
                    .distance(location.getDistance())
                    .averageRating(location.getAverageRating())
                    .category(location.getCategory())
                    .phone(location.getPhone())
                    .review(reviews)
                    .build();
        }
    }
}