package site.lemongproject.web.reply.model.service;

import site.lemongproject.web.reply.model.vo.Reply;

import java.util.List;

public interface ReplyService {


    List<Reply> selectFeedReply();
}
