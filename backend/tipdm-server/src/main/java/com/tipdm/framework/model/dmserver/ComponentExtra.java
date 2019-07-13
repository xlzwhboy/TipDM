package com.tipdm.framework.model.dmserver;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * Created by TipDM on 2017/4/5.
 * E-mail:devp@tipdm.com
 */
@Embeddable
public class ComponentExtra implements Serializable{

    private static final long serialVersionUID = -8500579512357397082L;

    @Column(name = "extra_type")
    @Enumerated()
    private ExtraType type = ExtraType.SCRIPT;

    @Column(name = "extra_value")
    @Type(type="text")
    private String value;

    @Column(name = "algorithm_main_class")
    private String mainClass;

    @Enumerated()
    @Column
    private ENGINE engine = ENGINE.PYTHON;

    public ExtraType getType() {
        return type;
    }

    public void setType(ExtraType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ENGINE getEngine() {
        return engine;
    }

    public void setEngine(ENGINE engine) {
        this.engine = engine;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public enum ExtraType{
        SCRIPT,//脚本
        FILE,//jar/R文件
        NORMAL
    }

    public enum ENGINE{
        R,//R语言
        PYTHON,//python
    }
}
