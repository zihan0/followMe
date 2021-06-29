package com.follow.me.main.clip;

import com.follow.me.main.action.Action;

public class Clip {

    private String name;
    private Integer ticks;
    private Float chance1;
    private Float chance2;
    private Action action1;
    private Action action2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTicks() {
        return ticks;
    }

    public void setTicks(Integer ticks) {
        this.ticks = ticks;
    }

    public Float getChance1() {
        return chance1;
    }

    public void setChance1(Float chance1) {
        this.chance1 = chance1;
    }

    public Float getChance2() {
        return chance2;
    }

    public void setChance2(Float chance2) {
        this.chance2 = chance2;
    }

    public Action getAction1() {
        return action1;
    }

    public void setAction1(Action action1) {
        this.action1 = action1;
    }

    public Action getAction2() {
        return action2;
    }

    public void setAction2(Action action2) {
        this.action2 = action2;
    }
}