package webservices.restful.user;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 
 */

/**
 * @author amit.gh
 * 
 */
public class UserUniqueReferenceNoGenrator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String num = null;
		for (int i = 0; i < 2; i++) {
			num = getUniquePassthroughId1();
			System.out.println(num);
		
		}
		System.out.println("=========================================================================");
		for (int i = 0; i < 2; i++) {
			num = getUniquePassthroughId2();
			System.out.println(num);
		
		}
		System.out.println("=========================================================================");
		for (int i = 0; i < 2; i++) {
			num = getUniquePassthroughId3();
			System.out.println(num);
		
		}
		System.out.println("=========================================================================");
		for (int i = 0; i < 2; i++) {
			num = getUniquePassthroughId4();
			System.out.println(num);
		
		}
		
	}

	public static String getUniquePassthroughId1() {
		Long lTime = null;
		long tmp = 0, prim = 35071702912291L;
		tmp = (long) (Math.random() * prim);

		for (int i = 2; i < 7; i++)
			tmp = (long) (tmp * i * Math.random());
		tmp = tmp < 0 ? tmp * -1 : tmp;
		lTime = new Long(tmp);
		char[] charTime = (lTime.toString()).toCharArray();
		int charsToPlace = (int) (Math.random() * 10) % charTime.length;
		charsToPlace = charsToPlace <= 5 ? 7 : charsToPlace;
		for (int i = 0; i < charsToPlace; i++) {
			int loc = (int) (Math.random() * 10) % charTime.length;
			char one = (char) ((Math.random()) * 26 + 'a');
			charTime[loc] = one;
		}

		 StringBuffer buffer = new StringBuffer();
		
		buffer.append(charTime);
		char one ='k';
		if(charTime.length<20){
			for (int i = charTime.length; i <19 ; i++) {
				if(i%2==0)
				{
					one = (char) ((Math.random()) * 26 + 'a');
					buffer.append(one);
				}	else
				{
					charsToPlace = (int)(Math.random() * 10 );
					buffer.append(charsToPlace);
				}
			}
		}
		return buffer.toString();

	}


	
	public static String getUniquePassthroughId2(){
		 Random rand= new Random((long) ((Math.random()) * 1759));
		 long tmp = rand.nextLong();
		tmp = tmp >>>3;
		
		Long lTime = new Long(tmp);
		
		char[] charTime = (lTime.toString()).toCharArray();
		int charsToPlace =  rand.nextInt()% charTime.length;
		charsToPlace = charsToPlace <= 5 ? 7 : charsToPlace;
		for (int i = 0; i < charsToPlace; i++) {
			int loc = (int) (Math.random() * 10);
			char one = (char) ((Math.random()) * 26 + 'a');
			charTime[loc] = one;
		}
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(charTime);

		return buffer.toString();
		
	}
	
	public static String getUniquePassthroughId3()
	  {
		 SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(19);
	    
	    
	  }
	
	public static String getUniquePassthroughId4() {
		
		// This is although unique, its least reliable since it only uses Hex
		String rnd = Long.toHexString(Double.doubleToLongBits(Math.random()));
		
		return rnd;
		
	}


}
