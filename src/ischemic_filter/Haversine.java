package ischemic_filter;

public class Haversine {
	//Haversine Formula
	public static double distance1(double lat1, double lat2, double lon1,
	        double lon2) {

	    final int R = 6371; // Radius of the earth in km

	    double latDistance = Math.toRadians(lat2 - lat1); 
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double dist = R * c ; 
       //sinsqr of latdiff +cos of lat1 *cosof lat2 *sinsqr of long dist/2 
	    System.out.println("Aerial Distance: "+ dist);
	    return dist;

	}
}
