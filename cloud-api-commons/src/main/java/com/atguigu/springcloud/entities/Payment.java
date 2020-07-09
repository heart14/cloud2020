package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Payment
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2020/7/1 14:43
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private static final long serialVersionUID = -7942337272130211133L;

    private Long id;

    private String serial;
}
