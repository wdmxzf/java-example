package com.example.pattern.proxy.staticpatter

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 诉讼流程
 */
interface LitigationProcessInterface {
    // 提交诉讼申请
    fun submitLawsuit();
    // 举证
    fun proof();
    // 辩护
    fun defend();

    fun finish(){
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
        logger.info("----- LitigationProcessInterface::finish   结束");
    }
}