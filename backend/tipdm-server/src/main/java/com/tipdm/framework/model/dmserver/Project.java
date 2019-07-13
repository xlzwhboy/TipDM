package com.tipdm.framework.model.dmserver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tipdm.framework.model.IdEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by TipDM on 2016/12/9.
 * E-mail:devp@tipdm.com
 * 工程
 */
@Entity
@Table(name = "dm_project")
public class Project extends IdEntity<Long> {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private Integer status = 1;

    @Column(name = "is_delete")
    private Boolean isDelete = Boolean.FALSE;

    @Column(name = "is_paralleled")
    private Boolean isParalleled = Boolean.FALSE;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_open_time")
    private Date lastOpenTime = Calendar.getInstance().getTime();

    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long parentId;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String json;

    @Transient
    private Map<String, Map<String, String>> versions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Boolean getParalleled() {
        return isParalleled;
    }

    public void setParalleled(Boolean paralleled) {
        isParalleled = paralleled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastOpenTime() {
        return lastOpenTime;
    }

    public void setLastOpenTime(Date lastOpenTime) {
        this.lastOpenTime = lastOpenTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Map<String, Map<String, String>> getVersions() {
        return versions;
    }

    public void setVersions(Map<String, Map<String, String>> versions) {
        this.versions = versions;
    }
}
