package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Credential {
    public String email;
    public String password;
    public String domain;

    public Credential(String email, String password, String domain) {
        this.email = email;
        this.password = password;
        this.domain = domain;
    }


    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Credential myCredentials = new Credential("ss3917@abv.bg", "0876811300", "DHP2019");
        System.out.println("My credentials as json: \n" + gson.toJson(myCredentials));
    }
}
