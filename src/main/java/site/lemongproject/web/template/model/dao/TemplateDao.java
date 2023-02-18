package site.lemongproject.web.template.model.dao;

import site.lemongproject.web.template.model.dto.Template;
import site.lemongproject.web.template.model.vo.*;

import java.util.List;

public interface TemplateDao {
    TPUnsaveVo findUnSave(int userNo);

    int uploadUnSave(int userNo);

    List<Template> findList(TemplateFindVo findVo, int limit);
    TemplateDetailVo findDetail(TemplateFindVo findVo);
    Template findOne(int templateNo);

    int countTemplate(int categoryNo);

    int deleteUnSave(int userNo);

    int deleteTemp(int templateNo);

    int createTemp(int userNo);
    int updateUnSave(TemplateUpdateVo templateVo);


    boolean isWriter(WriterCheckVo writerCheckVo);

    int findRange(int templateNo);

    List<TemplateListVo> findByUser(int userNo);
}
