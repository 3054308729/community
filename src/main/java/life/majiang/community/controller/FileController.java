package life.majiang.community.controller;

import life.majiang.community.dto.FileDTO;
import life.majiang.community.provider.UCloudProvider;
import life.majiang.community.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.expression.Maps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 * Created by hp on 2019/9/1 12:05
 */
@Controller
public class FileController {


    @Autowired
    private UCloudProvider uCloudProvider;

    @RequestMapping(value = "/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request){

        //使用MultipartHttpServletRequest获取request中的数据
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        try {
            String url = uCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(url);
            return fileDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FileDTO();
    }


    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/file/upload.do",method = RequestMethod.POST)
    @ResponseBody
    public Object upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){
        //填充业务
        //首先拿到servlet的上下文
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = uCloudProvider.upload(file,path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
        Map fileMap = new HashMap();
        fileMap.put("name",targetFileName);
        fileMap.put("url",url);
        return fileMap;
    }

}
