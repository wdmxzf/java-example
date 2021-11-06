package com.example.pattern.proxy.staticpatter

object Client {
    @JvmStatic fun main(args: Array<String>) {
        val xiaoGang:LitigationProcessInterface = XiaoGang();
        val lawyer = LawyerProxy(xiaoGang as XiaoGang)
        lawyer.submitLawsuit()
        lawyer.proof()
        lawyer.defend()
        lawyer.finish()
    }
}