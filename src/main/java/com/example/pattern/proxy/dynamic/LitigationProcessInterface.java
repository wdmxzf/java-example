package com.example.pattern.proxy.dynamic;
/**
 * 诉讼流程
 */
public interface LitigationProcessInterface {
    // 提交诉讼申请
    void submitLawsuit();
    // 举证
    void proof();
    // 辩护
    void defend();
    // 结束
    void finish();
}
