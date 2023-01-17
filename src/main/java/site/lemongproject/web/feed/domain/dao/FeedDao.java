package site.lemongproject.web.feed.domain.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class FeedDao {

    final private SqlSession sqlSession;

//    public ArrayList selectFeed(){
//
//    }
}
