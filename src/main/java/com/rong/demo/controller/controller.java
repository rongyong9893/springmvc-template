package com.rong.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ Author ：rongyong
 * @ Date ：Created in 22:58 2019/3/15
 */
@Controller
public class controller
{
    @RequestMapping("get")
    public void get()
    {
        System.out.println("aaaaaaaaaaaaaaaa");
    }


    @RequestMapping("exportLog")
    public void exportLog(HttpServletResponse response){
//        //获取日志
//        List<DtmSystemLog> list = logService.getLogs();
//        //拼接字符串
//        StringBuffer text = new StringBuffer();
//        for(DtmSystemLog log:list){
//            text.append(log.getOpeuser());
//            text.append("|");
//            text.append(log.getOpedesc());
//            text.append("|");
//            text.append(dateString);
//            text.append("\r\n");//换行字符
//        }
        exportTxt(response,"adasdasdasda");

    }


    public void exportTxt(HttpServletResponse response,String text){
        response.setCharacterEncoding("utf-8");
        //设置响应的内容类型
        response.setContentType("json/plain");
        //设置文件的名称和格式
        response.addHeader("Content-Disposition","attachment;filename="
                + genAttachmentFileName( "文件名称", "JSON_FOR_UCC_")//设置名称格式，没有这个中文名称无法显示
                + ".json");
        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(text.getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            //LOGGER.error("导出文件文件出错:{}",e);
        } finally {try {
            buff.close();
            outStr.close();
        } catch (Exception e) {
            //LOGGER.error("关闭流对象出错 e:{}",e);
        }
        }
    }

    public  String genAttachmentFileName(String cnName, String defaultName) {
        try
        {
            cnName = new String(cnName.getBytes("gb2312"), "ISO8859-1");
        } catch (Exception e)
        {
            cnName = defaultName;
        }
        return cnName;
    }

    public static void main(String[] args)
    {
        String data = "#在抖音，记录美好生活#这大概就是冰雪美人吧…… czxhttp://v.douyin.com/eUWYth/ czxczx复制此链接，打开【抖音短视频】，直接观看视频！";

        String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
                + "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";

        final List<String> list = new ArrayList<String>();
        final Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
        final Matcher ma = pa.matcher(data);
        while (ma.find())
        {
            list.add(ma.group());
        }
        System.out.println(list.toString());
    }
}
