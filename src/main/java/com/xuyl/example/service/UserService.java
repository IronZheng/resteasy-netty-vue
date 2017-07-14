package com.xuyl.example.service;

import com.xuyl.example.jdbc.DBHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/7/14.
 */
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    static String sql = null;
    static DBHelper db1 = null;
    static ResultSet ret = null;

    public Map getList(String id){
        sql = "select *from user where id="+id;//SQL语句
        db1 = new DBHelper(sql);//创建DBHelper对象
        List<User> list = new ArrayList<>();
        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            while (ret.next()) {
                User user = new User();
                user.setId(Long.valueOf(ret.getString(1)));
                user.setName(ret.getString(2));
                user.setAge(Long.valueOf(ret.getString(3)));
                list.add(user);
            }//显示数据
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("data",list);
        return map;
    }

    class User{
        private Long id;
        private String name;
        private Long age;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getAge() {
            return age;
        }

        public void setAge(Long age) {
            this.age = age;
        }
    }
}
