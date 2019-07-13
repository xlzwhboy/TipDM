package com.tipdm.framework.model.dmserver;

import com.tipdm.framework.common.utils.StringKit;
import com.tipdm.framework.model.IdEntity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TipDM on 2016/12/9.
 * E-mail:devp@tipdm.com
 */
@Entity
@Table(name = "dm_element", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "tab_id"})})
public class Element extends IdEntity<Long> {

    private static final long serialVersionUID = -8777944709535765356L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String label;

    @Column(name = "default_value")
    @Type(type = "text")
    private String defaultValue;

    @Column
    @Type(type = "text")
    private String value;

    @Column
    @Type(type = "text")
    private String options = "";

    @Column
    @Type(type = "text")
    private String rexp = "";

    @Column
    private String placeholder;

    @Column(name = "tooltip")
    private String toolTip;

    @Column(name = "sequence")
    private Integer sequence = 1;

    @Column(name = "is_required")
    private Boolean isRequired = Boolean.FALSE;

    @Column(name = "is_visible")
    private Boolean isVisible = Boolean.FALSE;

    @Column(name = "type_id")
    private Long elementType;

    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="dm_element_extras", joinColumns=@JoinColumn(name="element_id"))
    private Map<String, String> extra = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        //如果参数值==空，读取默认值赋
        if(StringKit.isBlank(value)){
            return defaultValue;
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }

    public Long getElementType() {
        return elementType;
    }

    public void setElementType(Long elementType) {
        this.elementType = elementType;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }

    public Map.Entry<String, String> toEntry(){

        Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<>(name, value);
        return entry;
    }

    public String getRexp() {
        return rexp;
    }

    public void setRexp(String rexp) {
        this.rexp = rexp;
    }
}
