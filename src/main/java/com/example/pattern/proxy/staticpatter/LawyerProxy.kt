package com.example.pattern.proxy.staticpatter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
/**
 * 律师
 */
class LawyerProxy(private val xiaoGang: XiaoGang) : LitigationProcessInterface {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    override fun submitLawsuit() {
        logger.info("----- Lawyer::submitLawsuit1  律师代替小刚诉讼申请前")
        // 小刚提交的诉讼申请
        xiaoGang.submitLawsuit()
        logger.info("----- Lawyer::submitLawsuit2  律师代替小刚诉讼申请后")
    }
    override fun proof() {
        logger.info("----- Lawyer::proof1  律师在小刚举证内容前")
        // 小刚提交的举证
        xiaoGang.proof()
        logger.info("----- Lawyer::proof2  律师在小刚举证内容后")
    }
    override fun defend() {
        logger.info("----- Lawyer::defend1  律师在小刚辩护内容前")
        // 小刚的辩护
        xiaoGang.defend();
        logger.info("----- Lawyer::defend2  律师在小刚辩护内容后")
    }
}