package com.niocoder.core.type;

/**
 * 封装获取类信息的接口
 *
 * @author zhenglongfei
 */
public interface ClassMetadata {

    /**
     * 获取类名称
     *
     * @return
     */
    String getClassName();

    /**
     * 判断是否是接口
     *
     * @return
     */
    boolean isInterface();

    /**
     * 判断是否抽象
     *
     * @return
     */
    boolean isAbstract();

    /**
     * 判断是否类是否final
     *
     * @return
     */
    boolean isFinal();

    /**
     * 是否父类
     *
     * @return
     */
    boolean hasSuperClass();

    /**
     * 获取父类名称
     *
     * @return
     */
    String getSuperClassName();

    /**
     * 获取所有的接口名称
     *
     * @return
     */
    String[] getInterfaceNames();
}
