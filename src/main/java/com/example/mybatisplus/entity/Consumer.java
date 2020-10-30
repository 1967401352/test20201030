package com.example.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.example.mybatisplus.validation.IcreateValidation;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@Component
public class Consumer implements Cloneable {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id = "10";
    @NotBlank(groups={IcreateValidation.class},message ="用户名不能为空" )
    private String name;
    private Integer age;
    @Version
    private Long version;
    @TableLogic
    private Integer deleteFlag;
    @TableField(fill= FieldFill.INSERT)
    private Date newDate;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date updateDate;

    public Consumer(String name, Integer age, Long version, Integer deleteFlag, Date newDate, Date updateDate) {
        this.name = name;
        this.age = age;
        this.version = version;
        this.deleteFlag = deleteFlag;
        this.newDate = newDate;
        this.updateDate = updateDate;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
