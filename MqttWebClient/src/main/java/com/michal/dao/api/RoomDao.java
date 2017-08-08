package com.michal.dao.api;

import com.michal.dao.model.homestructure.Room;

import java.util.List;

public interface RoomDao {

    Room addNewRoom(Room room);
    List<Room> getAllRoom();
    Room get(Long id);
    boolean remove(Long id);

}
