package com.example.pattern.proxy.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 小刚需要提供的内容
 */
public class XiaoGang implements LitigationProcessInterface {
    private Logger logger = LoggerFactory.getLogger( this.getClass());
    public void submitLawsuit() {
        logger.info("----- XiaoGang::::::submitLawsuit  小刚提交诉讼申请");
    }

    public void proof() {
        logger.info("----- XiaoGang::::::proof  小刚举证");
    }

    public void defend() {
        logger.info("----- XiaoGang::::::defend  小刚辩护");
    }

    public void finish() {
        logger.info("----- XiaoGang::::::finish   结束");
    }
}
