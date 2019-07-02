package com.person.pjar.transform;

import org.objectweb.asm.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 使用 instrument 的类修改功能，我们需要实现它的 ClassFileTransformer 接口定义一个类文件转换器
 * transform() 方法会在类文件被加载时调用，在 transform 方法里，我们可以对传入的二进制字节码进行改写或替换，
 * 生成新的字节码数组后返回，JVM 会使用 transform 方法返回的字节码数据进行类的加载
 * @Author: jidonglin
 * @Date: 2019/6/17 15:20
 */
public class TestTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("Transforming " + className);
        ClassReader reader = new ClassReader(classfileBuffer);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classVisitor = new TestClassVisitor(classWriter);
        reader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        return classWriter.toByteArray();
    }

    class TestClassVisitor implements ClassVisitor{

        private ClassWriter writer;

        public TestClassVisitor(ClassWriter writer) {
            this.writer = writer;
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

        }

        @Override
        public void visitSource(String source, String debug) {

        }

        @Override
        public void visitOuterClass(String owner, String name, String desc) {

        }

        @Override
        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            return null;
        }

        @Override
        public void visitAttribute(Attribute attr) {

        }

        @Override
        public void visitInnerClass(String name, String outerName, String innerName, int access) {

        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
            MethodVisitor mv = writer.visitMethod(access, name, desc, signature, exceptions);
            if (name.equals("printSomeThing")){
                mv.visitCode();
                Label label0 = new Label();
                mv.visitLabel(label0);
                mv.visitLineNumber(19, label0);
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn("bytecode replaced!");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
                Label label1 = new Label();
                mv.visitLabel(label1);
                mv.visitLineNumber(20, label1);
                mv.visitInsn(Opcodes.RETURN);
                mv.visitMaxs(2, 0);
                mv.visitEnd();
            }
            return mv;
        }
        @Override
        public FieldVisitor visitField(final int access, final String name,
                                       final String desc, final String signature, final Object value) {

            return writer.visitField(access, name, desc, signature, value);

        }


        @Override
        public void visitEnd() {

        }

    }

}
