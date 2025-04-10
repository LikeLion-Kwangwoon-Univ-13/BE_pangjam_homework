package like_lion.pangjam.Dto;

import like_lion.pangjam.Model.Location;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class LocationDto {
    public static class Request {
        @Getter @Builder
        public static class Create{
            private String addressName;
            private String roadAddressName;
            private String categoryGroupName;
            private String categoryName;
            private String placeName;
            private String phone;
        }
    }

    @Builder
    public static class Response {

        private List<LocationInfo> locations;

        @Getter @Builder
        public static class Create{
            private int locationId;
            private String addressName;
            private String roadAddressName;
            private String categoryGroupName;
            private String categoryName;
            private String placeName;
            private String phone;
            //private double latitude;
            //private double longitude;
            //private String distance;
        }

        @Getter @Builder
        public static class LocationInfo{
            private int locationId;
            private String placeName;
            private String categoryName;
            private double rating;
            private String phone;

            public static LocationInfo from(Location location) {
                return LocationInfo.builder()
                        .locationId(location.getLocationId())
                        .placeName(location.getPlaceName())
                        .categoryName(location.getCategoryName())
                        .rating(location.getAverageRating())
                        .phone(location.getPhone())
                        .build();
            }
        }

        @Getter @Builder
        public static class Detail{
            private String placeName;
            private String roadAddressName;
            private String AddressName;
            private Double averageRating;
            private Double distance;
            private String phone;
            private String imageUrl;
            private List<ReviewInfo> review;

            @Getter @Builder
            public static class ReviewInfo{
                private int userId;
                private String nickname;
                private String content;
                private Double rating;
                private String createdAt;
            }
        }
    }
}