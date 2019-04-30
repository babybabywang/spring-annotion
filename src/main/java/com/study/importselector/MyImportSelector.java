package com.study.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义快速将bean注册到SpringIOC容器
 * @author Administrator
 */
public class MyImportSelector implements ImportSelector {
    /**
     * 返回值就是需要导入到容器中的组件全类名
     * @param annotationMetadata 当前标注@Import注解的类的所有注解信息
     * @return
     */
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.study.bean.Blue","com.study.bean.Red"};
    }
}
