package site.lemongproject.web.feed.domain.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class FeedDao {
    final SqlSession sqlSession;

    public ArrayList selectFeed(){
        return null;
    }
}
