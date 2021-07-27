package com.bjpowernode.utils;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class MethodUtils {
    public static String[] getParamNames(Method method) throws Exception {
        boolean isStatic = Modifier.isStatic(method.getModifiers()); // 是否是静态方法
        String name = method.getName(); // 方法名
        String descriptor = Type.getMethodDescriptor(method); // 方法签名
        Class declaringClass = method.getDeclaringClass(); // 方法所在的类
        int parameterCount = method.getParameterCount();// 方法参数的个数
        if (parameterCount == 0) {
            return new String[]{};
        }
        String[] result = new String[parameterCount]; // 声明最终结果

        // 解析字节码
        String className = declaringClass.getName().replace(".", "/") + ".class";
        InputStream in = declaringClass.getClassLoader().getResourceAsStream(className);
        ClassReader cr = new ClassReader(in);
        ClassNode cn = new ClassNode();
        cr.accept(cn, ClassReader.EXPAND_FRAMES);

        // 确定方法
        List<MethodNode> methods = cn.methods;
        MethodNode methodNode = null;
        for (MethodNode node : methods) {
            // 验证方法签名
            if (node.desc.equals(descriptor) && node.name.equals(name)) {
                methodNode = node;
                break;
            }
        }

        // 解析方法变量：方法参数、方法内部所有变量
        List<LocalVariableNode> localVariableNodes = methodNode.localVariables;
        for (LocalVariableNode node : localVariableNodes) {
            // index 记录了正确的方法本地变量索引(方法本地变量顺序可能会被打乱。而index记录了原始的顺序)。
            // 非静态方法的第1个参数是 this
            int index = isStatic ? node.index : node.index - 1;
            if (index >= 0 && index < parameterCount) {
                result[index] = node.name;
            }
        }

        return result;
    }


}
