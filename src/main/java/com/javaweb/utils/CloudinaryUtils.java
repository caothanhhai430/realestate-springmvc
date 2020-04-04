package com.javaweb.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryUtils {
    private static Cloudinary cloudinary = null;

    public static Cloudinary getCloudinary(){
        if(cloudinary==null){
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "dc4rxxjyt",
                    "api_key", "182393896791142",
                    "api_secret", "g95hnkYtxrJWTGY0FfSDs0yms5w"));
        }
        return cloudinary;
    }
}
