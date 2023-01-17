package site.lemongproject.web.template.model.dao;

import site.lemongproject.web.template.model.vo.Template;

public interface TemplateDao {
    Template findUnSave(int userNo);
}
