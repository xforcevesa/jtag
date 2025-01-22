package org.jtag
import org.objectweb.asm.*

object HelloWorldDump {
    @Throws(Exception::class)
    fun dump(): ByteArray {
        val classWriter = ClassWriter(ClassWriter.COMPUTE_FRAMES or ClassWriter.COMPUTE_MAXS)

        // public class HelloWorld
        classWriter.visit(
            Opcodes.V11,
            Opcodes.ACC_PUBLIC or Opcodes.ACC_SUPER,
            "HelloWorld",
            null,
            "java/lang/Object",
            null
        )

        // Source file
        classWriter.visitSource("HelloWorld.java", null)

        classWriter.visitInnerClass(
            "java/lang/invoke/MethodHandles\$Lookup",
            "java/lang/invoke/MethodHandles",
            "Lookup",
            Opcodes.ACC_PUBLIC or Opcodes.ACC_FINAL or Opcodes.ACC_STATIC
        )

        run {
            // public HelloWorld()
            val methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null)
            methodVisitor.visitCode()
            val label0 = Label()
            methodVisitor.visitLabel(label0)
            methodVisitor.visitLineNumber(1, label0)
            // return Object()
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
            methodVisitor.visitInsn(Opcodes.RETURN)
            val label1 = Label()
            methodVisitor.visitLabel(label1)
            // this = HelloWorld()
            methodVisitor.visitLocalVariable("this", "LHelloWorld;", null, label0, label1, 0)
            // MAX STACK = 1
            // MAX LOCALS = 1
            methodVisitor.visitMaxs(0, 0)
            methodVisitor.visitEnd()
        }
        run {
            // private static fibonacci(int a)
            val methodVisitor =
                classWriter.visitMethod(Opcodes.ACC_PRIVATE or Opcodes.ACC_STATIC, "fibonacci", "(I)I", null, null)
            methodVisitor.visitCode()
            val label0 = Label()
            methodVisitor.visitLabel(label0)
            methodVisitor.visitLineNumber(3, label0)
            // if (a <= 1)
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0)
            methodVisitor.visitInsn(Opcodes.ICONST_1)
            val label1 = Label()
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGT, label1)
            val label2 = Label()
            methodVisitor.visitLabel(label2)
            methodVisitor.visitLineNumber(4, label2)
            // return a;
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0)
            methodVisitor.visitInsn(Opcodes.IRETURN)
            methodVisitor.visitLabel(label1)
            methodVisitor.visitLineNumber(6, label1)
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
            // else
            // return fibonacci(a - 1) + fibonacci(a - 2);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0)
            methodVisitor.visitInsn(Opcodes.ICONST_1)
            methodVisitor.visitInsn(Opcodes.ISUB)
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "HelloWorld", "fibonacci", "(I)I", false)
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0)
            methodVisitor.visitInsn(Opcodes.ICONST_2)
            methodVisitor.visitInsn(Opcodes.ISUB)
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "HelloWorld", "fibonacci", "(I)I", false)
            methodVisitor.visitInsn(Opcodes.IADD)
            methodVisitor.visitInsn(Opcodes.IRETURN)
            val label3 = Label()
            methodVisitor.visitLabel(label3)
            methodVisitor.visitLocalVariable("n", "I", null, label0, label3, 0)
            methodVisitor.visitMaxs(0, 0)
            methodVisitor.visitEnd()
        }
        run {
            // public static void main(String[] args)
            val methodVisitor = classWriter.visitMethod(
                Opcodes.ACC_PUBLIC or Opcodes.ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                null
            )
            methodVisitor.visitCode()
            val label0 = Label()
            methodVisitor.visitLabel(label0)
            methodVisitor.visitLineNumber(10, label0)
            // System.out.println(fibonacci(12))
            methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
            methodVisitor.visitIntInsn(Opcodes.BIPUSH, 12)
            methodVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "HelloWorld", "fibonacci", "(I)I", false)
            methodVisitor.visitInvokeDynamicInsn(
                "makeConcatWithConstants",
                "(I)Ljava/lang/String;",
                Handle(
                    Opcodes.H_INVOKESTATIC,
                    "java/lang/invoke/StringConcatFactory",
                    "makeConcatWithConstants",
                    "(Ljava/lang/invoke/MethodHandles\$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;",
                    false
                ),
                *arrayOf<Any>("Hello: \u0001")
            )
            methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V",
                false
            )
            val label1 = Label()
            methodVisitor.visitLabel(label1)
            methodVisitor.visitLineNumber(11, label1)
            methodVisitor.visitInsn(Opcodes.RETURN)
            val label2 = Label()
            methodVisitor.visitLabel(label2)
            methodVisitor.visitLocalVariable("args", "[Ljava/lang/String;", null, label0, label2, 0)
            methodVisitor.visitMaxs(0, 0)
            methodVisitor.visitEnd()
        }
        classWriter.visitEnd()

        return classWriter.toByteArray()
    }
}
