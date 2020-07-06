package com.ohmybug.tank.strategy;

import com.ohmybug.tank.Player;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    public void fire(Player player);
}
