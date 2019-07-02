package com.person.pjar.agent;

import com.person.pjar.main.TransformOneTarget;
import com.person.pjar.main.TransformTarget;
import com.person.pjar.transform.SelfTransformer;
import com.person.pjar.transform.TestTransformer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @Author: jidonglin
 * @Date: 2019/6/17 15:07
 */
public class TestAgent {
    public static void agentmain(String args, Instrumentation inst){
        //添加一个可重转换器
        inst.addTransformer(new TestTransformer(), true);
        try {
            //向jvm发起重转换请求，对于已经加载的类重新进行转换处理
            inst.retransformClasses(TransformTarget.class);
            System.out.println("Agent Load done");
        } catch (UnmodifiableClassException e) {
            System.out.println("Agent Load fail");
        }
    }
}
