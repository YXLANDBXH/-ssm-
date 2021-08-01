package com.xl.service;

import com.xl.domain.Document;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-17 15:56
 */
public interface DocumentService {
    //查询公告信息
    List<Document> selectDocumentList();
    //根据id查询公告信息
    Document selectDocumentById(String id);
    //修改公告信息
    void updateDocument(Document document);
    //插入公告信息
    void insertDocument(Document document);
    //用户模糊查询
    List<Document> selectDocumentLike(Document document);
    //删除
    void deleteDocuments(String[] documentIds);
    //根据id查询filename
//    Document queryDocuFilenameById(String id);
}
