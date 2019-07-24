package com.atguigu.atcrowdfunding.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/23 0023 下午 11:31
 */
public class IdsUtil {

    public static List<Integer> ParseIds(String ids){
        List<Integer> idsList = new ArrayList<>();
        //即将接收到的ids进行拆分 放入list中
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            try {
                idsList.add(Integer.parseInt(id));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return idsList;
    }
}
