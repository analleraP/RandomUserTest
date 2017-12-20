package com.mydrafts.android.randomuser.data.entity;

import com.mydrafts.android.randomuser.data.entity.apimodel.ApiUser;
import com.mydrafts.android.randomuser.data.entity.apimodel.Location;
import com.mydrafts.android.randomuser.data.entity.apimodel.Name;
import com.mydrafts.android.randomuser.data.entity.apimodel.Picture;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

public class UserMapper implements Mapper<User, ApiUser> {

    @Inject
    public UserMapper() {
    }

    @Override
    public ApiUser modelToApi(User model) {
        throw new UnsupportedOperationException("method not implemented");
    }

    @Override
    public User apiToModel(ApiUser apiUser) {

        String id = md5(apiUser.getEmail().concat(apiUser.getName().getFirst().concat(apiUser.getName().getLast())));

        User user = new User(id);

        user.setGender(apiUser.getGender());
        user.setEmail(apiUser.getEmail());
        user.setPhone(apiUser.getPhone());
        user.setRegistered(apiUser.getRegistered());

        Name apiUserName = apiUser.getName();
        if (apiUserName != null) {
            user.setFirstName(apiUserName.getFirst());
            user.setLastName(apiUserName.getLast());
        }

        Location apiUserLocation = apiUser.getLocation();
        if (apiUserLocation != null) {
            user.setState(apiUserLocation.getState());
            user.setCity(apiUserLocation.getCity());
            user.setStreet(apiUserLocation.getStreet());
        }

        Picture apiUserPictures = apiUser.getPicture();
        if (apiUserPictures != null) {
            user.setLargePicture(apiUserPictures.getLarge());
            user.setMediumPicture(apiUserPictures.getMedium());
            user.setThumbnail(apiUserPictures.getThumbnail());
        }
        return user;
    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
