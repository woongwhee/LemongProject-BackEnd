package site.lemongproject.web.feed.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageInfo {
    private int listCount; // 총 게시글 갯수
    private int currentPage; // 현재 페이지 (즉, 요청한 페이지)
    private int pageLimit; // 하단에 보여질 페이징바의 페이지 목록 최대 갯수
    private int boardLimit; // 해당 페이지에 보여질 게시글 최대 갯수

    private int maxPage; // 가장 마지막 페이지의 수 (listCount, boardLimit 을 가지고 구함)
    private int startPage; // 해당 페이지에 보여질 페이징 바의 시작수 (pageLimit, currentPage 를 가지고 구함)
    private int endPage; // 해당 페이지에 보여질 페이징 바의 끝수 (startPage, pageLimit, maxPage)
}
