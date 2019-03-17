package com.rong.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;

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
}
