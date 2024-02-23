package com.lionel.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.lionel.generator.MainGenerator;
import com.lionel.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * generate子命令
 * 核心命令，接收参数并生成代码
 */
@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    @Option(names = {"-l", "--loop"}, arity="0..1", description = "是否循环", interactive = true, echo = true)
    private boolean loop;

    @Option(names = {"-a", "--author"}, arity="0..1", description = "作者", interactive = true, echo = true)
    private String author = "lionel";

    @Option(names = {"-o", "--outputText"}, arity="0..1", description = "输出文本", interactive = true, echo = true)
    private String outputText = "sum = ";

    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        // 快速将通过命令接收到的属性复制给MainTemplateConfig配置对象（Hutool）
        BeanUtil.copyProperties(this, mainTemplateConfig);
        System.out.println("配置信息：" + mainTemplateConfig);
        // 生成代码
        MainGenerator.doGenerate(mainTemplateConfig);
        return 0;
    }
}
