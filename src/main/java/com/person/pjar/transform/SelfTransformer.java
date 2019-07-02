package com.person.pjar.transform;

import com.sun.glass.ui.DelayedCallback;
import org.objectweb.asm.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

/**
 * @Author: jidonglin
 * @Date: 2019/6/19 14:32
 */
public class SelfTransformer implements ClassFileTransformer {


    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassReader reader = new ClassReader(classfileBuffer);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classVisitor = new DelClassAdapter(classWriter);
        reader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        return classWriter.toByteArray();
    }

    class DelClassAdapter extends ClassAdapter{
        public DelClassAdapter(ClassVisitor cv) {
            super(cv);
        }

//        @Override
//        public FieldVisitor visitField(int i, String s, String s1, String s2, Object o) {
//            if (s.equals("aa")){
//                FieldVisitor fieldVisitor =  super.visitField(ACC_PUBLIC, "bb", s1, s2, o);
//            }
//            return super.visitField(i, s, s1, s2, o);
//        }
        @Override
        public void visitEnd() {
            FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "timer", "J", null, null);
            if(fv!=null){
                fv.visitEnd();
            }
            cv.visitEnd();
        }
    }
}
