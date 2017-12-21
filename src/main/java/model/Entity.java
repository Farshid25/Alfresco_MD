package model;

import com.google.cloud.language.v1.EntityMention;

public class Entity {
    private String name;
    private Float salience;
    private EntityMention.Type type;


    public Entity(String name, Float salience, EntityMention.Type type) {
        this.name = name;
        this.salience = salience;
        this.type = type;
    }

    public Entity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSalience( ) {
        return salience;
    }
    public String getNameBySalience(Float Salience){
        return name;
    }
    public Float getSalienceByName(String name){
        return salience;
    }

    public void setSalience(Float salience) {
        this.salience = salience;
    }

    public EntityMention.Type getType() {
        return type;
    }

    public void setType(EntityMention.Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", salience=" + salience +
                ", type='" + type + '\'' +
                '}';
    }
}
