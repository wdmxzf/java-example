package com.example.principle.lod;

import java.util.List;

/**
 * 租客
 */
public class Tenant {

    // 不好的例子
//    public void rentRoom(Mediator mediator){
//        // 通过中介获取所有房屋
//        List<Room> allRoom = mediator.getAllRooms();
//        for (Room room: allRoom){
//            if (isSuitable(room)){
//                System.out.println("房屋找到了");
//            }
//        }
//    }
//
//    // 判断是否有合适的
//    private boolean isSuitable(Room room){
//        // 筛选房屋
//        return true;
//    }

    // 好例子
    public void rentRoom(Mediator mediator){
        System.out.println(mediator.rentRoom(20,2000));
    }
}
