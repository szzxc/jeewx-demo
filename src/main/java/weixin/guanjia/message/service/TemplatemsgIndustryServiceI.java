package weixin.guanjia.message.service;

import org.jeecgframework.core.common.service.CommonService;
import weixin.guanjia.message.entity.TemplatemsgIndustry;

import java.io.Serializable;

public interface TemplatemsgIndustryServiceI extends CommonService {

    public <T> void delete(T entity);

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    /**
     * 默认按钮-sql增强-新增操作
     *
     * @param id
     * @return
     */
    public boolean doAddSql(TemplatemsgIndustry t);

    /**
     * 默认按钮-sql增强-更新操作
     *
     * @param id
     * @return
     */
    public boolean doUpdateSql(TemplatemsgIndustry t);

    /**
     * 默认按钮-sql增强-删除操作
     *
     * @param id
     * @return
     */
    public boolean doDelSql(TemplatemsgIndustry t);
}
