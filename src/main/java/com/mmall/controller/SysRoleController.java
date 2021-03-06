package com.mmall.controller;

import com.mmall.common.JsonData;
import com.mmall.param.RoleParam;
import com.mmall.service.SysRoleAclService;
import com.mmall.service.SysRoleService;
import com.mmall.service.SysTreeService;
import com.mmall.service.SysUserService;
import com.mmall.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysTreeService sysTreeService;
    @Resource
    private SysRoleAclService sysRoleAclService;
//    @Resource
//    private SysRoleUserService sysRoleUserService;
    @Resource
    private SysUserService sysUserService;

    @RequestMapping("role.page")
    public ModelAndView page() {
        return new ModelAndView("role");
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveRole(RoleParam param) {
        sysRoleService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateRole(RoleParam param) {
        sysRoleService.update(param);
        return JsonData.success();
    }

    /**
     * 获取所有角色
     * @return
     */
    @RequestMapping("/list.json")
    @ResponseBody
    public JsonData list() {
        return JsonData.success(sysRoleService.getAll());
    }

    /**
     * 获取角色树
     * @param roleId
     * @return
     */
   @RequestMapping("/roleTree.json")
    @ResponseBody
    public JsonData roleTree(@RequestParam("roleId") int roleId) {
        return JsonData.success(sysTreeService.roleTree(roleId));
    }

    /**
     * 改变角色的权限
     * @param roleId 角色id
     * @param aclIds 权限ids
     * @return
     */
    @RequestMapping("/changeAcls.json")
    @ResponseBody
    public JsonData changeAcls(@RequestParam("roleId") int roleId, @RequestParam(value = "aclIds", required = false, defaultValue = "") String aclIds) {
        List<Integer> aclIdList = StringUtil.splitToListInt(aclIds);
        sysRoleAclService.changeRoleAcls(roleId, aclIdList);
        return JsonData.success();
    }
//
//    @RequestMapping("/changeUsers.json")
//    @ResponseBody
//    public JsonData changeUsers(@RequestParam("roleId") int roleId, @RequestParam(value = "userIds", required = false, defaultValue = "") String userIds) {
//        List<Integer> userIdList = StringUtil.splitToListInt(userIds);
//        sysRoleUserService.changeRoleUsers(roleId, userIdList);
//        return JsonData.success();
//    }
//
//    @RequestMapping("/users.json")
//    @ResponseBody
//    public JsonData users(@RequestParam("roleId") int roleId) {
//        List<SysUser> selectedUserList = sysRoleUserService.getListByRoleId(roleId);
//        List<SysUser> allUserList = sysUserService.getAll();
//        List<SysUser> unselectedUserList = Lists.newArrayList();
//
//        Set<Integer> selectedUserIdSet = selectedUserList.stream().map(sysUser -> sysUser.getId()).collect(Collectors.toSet());
//        for(SysUser sysUser : allUserList) {
//            if (sysUser.getStatus() == 1 && !selectedUserIdSet.contains(sysUser.getId())) {
//                unselectedUserList.add(sysUser);
//            }
//        }
//        // selectedUserList = selectedUserList.stream().filter(sysUser -> sysUser.getStatus() != 1).collect(Collectors.toList());
//        Map<String, List<SysUser>> map = Maps.newHashMap();
//        map.put("selected", selectedUserList);
//        map.put("unselected", unselectedUserList);
//        return JsonData.success(map);
//    }
}
