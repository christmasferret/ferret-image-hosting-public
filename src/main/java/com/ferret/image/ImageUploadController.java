package com.ferret.image;

import com.ferret.image.common.ServerResponse;
import com.ferret.image.util.PropertiesUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;





@Controller
@RequestMapping("/image")
public class ImageUploadController {

    @Autowired
    private IFileService iFileService;

    @RequestMapping("health.do")
    @ResponseBody
    public ServerResponse health(HttpSession session, HttpServletRequest request) {
        System.out.println("application running");
        return ServerResponse.createBySuccess("on");
    }

    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile[] files, HttpServletRequest request) {

        List<Map> res = new ArrayList<>();
        for (MultipartFile file : files) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);
            System.out.println("Uploaded file : " + url);
            res.add(fileMap);
        }
        return ServerResponse.createBySuccess(res);
    }

}