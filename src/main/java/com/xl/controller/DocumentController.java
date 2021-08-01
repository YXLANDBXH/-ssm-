package com.xl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xl.domain.Document;
import com.xl.domain.User;
import com.xl.service.DocumentService;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author XLong
 * @create 2021-07-16 19:00
 */
@Controller
public class DocumentController {

    //设置每页显示的条数
    private static final int pageSize = 4;

    @Autowired
    private DocumentService documentService;

    /**
     * 文档分页查询
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "documentlist.action")
    public String selectDocumentList(Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Document> documentList = this.documentService.selectDocumentList();
        PageInfo<Document> pageInfo = new PageInfo<Document>(documentList);
        model.addAttribute("pageModel",pageInfo);
        return "document/documentlist";
    }

    /**
     * 跳转到文件上传页面
     * @return
     */
    @RequestMapping(value = "documentadd.action")
    public String toFileUnlode() {
        return "document/documentadd";
    }

    /**
     * 实现文件的上传
     * @param filename
     * @return
     */
    @RequestMapping(value = "documentaddsave.action")
    public String documentAddSave(MultipartFile filename,HttpSession session,
                                  @RequestParam(name = "title") String title,
                                  @RequestParam(name = "remark") String remark) throws Exception {
        Document document = new Document();
        String originalFilename = filename.getOriginalFilename(); //原始名称
        int index = originalFilename.indexOf(".");
        String newFileName = UUID.randomUUID().toString() + originalFilename.substring(index);
        File file = new File("G:\\桌面1\\a\\" + newFileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        filename.transferTo(file);
        String fileLast = "";
        document.setFilename(originalFilename);
        if (newFileName != "" && newFileName != null) {
            fileLast = newFileName.substring(newFileName.lastIndexOf("."));
        }
        document.setFiletype("未知");
        if (".jpg".equals(fileLast) || ".png".equals(fileLast)) {
            document.setFiletype("图片");
        }
        if (".mp4".equals(fileLast)) {
        document.setFiletype("视频");
    }
        if (".mp3".equals(fileLast)) {
        document.setFiletype("音频");
    }
        if (".txt".equals(fileLast)) {
        document.setFiletype("文本");
    }
    User user = (User) session.getAttribute("user_session");
        document.setUser(user);
        document.setTitle(title);
        document.setFilebytes(newFileName);
        document.setRemark(remark);
        document.setCreatDate(new Date());
        document.setUserId(user.getId());
        this.documentService.insertDocument(document);
        return "redirect:documentlist.action";
    }

    /**
     * 文档模糊查询
     * @param document
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "documentLikelist.action")
    public String selectDocumentLike(Document document,Model model,@RequestParam(name = "pageNum",required = false,defaultValue = "1") int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Document> documentList = this.documentService.selectDocumentLike(document);
        PageInfo<Document> pageInfo = new PageInfo<Document>(documentList);
        model.addAttribute("pageModel",pageInfo);
        return "document/documentlist";
    }

    /**
     * 修改数据回显
     * @return
     */
    @RequestMapping(value = "viewDocument.action")
    public String viewDocument(String id,Model model) {
        Document document = this.documentService.selectDocumentById(id);
        model.addAttribute("document",document);
        return "document/showUpdateDocument";
    }

    /**
     * 更新操作
     * @param filename
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "updateDocument.action")
    public String updateDocument(MultipartFile filename,
                                 HttpSession session,
                                 @RequestParam(name = "id") String id,
                                 @RequestParam(name = "title") String title,
                                 @RequestParam(name = "remark") String remark
                                 ) throws IOException {
        Document document = new Document();
        String originalFilename = filename.getOriginalFilename(); //原始名称
        int index = originalFilename.indexOf(".");
        String newFileName = UUID.randomUUID().toString() + originalFilename.substring(index);
        File file = new File("G:\\桌面1\\a\\" + newFileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        filename.transferTo(file);
        String fileLast = "";
        document.setFilename(originalFilename);
        if (newFileName != "" && newFileName != null) {
            fileLast = newFileName.substring(newFileName.lastIndexOf("."));
        }
        document.setFiletype("未知");
        if (".jpg".equals(fileLast) || ".png".equals(fileLast)) {
            document.setFiletype("图片");
        }
        if (".mp4".equals(fileLast)) {
            document.setFiletype("视频");
        }
        if (".mp3".equals(fileLast)) {
            document.setFiletype("音频");
        }
        if (".txt".equals(fileLast)) {
            document.setFiletype("文本");
        }
        User user = (User) session.getAttribute("user_session");
        document.setUser(user);
        document.setTitle(title);
        document.setFilebytes(newFileName);
        document.setCreatDate(new Date());
        document.setRemark(remark);
        document.setUserId(user.getId());
        document.setId(Integer.parseInt(id));
        this.documentService.updateDocument(document);
        return "redirect:documentlist.action";
    }

    /**
     * 删除操作
     * @param documentIds
     * @return
     */
    @RequestMapping(value = "removeDocument")
    public String deleteDocuments(String[] documentIds) {
        this.documentService.deleteDocuments(documentIds);
        return "redirect:documentlist.action";
    }

    /**
     * 文件下载
     * @param filename
     * @param id
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "documentdownload.action")
    public String documentPrevLoad(MultipartFile filename, String id, HttpServletResponse response) throws Exception {
        Document document = this.documentService.selectDocumentById(id);
        File file = new File("G:\\桌面1\\a\\"+document.getFilebytes());
        if (!file.exists()) {
            return "document/documentlist";
        }
        InputStream in = new FileInputStream("G:\\桌面1\\a\\"+document.getFilebytes());
        //设置响应的方式为下载方式,filename为下载名称
        response.setHeader("Content-Disposition", "attachment;filename="+document.getFilename());
        //输出对象建立
        ServletOutputStream out = response.getOutputStream();
        int len = 0;
        byte[] b = new byte[1024];
        while((len=in.read(b))!=-1){
            out.write(b, 0, len);
        }
        out.flush();
        out.close();
        return "redirect:documentlist.action";
    }

    @RequestMapping(value = "documentprevload.action")
    public String documentPrevLoad( String id, HttpServletResponse response) throws IOException {
        Document document = this.documentService.selectDocumentById(id);
        File file = new File("G:\\桌面1\\a\\"+document.getFilebytes());
        if (!file.exists()) {
            return "document/documentlist";
        }
        InputStream in = new FileInputStream("G:\\桌面1\\a\\"+document.getFilebytes());
        //设置响应的方式为下载方式,filename为下载名称
//        response.setHeader("Content-Disposition", "attachment;filename="+document.getFilename());
        //输出对象建立
        ServletOutputStream out = response.getOutputStream();
        int len = 0;
        byte[] b = new byte[1024];
        while((len=in.read(b))!=-1){
            out.write(b, 0, len);
        }
        out.flush();
        out.close();
        return "redirect:documentlist.action";
    }
}























