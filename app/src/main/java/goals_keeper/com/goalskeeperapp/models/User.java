package goals_keeper.com.goalskeeperapp.models;

import java.util.ArrayList;
import java.util.List;

import goals_keeper.com.goalskeeperapp.R;

/**
 * Created by abdelrahman on 01/12/15.
 */
public class User {

    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private Boolean gender;
    private String dateOfBirth;
    private String profilePicture;
    private String facebookToken;
    private String facebookId;


    /**
     * @return The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The gender
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     * @param gender The gender
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     * @return The dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth The date_of_birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return The profilePicture
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * @param profilePicture The profile_picture
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * @return The facebookToken
     */
    public String getFacebookToken() {
        return facebookToken;
    }

    /**
     * @param facebookToken The facebook_token
     */
    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    /**
     * @return The facebookId
     */
    public String getFacebookId() {
        return facebookId;
    }

    /**
     * @param facebookId The facebook_id
     */
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }


}