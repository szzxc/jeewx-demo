package weixin.guanjia.message.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.entity.IdEntity;

import javax.persistence.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: 消息模板行业设置
 * @date 2016-05-04 13:41:47
 */
@Entity
@Table(name = "weixin_templatemsg_industry")
public class TemplatemsgIndustry extends IdEntity {
    /**
     * 创建人名称
     */
    private java.lang.String createName;
    /**
     * 创建人登录名称
     */
    private java.lang.String createBy;
    /**
     * 创建日期
     */
    private java.util.Date createDate;
    /**
     * 更新人名称
     */
    private java.lang.String updateName;
    /**
     * 更新人登录名称
     */
    private java.lang.String updateBy;
    /**
     * 更新日期
     */
    private java.util.Date updateDate;
    /**
     * 主行业
     */
    private java.lang.String nameOneFirst;
    /**
     * 副行业
     */
    private java.lang.String nameOneSecond;
    /**
     * 行业代码
     */
    private java.lang.Integer codeOne;
    /**
     * 第二主行业
     */
    private java.lang.String nameTwoFirst;
    /**
     * 第二副行业
     */
    private java.lang.String nameTwoSecond;
    /**
     * 第二行业代码
     */
    private java.lang.Integer codeTwo;

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  创建人名称
     */
    @Column(name = "CREATE_NAME", nullable = true, length = 50)
    public java.lang.String getCreateName() {
        return this.createName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  创建人名称
     */
    public void setCreateName(java.lang.String createName) {
        this.createName = createName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  创建人登录名称
     */
    @Column(name = "CREATE_BY", nullable = true, length = 50)
    public java.lang.String getCreateBy() {
        return this.createBy;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  创建人登录名称
     */
    public void setCreateBy(java.lang.String createBy) {
        this.createBy = createBy;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  创建日期
     */
    @Column(name = "CREATE_DATE", nullable = true, length = 20)
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  创建日期
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  更新人名称
     */
    @Column(name = "UPDATE_NAME", nullable = true, length = 50)
    public java.lang.String getUpdateName() {
        return this.updateName;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  更新人名称
     */
    public void setUpdateName(java.lang.String updateName) {
        this.updateName = updateName;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  更新人登录名称
     */
    @Column(name = "UPDATE_BY", nullable = true, length = 50)
    public java.lang.String getUpdateBy() {
        return this.updateBy;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  更新人登录名称
     */
    public void setUpdateBy(java.lang.String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  更新日期
     */
    @Column(name = "UPDATE_DATE", nullable = true, length = 20)
    public java.util.Date getUpdateDate() {
        return this.updateDate;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  更新日期
     */
    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主行业
     */
    @Column(name = "NAME_ONE_FIRST", nullable = true, length = 50)
    public java.lang.String getNameOneFirst() {
        return this.nameOneFirst;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  主行业
     */
    public void setNameOneFirst(java.lang.String nameOneFirst) {
        this.nameOneFirst = nameOneFirst;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  副行业
     */
    @Column(name = "NAME_ONE_SECOND", nullable = true, length = 50)
    public java.lang.String getNameOneSecond() {
        return this.nameOneSecond;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  副行业
     */
    public void setNameOneSecond(java.lang.String nameOneSecond) {
        this.nameOneSecond = nameOneSecond;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  行业代码
     */
    @Column(name = "CODE_ONE", nullable = true, length = 2)
    public java.lang.Integer getCodeOne() {
        return this.codeOne;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  行业代码
     */
    public void setCodeOne(java.lang.Integer codeOne) {
        this.codeOne = codeOne;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  第二主行业
     */
    @Column(name = "NAME_TWO_FIRST", nullable = true, length = 50)
    public java.lang.String getNameTwoFirst() {
        return this.nameTwoFirst;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  第二主行业
     */
    public void setNameTwoFirst(java.lang.String nameTwoFirst) {
        this.nameTwoFirst = nameTwoFirst;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  第二副行业
     */
    @Column(name = "NAME_TWO_SECOND", nullable = true, length = 50)
    public java.lang.String getNameTwoSecond() {
        return this.nameTwoSecond;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  第二副行业
     */
    public void setNameTwoSecond(java.lang.String nameTwoSecond) {
        this.nameTwoSecond = nameTwoSecond;
    }

    /**
     * 方法: 取得java.lang.Integer
     *
     * @return: java.lang.Integer  第二行业代码
     */
    @Column(name = "CODE_TWO", nullable = true, length = 2)
    public java.lang.Integer getCodeTwo() {
        return this.codeTwo;
    }

    /**
     * 方法: 设置java.lang.Integer
     *
     * @param: java.lang.Integer  第二行业代码
     */
    public void setCodeTwo(java.lang.Integer codeTwo) {
        this.codeTwo = codeTwo;
    }
}
