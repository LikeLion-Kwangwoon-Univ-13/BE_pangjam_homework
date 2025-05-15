package like_lion.pangjam.dto.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SliceResponseDto<T> {
    private List<T> content;
    private boolean hasNext;
    private int currentPage;
    private int size;

    // 페이징 정보만 담는 생성자 추가
    public static <T> SliceResponseDto<T> of(List<T> content, boolean hasNext, int currentPage, int size) {
        return new SliceResponseDto<>(content, hasNext, currentPage, size);
    }
}