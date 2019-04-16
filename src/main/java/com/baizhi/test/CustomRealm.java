package com.baizhi.test;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.DomainPermission;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.Collection;

public class CustomRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AuthorizationInfo authorizationInfo=new AuthorizationInfo() {
            @Override
            public Collection<String> getRoles() {
                ArrayList<String>arrayList=new ArrayList<>();
                arrayList.add("super");
                return arrayList;
            }

            @Override
            public Collection<String> getStringPermissions() {
                ArrayList<String>arrayList=new ArrayList<>();
                arrayList.add("user:update");
                return arrayList;
            }

            @Override
            public Collection<Permission> getObjectPermissions() {
                ArrayList<Permission>arrayList=new ArrayList<>();
                Permission permission=new DomainPermission("user:select");
                Permission permission1=new DomainPermission("user:delete");
                arrayList.add(permission);
                arrayList.add(permission1);
                return arrayList;
            }
        };
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Md5Hash md5Hash = new Md5Hash("123","abc",1024);
        String s = md5Hash.toHex();
        AuthenticationInfo authenticationInfo=new SimpleAccount("123",s, ByteSource.Util.bytes("abc"),"customRealm");
        return authenticationInfo;
    }

}
