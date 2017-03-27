package com.letv.tbtSps.common.cotext.config;

import org.springframework.util.Assert;

/**
 * LanguageContext:在上下文保存当前语言
 * 
 * @author yuguodong
 * @version 2016-11-21 下午9:30:33
 */
public class LanguageContext {

    /** 当前语言，如：zh_CN-代表 简体中文 */
    private String language;

    private final static ThreadLocal<LanguageContext> holder = new ThreadLocal<LanguageContext>() {
        protected LanguageContext initialValue() {
            return new LanguageContext();
        }
    };

    private LanguageContext() {
    }

    /** 生成并保存当前语言，实际存在 ThreadLocal中 */
    public static void set(String language) {
        LanguageContext context = new LanguageContext();
        context.setLanguage(language);
        holder.set(context);
    }

    /**
     * 取出UserContext
     */
    public static LanguageContext get() {
        return holder.get();
    }

    /**
     * 删除上下文、其实一般不用删除
     */
    public static void remove() {
        holder.remove();
    }

    /**
     * 从 LanguageContext 取得当前语言
     * 
     * @return
     */
    public String getLanguage() {
        Assert.notNull(language, "language must not be null");
        return language;
    }

    /**
     * @param language
     *            the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

}
