package com.lionel.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.lionel.model.MainTemplateConfig;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

/**
 * config子命令
 * 辅助命令，输出允许用户传入的动态参数的信息
 */
@Command(name = "config", description = "查看参数信息", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{

    @Override
    public void run() {
        // 实现config命令的逻辑
        System.out.println("查看参数信息：");
        // java 原生反射
//        Class<?> myClass = MainTemplateConfig.class;
//        Field[] fields = myClass.getDeclaredFields();
        // Hutool反射工具类
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);

        for (Field field : fields) {
            System.out.println("字段名称：" + field.getName());
            System.out.println("字段类型：" + field.getType());
            System.out.println("---");
        }


    }
}
