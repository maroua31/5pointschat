package chat.security;

import org.springframework.security.crypto.password.PasswordEncoder;


public class MD5PasswordEncoder implements PasswordEncoder
{
	private final DigestCalculator digestCalculator = DigestCalculator.getInstance("MD5");

	protected String calculateMD5(String plain)
	{
		return this.digestCalculator.calculateDigest(plain);
	}

	@Override
	public String encode(CharSequence rawPassword)
	{
		return this.calculateMD5((String) rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword)
	{
		if (rawPassword == null)
		{
			rawPassword = "";
		}
		if (encodedPassword == null)
		{
			encodedPassword = "";
		}
		return encodedPassword.equalsIgnoreCase(this.encode(rawPassword));
	}

}
