package like_lion.pangjam.Dto;

import like_lion.pangjam.Model.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ReviewDto {
    public static class Request {

        @Getter @Builder
        public static class Create {
            private int userId;
            private int locationId;
            private String content;
            private double rating;
        }
        public static class GetAll {
            private int page;
            private int size;
        }
        public static class Update {
            private String content;
            private double rating;
        }

    }

    @Builder
    public static class Response {

        private List<ReviewInfo> reviews;

        @Getter @Builder
        public static class ReviewInfo {
            private int userId;
            private String nickname;
            private String content;
            private double rating;
            private String createdAt;

            public static ReviewDto.Response.ReviewInfo from(Review review) {
                return ReviewDto.Response.ReviewInfo.builder()
                        .userId(review.getUser().getUserId())
                        .nickname(review.getUser().getNickname())
                        .content(review.getContent())
                        .rating(review.getRating())
                        .createdAt(review.getCreatedAt())
                        .build();
            }
        }
        @Getter @Builder
        public static class Create {
            private String nickname;
            private String content;
            private double rating;
            private String createdAt;
        }
        public static class GetAll {
            private int reviewId;
            private String nickname;
            private String content;
            private double rating;
            private String createdAt;
        }
        public static class Update {
            private String nickname;
            private String content;
            private double rating;
            private String modifiedAt;
        }
    }
}