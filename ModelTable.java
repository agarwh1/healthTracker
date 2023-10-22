package com.example.iacode;

public class ModelTable {
    String Dishname;
    float Calories, Protein, Carbs, Fat;

    public ModelTable(String Dishname, float Calories, float Protein, float Carbs, float Fat) {
        this.Dishname = Dishname;
        this.Calories = Calories;
        this.Protein = Protein;
        this.Carbs = Carbs;
        this.Fat = Fat;
    }

    public String getDishname() {
        return Dishname;
    }

    public float getCalories() {
        return Calories;
    }

    public float getProtein() {
        return Protein;
    }

    public float getCarbs() {
        return Carbs;
    }

    public float getFat() {
        return Fat;
    }
}
