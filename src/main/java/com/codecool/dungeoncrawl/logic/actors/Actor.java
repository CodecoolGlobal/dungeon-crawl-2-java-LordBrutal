package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.*;
import com.codecool.dungeoncrawl.logic.actors.enemys.Cyclops;
import com.codecool.dungeoncrawl.logic.actors.enemys.Enemy;


public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    protected int attack;


    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor actor = cell.getActor();
        if (this.health <= 0) {
            return;
        }
        if(nextCell.getType().equals(CellType.FLOOR) && nextCell.getActor() == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        else if (nextCell.getActor() != null) {
            if (!(this.getTileName() != "player" && nextCell.getTileName() != "player")) {
                attack(nextCell.getActor());
            }
        }else if (nextCell.getType().equals(CellType.BREAKABLEWALL)) {
            if ((actor instanceof Player && ((Player)actor).getHasPickAxe()) || actor instanceof Cyclops) {
                nextCell.setType(CellType.FLOOR);
            }
        } else if (nextCell.getType().equals(CellType.DOOR)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
    public void attack(Actor target) {
        if (target instanceof Player) {
            if (target.getHealth() > 0) {
                if (this.attack - ((Player) target).getDefense()> 0) {
                    target.setHealth(target.getHealth() - (this.attack - ((Player) target).getDefense()));
                }
                if (target.getHealth() > 0) {
                    this.health -= target.attack;
                }
            }
        }else if (target instanceof Enemy) {
            if (target.getHealth() > 0) {
                target.setHealth(target.getHealth() - this.attack);
                if (target.getHealth() > 0) {
                    if ((target.attack - cell.getMap().getPlayer().getDefense()) > 0) {
                        this.health -= (target.attack - cell.getMap().getPlayer().getDefense());
                    }
                }
            }
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
