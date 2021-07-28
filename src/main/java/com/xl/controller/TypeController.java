package com.xl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xl.domain.Type;
import com.xl.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-16 19:00
 */
@Controller
public class TypeController {

    //设置每页显示的条数
    private static final int pageSize = 4;

    @Autowired
    private TypeService typeService;

    /**
     * 分页查询
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "typelist.action")
    public String selectTypeList(Model model, @RequestParam(name = "pageNum",required = false,defaultValue = "1")int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Type> typeList = this.typeService.selectAllTypelist();
        PageInfo<Type> pageInfo = new PageInfo<Type>(typeList);
        model.addAttribute("pageModel",pageInfo);
        return "notice/typelist";
    }

    /**
     * 跳转到公告添加页面
     * @return
     */
    @RequestMapping(value = "typeadd.action")
    public String toTypeAdd() {
        return "notice/type_save_update";
    }

    /**
     * 修改数据回显
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "viewType.action")
    public String viewType(String id,Model model) {
        Type type = this.typeService.getTypeById(id);
        model.addAttribute("type",type);
        return "notice/type_save_update";
    }

    /**
     * 修改或者添加操作
     * @param type
     * @return
     */
    @RequestMapping(value = "typesaveOrUpdate.action")
    public String updateType(Type type){
        if (type.getId()!=null) { //修改操作
            this.typeService.updateType(type);
        } else { //添加操作
            this.typeService.insertType(type);
        }
        return "redirect:typelist.action";
    }

    /**
     * 删除操作
     * @param ids
     * @return
     */
    @RequestMapping(value = "typedel.action")
    public String delTypes(String ids[]) {
        this.typeService.deleteTypes(ids);
        return "redirect:typelist.action";
    }

    /**
     * 模糊查询
     * @param type
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "typelistlike.action")
    public String selectTypeListLike(Type type,Model model, @RequestParam(name = "pageNum",required = false,defaultValue = "1")int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<Type> typeList = this.typeService.selectTypeLike(type);
        PageInfo<Type> pageInfo = new PageInfo<Type>(typeList);
        model.addAttribute("pageModel",pageInfo);
        return "notice/typelist";
    }

}
