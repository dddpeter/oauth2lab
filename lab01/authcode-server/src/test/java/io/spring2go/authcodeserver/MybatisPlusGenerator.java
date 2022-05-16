package io.spring2go.authcodeserver;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

import java.util.Collections;

/**
 * @author lijinde
 * @create 2022/5/14 13:57
 **/
public class MybatisPlusGenerator {
    @Test
    public void generrateEntity(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/oauth2?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8",
                        "root",
                        "123456")
                .globalConfig(builder -> {
                    builder.author("lijinde") // 设置作者
                          //  .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/hqins/logs/code"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("io.spring2go.authcodeserver") // 设置父包名
                            .moduleName("user") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.entity, "/Users/hqins/logs/code")); // 设置mapperXml生成路径
                })
              /*  .strategyConfig(builder -> {
                    builder.addInclude("t_simple") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })*/
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
