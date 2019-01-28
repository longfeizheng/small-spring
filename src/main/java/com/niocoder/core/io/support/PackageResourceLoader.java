package com.niocoder.core.io.support;

import com.niocoder.core.io.FileSystemResource;
import com.niocoder.core.io.Resource;
import com.niocoder.util.Assert;
import com.niocoder.util.ClassUtils;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 将一个目录下的类文件加载为资源文件
 *
 * @author zhenglongfei
 */
@Log
public class PackageResourceLoader {

    /**
     * 给定一个包的路径,将包下面的文件转换为Resource
     *
     * @param basePackage
     * @return
     * @throws IOException
     */
    public Resource[] getResource(String basePackage) throws IOException {

        Assert.notNull(basePackage, "basepackage must not be null");
        String location = ClassUtils.convertClassNameToResourcePath(basePackage);
        URL url = ClassUtils.getDefaultClassLoader().getResource(location);
        File rootDir = new File(url.getFile());

        Set<File> matchingFiles = retrieveMatchingFiles(rootDir);
        Resource[] resources = new Resource[matchingFiles.size()];
        int i = 0;
        for (File file : matchingFiles) {
            resources[i++] = new FileSystemResource(file);
        }
        return resources;
    }

    private Set<File> retrieveMatchingFiles(File rootDir) throws IOException {
        if (!rootDir.exists()) {
            log.info("Skipping [" + rootDir.getAbsolutePath() + "] because is not exist");
        }

        if (!rootDir.isDirectory()) {
            log.info("Skipping [" + rootDir.getAbsolutePath() + " ] because it a Directory");
        }

        if (!rootDir.canRead()) {
            log.info("Cannot search for matching files underneath directory [" + rootDir.getAbsolutePath() +
                    "] because the application is not allowed to read the directory");
            return Collections.emptySet();
        }

        Set<File> result = new LinkedHashSet<File>(8);
        doRetrieveMatchingFiles(rootDir, result);
        return result;
    }

    protected void doRetrieveMatchingFiles(File dir, Set<File> result) throws IOException {

        File[] dirContents = dir.listFiles();
        if (dirContents == null) {
            log.info("Could not retrieve contents of directory [" + dir.getAbsolutePath() + "]");
            return;
        }
        for (File content : dirContents) {

            if (content.isDirectory()) {
                if (!content.canRead()) {
                    log.info("Skipping subdirectory [" + dir.getAbsolutePath() +
                            "] because the application is not allowed to read the directory");
                } else {
                    doRetrieveMatchingFiles(content, result);
                }
            } else {
                result.add(content);
            }

        }
    }
}
