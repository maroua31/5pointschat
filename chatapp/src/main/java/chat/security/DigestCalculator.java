package chat.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;


public class DigestCalculator
{
	private final MessageDigest messageDigest;

	public static boolean isAlgorithmSupported(String algorithm)
	{
		if (algorithm == null)
		{
			return false;
		}
		else
		{
			try
			{
				MessageDigest.getInstance(algorithm);
				return true;
			}
			catch (NoSuchAlgorithmException arg0)
			{
				return false;
			}
		}
	}

	public static DigestCalculator getInstance(String algorithm)
	{
		DigestCalculator result = null;

		try
		{
			result = new DigestCalculator(MessageDigest.getInstance(algorithm));
			return result;
		}
		catch (NoSuchAlgorithmException arg2)
		{
			throw new RuntimeException(arg2);
		}
	}

	private DigestCalculator(MessageDigest messageDigest)
	{
		this.messageDigest = messageDigest;
	}

	public synchronized String calculateDigest(String plain)
	{
		StringBuilder result = new StringBuilder(this.messageDigest.getDigestLength());
		this.messageDigest.update(plain.getBytes());
		Formatter formatter = new Formatter(result);
		byte[] arg6;
		int arg5 = (arg6 = this.messageDigest.digest()).length;

		for (int arg4 = 0; arg4 < arg5; ++arg4)
		{
			byte b = arg6[arg4];
			formatter.format("%02x", new Object[]
			{ Byte.valueOf(b) });
		}

		formatter.close();
		return result.toString();
	}
}
