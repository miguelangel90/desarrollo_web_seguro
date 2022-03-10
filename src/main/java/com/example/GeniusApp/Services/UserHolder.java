package com.example.GeniusApp.Services;

import com.example.GeniusApp.Models.Song;
import com.example.GeniusApp.Models.Users.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserHolder {
    private Map<Long, User> users=new ConcurrentHashMap<>();
    private AtomicLong id=new AtomicLong();

    public void addUser(User user){
        long identification=id.incrementAndGet();
        //user.setId(identification);
        users.put(identification,user);
    }
    public Collection<User> getAll(){
        return users.values();
    }

    public User getUser(long id){
        return users.get(id);
    }

    public void removeUser(long id){
        users.remove(id);
    }

    public boolean checkUser(User user){
        if (users.containsValue(user)) {
            return true;
        }else{
            return false;
        }
    }

    public boolean checkPassword(User user, String pass){
        String userPass = user.getPassword();
        if (userPass.equals(pass)){
            return true;
        }else{
            return false;
        }
    }

    /*public void updateSong(long id, User updateU){
        updateSong.setId(id);
        songs.put(updateSong.getId(),updateSong);
    }*/
}