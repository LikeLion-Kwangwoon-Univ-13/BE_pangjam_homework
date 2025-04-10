package like_lion.pangjam.Controller;

import like_lion.pangjam.Dto.LocationDto;
import like_lion.pangjam.Model.Location;
import like_lion.pangjam.Service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    // location 생성
    @PostMapping
    public ResponseEntity<LocationDto.Response.Create> createLocation(@RequestBody LocationDto.Request.Create locationDto) {
        LocationDto.Response.Create createdLocation = locationService.createLocation(locationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
    }

    //식당 검색(이름)
    @GetMapping
    public ResponseEntity<LocationDto.Response> getLocationByPlaceName(@RequestParam String placeName) {
        LocationDto.Response locations = locationService.getLocationByPlaceName(placeName);
        if (locations == null) {
            return ResponseEntity.notFound().build(); // HTTP 404 반환
        }
        return ResponseEntity.ok(locations); // HTTP 200 반환
    }

    //식당 검색(대분류)
    @GetMapping
    public ResponseEntity<LocationDto.Response> getLocationByCategoryGroupName(@RequestParam String categoryGroupName) {
        LocationDto.Response locations = locationService.getLocationByCategoryGroupName(categoryGroupName);
        if (locations == null) {
            return ResponseEntity.notFound().build(); // HTTP 404 반환
        }
        return ResponseEntity.ok(locations); // HTTP 200 반환
    }

    //식당 검색(소분류)
    @GetMapping
    public ResponseEntity<LocationDto.Response> getLocationByCategoryName(@RequestParam String categoryName) {
        LocationDto.Response locations = locationService.getLocationByCategoryName(categoryName);
        if (locations == null) {
            return ResponseEntity.notFound().build(); // HTTP 404 반환
        }
        return ResponseEntity.ok(locations); // HTTP 200 반환
    }

    // 식당 상세 정보 조회
    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDto.Response.Detail> getLocationDetail(@PathVariable int locationId) {
        LocationDto.Response.Detail locationDetail = locationService.getLocationDetail(locationId);
        if (locationDetail == null) {
            return ResponseEntity.notFound().build(); // HTTP 404 반환
        }
        return ResponseEntity.ok(locationDetail); // HTTP 200 반환
    }
}
