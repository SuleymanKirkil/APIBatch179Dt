package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)  // If data we receive from database has extra attributes this notation will ignore them.
public class JsonPlaceHolderPojo {

    /*
            POJO ------> Plain Old Java Object
                    ---> Pojo class is perfect template for creating Objects

                1)  Create all private variables
                2)  Create Constructors with all parameters and without parameters
                3)  Create getters and setters for all variables
                4)  Create toString() method
     */

           //  1)  Create all private variables
    private Integer userId;
    private String title;
    private Boolean completed;

            //   2)  Create Constructors with all parameters and without parameters

    public JsonPlaceHolderPojo() {
    }

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

           //    3)  Create getters and setters for all variables

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

          //   4)  Create toString() method


    @Override
    public String toString() {
        return "{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
