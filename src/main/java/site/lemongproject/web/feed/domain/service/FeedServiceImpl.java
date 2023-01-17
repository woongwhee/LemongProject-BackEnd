package site.lemongproject.web.feed.domain.service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.web.feed.domain.dao.FeedDao;

import java.util.ArrayList;

//@Service
//@Transactional // 트래직션 처리해줌
//@RequiredArgsConstructor // final붙은거 생성자로
//public class FeedServiceImpl implements FeedService{
//
//
//
//}
