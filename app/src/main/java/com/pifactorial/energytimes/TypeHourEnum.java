package com.pifactorial.energytimes;

import android.content.Context;
import com.pifactorial.energytimes.R;
import android.content.res.Resources;

class TypeHourEnum {
    // I hate this, it has to be the same as in strings.xml. Have to export a string array programatically in the future
    private static String BI = "BI";
    private static String TRI = "TRI";

    private String value;

    public TypeHourEnum(String v) {
        if(v.equals(BI) || v.equals(TRI))
            this.value = v;
        // It is an error but java exception handling is horrible
        else
            this.value = BI;
    }

    public String getHumanString(Resources res) {
        if(this.value.equals(BI)) {
            return res.getStringArray(R.array.type_of_hours_descriptive)[0];
        }

        else
            return res.getStringArray(R.array.type_of_hours_descriptive)[1];
    }

    public boolean isBiHour() {
        return this.value.equals(BI);
    }
}
