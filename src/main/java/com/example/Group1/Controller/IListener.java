package com.example.Group1.Controller;

import com.example.Group1.Bean.Event;

import java.util.List;

public interface IListener {

    void update(Event eventType, List<String> listener);

}

