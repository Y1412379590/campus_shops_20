package cn.edu.mju.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SqlSessionFactoryConfiguration {


    @Autowired
    private DataSource dataSource;

    private static String mybatisConfigFile;

    @Value("${mybatis_config_file}")
    public void setMybatisConfigFile(String mybatisConfigFile) {
        SqlSessionFactoryConfiguration.mybatisConfigFile = mybatisConfigFile;
    }

    private static String mapperPath;

    @Value("${mapper_path}")
    public void setMapperPath(String mapperPath) {
        SqlSessionFactoryConfiguration.mapperPath = mapperPath;
    }

    @Value("${type_alias_package}")
    private String typeAliasPackage;


    /**
     *
     * 创建sqlSessionFactoryBean
     * @see SqlSessionFactoryBean
     *
     * 设置dataSource数据源
     *
     * @return
     */

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //mybatis 的配置文件
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));

        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver
                = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;

        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));

        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);

        return sqlSessionFactoryBean;

    }



}
