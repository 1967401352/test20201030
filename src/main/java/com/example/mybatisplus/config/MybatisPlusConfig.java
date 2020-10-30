package com.example.mybatisplus.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.IdGenerator;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Configuration
@EnableSwagger2
@MapperScan({"com.example.mybatisplus.mapper"})
public class MybatisPlusConfig {
    @Bean
    public IdentifierGenerator  IdentifierGenerator (){
       return new IdentifierGenerator () {

           @Override
           public Number nextId(Object entity) {
               return 222;
           }

           @Override
           public String nextUUID(Object entity) {
               String uuid = UUID.randomUUID().toString();
               return uuid;
           }
       };
    }
    @Bean
    public MetaObjectHandler metaObjectHandler (){

        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject,"newDate", Date.class,new Date());
                this.strictInsertFill(metaObject,"updateDate", Date.class,new Date());

            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject,"updateDate", Date.class,new Date());
            }
        };
    }
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {


        return new OptimisticLockerInterceptor();
    }

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Kitty API Doc")
                .description("This is a restful api document of Kitty.")
                .version("1.0")
                .build();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

}
