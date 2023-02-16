package site.lemongproject.web.template.service;


import org.junit.Test;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.config.Configure;
import site.lemongproject.web.member.model.dao.MemberDao;
import site.lemongproject.web.template.model.dao.ReviewDao;
import site.lemongproject.web.template.model.dto.Review;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateCategory;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//@TestRunner
@Transactional
//@WebAppConfiguration
//@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class,classes = ApplicationConfig.class)
//@SpringJUnitConfig(ApplicationConfig.class)

public class TpReadServiceTest extends Configure{

    @Autowired private ReviewDao reviewDao;
    @Autowired private TemplateReadService readService;
//    final private Logger logger= LoggerFactory.getLogger(TpReadServiceTest.class);
    @Autowired private MemberDao memberDao;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
    final static int TEST_TP_NO=30;
    final static int TEST_USER_NO=1;
    @Test

    @DisplayName("목록조회 테스트")
    public void selectTemplate(){
        List<Template> t=readService.getTemplateList(new TemplateFindVo(0,0,TEST_USER_NO));
        assertThat(t).isNotNull();
        assertThat(t.size()).isLessThan(9);

    }
    @Test
    @DisplayName("상세조회 테스트")
    public void selectDetail(){

        TemplateDetailVo t=readService.getTemplateDetail(new TemplateFindVo(TEST_TP_NO,TEST_USER_NO));
        System.out.println(t);
        assertThat(t).isNotNull();
        assertThat(t.getCreate()).isNotNull();
        assertThat(t.getTodoList()).isNotNull();
    }
    @Test
    @DisplayName("투두조회테스트")
    public void getTemplateTodo(){
        List<TemplateTodo> todoList=readService.getTodoByDay(new TPDayTodoVo(TEST_TP_NO,1));
        assertThat(todoList).isNotNull();
        assertThat(todoList.size()).isNotZero();
    };

    @Test
    @DisplayName("리뷰 입력테스트")
    public void insertReview(){
        ReviewInsertVo riv=new ReviewInsertVo();
        riv.setUserNo(TEST_USER_NO);
        riv.setTemplateNo(TEST_TP_NO);
        riv.setReviewDetail("리뷰테스트");
        int result=readService.insertReview(riv);
        assertThat(result).isEqualTo(1);
    }
    @Test
    @DisplayName("카테고리 총계수계산")
    public void count(){
        int count= readService.getTemplateCount(0);
        int count2= readService.getTemplateCount(1);
        System.out.println(count);
        System.out.println(count2);
    }
    @Test
    @DisplayName("리뷰조회테스트")
    public void  getReviewList(){
        insertReview();
        List<Review>reviews=readService.getReviewList(TEST_TP_NO);
        System.out.println(reviews);
        assertThat(reviews).isNotNull();
        assertThat(reviews.size()).isNotZero();

    }
    @Test
    @DisplayName("리뷰삭제테스트")
    public void deleteReview(){
        Review r=reviewDao.findOne(3);
        System.out.println(r);
        assertThat(r).isNotNull();
        int result=readService.deleteReview(new ReviewDeleteVo(3,TEST_USER_NO));
        assertThat(result).isNotZero();
        r=reviewDao.findOne(3);
        assertThat(r).isNull();
    }
    @Test
    @DisplayName("카테고리")
    public void getCategory(){
        List<TemplateCategory> categoryList=readService.getTemplateCategory();
        System.out.println(categoryList);
        assertThat(categoryList).isNotNull();
    }

}
