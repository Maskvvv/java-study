package com.zhy.web;

import com.alibaba.fastjson.JSON;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class GeoLite2Example {
    public static void main(String[] args) {
        // 数据库文件路径
        String dbPath = "/Users/mask/Downloads/GeoLite2-City.mmdb"; // 替换为实际路径
        File database = new File(dbPath);

        // 要查询的 IP 地址
        String ip = "39.64.139.91"; // 替换为实际 IP

        try {
            // 加载数据库
            DatabaseReader reader = new DatabaseReader.Builder(database).build();

            // 解析 IP 地址
            InetAddress ipAddress = InetAddress.getByName(ip);

            // 查询 IP 地址对应的地理位置
            CityResponse response = reader.city(ipAddress);

            // 获取经纬度
            double latitude = response.getLocation().getLatitude();
            double longitude = response.getLocation().getLongitude();

            // 输出结果
            System.out.println("IP 地址: " + ip);
            System.out.println("经纬度: (" + latitude + ", " + longitude + ")");
            System.out.println("城市: " + response.getCity().getName());
            System.out.println("国家: " + response.getCountry().getName());

            System.out.println(JSON.toJSONString(response));
        } catch (IOException | GeoIp2Exception e) {
            e.printStackTrace();
        }
    }
}