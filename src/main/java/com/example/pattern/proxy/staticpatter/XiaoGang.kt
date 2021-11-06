package com.example.pattern.proxy.staticpatter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
class XiaoGang : LitigationProcessInterface {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun submitLawsuit() {
        logger.info("----- XiaoGang::::::submitLawsuit  小刚提交诉讼申请");
    }

    override fun proof() {
        logger.info("----- XiaoGang::::::proof  小刚举证");
    }

    override fun defend() {
        logger.info("----- XiaoGang::::::defend  小刚辩护");
    }
}