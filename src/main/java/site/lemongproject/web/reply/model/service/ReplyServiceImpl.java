package site.lemongproject.web.reply.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.reply.model.dao.ReplyDao;
import site.lemongproject.web.reply.model.vo.Reply;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    final private ReplyDao replyDao;

    public List<Reply> selectFeedReply(){
        return replyDao.selectFeedReply();
    }
}
