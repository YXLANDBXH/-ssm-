package com.xl.service;

import com.xl.domain.Document;
import com.xl.domain.DocumentExample;
import com.xl.mapper.DocumentAndUserMapper;
import com.xl.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-17 15:57
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private DocumentAndUserMapper documentAndUserMapper;

    @Override
    public List<Document> selectDocumentList() {
        return this.documentAndUserMapper.getDocumentList();
    }

    @Override
    public Document selectDocumentById(String id) {
        return this.documentMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public void updateDocument(Document document) {
        this.documentMapper.updateByPrimaryKeySelective(document);
    }

    @Override
    public void insertDocument(Document document) {
        this.documentMapper.insertSelective(document);
    }

    /**
     * 模糊查询
     * @param document
     * @return
     */
    @Override
    public List<Document> selectDocumentLike(Document document) {
        DocumentExample documentExample = new DocumentExample();
        DocumentExample.Criteria criteria = documentExample.createCriteria();
        if (document!=null) {
            if (document.getTitle()!=null && !document.getTitle().equals("")) {
                criteria.andTitleLike(document.getTitle());
            }
        }
        return this.documentMapper.selectByExample(documentExample);
    }

    @Override
    public void deleteDocuments(String[] documentIds) {
        for (int i=0;i<documentIds.length;i++) {
            this.documentMapper.deleteByPrimaryKey(Integer.parseInt(documentIds[i]));
        }
    }
}
