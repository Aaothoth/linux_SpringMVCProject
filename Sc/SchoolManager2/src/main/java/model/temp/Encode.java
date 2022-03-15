package model.temp;

public class Encode 
{
	private static String[] encodes = {"GBK", "GB2321", "ISO-8859-1", "UTF-8"};
	
	public static boolean isEncoding(String str, String encode)
	{
		try 
		{
			if (str.equals(new String(str.getBytes(), encode)))
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getEncoding(String str)
	{
		for (int i = 0; i < encodes.length; i++)
		{
			if (isEncoding(str, encodes[i]))
			{
				return encodes[i];
			}
		}
		
		return null;
	}
}
