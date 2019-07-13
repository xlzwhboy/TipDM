package com.tipdm.framework.model.dmserver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tipdm.framework.common.utils.StringKit;
import com.tipdm.framework.model.IdEntity;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.slf4j.helpers.MessageFormatter;

import javax.persistence.*;
import java.util.*;

/**
 * Created by TipDM on 2016/12/9.
 * E-mail:devp@tipdm.com
 * 算法组件
 */
@Entity
@Table(name = "dm_component", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "parent_id", "creator_id", "in_built"})})
public class Component extends IdEntity<Long> {

    private static final long serialVersionUID = 6220545749518371134L;

    private final static String TAB = "    ";

    private static Map<ComponentExtra.ENGINE, String> codeFormatString =  new HashMap<>();

    static {

        codeFormatString.put(ComponentExtra.ENGINE.PYTHON, "# <editable>\n" +
                "# 算法描述\n"+
                "# </editable>\n" +
                "# conn               数据库连接"+
                "# inputs：           输入数据集合，数据类型：list， 存储组件输入节点对应的数据，\n" +
                "#                    通过输入节点的key获取数据，列入配置的key为“input1”, inputs$input1\n" +
                "#                    即为该节点对应的数据表\n"+
                "# params：           参数集合，数据类型：list， 存储，获取的规则与inputs一致。需要注意的是：\n" +
                "#                    params中参数的值都是字符类型的，需要在代码中进行数据类型转换，比如：\n" +
                "#                    as.integer(params$centers)\n"+
                "# outputs：          存储规则参见inputs\n"+
                "# reportFileName：   算法运行报告文件的存储路径\n" +
                "# 返回值(可选)：      如果函数用于训练模型，则必须返回模型对象\n" +
                "def execute(conn, inputs, params, outputs, reportFileName):\n"+
                "    #<editable>\n"+
                "    {}\n"+
                "    #</editable>\n" +
                "\n");
    }

    @Column(length = 20, nullable = false)
    private String name;//组件中文名

    @Column(name = "target_algorithm", length = 128)
    private String targetAlgorithm;

    @Column(name = "parent_id", nullable = false)
    private Long parentId = 0L;

    @Column(name = "is_enabled")
    private Boolean isEnabled = Boolean.TRUE;

    @Column(name = "is_paralleled")
    private Boolean paralleled = Boolean.FALSE;

    @Column(name = "is_component")
    private Boolean isComponent = Boolean.FALSE;

    @Column(name = "view_source")
    private Boolean allowViewSource = Boolean.FALSE;//是否能够查看源码

    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "minimum_input")
    private Integer minimumInput = 0;//定义组件输入数据源的最小数目

    @Column(name = "in_built")
    private Boolean inBuilt = false;//是否系统内置组件

    @Column(name = "has_report")
    private Boolean hasReport = Boolean.FALSE;

    @Column()
    @Type(type = "text")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id")
    @OrderBy("id")
    private List<ElementTab> tabs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "component_id")
    @Where(clause = "io_type = 0")
    @OrderBy("id asc ")
    private List<ComponentIO> inputs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "component_id")
    @Where(clause = "io_type = 1")
    @OrderBy("id asc ")
    private List<ComponentIO> outputs = new ArrayList<>();

    @Column(name = "update_time")
    private Date updateTime = Calendar.getInstance().getTime();//修改时间

    @Column(name = "sequence")
    private Integer sequence = 0;//排序

    @Embedded
    private  ComponentExtra extra = new ComponentExtra();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="dm_component_script")
    @MapKeyEnumerated(EnumType.ORDINAL)
    @MapKeyColumn(name="step")
    @Column(name="value")
    @OrderBy("step asc")
    @Type(type = "text")
    private Map<Step, String> script = new LinkedHashMap<Step, String>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private Long clientId;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String uploadId;

    public Date getUpdateTime() {
        if(updateTime == null){
            return getCreateTime();
        }
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetAlgorithm() {
        return targetAlgorithm;
    }

    public void setTargetAlgorithm(String targetAlgorithm) {
        this.targetAlgorithm = targetAlgorithm;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Boolean getParalleled() {
        return this.paralleled;
    }


    public Boolean isComponent() {
        return isComponent;
    }

    public void setComponent(Boolean component) {
        isComponent = component;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Integer getMinimumInput() {
        return minimumInput;
    }

    public void setMinimumInput(Integer minimumInput) {
        this.minimumInput = minimumInput;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ComponentIO> getInputs() {
        return inputs;
    }

    public void setInputs(List<ComponentIO> inputs) {
        this.inputs = inputs;
    }

    public List<ComponentIO> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<ComponentIO> outputs) {
        this.outputs = outputs;
    }

    public List<ElementTab> getTabs() {
        return tabs;
    }

    public void setTabs(List<ElementTab> tabs) {
        this.tabs = tabs;
    }

    public Boolean getAllowViewSource() {
        return allowViewSource;
    }

    public void setAllowViewSource(Boolean allowViewSource) {
        this.allowViewSource = allowViewSource;
    }

    public Boolean getInBuilt() {
        return inBuilt;
    }

    public void setInBuilt(Boolean inBuilt) {
        this.inBuilt = inBuilt;
    }

    public Boolean getHasReport() {
        return hasReport;
    }

    public void setHasReport(Boolean hasReport) {
        this.hasReport = hasReport;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public ComponentExtra getExtra() {
        return extra;
    }

    public void setExtra(ComponentExtra extra) {
        this.extra = extra;
    }

    public Map<Step, String> getScript() {

        //兼容性处理
        if(script.get(Step.MAIN) == null && (extra != null && StringKit.isNotBlank(extra.getValue()))){
            String[] values = StringKit.split(extra.getValue(), "\n");
            StringBuilder sb = new StringBuilder();
            for(String str : values){
                sb.append(TAB).append(str).append("\n");
            }
            String value = MessageFormatter.format(codeFormatString.get(extra.getEngine()), sb.toString()).getMessage();
            script.put(Step.MAIN, value);
            extra.setValue(null);
        }
        return script;
    }

    public void setScript(Map<Step, String> script) {
        //替换
        if(null != script){
            for(Map.Entry<Step, String> entry : script.entrySet()){
                entry.setValue(StringKit.replace(entry.getValue(), "\r\n", "\n"));
            }
        }
        this.script = script;
    }

    public Map<String, String> getParameters(){

        Map<String, String> params = new HashMap<>();
        for(ElementTab tab : this.tabs){
            for(Element ele : tab.getElements()) {
                Map.Entry<String, String> entry = ele.toEntry();
                params.put(entry.getKey(), entry.getValue());
            }
        }
        return params;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

}
