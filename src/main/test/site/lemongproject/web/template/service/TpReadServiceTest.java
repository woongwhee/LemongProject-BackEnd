package site.lemongproject.web.template.service;


import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import site.lemongproject.common.config.ApplicationConfig;
import site.lemongproject.config.Configure;
import site.lemongproject.web.member.model.dao.MemberDao;
import site.lemongproject.web.member.model.dto.ChangePwdVo;
import site.lemongproject.web.member.model.vo.Member;
import site.lemongproject.web.template.model.dao.ReviewDao;
import site.lemongproject.web.template.model.dto.Review;
import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.dto.TemplateTodo;
import site.lemongproject.web.template.model.vo.ReviewDeleteVo;
import site.lemongproject.web.template.model.vo.ReviewInsertVo;

import java.util.ArrayList;
import java.util.LinkedList;
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
        List<Template> t=readService.getTemplateList(0,0);
        assertThat(t).isNotNull();
        assertThat(t.size()).isLessThan(9);

    }
    @Test
    @DisplayName("상세조회 테스트")
    public void selectDetail(){
        Template t=readService.getTemplateDetail(TEST_TP_NO);
        System.out.println(t);
        assertThat(t).isNotNull();
        assertThat(t.getCreate()).isNotNull();
        assertThat(t.getTodoList()).isNotNull();
    }
    @Test
    @DisplayName("투두조회테스트")
    public void getTemplateTodo(){
        List<TemplateTodo> todoList=readService.getTemplateTodo(TEST_TP_NO);
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

}
