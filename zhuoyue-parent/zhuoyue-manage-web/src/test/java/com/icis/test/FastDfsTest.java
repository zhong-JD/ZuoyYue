package com.icis.test;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;

public class FastDfsTest {
    public static void main(String[] args) throws IOException, MyException {
        //初始化配置  指定服务器
        String file =FastDfsTest.class.getResource("/tracker.conf").getFile();
        ClientGlobal.init(file);
        //客户端
        TrackerClient trackerClient=new TrackerClient();
        //根据客户端获得服务端
        TrackerServer trackerServer=trackerClient.getTrackerServer();
        //根据服务端获得存储节点
        StorageClient storageClient=new StorageClient(trackerServer,null);
        String orginalFilename="E:\\pic\\dog1.jpg";
        String[] upload_file = storageClient.upload_file(orginalFilename, "jpg", null);
        //返回字符串数组  组名(盘符)  文件夹
        for (int i = 0; i < upload_file.length; i++) {
            String s = upload_file[i];
            System.out.println("s = " + s);
        }
    }
}
