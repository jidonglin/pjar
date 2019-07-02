package com.person.pjar.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * @Author: jidonglin
 * @Date: 2019/6/14 15:57
 */
public class JarUtils {

    public static File createTempJar(String root, String jarName, String jarVersion) throws IOException {
        if (!new File(root).exists()){
            return null;
        }
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().putValue("Manifest-Version", "1.0");
//        manifest.getMainAttributes().putValue("Main-Class", "com.person.pjar.business.HelloWorld");//指定main class
        //创建临时文件，指定jar包名称和jar包的输出路径，名称会有一个随机值
//        final File jarFile = File.createTempFile(jarName + "-" + jarVersion, ".jar", new File("D:\\tempJar"));
        //创建文件，指定输出路径和名称
        final File jarFile = new File("D:\\tempJar", jarName + "-" + jarVersion + ".jar");

        JarOutputStream outputStream = new JarOutputStream(new FileOutputStream(jarFile), manifest);
        createTempJarInner(outputStream, new File(root), "");
        outputStream.close();
        return  jarFile;
    }

    private static void createTempJarInner(JarOutputStream out, File file, String base) throws  IOException{
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (base.length() > 0){
                base = base + "/";
            }
            for (int i = 0 ; i < files.length; i++){
                if ("MANIFEST.MF".equals(files[i].getName())){
                    //如果存在MANIFEST.MF，则不生成文件
                }else {
                    createTempJarInner(out, files[i], base + files[i].getName());
                }

            }
        }else{
            out.putNextEntry(new JarEntry(base));
            FileInputStream in = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int n = in.read(buffer);
            while (n != -1){
                out.write(buffer, 0 , n);
                n = in.read(buffer);
            }
            in.close();
        }
    }
}
