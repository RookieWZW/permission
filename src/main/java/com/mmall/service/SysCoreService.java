package com.mmall.service;

import com.google.common.collect.Lists;
import com.mmall.common.RequestHolder;
import com.mmall.dao.SysAclMapper;
import com.mmall.dao.SysRoleAclMapper;
import com.mmall.dao.SysRoleUserMapper;
import com.mmall.model.SysAcl;
import com.mmall.model.SysUser;
import com.mmall.util.JsonMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by RookieWangZhiWei on 2018/11/6.
 */
@Service
public class SysCoreService {

    @Resource
    private SysAclMapper sysAclMapper;

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    @Resource
    private SysRoleAclMapper sysRoleAclMapper;

    public List<SysAcl> getCurrentUserAclList() {

        int userId = RequestHolder.getCurrentUser().getId();

        return getUserAclList(userId);
    }


    public List<SysAcl> getRoleAclList(int roleId) {
        List<Integer> aclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(Lists.<Integer>newArrayList(roleId));
        if (CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(aclIdList);
    }

    public List<SysAcl> getUserAclList(int userId) {
        if (isSuperAdmin()) {
            return sysAclMapper.getAll();
        }

        List<Integer> userRoleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        List<Integer> userAclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(userRoleIdList);
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }

        return sysAclMapper.getByIdList(userAclIdList);


    }

    public boolean isSuperAdmin(){
        SysUser sysUser = RequestHolder.getCurrentUser();

        if (sysUser.getMail().contains("admin")){
            return true;
        }
        return false;
    }

    public boolean hasUrlAcl(String url){
        if (isSuperAdmin()){
            return true;
        }

        List<SysAcl> aclList = sysAclMapper.getByUrl(url);

        if (CollectionUtils.isEmpty(aclList)){
            return true;
        }

        List<SysAcl> userAclList = getCurrentUserAclList();
        Set<Integer> userAclIdSet = userAclList.stream().map(acl -> acl.getId()).collect(Collectors.toSet());

        boolean hasValidAcl = false;

        for (SysAcl acl: aclList) {
            if (acl == null || acl.getStatus() != 1){
                continue;
            }
            hasValidAcl = true;
            if (userAclIdSet.contains(acl.getId())){
                return true;
            }
        }

        if (!hasValidAcl){
            return true;
        }
        return false;
    }


    public List<SysAcl> getCurrentUserAclListFromCache(){
      /*  int userId = RequestHolder.getCurrentUser().getId();

        String cacheValue = sysCacheService.getFromCache(CacheKeyConstants.USER_ACLS, String.valueOf(userId));

        if (StringUtils.isBlank(cacheValue)){
            List<SysAcl> aclList = getCurrentUserAclList();
            if (CollectionUtils.isNotEmpty(aclList)){
                sysCacheService.saveCache(JsonMapper.obj2String(aclList),600, CacheKeyConstants.USER_ACLS, String.valueOf(userId));
            }

            return aclList;
        }
        return JsonMapper.string2Obj(cacheValue, new TypeReference<List<SysAcl>>() {
        });
        */
      return null;
    }


}
