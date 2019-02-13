package com.niocoder.context.annotation;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.factory.BeanDefinitionStoreException;
import com.niocoder.beans.factory.support.BeanDefinitionRegistry;
import com.niocoder.beans.factory.support.BeanNameGenerator;
import com.niocoder.core.io.Resource;
import com.niocoder.core.io.support.PackageResourceLoader;
import com.niocoder.core.type.classreading.MetadataReader;
import com.niocoder.core.type.classreading.SimpleMetadataReader;
import com.niocoder.stereotype.Component;
import com.niocoder.util.StringUtils;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 扫描component注解定义的bean
 *
 * @author zhenglongfei
 */
@Log
public class ClassPathBeanDefinitionScanner {

    /**
     * 注册到BeanFactory中，所以需要BeanDefinitionRegistry
     */
    private final BeanDefinitionRegistry registry;

    /**
     * 扫描包下的文件返回Resource
     */
    private PackageResourceLoader resourceLoader = new PackageResourceLoader();

    /**
     * 生成Bean的名称beanName
     */
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 核心方法，给定包名，包下面所有被@Component会被创建BeanDefinition并返回
     *
     * @param packagesToScan
     * @return
     */
    public Set<BeanDefinition> doScan(String packagesToScan) {

        String[] basePackages = StringUtils.tokenizeToStringArray(packagesToScan, ",");

        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<BeanDefinition>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                beanDefinitions.add(candidate);
                registry.registerBeanDefinition(candidate.getId(), candidate);

            }
        }
        return beanDefinitions;
    }


    /**
     * 查找被Component标记的类并返回
     *
     * @param basePackage
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();
        try {

            Resource[] resources = this.resourceLoader.getResource(basePackage);

            for (Resource resource : resources) {
                try {

                    MetadataReader metadataReader = new SimpleMetadataReader(resource);

                    if (metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName())) {
                        ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader.getAnnotationMetadata());
                        String beanName = this.beanNameGenerator.generateBeanName(sbd, this.registry);
                        sbd.setId(beanName);
                        candidates.add(sbd);
                    }
                } catch (Throwable ex) {
                    throw new BeanDefinitionStoreException(
                            "Failed to read candidate component class: " + resource, ex);
                }

            }
        } catch (IOException ex) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning", ex);
        }
        return candidates;
    }
}
