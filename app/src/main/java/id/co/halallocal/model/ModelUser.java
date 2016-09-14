package id.co.halallocal.model;

/**
 * Created by Milzam on 9/10/2016.
 */
public class ModelUser {

    private String mName;
    private String mEmail;

    public ModelUser(String pName, String pEmail) {
        mName = pName;
        mEmail = pEmail;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String pEmail) {
        mEmail = pEmail;
    }

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }
}
