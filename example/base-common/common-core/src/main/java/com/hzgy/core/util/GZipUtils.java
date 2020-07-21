package com.hzgy.core.util;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipUtils {

    private static final int BUFFER = 1024;
    public static final String EXT = ".gz";

    public static File compressDir(String source, String target, boolean isDelete) {

        return compressDir(source, target, isDelete, null);
    }

    /**
     * 将文件目录压缩程tar.gz
     * @param source 需要压缩的文件或目录
     * @param baseDir 基于哪个目录来取需要压缩的文件或目录的相对路径
     * @param isDelete 是否删除原文件
     * @param baseDir 取源文件相对于哪个目录的路径
     * @return File 返回压缩后的文件
     */
    public static File compressDir(String source,String target,boolean isDelete, String baseDir) {
        File targetTar = new File(target + ".tar");
        File packFile =  pack(source,targetTar, baseDir);
        File targetGz = new File(packFile.getAbsolutePath() + ".gz");
        FileInputStream in = null;
        GZIPOutputStream out = null;
        try {
            in = new FileInputStream(packFile);
            out = new GZIPOutputStream(new FileOutputStream(targetGz));
            byte[] array = new byte[1024];
            int number = -1;
            while ((number = in.read(array, 0, array.length)) != -1) {
                out.write(array, 0, number);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //删除原始文件
            if (isDelete){
                if (packFile.exists()) {
                    packFile.delete();
                }
            }
        }
        return targetGz;
    }

    /**
     * 将文件目录压缩程tar.gz
     * @param sources 需要压缩的文件或目录
     * @param baseDir 基于哪个目录来取需要压缩的文件或目录的相对路径
     * @param isDelete 是否删除原文件
     * @param baseDir 取源文件相对于哪个目录的路径
     * @return File 返回压缩后的文件
     */
    public static File compressDir(List<String> sources,String target,boolean isDelete, String baseDir) {
        File targetTar = new File(target + ".tar");
        File packFile =  pack(sources,targetTar, baseDir);
        File targetGz = new File(packFile.getAbsolutePath() + ".gz");
        FileInputStream in = null;
        GZIPOutputStream out = null;
        try {
            in = new FileInputStream(packFile);
            out = new GZIPOutputStream(new FileOutputStream(targetGz));
            byte[] array = new byte[1024];
            int number = -1;
            while ((number = in.read(array, 0, array.length)) != -1) {
                out.write(array, 0, number);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //删除原始文件
            if (isDelete){
                if (packFile.exists()) {
                    packFile.delete();
                }
            }
        }
        return targetGz;
    }

    /***
     * 指定文件夹下的所有文件
     * @param path 文件路径
     * @return 文件列表
     */
    public static List<File> getFiles(String path) {
        File root = new File(path);
        List<File> files = new ArrayList<File>();
        if (!root.isDirectory()) {
            files.add(root);
        } else {
            File[] subFiles = root.listFiles();
            for (File f : subFiles) {
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }

    public static File pack(String path, File target) {
        return pack(path, target, null);
    }

    private static String getRelativePath(String path, String basePath) {
        java.nio.file.Path path1 = java.nio.file.Paths.get(path);
        java.nio.file.Path basePath1 = java.nio.file.Paths.get(basePath);

        return basePath1.relativize(path1).toString();
    }

    /**
     * 将一组文件打成tar包
     *
     * @param path 要打包的文件或目录
     * @param target  打包后的文件
     * @param baseDir 基于哪个目录来取需要压缩的文件或目录的相对路径
     * @return File 返回打包后的文件
     */
    public static File pack(String path, File target, String baseDir) {
        //获取所有待压缩文件
        List<File> sources = getFiles(path);
        FileOutputStream out = null;
        try {
            //文件目录不存在创建目录
            File parentFile = target.getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }
            out = new FileOutputStream(target);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        TarArchiveOutputStream os = new TarArchiveOutputStream(out);
        os.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
        for (File file : sources) {
            try {
                if (baseDir != null) {
                    os.putArchiveEntry(new TarArchiveEntry(file, getRelativePath(file.getPath(), baseDir)));
                }
                else {
                    os.putArchiveEntry(new TarArchiveEntry(file));
                }
                IOUtils.copy(new FileInputStream(file), os);
                os.closeArchiveEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 将一组文件打成tar包
     *
     * @param paths 要打包的文件或目录
     * @param target  打包后的文件
     * @param baseDir 基于哪个目录来取需要压缩的文件或目录的相对路径
     * @return File 返回打包后的文件
     */
    public static File pack(List<String> paths, File target, String baseDir) {
        //获取所有待压缩文件
        List<File> sources = new ArrayList<>();
        for (String path : paths) {
            sources.addAll(getFiles(path));
        }

        FileOutputStream out = null;
        try {
            //文件目录不存在创建目录
            File parentFile = target.getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }
            out = new FileOutputStream(target);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        TarArchiveOutputStream os = new TarArchiveOutputStream(out);
        os.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
        for (File file : sources) {
            try {
                if (baseDir != null) {
                    os.putArchiveEntry(new TarArchiveEntry(file, getRelativePath(file.getPath(), baseDir)));
                }
                else {
                    os.putArchiveEntry(new TarArchiveEntry(file));
                }
                IOUtils.copy(new FileInputStream(file), os);
                os.closeArchiveEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 数据压缩
     *
     * @param data 压缩数据
     * @return 返回压缩数据
     * @throws Exception 异常
     */
    public static byte[] compress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 压缩
        compress(bais, baos);
        byte[] output = baos.toByteArray();
        baos.flush();
        baos.close();
        bais.close();
        return output;
    }

    /**
     * 文件压缩
     *
     * @param file 压缩文件对象
     * @throws Exception 异常
     */
    public static void compress(File file) throws Exception {
        compress(file, true);
    }

    /**
     * 文件压缩
     *
     * @param file   压缩文件对象
     * @param delete 是否删除原始文件
     * @throws Exception 异常
     */
    private static void compress(File file, boolean delete) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file.getPath() + EXT);
        compress(fis, fos);
        fis.close();
        fos.flush();
        fos.close();
        if (delete) {
            file.delete();
        }
    }

    /**
     * 数据压缩
     *
     * @param is 压缩输入文件流
     * @param os 输出文件流
     * @throws Exception 异常
     */
    private static void compress(InputStream is, OutputStream os)
            throws Exception {
        GZIPOutputStream gos = new GZIPOutputStream(os);
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = is.read(data, 0, BUFFER)) != -1) {
            gos.write(data, 0, count);
        }
        gos.finish();
        gos.flush();
        gos.close();
    }

    /**
     * 文件压缩
     *
     * @param path 文件路径
     * @throws Exception 异常
     */
    public static void compress(String path) throws Exception {
        compress(path, true);
    }

    /**
     * 文件压缩
     *
     * @param path   压缩文件目录
     * @param delete 是否删除原始文件
     * @throws Exception 异常
     */
    public static void compress(String path, boolean delete) throws Exception {
        File file = new File(path);
        compress(file, delete);
    }

    /**
     * 文件压缩
     *
     * @param inputFileName  输入文件
     * @param outputFileName 输出文件
     * @throws Exception 异常
     */
    public static void compress(String inputFileName, String outputFileName)
            throws Exception {
        FileInputStream inputFile = new FileInputStream(inputFileName);
        FileOutputStream outputFile = new FileOutputStream(outputFileName);
        compress(inputFile, outputFile);
        inputFile.close();
        outputFile.flush();
        outputFile.close();
    }

    /**
     * 数据解压缩
     *
     * @param data 解压缩字节数据
     * @return 返回字节数组
     * @throws Exception 异常
     */
    public static byte[] decompress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 解压缩
        decompress(bais, baos);
        data = baos.toByteArray();
        baos.flush();
        baos.close();
        bais.close();
        return data;
    }

    /**
     * 文件解压缩
     *
     * @param file 解压缩文件
     * @throws Exception 异常
     */
    public static void decompress(File file) throws Exception {
        decompress(file, true);
    }

    /**
     * 文件解压缩
     *
     * @param file   解压缩文件
     * @param delete 是否删除原始文件
     * @throws Exception 异常
     */
    public static void decompress(File file, boolean delete) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file.getPath().replace(EXT, ""));
        decompress(fis, fos);
        fis.close();
        fos.flush();
        fos.close();
        if (delete) {
            file.delete();
        }
    }


    /**
     * 文件解压缩
     *
     * @param inputFileName  解压缩输入文件
     * @param outputFileName 解压输出文件
     * @throws Exception 异常
     */
    public static void decompress(String inputFileName, String outputFileName)
            throws Exception {
        FileInputStream inputFile = new FileInputStream(inputFileName);
        FileOutputStream outputFile = new FileOutputStream(outputFileName);
        decompress(inputFile, outputFile);
        inputFile.close();
        outputFile.flush();
        outputFile.close();
    }


    /**
     * 数据解压缩
     *
     * @param is 解压缩输入流
     * @param os 解压缩输出流
     * @throws Exception 异常
     */
    public static void decompress(InputStream is, OutputStream os)
            throws Exception {
        GZIPInputStream gis = new GZIPInputStream(is);
        int count;
        byte data[] = new byte[BUFFER];
        while ((count = gis.read(data, 0, BUFFER)) != -1) {
            os.write(data, 0, count);
        }
        gis.close();
    }

    /**
     * 文件解压缩
     *
     * @param path 解压路径
     * @throws Exception 异常
     */
    public static void decompress(String path) throws Exception {
        decompress(path, true);
    }

    /**
     * 文件解压缩
     *
     * @param path   解压路径
     * @param delete 是否删除原始文件
     * @throws Exception 异常
     */
    public static void decompress(String path, boolean delete) throws Exception {
        File file = new File(path);
        decompress(file, delete);
    }

    /**
     * 删除文件目录及其下面所有文件
     * @param delPath 删除的目录
     * @return 是否成功
     */
    private static boolean deleteDir(String delPath) {
        File delFile = new File(delPath);
        if (delFile.isDirectory()) {
            File[] files = delFile.listFiles();
            if (files == null || files.length == 0){
                return true;
            }
            //递归删除目录中的子目录下
            for (File file : files) {
                deleteDir(file.getAbsolutePath());
            }
        }
        return delFile.delete();
    }

    /**
     * 拷贝文件
     * @param source 拷贝源
     * @param dest 存储目录
     */
    public static boolean copyFile(String source,String dest){
        try {
            File sfile = new File(source);
            File dfile = new File(dest);
            if (sfile.isDirectory()) {
                File [] files = sfile.listFiles();
                if (files == null || files.length == 0){
                    return true;
                }
                for (File file:files) {
                    //递归调用函数
                    String fpath = file.getAbsolutePath();
                    copyFile(fpath,dest);
                }
            }
            else {
                //如果存放为目录，文件名称相同
                if (dfile.isDirectory()) {
                    if (!dfile.exists()) {
                        dfile.mkdirs();
                    }
                    dest += File.separator + sfile.getName();
                }
                FileUtils.copyFile(sfile, new File(dest));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

