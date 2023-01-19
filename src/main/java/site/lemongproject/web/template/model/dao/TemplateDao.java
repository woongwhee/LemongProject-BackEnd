package site.lemongproject.web.template.model.dao;

import site.lemongproject.web.template.model.vo.Template;

import java.util.Map;

public interface TemplateDao {
    Template findUnSave(int userNo);

    int uploadTemp(int userNo);

    int resetUnSave(int userNo);

    int deleteTemp(int templateNo);

    int createTemp(Map<String,Object> createMap);
    int updateUnSave(Template template);



}
