package like_lion.pangjam.controller;

import like_lion.pangjam.dto.global.SliceResponseDto;
import like_lion.pangjam.dto.place.PlaceResponseDto;
import like_lion.pangjam.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    // place 조회
    @GetMapping("page/{page}")
    public ResponseEntity<SliceResponseDto<PlaceResponseDto.PlaceInfo>> searchPlacesInfos(
            @PathVariable int page,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "15") int size
    ) {
        SliceResponseDto <PlaceResponseDto.PlaceInfo> placeSlice = placeService.searchPlacesInfos(name, category, size, page);
        return ResponseEntity.ok(placeSlice);
    }

    // place 상세 조회
    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceResponseDto.Detail> getPlaceDetail(@PathVariable long placeId) {
        PlaceResponseDto.Detail placeDetail = placeService.getPlaceDetail(placeId);
        return ResponseEntity.ok(placeDetail);
    }
}
