package com.kancolle.server.utils.picturebook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PictureBookUtils {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        File jsonFile = ResourceUtils.getFile("classpath:json/picture_book.json");
        JSONObject jObject = JSON.parseObject(Files.toString(jsonFile, Charset.defaultCharset()));
        JSONArray jArray = jObject.getJSONArray("ship_list");
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/kancolle";
        String username = "root";
        String password = "";
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement pstmt = con.prepareStatement("insert into t_ship_picturebook  values(?,?,?,?)");) {
            for (int i = 0; i < jArray.size(); i++) {
                JSONObject ship = jArray.getJSONObject(i);
                int ship_id = ship.getJSONArray("api_table_id").getIntValue(0);
                int ship_ctype = ship.getIntValue("api_ctype");
                int ship_cnum = ship.getIntValue("api_cnum");
                String ship_info = ship.getString("api_sinfo");
                pstmt.setInt(1, ship_id);
                pstmt.setInt(2, ship_ctype);
                pstmt.setInt(3, ship_cnum);
                pstmt.setString(4, ship_info);
                pstmt.execute();
            }
        }
    }

}
