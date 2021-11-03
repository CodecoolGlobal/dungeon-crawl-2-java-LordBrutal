package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.actors.enemys.Cyclops;


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
            attack(nextCell.getActor());
        }else if (nextCell.getType().equals(CellType.BREAKABLEWALL)) {
            if ((actor instanceof Player && cell.getMap().getPlayer().getHasPickAxe()) || actor instanceof Cyclops) {
                nextCell.setType(CellType.FLOOR);
            }
        }
    }
    public void attack(Actor enemy) {
        if (enemy.getHealth() > 0) {
            enemy.setHealth(enemy.getHealth() - this.attack);
            if (enemy.getHealth() > 0) {
                this.health -= enemy.attack;
            }
        }
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
