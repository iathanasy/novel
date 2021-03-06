package top.icss.novel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cd.wang
 * @create 2020-10-21 14:14
 * @since 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 资源映射路径 前缀
     */
    private String resourcePrefix = "/full";

    /**
     * 文件路径 示例（ Windows配置F:/mnt/upload，Linux配置 /mnt/upload）
     */
    private String resourceUploadPath = "F:/Python/scrapy/novelScrapy/novelScrapy/data/image";

    /**
     * 配置图片映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 本地文件上传路径 */
        registry.addResourceHandler(resourcePrefix + "/**")
                .addResourceLocations("file:" + resourceUploadPath + resourcePrefix+ "/");
    }
}
