package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.Objects;

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
        if (this.health <= 0) {
            return;
        }
        if(nextCell.getType().equals(CellType.FLOOR) && nextCell.getActor() == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
        else if (this.cell.getActor() instanceof Player && nextCell.getActor() != null) {
            attack(nextCell.getActor());
        } else if (nextCell.getType().equals(CellType.BREAKABLEWALL)) { //  && player has pickaxe
            nextCell.setType(CellType.FLOOR);
        }
    }
    public void attack(Actor skeleton) {
        if (skeleton.getHealth() > 0) {
            skeleton.setHealth(skeleton.getHealth() - this.attack);
            if (skeleton.getHealth() > 0) {
                this.health -= skeleton.attack;
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
