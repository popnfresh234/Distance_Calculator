package alexaner.dmtaiwan.com.distancecalculator;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 9/4/2015.
 */
public class Utilities {
    private static final Double mDmLat = 25.04731;
    private static final Double mDmLong = 121.57725;

    public static int falseCode = 0;
    public static int trueCode = 1;
    public static int nullCode = 2;

    private static LatLng getDmLatLng() {
        return new LatLng(mDmLat, mDmLong);
    }

    private static LatLng convertAdddressToLatLng(String streetAddress, Context context) {
        Geocoder geocoder = new Geocoder(context);
        List<Address> addressList;
        LatLng targetLatLng = null;
        try {
            addressList = geocoder.getFromLocationName(streetAddress, 1);
            if (addressList == null) {
                return null;
            }
            Address location = addressList.get(0);
            targetLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            return targetLatLng;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static float isNearEnough(String streetAddress, Context context) {
        LatLng dmLatLng = getDmLatLng();
        LatLng targetLatLng = convertAdddressToLatLng(streetAddress, context);
        if (targetLatLng != null) {
            Location dmLocation = new Location("");
            dmLocation.setLatitude(dmLatLng.latitude);
            dmLocation.setLatitude(dmLatLng.longitude);

            Location targetLocation = new Location("");
            targetLocation.setLatitude(targetLatLng.latitude);
            targetLocation.setLongitude(targetLatLng.longitude);

            float distance = targetLocation.distanceTo(targetLocation);
            return distance;

        } else {
            return -1;
        }
    }
    
}
