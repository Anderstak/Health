package com.example.health;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//имитация получения данных из сети, пока локальная реализация
public class Repository {
    private static Repository repository;
    private String userName= "login";
    private String password = "password";
    private String imageUrl;
    private List<ExampleItem> examplesInGym = new ArrayList<>();
    {
        ExampleItem exampleItem = new ExampleItem("Тяга блока", "В упражнении участвует практически весь " +
                "верх тела. При технически правильной работе значительную нагрузку берут на себя " +
                "широчайшие мышцы спины.В упражнении участвует практически весь верх тела. При " +
                "технически правильной работе значительную нагрузку берут на себя широчайшие мышцы спины." +
                " \nВ качестве вспомогательных мышц активируются:\nбольшая круглая мышца спины\n" +
                " бицепсы\nпредплечье\nтрапециевидная мышца\n");
        exampleItem.setImageOne(R.drawable.img_block_thrust_one);
        exampleItem.setImageTwo(R.drawable.img_block_thrust_two);
        exampleItem.setImageThree(R.drawable.img_block_thrust_three);
        examplesInGym.add(exampleItem);
        exampleItem = new ExampleItem("Подтягивание", "Задействованы сразу несколько групп мышц спины, груди, живота, плечевого пояса.\n");
        exampleItem.setImageOne(R.drawable.img_pull_up_one);
        exampleItem.setImageTwo(R.drawable.img_pull_up_two);
        exampleItem.setImageThree(R.drawable.img_pull_up_three);
        examplesInGym.add(exampleItem);
    }
    private List<ExampleItem> examplesOutdoor = new ArrayList<>();
    {
        ExampleItem exampleItem = new ExampleItem("Подтягивание", "Задействованы сразу несколько групп мышц спины, груди, живота, плечевого пояса.\n");
        exampleItem.setImageOne(R.drawable.img_pull_up_outdoor_one);
        exampleItem.setImageTwo(R.drawable.img_pull_up_outdoor_two);
        exampleItem.setImageThree(R.drawable.img_pull_up_three);
        examplesOutdoor.add(exampleItem);
    }
    @NotNull
    private List<String> menuCategory = new ArrayList<>(Arrays.asList("В спортзале", "Дома", "Здоровое питание"));

    private Repository(){}

    public static Repository getInstance(){
        if(repository == null){
            repository = new Repository();
        }
        return repository;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(List<String> menuCategory) {
        this.menuCategory = menuCategory;
    }

    public List<ExampleItem> getExamples() {
        return examplesInGym;
    }

    public void setExamples(List<ExampleItem> examples) {
        this.examplesInGym = examples;
    }

    public List<ExampleItem> getExamplesInGym() {
        return examplesInGym;
    }

    public void setExamplesInGym(List<ExampleItem> examplesInGym) {
        this.examplesInGym = examplesInGym;
    }

    public List<ExampleItem> getExamplesOutdoor() {
        return examplesOutdoor;
    }

    public void setExamplesOutdoor(List<ExampleItem> examplesOutdoor) {
        this.examplesOutdoor = examplesOutdoor;
    }
}
