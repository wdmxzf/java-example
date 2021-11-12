package com.example.principle.lod;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介
 */
public class Mediator {
    private List<Room> allRooms = new ArrayList<Room>();
    public Mediator(){
        for (int i=0;i<10;i++){
            Room room =new Room();
            room.setAre((i+1)*5);
            room.setPay((i%4==0?1:i%4)* 1000);
            allRooms.add(room);
        }
    }

    public List<Room> getAllRooms() {
        return allRooms;
    }

    public Room rentRoom(int are, int pay){
        // 通过中介获取所有房屋
        for (Room room: allRooms){
            if (isSuitable(room, are, pay)){
                return room;
            }
        }
        return null;
    }

    // 判断是否有合适的
    private boolean isSuitable(Room room, int are, int pay){
        // 筛选房屋
        return true;
    }
}
