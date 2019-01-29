package com.niocoder.core.type.classreading;

import com.niocoder.core.annotation.AnnotationAttributes;
import jdk.internal.org.objectweb.asm.Type;
import org.springframework.asm.AnnotationVisitor;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 读取class文件，注解信息
 *
 * @author zhenglongfei
 */
public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor {

    /**
     * com.niocoder.stereotype.Component
     */
    private final Set<String> annotationSet = new LinkedHashSet<>(4);
    /**
     * com.niocoder.stereotype.Component
     * AnnotationAttributes  value->nioCoder
     */
    private final Map<String, AnnotationAttributes> attributeMap = new LinkedHashMap<>(4);

    public AnnotationMetadataReadingVisitor() {

    }


    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
        String className = Type.getType(desc).getClassName();
        this.annotationSet.add(className);
        return new AnnotationAttributesReadingVisitor(className, this.attributeMap);
    }

    public Set<String> getAnnotationTypes() {
        return this.annotationSet;
    }

    public boolean hasAnnotation(String annotationType) {
        return this.annotationSet.contains(annotationType);
    }

    public AnnotationAttributes getAnnotationAttributes(String annotationType) {
        return this.attributeMap.get(annotationType);
    }
}
