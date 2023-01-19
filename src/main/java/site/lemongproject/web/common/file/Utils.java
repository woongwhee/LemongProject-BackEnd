package site.lemongproject.web.common.file;

import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    // 변경된 이름을 돌려주면서 원본파일을 변경된 파일 이름으로 저장시키는 메서드
    static public String saveFile(MultipartFile upfile , String savePath){

        // 1. 원본 파일명 뽑기
        String originName = upfile.getOriginalFilename();

        // 2. 시간 형식을 문자열로 뽑아오기
        // 년 월 일 시 분 초
        String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // 3. 뒤에 붙을 5자리 랜덤값 뽑기
        int random = (int) (Math.random() * 90000 + 10000); // 5자리 랜덤값

        // 4. 원본 파일명으로부터 확장자명 뽑기.
        // .jpg
        String ext = originName.substring(originName.lastIndexOf("."));

        // 5. 전부 이어붙이기.
        String changeName = currentTime + random + ext;

        return changeName;

    }
}
