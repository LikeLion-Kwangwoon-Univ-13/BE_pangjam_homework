package like_lion.pangjam.dto.place;
import java.util.List;

import like_lion.pangjam.domain.Place;
import like_lion.pangjam.domain.PlaceReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResponseDto {

    private List<PlaceInfo> places;

    @Getter @Builder
    public static class PlaceInfo
    {
        private long placeId;
        private String name;
        private String category;
        private double averageRating;
        private String phone;
        private String distance;
        private double latitude;
        private double longitude;

        public static PlaceInfo from(Place place) {
            return PlaceInfo.builder()
                    .placeId(place.getPlaceId())
                    .name(place.getName())
                    .category(place.getCategory())
                    .averageRating(place.getAverageRating())
                    .phone(place.getPhone())
                    .distance(place.getDistance())
                    .latitude(place.getLatitude())
                    .longitude(place.getLongitude())
                    .build();
        }
    }

    //상세정보 : 이름, 주소, 거리, 평균 평점, 카테고리, 번호
    @Getter @Builder
    public static class Detail
    {
        private long placeId;
        private String name;
        private String address;
        private String distance;
        private double averageRating;
        private List<Integer> ratingsCount;
        private int reviewCount;
        private String thumbNail;
        private String category;
        private String phone;

        private List<Reviews> review;

        @Getter @Builder
        public static class Reviews{
            private long placeReviewId;
            private String nickname;
            private String content;
            private int rating;
            private String createdAt;
        }

        // Place 객체를 변환하는 메서드
        public static Detail from(Place place, List<PlaceReview> recentReviews,List<PlaceReview> allReviews ) {
            //recent review 5개
            List<Detail.Reviews> reviews = recentReviews.stream()
                    .map(review -> Reviews.builder()
                            .placeReviewId(review.getPlaceReviewId())
                            .nickname(review.getNickname())
                            .content(review.getContent())
                            .rating(review.getRating())
                            .createdAt(review.getCreatedAt().toString())
                            .build())
                    .toList();

            return Detail.builder()
                    .placeId(place.getPlaceId())
                    .name(place.getName())
                    .address(place.getAddress())
                    .distance(place.getDistance())
                    .averageRating(place.getAverageRating())
                    .ratingsCount(place.getRatingsCount())
                    .reviewCount(place.getReviewCount())
                    .thumbNail(place.getThumbNail())
                    .category(place.getCategory())
                    .phone(place.getPhone())
                    .review(reviews)
                    .build();
        }
    }
}