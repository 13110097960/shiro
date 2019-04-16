package com.baizhi.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.DomainPermission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;

public class Shiro {
    public static void main(String[] args) {
        
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager instance = iniSecurityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("123","123");
        try {
            subject.login(usernamePasswordToken);
            System.out.println("认证成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("认证失败");
        }
        System.out.println(subject.hasRole("super"));
        System.out.println(subject.isPermitted("user:update"));
        ArrayList<Permission> arrayList=new ArrayList<>();
        Permission permission=new DomainPermission("user:select");
        Permission permission1=new DomainPermission("user:delete");
        arrayList.add(permission);
        arrayList.add(permission1);
        System.out.println(subject.isPermittedAll(arrayList));
    }
}
