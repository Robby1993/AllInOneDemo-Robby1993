package com.app.municy.utilities;

public enum MIMEType {
    IMAGE("image/*"), VIDEO("video/*"), ALL("*/*");
    public String value;

    MIMEType(String value) {
        this.value = value;
    }


}
