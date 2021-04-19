package com.example.jvm;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * 代码命名规范校验注解处理器
 */
@SupportedAnnotationTypes("*")// 表示对哪些注解感兴趣 
@SupportedSourceVersion(SourceVersion.RELEASE_8)// 需要处理哪个版本的Java代码 
public class NameCheckProcessor extends AbstractProcessor {

    private NameChecker nameChecker;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameChecker(processingEnv);
    }

    /**
     * 对输入的语法树的各个节点进行名称检查
     *
     * @param annotations 获取此注解处理器要处理的注解集合
     * @param roundEnv    从该参数访问到当前这个轮次（Round）中的抽象语法树节点
     * @return
     */
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements()) {
                nameChecker.checkNames(element);
            }
        }
        // 返回false通知该轮次中代码并未改变 
        return false;
    }
} 