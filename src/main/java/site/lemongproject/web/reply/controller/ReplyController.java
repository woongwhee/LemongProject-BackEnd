package site.lemongproject.web.reply.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lemongproject.web.reply.model.service.ReplyService;
import site.lemongproject.web.reply.model.vo.Reply;

import java.util.List;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
    final private ReplyService replyService;

    // userNo와 feedNo와 replyNo에 해당하는 댓글 목록 불러오기
    @GetMapping("/selectReply")
    public List<Reply> selectFeedReply(){
        List<Reply> rList = replyService.selectFeedReply();

        return rList;
    }


}
