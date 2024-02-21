package com.lionel.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 静态文件生成
 */
public class StaticGenerator {

    public static void main(String[] args) {
        // 获取整个项目的根路径
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径：ACM示例模板代码
        String inputPath = new File(parentFile, "lion-generator-demo-projects/acm-template").getAbsolutePath();
        // 输出路径：根目录
        copyFilesByHutool(inputPath, projectPath);
    }

    /**
     * 拷贝文件（Hutool实现，完整复制文件或目录）
     * @param inputPath 源文件路径
     * @param outputPath 目标文件或目录路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath, outputPath, false);
    }

    /**
     * 递归拷贝文件
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath){
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
        } catch (IOException e) {
            System.out.println("文件复制失败");
            e.printStackTrace();
        }
    }

    /**
     * 文件 A => 目录 B，则文件 A 放在目录 B 下
     * 文件 A => 文件 B，则文件 A 覆盖文件 B
     * 目录 A => 目录 B，则目录 A 放在目录 B 下
     * 核心思路：先创建目录，然后遍历目录内的文件，依次复制(递归)
     * @param inputFile
     * @param outputFile
     */
    private static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        if(inputFile.isDirectory()){
            File dest = new File(outputFile, inputFile.getName());
            if(!dest.exists()) {
                dest.mkdirs();
            }
            File[] files = inputFile.listFiles();
            if(ArrayUtil.isEmpty(files)){
                return;
            }
            for (File file : files) {
                // 递归拷贝下一层
                copyFileByRecursive(file, dest);
            }
        }else{
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
