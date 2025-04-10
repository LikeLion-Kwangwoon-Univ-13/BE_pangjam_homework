package like_lion.pangjam.Service;

import like_lion.pangjam.Dto.LocationDto;
import like_lion.pangjam.Model.Location;
import like_lion.pangjam.Model.Review;
import like_lion.pangjam.Repository.LocationRepository;
import like_lion.pangjam.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final ReviewRepository reviewRepository;

    //[과제용] 식당 정보 추가하기
    public LocationDto.Response.Create createLocation(LocationDto.Request.Create locationDto) {

        Location location = Location.builder()
                .addressName(locationDto.getAddressName())
                .roadAddressName(locationDto.getRoadAddressName())
                .categoryGroupName(locationDto.getCategoryGroupName())
                .categoryName(locationDto.getCategoryName())
                .placeName(locationDto.getPlaceName())
                .phone(locationDto.getPhone())
                .build();

        Location saved = locationRepository.save(location);

        return LocationDto.Response.Create.builder()
                .locationId(saved.getLocationId())
                .addressName(saved.getAddressName())
                .roadAddressName(saved.getRoadAddressName())
                .categoryGroupName(saved.getCategoryGroupName())
                .categoryName(saved.getCategoryName())
                .placeName(saved.getPlaceName())
                .phone(saved.getPhone())
                .build();
    }

    //식당 상세정보 조회
    public LocationDto.Response.Detail getLocationDetail(int locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        //최신 리뷰 3개 가져오기
        List<Review> reviews = reviewRepository.findTop3ByLocationIdOrderByCreatedAtDesc(locationId);



        List<LocationDto.Response.Detail.ReviewInfo> reviewInfos = reviews.stream()
                .map(review -> LocationDto.Response.Detail.ReviewInfo.builder()
                        .userId(review.getUser().getUserId())
                        .nickname(review.getUser().getNickname())
                        .content(review.getContent())
                        .rating(review.getRating())
                        .createdAt(review.getCreatedAt())
                        .build())
                .toList();

        return LocationDto.Response.Detail.builder()
                .placeName(location.getPlaceName())
                .roadAddressName(location.getRoadAddressName())
                .AddressName(location.getAddressName())
                .averageRating(location.getAverageRating())
                .phone(location.getPhone())
                .review(reviewInfos)
                .build();
    }

    //식당 검색(이름)
    public LocationDto.Response getLocationByPlaceName(String placeName) {
        List<Location> locations = locationRepository.findByPlaceNameContaining(placeName);
        return convertToResponse(locations);
    }

    //식당 검색(대분류)
    public LocationDto.Response getLocationByCategoryGroupName(String categoryGroupName) {
        List<Location> locations = locationRepository.findByCategoryGroupName(categoryGroupName);
        return convertToResponse(locations);
    }

    //식당 검색(소분류)
    public LocationDto.Response getLocationByCategoryName(String categoryName) {
        List<Location> locations = locationRepository.findByCategoryName(categoryName);
        return convertToResponse(locations);
    }




    public LocationDto.Response convertToResponse(List<Location> locations) {
        List<LocationDto.Response.LocationInfo> locationInfos = locations.stream()
                .map(LocationDto.Response.LocationInfo::from)
                .toList();

        return LocationDto.Response.builder()
                .locations(locationInfos)
                .build();
    }
}
