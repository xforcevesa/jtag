package org.jtag

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label

object HelloWorldDump {
    // Dump a HelloWorld Java bytecode
    @Throws(Exception::class)
    fun dump(): ByteArray {
        val classWriter = ClassWriter(0)
        var methodVisitor: MethodVisitor

        classWriter.visit(
            Opcodes.V11,
            Opcodes.ACC_PUBLIC or Opcodes.ACC_SUPER,
            "HelloWorld",
            null,
            "java/lang/Object",
            null
        )

        classWriter.visitSource("HelloWorld.java", null)

        run {
            methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null)
            methodVisitor.visitCode()
            val label0 = Label()
            methodVisitor.visitLabel(label0)
            methodVisitor.visitLineNumber(1, label0)
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
            methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
            methodVisitor.visitInsn(Opcodes.RETURN)
            val label1 = Label()
            methodVisitor.visitLabel(label1)
            methodVisitor.visitLocalVariable("this", "LHelloWorld;", null, label0, label1, 0)
            methodVisitor.visitMaxs(1, 1)
            methodVisitor.visitEnd()
        }
        run {
            methodVisitor = classWriter.visitMethod(
                Opcodes.ACC_PUBLIC or Opcodes.ACC_STATIC,
                "main",
                "([Ljava/lang/String;)V",
                null,
                null
            )
            methodVisitor.visitCode()
            val label0 = Label()
            methodVisitor.visitLabel(label0)
            methodVisitor.visitLineNumber(3, label0)
            methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
            methodVisitor.visitLdcInsn("Hello World")
            methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V",
                false
            )
            val label1 = Label()
            methodVisitor.visitLabel(label1)
            methodVisitor.visitLineNumber(4, label1)
            methodVisitor.visitInsn(Opcodes.RETURN)
            val label2 = Label()
            methodVisitor.visitLabel(label2)
            methodVisitor.visitLocalVariable("args", "[Ljava/lang/String;", null, label0, label2, 0)
            methodVisitor.visitMaxs(2, 1)
            methodVisitor.visitEnd()
        }
        classWriter.visitEnd()

        return classWriter.toByteArray()
    }
}


